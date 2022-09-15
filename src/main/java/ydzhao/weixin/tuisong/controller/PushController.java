package ydzhao.weixin.tuisong.controller;

/**
 *@ClassName PushController
 *@Description TODO
 *@Author ydzhao
 *@Date 2022/8/2 15:48
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ydzhao.weixin.tuisong.util.Pusher;

import java.io.IOException;

@RestController
public class PushController {
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

    /**
     * 微信测试账号推送
     *
     */
    @GetMapping("/push/lovers")
    public void pushLovers() throws IOException {
        Pusher.push(lovers, Boolean.TRUE);
    }

    /**
     * 微信测试账号推送
     * */
    @GetMapping("/push/mine")
    public void pushMine() throws IOException {
        Pusher.push(mine, Boolean.TRUE);
    }
}
