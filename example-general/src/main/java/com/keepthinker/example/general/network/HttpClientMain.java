package com.keepthinker.example.general.network;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
//comm.vivo.com.cn/v2/deviceconfig/getsfinfo?model=vivo x5pro D&elapsedtime=65216548&imei=1456489411&productName=PD1421&sysver=funtouch&romver=as34312
public class HttpClientMain {
	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient  httpclient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(3000).build())
                .build();


//			String basicUrl = "http://comm.vivo.com.cn/v2/deviceconfig/getsfinfo";
            String basicUrl = "https://www.baidu.com";
			URIBuilder builder = new URIBuilder(basicUrl);
			builder.setParameter("model", "value1").setParameter("elapsedtime", "value2")
			.setParameter("imei", "value2").setParameter("productName", "value2")
			.setParameter("sysver", "value2").setParameter("romver", "value2");
			
			HttpGet httpget = new HttpGet(builder.build());
            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
        long t1 = System.currentTimeMillis();
        try {
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        } finally {
            System.out.println("http elapsed time: " + (System.currentTimeMillis() - t1));
            httpclient.close();
        }
	}
}
