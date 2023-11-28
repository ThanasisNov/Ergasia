package Accounts;

import Accounts.Message;
import Accounts.SendMessageInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class SendMessage extends UnicastRemoteObject implements SendMessageInt {
    private HashMap<Integer,String> a1;
    private HashMap<Integer,List<Message>> b1;//users and their inbox
    private HashMap<Integer,Message> messages;//all messages without users
    public SendMessage(HashMap<Integer,String> a, HashMap<Integer, List<Message>> b) throws RemoteException {

        a1=a;
        b1=b;
        messages = new HashMap<>(); // Initialize the messages HashMap

        Message c= new Message();//fake mail so its no null
        c.setSender("fake");
        c.setReceiver("fake");
        c.setBody("Faking that something exists");
        c.setRead(false);
        c.setMessage_ID(-32324);
        messages.put(1,c);

    }



  @Override
    public boolean sendMessage(int sender, String receiver, String Message)
    {
if(Message.isEmpty() ) Message="Empty Message";
        //System.out.println(a1.containsKey(sender) +"---"+a1.containsValue(receiver)+"Receiver->"+receiver);
if(a1.containsKey(sender) && a1.containsValue(receiver))
{
    System.out.println("Sending Message to "+receiver+" from "+sender);
    Integer keyForValue = null;
    for(Map.Entry<Integer, String> entry : a1.entrySet()) {//find the key of Receiver
        if(entry.getValue().equals(receiver)) {
            keyForValue = entry.getKey();
            break; // If you want only the first match, you can break the loop here
        }
    }


   int randomNum;

    Random random= new Random();
    do {
        randomNum = random.nextInt(9999 + 1);


    } while ( messages.containsKey(randomNum));

    Message mail= new Message();
    mail.setSender(a1.get(sender));
    mail.setReceiver(receiver);
    mail.setBody(Message);
    mail.setRead(false);
    mail.setMessage_ID(randomNum);

    messages.put(randomNum,mail);//used to make use that the same number will not be used

    b1.get(keyForValue).add(mail);//HashMap

    System.out.println("Success!");

    return true;
}
else
{
    System.out.println("Something went Wrong in Accounts.SendMessage");
    return false;
}

    }

    @Override
    public HashMap<Integer,List<Message>> getMessagesb1() {
        return b1;
    }


}
