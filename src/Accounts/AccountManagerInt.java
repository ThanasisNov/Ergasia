package Accounts;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface AccountManagerInt  extends Remote {
    public boolean CreateAccount(String a) throws RemoteException;
    public int getAuth() throws  RemoteException;
    public  void setAuth(Integer a) throws  RemoteException;
    public HashMap getAccounts() throws  RemoteException;
}
