package Accounts;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

public interface ShowInboxInt extends Remote {
    public HashMap<Integer, List<Message>> show() throws RemoteException;
}
