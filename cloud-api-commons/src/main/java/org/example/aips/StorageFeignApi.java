package org.example.aips;

import org.example.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service")
public interface StorageFeignApi {
    @PostMapping(value = "/storage/decrease")
    ResultData decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
