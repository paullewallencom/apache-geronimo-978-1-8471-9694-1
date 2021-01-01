public interface DeploymentWatcher {
    void deployed(Artifact id);
    void undeployed(Artifact id);
}
