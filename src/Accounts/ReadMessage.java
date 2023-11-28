package Accounts;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

public class ReadMessage extends UnicastRemoteObject implements ReadMessageInt{
    private HashMap<Integer, List<Message>> a1;
    public ReadMessage( HashMap<Integer, List<Message>> a) throws RemoteException
    {
     a1=a;
    }

    @Override
    public String showMessage(int authToken,int message_id)
    {
        System.out.println("Reading Message...");
        if(a1.isEmpty()) return null;
        List<Message> a = a1.get(authToken);
        if(a.isEmpty()) return null;
        int   i=0;
        String message = null;
        System.out.println("?3");
      for(Message b: a)
      {
          System.out.println("?2");
          if(b.getMessage_ID()==message_id)
          {
              System.out.println("?1");
              message="("+b.getSender()+")"+b.getBody();
              break;
          }
          System.out.println("?");
          i++;

      }
        a1.get(authToken).get(i).setRead(true);//Message read
        return message;
    }
}
