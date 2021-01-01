// MyServiceLocal.java

package simple.ejb21;

import javax.ejb.*;

public interface MyServiceLocal extends EJBLocalObject
{
    public String commonMethod()
    throws EJBException;

    public String userMethod()
    throws EJBException;

    public String adminMethod()
    throws EJBException;

    public String noaccessMethod()
    throws EJBException;
}
