import configuration.IConfiguration;
import configuration.RealConfiguration;
import org.slf4j.Logger;
import server.InternalServer;

public class ApplicationMain {

    private final IConfiguration configuration;
    private InternalServer server;

    private Logger logger;

    public ApplicationMain(IConfiguration config) {
        this.configuration = config;
        this.server = config.getServer();
        this.logger = config.getLogger(getClass().getName());
    }

    public ApplicationMain CreateApplication() {
        return new ApplicationMain(new RealConfiguration());
    }

    public void start() {
        logger.info("Application has been configured for environment '" + configuration.getEnvironment() + "'");
        try {
            server.start();
            logger.info("Server has been started.");
        } catch (Exception e) {
            logger.error("Server could not be started: " + e.getMessage());
        }
    }

    public void stop() throws Exception {
        server.stop();
        logger.info("Application stopped.");
    }
}
