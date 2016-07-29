package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/** spring-test ,junit
 * Created by Administrator on 2016/7/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})

public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;

    @Test
    public void queryByIdtest() throws Exception {

        long id = 5;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void queryAlltest() throws Exception {
        /**
         *  Caused by: org.apache.ibatis.binding.BindingException: Parameter 'offset' not found. Available parameters are [0, 1, param1, param2]
         *  at org.apache.ibatis.binding.MapperMethod$ParamMap.get(MapperMethod.java:168)
         *
         *   List<Seckill> queryAll(int offset ,int limit)
         *   java没有保存形参的记录：queryAll ( Int offset,int limit -> queryAll(arg0,arg1) )
         *
         *
         */



        List<Seckill> seckills = seckillDao.queryAll(0,100);
        for (Seckill seckill :seckills){
            System.out.println(seckill);
        }

    }
    @Test
    public void reduceNumber() throws Exception {

        Date killDate = new Date();
        int updateCount = seckillDao.reduceNumber(5,killDate);
        System.out.println(updateCount);
    }
}