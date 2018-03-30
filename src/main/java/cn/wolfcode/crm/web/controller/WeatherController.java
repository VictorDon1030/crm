package cn.wolfcode.crm.web.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Controller
public class WeatherController {


    @RequestMapping("weather")
    public String view(){
        return "weather";
    }

    @RequestMapping("trying")
    @ResponseBody
    public Object trying(String city){
        try {

            String str = "https://way.jd.com/he/freeweather?city="+city+"&appkey=7f77cb0db842f501e3e4c080d74dace5";
            URL url = new URL(str);
            //得到connection对象。
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            connection.setRequestMethod("GET");
            //连接
            connection.connect();
            //得到响应码
            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                //得到响应流
                InputStream inputStream = connection.getInputStream();
                //获取响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                String line;
                while ((line = reader.readLine()) != null){
                    //注意:这里返回就是JSON对象,不需要再进行转换格式
                    return line;
                }
                reader.close();
                //关闭连接
                connection.disconnect();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
