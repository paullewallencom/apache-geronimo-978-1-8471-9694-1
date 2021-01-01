public interface ModuleBuilder {
    Module createModule(File plan, JarFile moduleFile, Naming naming,
                        ModuleIDBuilder idBuilder)
                            throws DeploymentException;
    Module createModule(Object plan, JarFile moduleFile,
                        String targetPath, URL specDDUrl,
                        Environment environment,
                        Object moduleContextInfo,
                        AbstractName earName, Naming naming,
                        ModuleIDBuilder idBuilder)
                            throws DeploymentException;
    void installModule(JarFile earFile, EARContext earContext,
                       Module module, Collection configurationStores,
                       ConfigurationStore targetConfigurationStore,
                       Collection repository)
                           throws DeploymentException;
    void initContext(EARContext earContext, Module module,
                     ClassLoader cl) throws DeploymentException;
    void addGBeans(EARContext earContext, Module module,
                   ClassLoader cl, Collection repository)
                   throws DeploymentException;
    String getSchemaNamespace();
}
