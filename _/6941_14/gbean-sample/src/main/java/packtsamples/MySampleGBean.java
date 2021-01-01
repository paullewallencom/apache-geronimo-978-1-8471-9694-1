package packtsamples;

import java.io.PrintWriter;

import org.apache.geronimo.gbean.AbstractName;
import org.apache.geronimo.gbean.GBeanInfo;
import org.apache.geronimo.gbean.GBeanInfoBuilder;
import org.apache.geronimo.gbean.GBeanLifecycle;
import org.apache.geronimo.kernel.Kernel;
import org.apache.geronimo.system.serverinfo.ServerInfo;

public class MySampleGBean implements MySampleInterface, GBeanLifecycle {
	private Kernel kernel;
	private String objectName;
	private AbstractName abstractName;
	private ClassLoader classLoader;
	private String name;
	private String name2;
	private ServerInfo serverInfo;
	
	public MySampleGBean(Kernel kernel, String objectName, AbstractName abstractName, ClassLoader classLoader, String name) {
		this.kernel = kernel;
		this.objectName = objectName;
		this.abstractName = abstractName;
		this.classLoader = classLoader;
		this.name = name;
		System.out.println("kernel = "+kernel);
		System.out.println("objectName = "+objectName);
		System.out.println("abstractName = "+abstractName);
		System.out.println("classLoader = "+classLoader);
		System.out.println("name = "+name);
	}
	
	public void setServerInfo(ServerInfo serverInfo) {
		this.serverInfo = serverInfo;
		System.out.println("serverInfo = "+serverInfo);
	}
	
	public String myMethod1() {
		StringBuffer out = new StringBuffer(this.getClass().getSimpleName()+"["+name+"].myMethod1()\n");
		out.append("server version = "+serverInfo.getVersion()+"\n");
		out.append("server build date = "+serverInfo.getBuildDate()+"\n");
		out.append("server build time = "+serverInfo.getBuildTime()+"\n");
		out.append("server copyright = "+serverInfo.getCopyright()+"\n");
		out.append("server base directory = "+serverInfo.getBaseDirectory()+"\n");
		out.append("server current base directory = "+serverInfo.getCurrentBaseDirectory());
		System.out.println(out.toString());
		return out.toString();
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public void doFail() {
		System.out.println(this.getClass().getSimpleName()+"["+name+"] Failed.............");
	}

	public void doStart() throws Exception {
		System.out.println(this.getClass().getSimpleName()+"["+name+"] Started............");
	}

	public void doStop() throws Exception {
		System.out.println(this.getClass().getSimpleName()+"["+name+"] Stopped............");
	}

	public static final GBeanInfo GBEAN_INFO;
	
    static {
        GBeanInfoBuilder infoFactory = GBeanInfoBuilder.createStatic("GBean", MySampleGBean.class);
        // Initialize the GBean structure here
        infoFactory.addAttribute("kernel", Kernel.class, false);
        infoFactory.addAttribute("objectName", String.class, false);
        infoFactory.addAttribute("abstractName", AbstractName.class, false);
        infoFactory.addAttribute("classLoader", ClassLoader.class, false);
        infoFactory.addAttribute("name", String.class, true, true);
        infoFactory.addAttribute("name2", String.class, true, false);
        infoFactory.addReference("ServerInfo", ServerInfo.class);
        infoFactory.setConstructor(new String[] {"kernel", "objectName", "abstractName", "classLoader", "name"});
        infoFactory.addOperation("myMethod1", "java.lang.String");
        infoFactory.addInterface(MySampleInterface.class);
        GBEAN_INFO = infoFactory.getBeanInfo();
    }

    public static GBeanInfo getGBeanInfo() {
        return GBEAN_INFO;
    }
}
