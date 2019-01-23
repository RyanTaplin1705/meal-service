package configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.InternalServer;
import server.JettyServer;
import server.properties.InternalServerProperties;

import static java.lang.String.format;

public class RealConfiguration implements IConfiguration {

    @Override
    public String getEnvironment() {
        return "LIVE";
    }

    @Override
    public InternalServer getServer() {
        InternalServerProperties properties = new InternalServerProperties(getEnvironment());
        return new JettyServer(properties).registerStatusEndpoint("/status");
    }

    @Override
    public Logger getLogger(String className) {
        return LoggerFactory.getLogger(format("%s-%s", getEnvironment(), className).toUpperCase());
    }
}
