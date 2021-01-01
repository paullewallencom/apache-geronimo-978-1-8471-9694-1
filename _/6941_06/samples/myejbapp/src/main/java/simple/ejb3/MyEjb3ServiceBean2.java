package simple.ejb3;

import java.security.Principal;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
@DeclareRoles(value = {"ejb3user", "ejb3admin"})
@RunAs(value = "ejb3user")
public class MyEjb3ServiceBean2 implements MyEjb3Service2 {

	  @Resource
	  private SessionContext ctx;
	  
	  @EJB
	  private MyEjb3Service myejb3service;
	
      @PermitAll
	  public String commonMethod() {
		  return logCall("commonMethod") + "::" + myejb3service.commonMethod();
	  }

      @RolesAllowed({"ejb3user"})
	  public String userMethod() {
		  return logCall("userMethod") + "::" + myejb3service.userMethod();
	  }

      @RolesAllowed({"ejb3admin"})
	  public String adminMethod() {
		  return logCall("adminMethod") + "::" + myejb3service.adminMethod();
	  }
	  
      @DenyAll
	  public String noaccessMethod() {
		  return logCall("noaccessMethod") + "::" + myejb3service.noaccessMethod();
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
