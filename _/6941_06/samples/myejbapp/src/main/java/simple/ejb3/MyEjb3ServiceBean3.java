package simple.ejb3;

import java.security.Principal;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
@DeclareRoles(value = {"ejb3user", "ejb3admin"})
public class MyEjb3ServiceBean3 implements MyEjb3Service3 {

	public MyEjb3ServiceBean3() {
		super();
	}
	  @Resource
	  private SessionContext ctx;
	  
	  @EJB
	  private MyEjb3Service myejb3service;
	
      @PermitAll
	  public String commonMethod() {
    	  Object temp;
    	  try {
    		  temp = myejb3service.commonMethod();
    	  } catch(Throwable t) {
    		  temp = t;
    	  }
		  return logCall("commonMethod") + "::" + temp;
	  }

      @RolesAllowed({"ejb3user"})
	  public String userMethod() {
    	  Object temp;
    	  try {
    		  temp = myejb3service.userMethod();
    	  } catch(Throwable t) {
    		  temp = t;
    	  }
		  return logCall("userMethod") + "::" + temp;
	  }

      @RolesAllowed({"ejb3admin"})
	  public String adminMethod() {
    	  Object temp;
    	  try {
    		  temp = myejb3service.adminMethod();
    	  } catch(Throwable t) {
    		  temp = t;
    	  }
		  return logCall("adminMethod") + "::" + temp;
	  }
	  
      @DenyAll
	  public String noaccessMethod() {
    	  Object temp;
    	  try {
    		  temp = myejb3service.noaccessMethod();
    	  } catch(Throwable t) {
    		  temp = t;
    	  }
		  return logCall("noaccessMethod") + "::" + temp;
	  }
	  
	  private String logCall(String method) {
		  Principal caller = ctx.getCallerPrincipal();
		  String msg = this.getClass().getSimpleName()+"."+method+"  called by "
	      + "CallerPrincipal "+(caller == null ? null : caller.getClass().getSimpleName()+"["+caller.getName()+"]");
		  msg += " in roles ["+(ctx.isCallerInRole("ejb3user") ? "ejb3user" : "") + (ctx.isCallerInRole("ejb3admin") ? " ejb3admin" : "")+"]";
		  System.out.println(msg);
		  return msg;
	  }
}
