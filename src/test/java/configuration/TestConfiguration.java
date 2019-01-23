package configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.InternalServer;
import server.JettyServer;
import server.properties.InternalServerProperties;

import static java.lang.String.format;

public class TestConfiguration implements IConfiguration {

    private InternalServerProperties properties = new InternalServerProperties(getEnvironment());

    @Override
    public InternalServer getServer() {
        return new JettyServer(properties).registerStatusEndpoint("/status");
    }

    @Override
    public Logger getLogger(String className) {
        return LoggerFactory.getLogger(format("for {%s} in %s", getEnvironment().toUpperCase(), className));
    }

    @Override
    public String getEnvironment() {
        return "test";
    }

    public InternalServerProperties getProperties() {
        return properties;
    }
}
