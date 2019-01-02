package configuration.dependencies;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.impl.client.HttpClientBuilder;

public class StatusProbe {

    public String name;
    public String connection;

    public StatusProbe(String name, String connection) {
        this.name = name;
        this.connection = connection;
    }

    public String getStatus() {
        try {
            HttpOptions httpClient = new HttpOptions(connection);
            CloseableHttpResponse execute = HttpClientBuilder.create().build().execute(httpClient);
            if (execute.getStatusLine().getStatusCode() != 200) return "FAIL";
            else return "OK";
        } catch (Exception e) {
            return "FAIL";
        }
    }
}
