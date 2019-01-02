package configuration.dependencies;

public class ServerDependency {
    public String name;
    public String connection;

    public ServerDependency(String name, String connection) {
        this.name = name;
        this.connection = connection;
    }

    public StatusProbe toProbe() {
        return new StatusProbe(name, connection);
    }
}

