package com.packtpub.library.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebService;

@SuppressWarnings("unused")
@Remote
public interface MailBusinessRemote {
        
    public void sendMail() throws RemoteException;
}
