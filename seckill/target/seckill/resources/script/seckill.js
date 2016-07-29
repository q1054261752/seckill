//存放主要交互逻辑js代码
// javascript 模块化  包
var seckill = {
    //封装秒杀相磁ajax的url
    URL :{
        now :function () {
            return '/seckill/time/now';
        }


    },
    //验证手机号
    validatePhone : function (phone) {
        if(phone && phone.length == 11 && !isNaN(phone)){

            return true;
        }else {
            return false;
        }
    },
    //详情页秒杀逻辑
    countdown : function (seckillId,nowTime,startTime,endTime) {
        console.log(seckillId);
        var seckillBox = $('#seckill-box');
        if(nowTime > endTime){
            //秒杀结束
            seckillBox.html("秒杀结束！")

        }else if(nowTime < startTime){
            //秒杀未开始，计时
            var killIime = new Date(startTime + 1000);

            seckillBox.countdown(killIime,function (event) {
                var format = event.strftime('秒杀计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            });
        }else{
            //秒杀开始
        }
    },
    detail : {
        //详情页初始化
        init : function(params) {
            //手机验证和登录，计时交互
            //规划我们的交互流程
            //在cookie中查找手机号
            var killPhone = $.cookie('killPhone');

            //验证手机号
            if(!seckill.validatePhone(killPhone)){
                //绑定phone
                //控制输出
                var killphoneModal = $('#killPhoneModel');
                //显示弹出层
                killphoneModal.modal({
                    //显示弹出层
                    show:true,
                    //禁止位置关闭
                    backdrop:'static',
                    keyboard:false  //关闭键盘事件


                });
                $('#killPhoneBtn').click(function(){
                   var inputPhone = $('#killPhoneKey').val();
                    if (seckill.validatePhone(inputPhone)){
                        //电话写入cookie
                        $.cookie('killPhone',inputPhone,{expires:1,path:'seckill'});
                        //刷新页面
                        window.location.reload();
                    }else{
                        $('#killPhoneMessage').hide().html('<laber class="label label-danger">手机号错误</laber>').show(300);

                    }

                });
            }
            //已经登录
            //计时交互
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            $.get(seckill.URL.now(),{},function (result) {
                if(result && result['success']){

                    var nowTime = result['data'];
                    //时间判断
                    seckill.countdown(seckillId,nowTime,startTime,endTime);

                }else{

                    console.log('result:'+result);
                }
            });



         }


   }
}
