// MyServiceHome.java

package simple.ejb21;

import javax.ejb.*;
import java.rmi.*;

public interface MyServiceHome extends EJBHome
{

  public MyService create()
    throws CreateException, RemoteException;

}
