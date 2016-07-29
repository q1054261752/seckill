package org.seckill.dto;

/**
 * Created by Administrator on 2016/7/27.
 */
//所有ajax请求放回类型，封装json结果
public class SeckillResult<T> {

    private boolean success;

    private T data;

    private String error;

    public SeckillResult(boolean success,T data){
        this.data=data;
        this.success = success;
    }


    public SeckillResult(boolean success,String error){

        this.success= success;
        this.error = error;
    }


    public boolean isSuccess() {
        return success;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
