package com.keepthinker.example.general.minio;

public class MinIoMain {
    public static void main(String[] args) throws Exception {
        example();
    }

    public static void example() throws Exception {

        String bucketName = "test";
        String objectName = "test-upload/my-test-image.png";

        MinIOUtils.upload(bucketName, objectName, "C:\\Users\\shengkai.ke\\Pictures\\Screenshots\\屏幕截图 2023-02-21 175501.png");
        MinIOUtils.getObjectUrl(bucketName, objectName);
    }

}
