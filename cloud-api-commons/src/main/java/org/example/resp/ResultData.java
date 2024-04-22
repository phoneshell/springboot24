package org.example.resp;

import lombok.Data;
import lombok.experimental.Accessors;
import org.example.penum.ReturnCodeEnum;

@Data
@Accessors(chain = true)
public class ResultData<T>{
    private String code;
    private String msg;
    private T data;
    private long timestemp;

    public ResultData() {
        this.timestemp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data){
        ResultData<T> result = new ResultData<>();
        result.setCode(ReturnCodeEnum.RC200.getCode());
        result.setMsg(ReturnCodeEnum.RC200.getMsg());
        result.setData(data);
        return result;
    }

    public static <T> ResultData<T> fail(String code,String msg){
        ResultData<T> result = new ResultData<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}
