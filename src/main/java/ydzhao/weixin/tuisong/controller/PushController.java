package ydzhao.weixin.tuisong.controller;

/**
 *@ClassName PushController
 *@Description TODO
 *@Author ydzhao
 *@Date 2022/8/2 15:48
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ydzhao.weixin.tuisong.util.Pusher;

import java.io.IOException;

@RestController
public class PushController {
    /**
     * 要推送的用户openid
     */
    private static String qc = "oKl0X6C_YhohnFIOEVFeE_tNFPXk";
    private static String pa = "oKl0X6PQNjFJmJGP2-tEJjkcavDM";

    /**
     * 微信测试账号推送
     *
     */
    @GetMapping("/push/qc")
    public void pushQc() throws IOException {
        Pusher.push(qc);
    }

    /**
     * 微信测试账号推送
     * */
    @GetMapping("/push/pa")
    public void pushZyd() throws IOException {
        Pusher.push(pa);
    }


    /**
     * 微信测试账号推送
     * */
    @GetMapping("/push/{id}")
    public void pushId(@PathVariable("id") String id) throws IOException {
        Pusher.push(id);
    }
}
