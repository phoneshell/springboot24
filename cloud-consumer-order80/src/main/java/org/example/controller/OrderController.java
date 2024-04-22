package org.example.controller;

import jakarta.annotation.Resource;
import org.example.entities.PayDTO;
import org.example.resp.ResultData;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class OrderController {
    //public static String PaymentSrv_URL = "http://localhost:8001";//先写死，硬编码
    public static String PaymentSrv_URL = "http://cloud-payment-service";//服务注册中心上的微服务名称
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO){
        return restTemplate.getForObject(PaymentSrv_URL + "/payment/add", ResultData.class);
    }

    @GetMapping("/consumer/pay/delete/{id}")
    public ResultData deleteById(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/delete/"+id, ResultData.class,id);
    }

    @GetMapping("/consumer/pay/getById/{id}")
    public ResultData getById(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/getById/"+id, ResultData.class,id);
    }

    @GetMapping("/consumer/pay/get/info")
    public ResultData getInfo(){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/info", ResultData.class);
    }

    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/consumer/discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }
}
