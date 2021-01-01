// MyServiceBean.java

package simple.ejb21;

import java.security.Principal;

import javax.ejb.*;

public class MyServiceBean implements SessionBean
{

  public SessionContext ctx;

  public MyServiceBean()
  {
    // constructor
  }

  public void ejbCreate()
    throws CreateException
  {
    // when bean is created
  }

  public void ejbActivate()
  {
    // when bean is activated
  }

  public void ejbPassivate()
  {
    // when bean is deactivated
  }

  public void ejbRemove()
  {
    // when bean is removed
  }

  public void setSessionContext(SessionContext ctx)
  {
    this.ctx = ctx;
  }

  public void unsetSessionContext()
  {
    this.ctx = null;
  }

  public String commonMethod() throws EJBException {
	  return logCall("commonMethod");
  }

  public String userMethod() throws EJBException {
	  return logCall("userMethod");
  }

  public String adminMethod() throws EJBException {
	  return logCall("adminMethod");
  }
  
  public String noaccessMethod() throws EJBException {
	  return logCall("noaccessMethod");
  }
  
  private String logCall(String method) {
	  Principal caller = ctx.getCallerPrincipal();
	  String msg = this.getClass().getSimpleName()+"."+method+"  called by "
      + "CallerPrincipal "+(caller == null ? null : caller.getClass().getSimpleName()+"["+caller.getName()+"]");
	  System.out.println(msg);
	  return msg;
  }
}
