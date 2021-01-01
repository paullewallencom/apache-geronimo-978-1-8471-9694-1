// MyService.java

package simple.ejb21;

import javax.ejb.*;

import java.rmi.*;

public interface MyService extends EJBObject
{
    public double convertUSD2EURO(double usd)
    throws RemoteException;
    
    public double convertEURO2USD(double euro)
    throws RemoteException;
}
