package org.example.controller;

import jakarta.annotation.Resource;
import org.example.resp.ResultData;
import org.example.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Resource
    AccountService accountService;

    /**
     * 扣减账户余额
     */
    @RequestMapping("/account/decrease")
    public ResultData decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money){
        accountService.decrease(userId,money);
        return ResultData.success("扣减账户余额成功！");
    }
}
