package com.hhq.common_sdk.result;

import com.hhq.common_sdk.result.enums.ResultEnum;
import lombok.Data;

@Data
public class Result<T> {

    private String code;

    private String msg;

    private T data;


    private Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    private Result(T data) {
        this(ResultEnum.SUCCESS);
        this.data = data;
    }

    public static Result success(){
        return new Result(ResultEnum.SUCCESS);
    }

    public static <T> Result<T> success(T data){
        return new Result(data);
    }

    public static Result error(ResultEnum resultEnum){
        return new Result(resultEnum);
    }
}
