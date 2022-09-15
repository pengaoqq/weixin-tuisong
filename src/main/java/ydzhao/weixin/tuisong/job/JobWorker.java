package ydzhao.weixin.tuisong.job;

import org.springframework.stereotype.Component;
import ydzhao.weixin.tuisong.util.Pusher;

import java.io.IOException;

/**
 *@ClassName JobWorker
 *@Description TODO
 *@Author ydzhao
 *@Date 2022/8/2 16:00
 */
@Component
public class JobWorker {
    /**
     * 要推送的用户openid
     */
    private static String openId = "oKl0X6PQNjFJmJGP2-tEJjkcavDM";

//    @Scheduled(cron = "0 0/1 * * * ?")
    public void goodMorning() throws IOException {
        Pusher.push(openId);
    }

}
