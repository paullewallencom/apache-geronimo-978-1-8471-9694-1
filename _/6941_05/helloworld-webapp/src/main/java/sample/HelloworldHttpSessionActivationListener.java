package sample;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

public class HelloworldHttpSessionActivationListener implements
		HttpSessionActivationListener {
	
	public HelloworldHttpSessionActivationListener() {
		super();
	}

	public void sessionDidActivate(HttpSessionEvent event) {
		System.out.println("HelloworldHttpSessionActivationListener.sessionDidActivate: "+event.getSession());
	}

	public void sessionWillPassivate(HttpSessionEvent event) {
		System.out.println("HelloworldHttpSessionActivationListener.sessionWillPassivate: "+event.getSession());
	}

}
