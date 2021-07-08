package com.hanzoy.container_identification.utils.pythonSocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

@Component
public class socket_nofile  {
    //rabbitMQ
    public static String HOST = "127.0.0.1";
    public static int PORT = 55533;
    public static Socket socket;
    public static Object Lock;
    public  String remoteCall(String content){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("file_name", content);
        String str = jsonObject.toJSONString();
        System.out.println(str);//todo delete
        // 访问服务进程的套接字

        try{
            if(socket == null){
                socket = new Socket(HOST, PORT);
            }
            // 初始化套接字，设置访问服务的主机和进程端口号，HOST是访问python进程的主机名称，可以是IP地址或者域名，PORT是python进程绑定的端口号
            // 获取输出流对象
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os);
            // 发送内容
            out.print(str);
            // 告诉服务进程，内容发送完毕，可以开始处理
            out.print("over");
            // 获取服务进程的输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String tmp = null;
            StringBuilder sb = new StringBuilder();
            // 读取内容
            tmp = br.readLine();
            // 解析结果
            HashMap<String, String> map = JSON.parseObject(tmp, HashMap.class);
            System.out.println(map);
//            JSONArray res = JSON.parseArray(tmp);
            //接受到的结果
            String res = map.get("sendmsg");
            res = res.split(" ", 2)[1];
            res = res.split("\n", 2)[0];
            System.out.println(res);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("end");
        }
        return null;
    }

//    public static void main(String[] args) {
//        socket_nofile socket_te = new socket_nofile();
//        // 想传什么在这儿传
//        socket_te.remoteCall("E:/picture/1.jpg");
//    }
}
