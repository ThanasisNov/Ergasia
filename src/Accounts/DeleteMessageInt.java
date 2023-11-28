package Accounts;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DeleteMessageInt extends Remote {
    public String Delete_Message(int authToken,int message_id) throws RemoteException;
}
