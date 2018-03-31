package cn.wolfcode.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Controller
public class MessageController {


    @RequestMapping("message")
    public String view(){
        return "message";
    }

    @RequestMapping("tryingMsg")
    @ResponseBody
    public Object trying(String telNum){
        try {
            String key = "7f77cb0db842f501e3e4c080d74dace5";
            String str = "https://way.jd.com/jsmsxx025/SendTemplateSms?mobile="+telNum+"&content=【江苏美圣】您好，您的短信验证码是8888，点击下方的按钮，测试一下吧。&appkey="+key;
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
