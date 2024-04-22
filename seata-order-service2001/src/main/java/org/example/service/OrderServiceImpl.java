package org.example.service;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.aips.AccountFeignApi;
import org.example.aips.StorageFeignApi;
import org.example.entities.Order;
import org.example.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private AccountFeignApi accountFeignApi;
    @Resource
    private StorageFeignApi storageFeignApi;

    @Override
    @GlobalTransactional(name = "myCreateGlobalTransactional",rollbackFor = Exception.class)
    public void create(Order order) {
        //xid全局事务id的检查，重要
        String id = RootContext.getXID();
        //新建订单
        log.info("----------------开始新建订单-----------xid=" + id + "------");
        //订单新建时默认初始状态为0
        order.setStatus(0);
        int result = orderMapper.insertSelective(order);
        //1、插入订单成功后获得mysql实体对象
        Order orderFromDB = null;
        if (result > 0) {
            orderFromDB = orderMapper.selectOne(order);
            log.info("-----------新建订单成功，orderFromDB:" + orderFromDB + "-----------");
            log.info("----------------结束新建订单-----------xid=" + id + "------");
            //2、扣减库存
            log.info("----------------订单微服务开始调用库存，做扣减count-----------xid=" + id + "------");
            storageFeignApi.decrease(orderFromDB.getProductId(), orderFromDB.getCount());
            log.info("----------------订单微服务结束调用库存，做扣减count完成-----------xid=" + id + "------");
            //3、扣减账户余额
            log.info("----------------订单微服务开始扣减账户余额-----------xid=" + id + "------");
            accountFeignApi.decrease(orderFromDB.getUserId(), orderFromDB.getMoney());
            log.info("----------------订单微服务结束扣减账户余额-----------xid=" + id + "------");
            //4、修改订单状态为已完成
            log.info("----------------订单微服务开始修改订单状态-----------xid=" + id + "------");
            orderFromDB.setStatus(1);
            Example whereCondition = new Example(Order.class);
            Example.Criteria criteria = whereCondition.createCriteria();
            //where userId=? and status=0
            criteria.andEqualTo("userId", orderFromDB.getUserId()).andEqualTo("status", 0);
            int i = orderMapper.updateByExample(orderFromDB, whereCondition);
            log.info("----------------订单微服务结束修改订单状态-----------xid=" + id + "------");
            log.info("---------------------orderFromDB:" + orderFromDB + "-----------");
        }
    }
}
