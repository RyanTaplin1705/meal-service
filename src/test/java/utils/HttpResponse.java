package utils;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpResponse {

    public int statusCode;
    public String body;

    public HttpResponse(CloseableHttpResponse response) throws IOException {
        this.statusCode = response.getStatusLine().getStatusCode();
        this.body = EntityUtils.toString(response.getEntity());
    }
}
