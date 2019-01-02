package utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class TestHttpClient {

    private HttpGet request;

    public TestHttpClient(String uri) {
        request = new HttpGet(uri);
    }

    public static HttpResponse getResponseFrom(String uri) throws IOException {
        TestHttpClient httpClient = new TestHttpClient(uri);
        CloseableHttpResponse execute = HttpClientBuilder.create().build().execute(httpClient.request);
        return new HttpResponse(execute);
    }
}

