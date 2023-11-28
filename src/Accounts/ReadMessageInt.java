package Accounts;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface ReadMessageInt extends Remote {
public String showMessage(int authToken,int message_id) throws RemoteException;
}
