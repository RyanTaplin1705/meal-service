package handlers;

import handlers.servlets.MainServlet;
import handlers.servlets.StatusServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;

public class JettyHandlerBuilder {

    private final String environment;
    private ServletContextHandler handler;

    private String STATUS_ENDPOINT = "/status";

    private JettyHandlerBuilder(String environment) {
        this.environment = environment;
        this.handler = new ServletContextHandler();
    }

    public static JettyHandlerBuilder createJettyHandlerFor(String environment) {
        return new JettyHandlerBuilder(environment);
    }

    public JettyHandlerBuilder withStatusServlet() {
        addServlet(new StatusServlet(environment), STATUS_ENDPOINT);
        return this;
    }

    public JettyHandlerBuilder withMainServlet() {
        addServlet(new MainServlet(), "/any");
        return this;
    }

    public ServletContextHandler build() {
        return handler;
    }

    private void addServlet(HttpServlet httpServlet, String endpoint) {
        handler.addServlet(new ServletHolder(httpServlet), endpoint);
    }
}
