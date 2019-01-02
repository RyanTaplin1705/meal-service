package configuration.dependencies;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ServerDependencies {

    public final List<ServerDependency> deps;

    public ServerDependencies() {
        deps = new LinkedList<>();
    }
    public ServerDependencies(List<ServerDependency> deps) {
        this.deps = deps;
    }

    public List<StatusProbe> createStatusProbes() {
        return deps.stream().map(d -> new StatusProbe(d.name, d.connection)).collect(Collectors.toList());
    }
}