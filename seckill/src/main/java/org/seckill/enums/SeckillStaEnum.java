package org.seckill.enums;

/**
 * Created by Administrator on 2016/7/26.
 */
public enum SeckillStaEnum {

    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEATE_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据篡改");


    private int state;

    private String stateInfo;

    SeckillStaEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStaEnum stateOf(int index) {

        for (SeckillStaEnum state : values()) {

            if (state.getState() == index) {

                return state;
            }


        }
        return null;

    }

}
