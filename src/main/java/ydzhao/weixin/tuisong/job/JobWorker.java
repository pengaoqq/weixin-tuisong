package ydzhao.weixin.tuisong.job;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ydzhao.weixin.tuisong.util.Pusher;

import java.io.IOException;
import java.util.Objects;

/**
 *@ClassName JobWorker
 *@Description TODO
 *@Author ydzhao
 *@Date 2022/8/2 16:00
 */
@Component
public class JobWorker {
    /**
     * 对象的微信openid
     */
    @Value("${wx.openId.lovers}")
    private String lovers;

    /**
     * 自己的微信openid
     */
    @Value("${wx.openId.mine}")
    private String mine;

    @Value("${scheduled.goodMorningTaskOpen}")
    private Integer goodMorningTaskOpen;

    @Value("${scheduled.goodEveningTaskOpen}")
    private Integer goodEveningTaskOpen;

    @Scheduled(cron = "${scheduled.goodMorningTask}")
    public void goodMorning() throws IOException {
        if (Objects.equals(0, goodMorningTaskOpen)) {
            return;
        }
        Pusher.push(lovers, Boolean.TRUE);
        Pusher.push(mine, Boolean.TRUE);
    }

    @Scheduled(cron = "${scheduled.goodEveningTask}")
    public void goodEvening() throws IOException {
        if (Objects.equals(0, goodEveningTaskOpen)) {
            return;
        }
        Pusher.push(lovers, Boolean.FALSE);
        Pusher.push(mine, Boolean.FALSE);
    }

}
