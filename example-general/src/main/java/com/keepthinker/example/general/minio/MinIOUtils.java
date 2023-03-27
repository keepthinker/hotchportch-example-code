package com.keepthinker.example.general.minio;

import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MinIOUtils {
    private static final String ACCESS_KEY = "ByWvHr3BQMmI99XO";
    private static final String ACCESS_SECRET = "UjBacI61b5ifQxQ4PjTfC8IYOeoIpYNQ";
    private static MinioClient minioClient =
            MinioClient.builder()
                    .endpoint("http://172.28.255.113:9000/")
                    .credentials(ACCESS_KEY, ACCESS_SECRET)
                    .build();

    /**
     *     C:\Users\shengkai.ke\Pictures\Screenshots\屏幕截图 2023-02-21 175501.png
      */
    public static void upload(String bucketName, String objectName, String fileAbsPath) throws Exception {
        minioClient.uploadObject(
                UploadObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .filename(fileAbsPath)
                        .build());
    }

    public static void getObjectUrl(String bucketName, String objectName) throws Exception {
        Map<String, String> reqParams = new HashMap<String, String>();
        reqParams.put("response-content-type", "image/png");
        String url =
                minioClient.getPresignedObjectUrl(
                        GetPresignedObjectUrlArgs.builder()
                                .method(Method.GET)
                                .bucket(bucketName)
                                .object(objectName)
                                .expiry(2, TimeUnit.HOURS)
                                .extraQueryParams(reqParams)
                                .build());
        System.out.println(url);
    }


    public void example() throws Exception {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("https://play.min.io")
                            .credentials("Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG")
                            .build();

            // Make 'asiatrip' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("asiatrip").build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("asiatrip").build());
            } else {
                System.out.println("Bucket 'asiatrip' already exists.");
            }

            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            // 'asiatrip'.
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("asiatrip")
                            .object("asiaphotos-2015.zip")
                            .filename("/home/user/Photos/asiaphotos.zip")
                            .build());
            System.out.println(
                    "'/home/user/Photos/asiaphotos.zip' is successfully uploaded as "
                            + "object 'asiaphotos-2015.zip' to bucket 'asiatrip'.");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }


}
