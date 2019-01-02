package configuration;

import configuration.dependencies.ServerDependencies;
import org.slf4j.Logger;

import java.util.Optional;

public interface AppConfiguration {


    Logger getLogger(String className);

    String getEnvironment();
    int getPort();

    Optional<ServerDependencies> getDependencies();
}
