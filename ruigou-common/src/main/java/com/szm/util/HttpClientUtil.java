package com.szm.util;

import com.sun.jndi.toolkit.url.Uri;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http client 工具
 */
public class HttpClientUtil {
    /**
     * 带参数的get请求
     * @param url 请求地址
     * @param param 参数
     * @return 结果
     */
    public static String doGet(String url, Map<String ,String> param){
        CloseableHttpClient httpClient= HttpClients.createDefault();
        String resultString="";
        CloseableHttpResponse response=null;
        URIBuilder builder= null;
        try {
            builder = new URIBuilder(url);
            if (param!=null){
                for (String key:param.keySet()){
                    builder.addParameter(key,param.get(key));
                }
            }
            URI uri=builder.build();
            HttpGet httpGet=new HttpGet(uri);
            response=httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode()==200){
                resultString= EntityUtils.toString(response.getEntity(),"utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (response!=null){ response.close();}
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 不带参数的get请求
     * @param url
     * @return
     */
    public static String doGet(String url){
        return doGet(url,null);
    }

    /**
     * 带参数的post请求
     * @param url 地址
     * @param param 参数
     * @return 结果
     */
    public static String doPost(String url, Map<String ,String> param){
        CloseableHttpClient httpClient= HttpClients.createDefault();
        String resultString="";
        CloseableHttpResponse response=null;
        try {
            HttpPost httpPost=new HttpPost(url);
            if (param!=null){
                List<NameValuePair> paramList=new ArrayList<>();
                for (String key:param.keySet()){
                    paramList.add(new BasicNameValuePair(key,param.get(key)));
                }
                UrlEncodedFormEntity entity=new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }

            response=httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode()==200){
                resultString= EntityUtils.toString(response.getEntity(),"utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (response!=null){ response.close();}
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 不带参数的post
     * @param url
     * @return
     */
    public static String doPost(String url){
        return doPost(url,null);
    }

    public static String doPostJson(String url, String json){
        CloseableHttpClient httpClient= HttpClients.createDefault();
        String resultString="";
        CloseableHttpResponse response=null;
        try {
            HttpPost httpPost=new HttpPost(url);
            StringEntity entity=new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            response=httpClient.execute(httpPost);
            resultString= EntityUtils.toString(response.getEntity(),"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (response!=null){ response.close();}
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }
}
