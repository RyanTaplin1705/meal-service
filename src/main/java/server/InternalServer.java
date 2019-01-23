package server;

public interface InternalServer {

    void start();
    void stop();

    InternalServer registerStatusEndpoint(String endpoint);

}
