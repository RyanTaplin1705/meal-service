package configuration;

import configuration.dependencies.ServerDependencies;
import configuration.dependencies.ServerDependency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static java.lang.String.format;

public class TestAppConfiguration implements AppConfiguration {

    private Optional<ServerDependencies> dependencies;

    @Override
    public Logger getLogger(String className) {
        return LoggerFactory.getLogger(format("for {%s} in %s", getEnvironment().toUpperCase(), className));
    }

    @Override
    public String getEnvironment() {
        return "acceptancetest";
    }

    public Optional<ServerDependencies> getDependencies() {
        return dependencies;
    }

    @Override
    public int getPort() {
        return 8189;
    }

    public void setDependencies(Optional<ServerDependencies> dependencies) {
        this.dependencies = dependencies;
    }
}
