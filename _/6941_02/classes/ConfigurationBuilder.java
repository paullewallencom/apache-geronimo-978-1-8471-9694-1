

public interface ConfigurationBuilder {
    static final String CONFIG_BUILDER = "ConfigBuilder";
    Object getDeploymentPlan(File planFile, JarFile module,
                             ModuleIDBuilder idBuilder)
                             throws DeploymentException;
    Artifact getConfigurationID(Object plan, JarFile module,
                                ModuleIDBuilder idBuilder)
                   throws IOException, DeploymentException;
    DeploymentContext buildConfiguration(
boolean inPlaceDeployment, //Boolean specifying whether to deploy in place
Artifact configId,// the configuration id
Object plan, // the deployment plan
JarFile module, // the archive to deploy
Collection configurationStores, // the list of config stores
ArtifactResolver artifactResolver,
ConfigurationStore targetConfigurationStore // target store
) throws IOException, DeploymentException;
}
