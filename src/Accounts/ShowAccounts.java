package Accounts;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ShowAccounts  extends UnicastRemoteObject implements ShowAccountsInt{
private HashMap b;
    public ShowAccounts( HashMap a) throws RemoteException {
        b=a;
    }

    @Override
    public HashMap show() throws RemoteException
    {
       return b;
    }
}
