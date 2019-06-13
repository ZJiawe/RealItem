package com.yihu.ehr.health.util;



import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetBase {

    public static  String Appid="3Y31QxxuWi";
    public static void Gert() throws IOException{
        // 我们需要进行请求的地址：
        String temp = "http://27.154.233.186:10000/api/v1.0/admin/apps/" + Appid;
        try {
            // 1.URL类封装了大量复杂的实现细节，这里将一个字符串构造成一个URL对象
            URL url = new URL(temp);
            // 2.获取HttpURRLConnection对象
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            // 3.调用connect方法连接远程资源
            connection.connect();
            // 4.访问资源数据，使用getInputStream方法获取一个输入流用以读取信息
            BufferedReader bReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "UTF-8"));

            // 对数据进行访问
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            // 关闭流
            bReader.close();
            // 关闭链接
            connection.disconnect();
            // 打印获取的结果
            System.out.println(stringBuilder.toString());

            // 将获得的String对象转为JSON格式
            JSONObject jsonObject = JSONObject.fromObject(stringBuilder.toString());
            // 通过利用JSON键值对的key，来查询value
            System.out.println("successFlg   : " + jsonObject.get("successFlg").toString());
            System.out.println("message : " + jsonObject.get("message").toString());
            System.out.println("code : " + jsonObject.get("code").toString());

            // 对于value也是JSON的对象，将对象的value转为一个新的JSON继续进行
            JSONObject aObject = JSONObject.fromObject(jsonObject.get("obj").toString());
            System.out.println("userid:  " + aObject.get("id").toString());
            System.out.println("userid:  " + aObject.get("id").toString());
            System.out.println("userid:  " + aObject.get("id").toString());
            System.out.println("userid:  " + aObject.get("id").toString());
            System.out.println("userid:  " + aObject.get("id").toString());
            System.out.println("userid:  " + aObject.get("id").toString());
            System.out.println("userid:  " + aObject.get("id").toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
