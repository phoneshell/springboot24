package org.example.controller;

import jakarta.annotation.Resource;
import org.example.resp.ResultData;
import org.example.service.StorageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {
    @Resource
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @RequestMapping("/storage/decrease")
    public ResultData decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {

        storageService.decrease(productId, count);
        return ResultData.success("扣减库存成功!");
    }
}
