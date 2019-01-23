package server;

import org.eclipse.jetty.server.Server;
import server.properties.InternalServerProperties;

public class JettyServer implements InternalServer {

    private JettyServletHandler handler;
    private Server server;

    private InternalServerProperties properties;

    public JettyServer(InternalServerProperties properties) {
        this.properties = properties;
        if (canStart()) {
            this.server = new Server(properties.port);
            this.handler = new JettyServletHandler();
            this.server.setHandler(this.handler);
        }
    }

    @Override
    public void start() {
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public JettyServer registerStatusEndpoint(String endpoint) {
        handler.addServlet(properties.environment, endpoint);
        return this;
    }

    private boolean canStart() {
//        for (ServerDependency dependency : properties.dependencies) {
//            if (!dependency.isAvailable())
//                throw new IllegalStateException(String.format("Can not start server without dependency: %s", dependency.name));
//        }
        return true;
    }
}