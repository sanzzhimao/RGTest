import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class Test1 {
    @Test
    public void Test() throws IOException {
        //创建httpclient对象
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpGet get=new HttpGet("https://www.baidu.com");
        CloseableHttpResponse response=httpClient.execute(get);
        int statusCode=response.getStatusLine().getStatusCode();
        HttpEntity entity=response.getEntity();
        String string= EntityUtils.toString(entity,"utf-8");
        System.out.println(string);
        response.close();
        httpClient.close();
    }

}

