package org.example.aips;

import org.example.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-account-service")
public interface AccountFeignApi {
    @PostMapping("/account/decrease")
    ResultData decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money);
}
