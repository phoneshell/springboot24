package org.example.exp;

import lombok.extern.slf4j.Slf4j;
import org.example.penum.ReturnCodeEnum;
import org.example.resp.ResultData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    //RuntimeException 运行时异常     ServletException 找不到界面
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> handlerException(Exception e) {
        log.error("系统异常：",e);
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
    }


}
