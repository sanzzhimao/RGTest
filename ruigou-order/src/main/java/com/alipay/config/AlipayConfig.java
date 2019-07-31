package com.alipay.config;

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
	public static String app_id = "2016100100642937";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCbcfwSelIKBioxg9a6qZoaKohY4LjwDkV5g74AwL5hmPtbwtks1awRQ35JC9uH2BxHKF8vULRLNAv50QE3Llg2o5be20Lm+VVMLSSbU31Q+rH7CwOYSYCpEeyswC/HQPngfs2S/SR2NNyZNbhQpXLyc1zxYle07VLNtR9M/E6IVWRrYkGzOrOjLtYZXSk6LFqvCq2rYPscbq/HqDXTdfVqhJ0XQQ4B3Jb+h98kNpHzQcJw0OGd0o/Euy4vMRo29anJ59LUh2Bwg66AN8Wk3+q+6LyifKUurTFetZ0R4BGtth/vHbDc2aIdvxrq+U92oNQVw5RlNMLKlf8OKARzGNYfAgMBAAECggEAfOvxo/fhjmS96+UD4CcEzRp5UNPoTCNr+efB/mDnnw+OkYPZfIqLKaSQDKwW1Oj8cMMlG6VD/1voc+9fhrUb920ohhi3f1/JCNsOnDVQdI/EEPgc/3myiCz7gylAPGUfayb8lrARaV26GvZtFYKhCe9kNijOS3yeGusAKKJrimuxqbXDmPHc4pbcla9Xmzy2vdqgIhPbFjTGntphFYMhVxNhF2JZIU98+UVuA34lgWrH4DUeUnNdj+8N/s1m2UuY/FrSQgB1B0yX71BAYKIODNop2jyjXg3dNV94kB9A3ksFKUaefK8lVd/UdbSqoelB2EKLhFsJbVMRv6pN+cBOIQKBgQDmxfTZVrGRB5sUGehoEU6dDIHq4yO3TIaqoJgJya7avqeuhzeNzPc4R9JDcIBFuz9ZCv8diCmLkUEy9pX1ao+QF5EHY1WXIaZ3Ma3HZC21zcs95eYcdb9w6OgelGh0PRLVkQUqeWam/1mAVuPJwOYQqFUsHVli3engDjQHrYS/+wKBgQCscAWAFnAudymbA05ZZtP4knzLRnDhEqc7ybbzUyci8XHDhdoY7fJ2kObDL3GVOkhmCgHB3sj/1HUwaCpZeYDclsU8L3wjrZ6EfoC9ckhAVysIiyIunM8OO+tAUJhBFJPz9qShDC7dJx71LFpE/hhhZAnj9ccMb7ttSBqmkDKVLQKBgEnLkZGSfHOxJ/EUm9Hzft0Pq9fNzJUH3qRfeF7g5I5Em2BfEu/AVGnl5aH1Lr+dshMqRgxOh2J/D4dZfPiem8vrDVB8flk2xIGnHDf/m/JgHZbslloReSeFAv0MXh6VjRlIJ4u+iInsa9Vzc6IHkkpPWfUiaXwJFIm2YQL3hNTTAoGASmisdvE3x1U0ZueuT3XH0mOj5jOnUfgyoeIRSD6BjNqTNMP1sPw8vF0atu8qHDLiOuXmjDoOkhaj2xeX7rQ3nviQc8Ap2Vt6I8JS220NMHZs42rmfSa9Um8YvNAKY0r17ThpIfWX42aWPUW+Tjc3Xg9FFVFha0RrpGSQ238t1KECgYEAr9FR4+S+8Ly/gbIYzmGGJXndkhC2L68BYacvOzLiqIsq/6IUqNVNPZUicXg8lHzqxKjps+KUhDaCbITniTzXng7jSTWlg6LA1L/ioE43ObvVNi5MDYL+kYpuKKSDL1/65ONtPatWQZapN1J7P6Dpg1B4yRb3vSHLbIY/6pCge8A=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkFLw5dRGnCUAKYHEzNrzoWBrmryzd3nRnEMBnemCWzbVOjbcDq/78MMkHZgTbwaHy9ud8ZdO8gFI5XgPy683IuWLwApZT1GXAifX/f4QEsAC4QsUfBhlQ3IszUHAb7Z+Zz3WMO3GOstxtvhtFGITpmAYzMco/+cZbz8TbCZwOSli/YtTrck1Xf01x4oTh9ETVKu65cjas9j54comD/LE2LFG+AqZDqPMY/f6PtNUkRuXLwtsq2kskph4wG4tl6VQ0BIP2BjXeo3xkAymw2lu/7/gfi3AOP5I7X8/zZaoDffCy77NMp5RbzV0a/RpXD8FDiVEITjupuIOTI5opiL5OQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
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

