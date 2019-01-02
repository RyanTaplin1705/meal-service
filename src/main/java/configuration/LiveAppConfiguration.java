package configuration;

import configuration.dependencies.ServerDependencies;
import configuration.dependencies.ServerDependencyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static java.lang.String.format;

public class LiveAppConfiguration implements AppConfiguration {

    @Override
    public Logger getLogger(String className) {
        return LoggerFactory.getLogger(format("%s-%s", getEnvironment(), className).toUpperCase());
    }

    @Override
    public String getEnvironment() {
        return "LIVE";
    }

    @Override
    public int getPort() {
        return 8080;
    }

    @Override
    public Optional<ServerDependencies> getDependencies() {
        return ServerDependencyBuilder.create().build();
    }
}
