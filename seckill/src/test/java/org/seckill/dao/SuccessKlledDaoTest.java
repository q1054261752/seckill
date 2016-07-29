package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/7/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKlledDaoTest {

    @Resource
    private SuccessKlledDao successKlledDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        long id = 6L;
        long phone = 1258566555;
        int insertCount = successKlledDao.insertSuccessKilled(id,phone);
        System.out.println("insertCount=" + insertCount);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long id = 6L;
        long phone = 1258566555;
       SuccessKilled successkilled = successKlledDao.queryByIdWithSeckill(id,phone);
        System.out.println(successkilled);
        System.out.println(successkilled.getSeckill());
    }

}