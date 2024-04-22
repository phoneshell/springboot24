package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.entities.Pay;
import org.example.entities.PayDTO;
import org.example.resp.ResultData;
import org.example.service.PayService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Tag(name = "支付微服务模块", description = "支付接口")
public class PayController {
    @Resource
    PayService payService;

    @Operation(summary = "新增",description = "新增支付,json参数")
    @PostMapping("/pay/add")
    public ResultData<String> addPay(@RequestBody Pay pay){
        int i= payService.add(pay);
        return ResultData.success("插入成功，返回值："+i);

    }

    @PutMapping("/pay/update")
    @Operation(summary = "更新",description = "更新支付,json参数")
    public ResultData<String> updatePay(@RequestBody PayDTO paydto){
        Pay pay = new Pay();
        BeanUtils.copyProperties(paydto, pay);
        int i= payService.update(pay);
        return ResultData.success("更新成功，返回值："+i);
    }

    @DeleteMapping("/pay/delete/{id}")
    @Operation(summary = "删除",description = "删除支付,主键id参数")
    public ResultData<String> deletePay(@PathVariable("id") Integer id){
        int i= payService.delete(id);
        return ResultData.success("删除成功，返回值："+i);
    }
    @GetMapping("/pay/getById/{id}")
    @Operation(summary = "查询",description = "根据主键id查询支付,主键id参数")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){
        Pay pay = payService.getById(id);
        if(id == -4) throw new RuntimeException("id不能为负数");
        if(id == -5) {
            try {
                TimeUnit.SECONDS.sleep(62);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return ResultData.success(pay);
    }
    @GetMapping("/pay/getAll")
    @Operation(summary = "查询",description = "查询所有支付")
    public ResultData<List<Pay>> getAll(){
        List<Pay> list = payService.getAll();
        return ResultData.success(list);
    }


    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/pay/get/info")
    private ResultData<String> getInfoByConsul(@Value("${atguigu.info}") String atguiguInfo)
    {
        return ResultData.success("atguiguInfo: "+atguiguInfo+"\t"+"port: "+port);
    }
}
