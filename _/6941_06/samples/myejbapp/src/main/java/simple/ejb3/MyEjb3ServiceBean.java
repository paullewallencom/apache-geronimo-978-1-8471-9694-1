package simple.ejb3;

import java.security.Principal;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
@DeclareRoles({"ejb3user", "ejb3admin"})
public class MyEjb3ServiceBean implements MyEjb3Service {

	  @Resource
	  private SessionContext ctx;
	
      @PermitAll
	  public String commonMethod() {
		  return logCall("commonMethod");
	  }

      @RolesAllowed({"ejb3user"})
	  public String userMethod() {
		  return logCall("userMethod");
	  }

      @RolesAllowed({"ejb3admin"})
	  public String adminMethod() {
		  return logCall("adminMethod");
	  }
	  
      @DenyAll
	  public String noaccessMethod() {
		  return logCall("noaccessMethod");
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
