// MyServiceLocal.java

package simple.ejb21;

import javax.ejb.*;

public interface MyServiceLocal extends EJBLocalObject
{
    public double convertUSD2EURO(double usd)
    throws EJBException;
    
    public double convertEURO2USD(double euro)
    throws EJBException;
}
