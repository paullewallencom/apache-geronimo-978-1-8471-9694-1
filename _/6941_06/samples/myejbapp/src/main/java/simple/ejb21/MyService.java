// MyService.java

package simple.ejb21;

import javax.ejb.*;

import java.rmi.*;

public interface MyService extends EJBObject
{
    public String commonMethod()
    throws RemoteException;

    public String userMethod()
    throws RemoteException;

    public String adminMethod()
    throws RemoteException;

    public String noaccessMethod()
    throws RemoteException;
}
