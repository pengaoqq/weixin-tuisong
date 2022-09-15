package ydzhao.weixin.tuisong.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @ClassName JiNianRi
 * @Description TODO
 * @Author ydzhao
 * @Date 2022/8/2 17:32
 */
@Component
public class JiNianRi {
    /**
     * 恋爱日
     */
    public static String lianAi;

    /**
     * 生日
     */
    public static String shengRi;

    @Value("${lianAi}")
    public void setLianAi(String lianAi) {
        JiNianRi.lianAi = lianAi;
    }

    @Value("${shengRi}")
    public void setShengRi(String shengRi) {
        JiNianRi.shengRi = shengRi;
    }

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static SimpleDateFormat MM_DD_SIMPLE_DATE_FORMAT = new SimpleDateFormat("MM-dd");

    /**
     * 距离date还有多少天
     * @param date
     * @return
     */
    public static int before(String date) {
        int day = 0;
        try {
            long time = MM_DD_SIMPLE_DATE_FORMAT.parse(date).getTime() - MM_DD_SIMPLE_DATE_FORMAT.parse(MM_DD_SIMPLE_DATE_FORMAT.format(System.currentTimeMillis())).getTime();
            day = (int) (time / 86400000L);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }


    /**
     * 已经过去date多少天
     * @param date
     * @return
     */
    public static int after(String date) {
        int day = 0;
        try {
            long time = System.currentTimeMillis() - simpleDateFormat.parse(date).getTime();
            day = (int) (time / 86400000L);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    public static int getLianAi() {
        return after(lianAi);
    }

    public static int getShengRi(){
        return before(shengRi);
    }

    public static void main(String[] args) {
        System.out.println(getLianAi());
        System.out.println(getShengRi());
    }

}
