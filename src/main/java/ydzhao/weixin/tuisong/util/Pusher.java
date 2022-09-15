package ydzhao.weixin.tuisong.util;

import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 *@ClassName Pusher
 *@Description TODO
 *@Author ydzhao
 *@Date 2022/8/2 16:03
 */
public class Pusher {

    /**
     * 测试号的appId和secret
     */
    private static String appId = "wx0b4f152ad4faa0dd";
    private static String secret = "254daaf65e59b7d87e10d1b656180cd9";

    /**
     * 模版id
     */
    private static String templateId = "-5tgqNsHHcS4NyENIstPK5L4sslHEGJ9-AmHls6CDiA";

    public static void push(String openId) throws IOException {
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appId);
        wxStorage.setSecret(secret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId)
                .templateId(templateId)
                //.url("https://30paotui.com/")//点击模版消息要访问的网址
                .build();
        //3,如果是正式版发送模版消息，这里需要配置你的信息
        //        templateMessage.addData(new WxMpTemplateData("name", "value", "#FF00FF"));
        //                templateMessage.addData(new WxMpTemplateData(name2, value2, color2));
        //填写变量信息，比如天气之类的
        JSONObject todayWeather = Tianqi.getNanjiTianqi();
        templateMessage.addData(new WxMpTemplateData("riqi",todayWeather.getString("date") + "  "+ todayWeather.getString("week"),"#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("tianqi",todayWeather.getString("text_day"),"#00FFFF"));
        templateMessage.addData(new WxMpTemplateData("low",todayWeather.getString("low") + "","#173177"));
        templateMessage.addData(new WxMpTemplateData("high",todayWeather.getString("high")+ "","#FF6347" ));
        templateMessage.addData(new WxMpTemplateData("caihongpi",CaiHongPi.getCaiHongPi(),"#FF69B4"));
        templateMessage.addData(new WxMpTemplateData("lianai",JiNianRi.getLianAi()+"","#FF1493"));
        templateMessage.addData(new WxMpTemplateData("shengri",JiNianRi.getShengRi()+"","#FFA500"));
        templateMessage.addData(new WxMpTemplateData("jinju",CaiHongPi.getJinJu()+"","#C71585"));
        //templateMessage.addData(new WxMpTemplateData("jiehun",JiNianRi.getJieHun()+""));
        templateMessage.addData(new WxMpTemplateData("linzhen",JiNianRi.getLinZhen()+"","#FF6347"));
        String beizhu = "";
        if(JiNianRi.getJieHun() % 365 == 0){
            beizhu = "今天是结婚纪念日！";
        }
        if(JiNianRi.getLianAi() % 365 == 0){
            beizhu = "今天是恋爱纪念日！";
        }
        if(JiNianRi.getLinZhen() % 365 == 0){
            beizhu = "今天是结婚纪念日！";
        }
        if(StringUtils.isBlank(beizhu)){
            //获取节假日
            beizhu = getHoliday(beizhu);
        }
        templateMessage.addData(new WxMpTemplateData("beizhu",beizhu,"#FF0000"));
        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 去ROLL管理系统获取节假日
     * @param beizhu :
     * @return java.lang.String
     * @author pengao
     * @date 2022/9/15
     */
    private static String getHoliday(String beizhu) throws IOException {
        String currDate = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
        String appId = "ld9eiyjxjlljnijm";
        String appSecret = "QXJmWEpJVk1MQzhJSTZLVFQ1Uisvdz09";
        String getHoliday = String.format("https://www.mxnzp.com/api/holiday/single/%s?ignoreHoliday=false&app_id=%s&app_secret=%s", currDate, appId, appSecret);
        String result = HttpUtil.getUrl(getHoliday);
        JSONObject jsonObject = JSONObject.parseObject(result);
        Integer code = jsonObject.getInteger("code");
        if(Objects.equals(code, 1)){
            JSONObject dataJson = JSONObject.parseObject(jsonObject.getString("data"));
            //类型 0 工作日 1 假日 2 节假日 如果ignoreHoliday参数为true，这个字段不返回
            Integer type = dataJson.getInteger("type");
            if(Objects.equals(type, 2)){
                //节假日
                //类型描述 比如 国庆,休息日,工作日 如果ignoreHoliday参数为true，这个字段不返回
                String typeDes = dataJson.getString("typeDes");
                beizhu = typeDes;
            }else{
                //默认节日
                beizhu = "今天也是爱你的日子哦";
            }
        }
        return beizhu;
    }
}
