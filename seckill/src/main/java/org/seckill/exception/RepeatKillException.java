package org.seckill.exception;

/**
 * 重复秒杀异常 （运行期异常）
 * spring声明式事物，只对运行时异常进行事物的回滚
 * Created by Administrator on 2016/7/26.
 */
public class RepeatKillException extends SeckillException{


    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
