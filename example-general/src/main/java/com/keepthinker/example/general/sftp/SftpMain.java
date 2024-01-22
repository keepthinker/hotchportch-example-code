package com.keepthinker.example.general.sftp;

import com.jcraft.jsch.SftpException;

import java.io.*;

public class SftpMain {
    private static final String host = "localhost";
    private static final int port = 2222;
    //上传文件测试
    public static void main(String[] args) throws SftpException, IOException {
        checkExist();
    }

    public static void upload() throws SftpException, FileNotFoundException {

        SftpUtil sftp = new SftpUtil("sftpuser", "sftpuser", host, port);
        sftp.login();
        File file = new File("D:\\dump-hopdata_market-202303311036.sql");
        InputStream is = new FileInputStream(file);

        sftp.upload("/sftpuser", "/test", "test_sftp.jpg", is);
        sftp.logout();
    }

    public static void ls() throws SftpException {
        SftpUtil sftp = new SftpUtil("sftpuser", "sftpuser", host, port);
        sftp.login();
        System.out.println(sftp.listFiles("/sftuser/test/test_sftp.jpg"));
        sftp.logout();
    }

    public static void checkExist() throws SftpException {
        SftpUtil sftp = new SftpUtil("sftp", "sftp", host, port);
        sftp.login();
        System.out.println(sftp.isExistFile("sftpuser/test/test_sftp.jpg"));
        sftp.logout();
    }
}
