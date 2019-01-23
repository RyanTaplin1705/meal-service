package configuration;

import org.slf4j.Logger;
import server.InternalServer;

public interface IConfiguration {

    InternalServer getServer();
    Logger getLogger(String className);

    String getEnvironment();
}
