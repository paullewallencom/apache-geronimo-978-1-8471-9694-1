package simple.ejb3;

import javax.ejb.Remote;

@Remote
public interface MyEjb3Service2 {
    String commonMethod();
    String userMethod();
    String adminMethod();
    String noaccessMethod();
}
