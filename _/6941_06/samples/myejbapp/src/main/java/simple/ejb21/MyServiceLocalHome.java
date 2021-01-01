// MyServiceLocalHome.java

package simple.ejb21;

import javax.ejb.*;

public interface MyServiceLocalHome extends EJBLocalHome
{

  public MyServiceLocal create()
    throws CreateException, EJBException;

}
