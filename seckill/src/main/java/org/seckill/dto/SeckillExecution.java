package org.seckill.dto;

import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStaEnum;

/**
 * 封装秒杀执行后结果
 * Created by Administrator on 2016/7/26.
 */
public class SeckillExecution {

    private long seckillId;

    private int state;

    private String stateInfo;

    //秒杀成功对象
    private SuccessKilled successKilled;

    //成功的输出
    public SeckillExecution(long seckillId, SeckillStaEnum seckillStaEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = seckillStaEnum.getState();
        this.stateInfo = seckillStaEnum.getStateInfo();
        this.successKilled = successKilled;
    }
    //失败的输出


    public SeckillExecution(long seckillId, SeckillStaEnum seckillStaEnum) {
        this.seckillId = seckillId;
        this.state = seckillStaEnum.getState();
        this.stateInfo = seckillStaEnum.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
