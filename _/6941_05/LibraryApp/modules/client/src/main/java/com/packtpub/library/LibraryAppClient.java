package com.packtpub.library;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.packtpub.library.ejb.MailBusinessRemote;

public class LibraryAppClient {
	
	public static MailBusinessRemote mbr;
	
	public static void main(String[] args){
		try {
			Context ctx = new InitialContext();		
			Service mailService = (Service) ctx.lookup("java:comp/env/service/SendMail");
			mbr = mailService.getPort(new QName("MailServiceSOAP11port"),MailBusinessRemote.class);
			mbr.sendMail();
			System.out.println("Invoked Sent mail. See Server log for details");
		} catch (NamingException e1) {
			e1.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace(System.out);
		}
	}
	
}
