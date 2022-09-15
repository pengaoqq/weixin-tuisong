package ydzhao.weixin.tuisong.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName Tianqi
 * @Description TODO
 * @Author ydzhao
 * @Date 2022/8/2 16:45
 */
@Component
public class Tianqi {

    private static String ak;
    private static String district_id;

    @Value("${baidu.tianqi.ak}")
    public void setAk(String ak) {
        Tianqi.ak = ak;
    }

    @Value("${baidu.tianqi.districtId}")
    public void setDistrict_id(String district_id) {
        Tianqi.district_id = district_id;
    }

    public static JSONObject getNanjiTianqi() {
        String result = null;
        JSONObject today = new JSONObject();
        try {
            result = HttpUtil.getUrl("https://api.map.baidu.com/weather/v1/?district_id=" + district_id + "&data_type=all&ak=" + ak);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.getString("message").equals("success")) {
                JSONArray arr = jsonObject.getJSONObject("result").getJSONArray("forecasts");
                today = arr.getJSONObject(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(today);
        return today;
    }

    public static void main(String[] args) {
        System.out.println(getNanjiTianqi());
    }
}
