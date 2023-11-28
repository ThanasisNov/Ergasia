package Accounts;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

public class DeleteMessage extends UnicastRemoteObject implements DeleteMessageInt{
    private HashMap<Integer, List<Message>> a1;
    public DeleteMessage( HashMap<Integer, List<Message>> a) throws RemoteException
    {
        a1=a;
    }


    @Override
    public String Delete_Message(int authToken, int message_id) throws RemoteException {
        System.out.println("Deleting Message...");
        List<Message> a = a1.get(authToken);
        int   i=0;
        for(Message b: a)
        {

            if(b.getMessage_ID()==message_id)
            {
                a1.get(authToken).remove(i);//Message read
                return "OK";
            }
            i++;

        }

        return "Message "+message_id+" does not exist";
    }
}
