package configuration.dependencies;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ServerDependencyBuilder {

    private List<ServerDependency> deps = new LinkedList<>();

    ServerDependencyBuilder() { }
    ServerDependencyBuilder(ServerDependency dep) {
        deps.add(dep);
    }

    public static ServerDependencyBuilder create() {
        return new ServerDependencyBuilder();
    }

    public ServerDependencyBuilder withDependency(ServerDependency dep) {
        deps.add(dep);
        return this;
    }

    public Optional<ServerDependencies> build() {
        return Optional.of(new ServerDependencies(deps));
    }
}



