package Accounts;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

public class ShowInbox extends UnicastRemoteObject implements ShowInboxInt {
   private  HashMap<Integer,List<Message>>  a1;
    public ShowInbox( HashMap<Integer, List<Message>> a) throws RemoteException {

        a1=a;
    }

    @Override
    public  HashMap<Integer,List<Message>> show() throws RemoteException {

System.out.println("This is it!");
        return a1;
    }
}
