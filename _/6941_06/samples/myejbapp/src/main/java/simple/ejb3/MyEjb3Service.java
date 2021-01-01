package simple.ejb3;

import javax.ejb.Remote;

@Remote
public interface MyEjb3Service {
    String commonMethod();
    String userMethod();
    String adminMethod();
    String noaccessMethod();
}
