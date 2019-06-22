package com.qf.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author: WangXi
 * @Date: 2019/6/18
 */
public class HttpClientUtils {

    public static String doGet(String url, Map<String,String> params){

        String result="";
        //1.创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            URIBuilder builder = new URIBuilder(url);
            if(params!=null){
                for (String s : params.keySet()) {
                    builder.addParameter(s,params.get(s));
                }
            }
            URI build = builder.build();

            //创建get请求
            HttpGet httpGet = new HttpGet(build);

            CloseableHttpResponse response = httpClient.execute(httpGet);

            if(response.getStatusLine().getStatusCode()==200){
                result = EntityUtils.toString(response.getEntity(),"utf-8");
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }

    public static String doGet(String url){
        return doGet(url,null);
    }

}
