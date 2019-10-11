package com.alipay.demo.trade.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016091100488978";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEogIBAAKCAQEAnPe2y0IXCFQbufP+DbmS3/ojCqzFUCasvLNmX5gq7NoDjNxg4BdQAuCjryjXOeKFUAQ9HIGDoq4eovaUwa5jHcZFHoCp4iwUktE6t+F5zJt1okv+7iVfu0DbWRiQdzr6sBM6FbSev/u2cH/hXPWe452I3WcwYNyNk3F3O467bHC9He29r0lYBBSt2S5YhCz80iu/qBKHFtbBBs7mluGP5Su099v8E1tTMxfBVuTU/qpJt8nVWFYKjmr78obP6Rmac1SWsJtn+SSs29+2ddg3f7YsEIZVQEPvXdLO4Hb2Brw6jOYUYjeGv2sRK+kgNcj1IyfjJaRmEqmviBECglQFyQIDAQABAoIBAAlvLhJrZ3z9qSj1DEkA5V4G58JFfhqCKJGy6q1GNRZFSD9YI450eD+kBtKJTt5Qyhh0GMQ02JeXhYnf7jLx600n1JGvxAYvBhGI99GDgNGWamyVyr2/i1By5nwlaNFxvw2EKlKRp5T8aQu78+ylaqZdmpNMF3FK8EtzX2o5+vPafqfGUpWIurYIeVGwueYSFV+9loAh5fo3cNyISDvHvP64+f25PShV3TnBHI1iBdDdE8XZZFoWdZEtsWLK34Raxo6mbr3WU6Fi9oQ6mq6sFO2eZgcL9w9iXN4vpZ3G/I2pAOcVbhwFNnoRWy39RNSd/oldQ7phVqzGZnllCmWmgoECgYEA2Lz0eHhbtYwzjYpQ/panZMWOtWyGWYlKcbGAsxSD+XLDo+fXGlePuMOWteImozf4cbshZE2I8/Bjfl0OT4FrGPoF+WAWR+m3qkasEbvlpv+uHVjMaSCO5GKlzjzFAsPwCswDnAKUa1Gxf45bPzEvvEtHHHXFcNlkyBRbo9bcUzkCgYEAuWb0TscO5OeWTG0lxzDrNg3Zyn6XrJpjntGwawHx6XnAmmiFowt5F9QF/yP8kq9vUQFTnXcGCwHnWShE0EOoVltpbi2uK1KF6HGkzHui6Uwlt2faR+8AWejhPFa02nUQhUauAoPyO+frRVvBCeeftg7O9mnfBPG1hHvdKGwbdxECgYAK8lAunXWUT8ZyyZ+ZnCZMMqYoh5ipgQOfhmWHpeucd61l49m7XNzHail8ejWVXMhRhjADjt7x4xxDT5PfdBvioQ4YMEAv1aAPG46pPqI0swkqNkfsr1ZINqtFJVCV+StwgClWgdLZMMEUPi5hnKxaiDrJNzGHp5Mtf8m5Fqr+EQKBgEwBg/hjrJx0wG32dP1S9apERVu5SA7PgDWUuyEzO+w+KJJkdRyhUHRPTGILa7jqyNTJmXC9YbHThvdLBeLnSbSVZwRvH67M9u6S4D+gE+pWeEPXBGpm9ZrmWKf218+THEG29xE67GxYqF/XvKyF0zi8m2XzgyUwvfxhFwpCP4QxAoGAd227YwuSbRy3rPoUJNYMvGzMNvjPbSV42EJBTuBOS/cQw0TVZz43iSn8EAno1E48ZWFtaHHB/cnOIDLyIh3gGBzFo4ci7npEBVmeV15XBoiMRpaAuqZEZXj82I3J9TofK6/eWxJKvzzv/8/RaC80BFsNORJsYu7pznQJE47nQTQ=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmxFjGlxoj8YQNKWyIyTtLrnUO1UpWLcaKfc/XQjE/4jcuUwehQfCEomRJafOB0K0oll/gCNSs4KdN2vax3kYPwaMDV5SBwMQeV2v+OyGui5UDmP2F/BV/SXEXuL+GlLN2qskdr8OI54NblDcAJH4opJgcDJ02pXhEyo2+V8JbisHB+gun9/feQCEDUzxr2yrKVR8QTj6tQFC4bzJbcA3i8WlXcrnjOugCiK2iKizh855GmAG8u/IqXjxrjmQ9GvF+BGlbu8QCOHtQpdvtKiKN3OvaQWAaq2NTfhoydE6989STs00Uazb78MwRyl8yn/f/i0M8BrR0iL8NgcYtldZ8QIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://4myhxr.natappfree.cc/ACPSample_B2C/frontRcvResponse";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://4myhxr.natappfree.cc/aliPayAsynCallback";
    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "D:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

