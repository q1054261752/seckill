package org.seckill.service.Impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/7/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:spring/spring-dao.xml",
     "classpath:spring/spring-service.xml"
})
public class SeckillServiceImplTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;
    @Test
    public void getSeckillList() throws Exception {

        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);

    }

    @Test
    public void getById() throws Exception {
        long id = 5;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}",seckill);

    }

    @Test
    public void exportSeckillUrl() throws Exception {

        long id =5;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}",exposer);
    }

    @Test
    public void executeSeckill() throws Exception {

        long id = 5;
        long phone=15426582565L;
        String md5 ="a0ecdca3e281da9cd8607d74781a8c03";
        SeckillExecution execution = null;

        try {
            execution = seckillService.executeSeckill(id,phone,md5);
        }catch (SeckillCloseException e2){

            logger.error(e2.getMessage());

        }catch (RepeatKillException e1){

            logger.error(e1.getMessage());

        } catch (SeckillException e) {
           logger.error(e.getMessage());
        }
        logger.info("execution{}",execution);


    }

    //测试代码完整逻辑，注意可重复执行
    @Test
    public void testSeckillLogic() throws Exception {

        long id =5;
        Exposer exposer = seckillService.exportSeckillUrl(id);

        if(exposer.isExposed()){

            logger.info("exposer={}",exposer);


            long phone=15426582565L;
            String md5 =exposer.getMd5();


            try {
                SeckillExecution execution = seckillService.executeSeckill(id,phone,md5);
            }catch (SeckillCloseException e2){

                logger.error(e2.getMessage());

            }catch (RepeatKillException e1){

                logger.error(e1.getMessage());

            } catch (SeckillException e) {
                logger.error(e.getMessage());
            }


        }else{
            //秒杀未开启
            logger.warn("export={}",exposer);

        }


    }


}