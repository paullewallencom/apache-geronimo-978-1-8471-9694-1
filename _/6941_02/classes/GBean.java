import org.apache.geronimo.gbean.GBeanInfo;
import org.apache.geronimo.gbean.GBeanInfoBuilder;
import org.apache.geronimo.gbean.GBeanLifecycle;
public class TestGBean implements GBeanLifecycle{
  private String name;
  public TestGBean(String name){
    this.name = name;
  }
  public void doFail() {
    System.out.println("Failed.............");
  }
  public void doStart() throws Exception {
    System.out.println("Started............"+name);
  }
  public void doStop() throws Exception {
    System.out.println("Stopped............"+name);
  }
   public static final GBeanInfo GBEAN_INFO;
      static {
        GBeanInfoBuilder infoBuilder = GBeanInfoBuilder
                                            .createStatic(TestGBean
                                            .class, "TestGBean");
          infoBuilder.setPriority(2);
          infoBuilder.addAttribute("name", String.class, true);
          infoBuilder.setConstructor(new String[]{"name"});
          GBEAN_INFO = infoBuilder.getGBeanInfo();
      }
      public static GBeanInfo getGBeanInfo() {
          return GBEAN_INFO;
      }
}
