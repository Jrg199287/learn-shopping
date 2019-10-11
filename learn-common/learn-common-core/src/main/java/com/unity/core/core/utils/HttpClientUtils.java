package com.unity.core.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.www.http.HttpClient;
import java.io.IOException;
import java.util.*;


/**
 * Created by xcy on 2018/10/19.
 */
public class HttpClientUtils {

    /** 记录日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * 发送get请求
     * @param url
     * @param decodeCharset
     * @return
     */
    public static String sendGetRequest(String url, String decodeCharset) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String responseContent = null;
        HttpGet httpGet = new HttpGet(url);
        org.apache.http.HttpEntity entity = null;
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            System.out.println(response);
            entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
            }
        } catch (Exception e) {
            LOGGER.error("该异常通常是网络原因引起的,如HTTP服务器未启动等,堆栈信息如下", e);
        } finally {
            try {
                EntityUtils.consume(entity);
                httpclient.getConnectionManager().shutdown();
            } catch (Exception ex) {
                LOGGER.error("net io excepiton", ex);
            }
        }
        return responseContent;
    }

    /**
     * post 请求
     * @param reqURL
     * @param  data  可以为param="key1=value1&key2=value2"的一串字符串,或者是jsonObject
     * @return
     */
    public static String sendHttpPostRequest(String reqURL, String data) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String respStr = "";
        try {
            HttpPost httppost = new HttpPost(reqURL);
            StringEntity strEntity = new StringEntity(data, "UTF-8");
            strEntity.setContentType("application/x-www-form-urlencoded");
            httppost.setEntity(strEntity);
            LOGGER.info(EntityUtils.toString(strEntity));
            LOGGER.info("executing request " + httppost.getRequestLine());

            CloseableHttpResponse response = httpclient.execute(httppost);
            org.apache.http.HttpEntity resEntity = response.getEntity();

            if (resEntity != null) {
                LOGGER.info("返回数据长度: " + resEntity.getContentLength());
                respStr = EntityUtils.toString(resEntity);
                LOGGER.info("respStr " + respStr);
            }

        } catch (ClientProtocolException e) {
            LOGGER.error("sendHttpPostRequest : " ,e);
        } catch (IOException e) {
            LOGGER.error("sendHttpPostRequest : " ,e);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return respStr;
    }

    /**
     * 发送post请求
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String sendHttpPostRequest(String url, Map<String, String> params) {
        String respStr = "";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
        httpclient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
        LOGGER.info("url: " + url);
        LOGGER.info("params: " + params);
        try {
            HttpPost post = new HttpPost(url);
            List<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                postData.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postData, "UTF-8");
            post.setEntity(entity);
            CloseableHttpResponse response = httpclient.execute(post);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                LOGGER.info("返回数据长度: " + resEntity.getContentLength());
                respStr = EntityUtils.toString(resEntity);
                LOGGER.info("respStr " + respStr);
            }
        } catch (ClientProtocolException e) {
            LOGGER.error("sendHttpPostRequest : " +e);
        } catch (IOException e) {
            LOGGER.error("sendHttpPostRequest : " +e);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return respStr;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        String params = "bankcard=6217856101018144878&key=316fcfd892e7e4d24ded8699f1f7d957";
        String resultstr = HttpClientUtils.sendHttpPostRequest("http://apis.juhe.cn/bankcardcore/query", params);
        System.out.println(resultstr);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map map = mapper.readValue(resultstr, Map.class);
            Set<Map.Entry> set = map.entrySet();
            for (Map.Entry entry : set) {
                System.out.println(entry.getKey() + "==" + entry.getValue());
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

}