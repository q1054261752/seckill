package org.seckill.web;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStaEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;


    //获取列表页
    @RequestMapping(name = "/list",method = RequestMethod.GET)
    public String list(Model model){

        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list" , list);
        return "list";

    }

    //商品详情页
    @RequestMapping(value = "/{seckillId}/detail",method =  RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId,Model model){
       if(seckillId == null){
            return "redirect:/seckill/list";
       }
       Seckill seckill = seckillService.getById(seckillId);
        if(seckill == null){

            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckill);

        return "detail";
    }
    //ajax json
    @RequestMapping(value = "{seckillId}/exposer",
                              method = RequestMethod.POST,
                              produces = {"application/json;charset=utf-8"})
    @ResponseBody //json
    public SeckillResult<Exposer> exposer(Long seckillId){

        SeckillResult<Exposer> result=null;
        try {
        Exposer exposer= seckillService.exportSeckillUrl(seckillId);
        result = new SeckillResult<Exposer>(true,exposer);

        }catch (Exception e){
            logger.error(e.getMessage());
            result = new SeckillResult<Exposer>(false,e.getMessage());
        }

        return result;
    }

    //执行秒杀
    @RequestMapping(value = "/{seckillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5")String md5,
                                                   @CookieValue(value = "killPhone",required = false) Long phone){
        if (phone==null){

            return new SeckillResult<SeckillExecution>(false,"未注册");
        }
        SeckillResult<SeckillExecution> result;
        try {
            SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);
            return new SeckillResult<SeckillExecution>(true,execution);
        }catch (RepeatKillException e){

            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStaEnum.REPEATE_KILL);
            return new SeckillResult<SeckillExecution>(false,execution);
        }catch (SeckillCloseException e1){
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStaEnum.END);
            return new SeckillResult<SeckillExecution>(false,execution);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStaEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(false,execution);
        }

    }
    @RequestMapping(value = "/time/now",method = RequestMethod.GET )
  @ResponseBody
   public SeckillResult<Long> time(){
        Date now = new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }


}
