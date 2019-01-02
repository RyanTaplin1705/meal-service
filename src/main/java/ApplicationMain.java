import configuration.AppConfiguration;
import configuration.LiveAppConfiguration;
import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;

import static handlers.JettyHandlerBuilder.createJettyHandlerFor;

public class ApplicationMain {

    private AppConfiguration configuration = new LiveAppConfiguration();
    private Server server;

    private Logger logger;

    public ApplicationMain() {
        this.logger = configuration.getLogger(getClass().getName());
        disableJettyLogs();
    }

    public ApplicationMain(AppConfiguration config) {
        this.configuration = config;
        this.logger = config.getLogger(getClass().getName());
        disableJettyLogs();
    }

    private void disableJettyLogs() {
        System.setProperty("org.eclipse.jetty.util.log.class", "org.eclipse.jetty.util.log.StdErrLog");
        System.setProperty("org.eclipse.jetty.LEVEL", "OFF");
    }

    public void start() {
        logger.info("Application has been configured for environment '" + configuration.getEnvironment() + "'");
        try {
            serverSetup(configuration.getPort());
        } catch (Exception e) {
            logger.error("Server could not be started: " + e.getMessage());
        }
    }

    private void serverSetup(int port) throws Exception {
        server = new Server(port);
        server.setHandler(
                createJettyHandlerFor(configuration.getEnvironment())
                        .withMainServlet()
                        .withStatusServlet()
                        .build());
        server.start();
//        server.join();
        logger.info("Server has been started on port: " + port);
    }

    public void stop() throws Exception {
        server.stop();
        logger.info("Application stopped.");
    }
}
