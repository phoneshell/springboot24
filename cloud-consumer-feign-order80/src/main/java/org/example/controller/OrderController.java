package org.example.controller;

import cn.hutool.core.date.DateUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.aips.PayFeignApi;
import org.example.entities.PayDTO;
import org.example.resp.ResultData;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;
    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        ResultData resultData = payFeignApi.addPay(payDTO);
        return resultData;
    }

    @GetMapping("/feign/pay/getById/{id}")
    public ResultData getById(@PathVariable("id") Integer id){
        System.out.println("调用开始"+DateUtil.now());
        ResultData resultData = null;
        try {
            resultData = payFeignApi.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用结束"+DateUtil.now());
            return ResultData.fail("500","调用失败");
        }
        return resultData;
    }

    @GetMapping("/feign/pay/getInfo")
    public ResultData getInfo() {
        ResultData resultData = payFeignApi.getInfo();
        return resultData;
    }
}
