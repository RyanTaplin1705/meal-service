package handlers.servlets;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StatusServlet extends HttpServlet {

    private final String environment;

    public StatusServlet(String environment) {
        this.environment = environment;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json");
        response.getWriter().write("Hello...");
    }

    //TODO: at some point add probes & dependencies (i.e database)
    private JSONObject response() {
        JSONObject json = new JSONObject()
                .put("Status", overallStatus())
                .put("Environment", environment);
        return json;
    }

    //TODO: logic will change when probes implemented
    private String overallStatus() {
        return "OK";
    }

}
