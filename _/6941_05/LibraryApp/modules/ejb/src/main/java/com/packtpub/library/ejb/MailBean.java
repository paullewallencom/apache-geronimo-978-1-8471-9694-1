package com.packtpub.library.ejb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.mail.Session;
import javax.sql.DataSource;

import com.packtpub.library.ListUtility;

@Stateless(name="MailBean")
@WebService(serviceName = "MailService",
        portName="MailServiceSOAP11port",
        // endpointInterface = "com.packtpub.library.ejb.MailBusinessRemote",
        targetNamespace = "http://ejb.library.packt.com",
        wsdlLocation = "META-INF/wsdl/MailService.wsdl")

public class MailBean implements MailBusinessRemote, MailBusinessLocal{

	@Resource(name="mail/MailSession")
	Session mailSession;

	@Resource(name="jdbc/DataSource")
	private DataSource ds;

	@WebMethod
	public void sendMail() {
		try {
			ListUtility.sendMail(ds, mailSession);
		} catch (Exception e) {
		    System.out.println("Sending of email failed: "+e.getMessage());
		}
	}

}
