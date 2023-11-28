package Accounts;

import Accounts.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

public interface SendMessageInt extends Remote {
        public boolean sendMessage(int sender,String receiver,String Message) throws RemoteException;//
        public  HashMap<Integer, List<Message>> getMessagesb1() throws  RemoteException;

}
