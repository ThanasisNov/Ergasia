package Accounts;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface ShowAccountsInt  extends Remote  {

    public  HashMap show() throws RemoteException;


}
