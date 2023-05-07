package com.keepthinker.example.general.ftp;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FtpMain {
    public static void main(String[] args) {
        new ByteArrayInputStream(new byte[]{});
    }

    public String getFTPProperties(String base64){
        try{
            //设置生成的目录
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
            //生成目录
            String dir = sdf.format(new Date());
            //获取文件的名称
            String hostName = "127.0.0.1";
            Integer port = 19999;
            String userName = "username";
            String password = "password";
            String pathname = "/"; //转账文件存储在根目录
            String fileName = "alibaba-demo.jpeg";
            InputStream inputStream = Files.newInputStream(new File("D:\\test\\alibaba-demo.jpeg").toPath());
            //调用FTP上传工具
            boolean flag = FtpUtils.uploadFile(hostName, userName, password, port, pathname, fileName, inputStream);
            //如果上传成功
            String imageUrl = "";
            if (flag) {
                imageUrl = "http://ftp-host" + "/" + dir + "/" + fileName;
            }
            return imageUrl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
