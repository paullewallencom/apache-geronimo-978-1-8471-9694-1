package com.packtpub.library.ejb;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MailBusinessRemote extends Remote{        
    public void sendMail() throws RemoteException;
}
