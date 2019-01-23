package server.properties;

public class InternalServerProperties {

    public int port = 8080;
    public String environment;

    public InternalServerProperties(String environment) {
        this.environment = environment;
    }
}
