package server.servlets;

import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class StatusServlet extends HttpServlet {

    private final String environment;

    public StatusServlet(String environment) {
        this.environment = environment;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        writeResponse(response.getWriter());
    }

    private void writeResponse(PrintWriter writer) {
        JSONObject json = new JSONObject()
                .put("Environment", environment)
                .put("Overall Status", overallStatus());
        json.write(writer);
    }

    private String overallStatus() {
        return "OK";
    }

}
