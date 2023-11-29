import Accounts.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

import static java.lang.System.exit;
// To change the 2 input parameters (double numbers) in Intellij go to: Edit Configurations -> Client -> Built and Run

public class Client {
    public static void main(String args[]) {
        try {
            if(args.length<3)
            {
                System.out.println("Something is missing");
                System.out.println("Tip:java client <ip> <port number> <FN_ID> <args-Note:enter a number first>!");
                exit(0);
            }

            String ip =  args[0];;
            String  port = args[1];;
            String FN_ID =  args[2];
if(Integer.parseInt(FN_ID)<=0 ||  Integer.parseInt(FN_ID)>6)
{
    System.out.println("Manual:");
    System.out.println("Create Account (FN_ID: 1)");
    System.out.println("Show Accounts (FN_ID: 2)");
    System.out.println("Send Message (FN_ID: 3)");
    System.out.println("Show Inbox (FN_ID: 4)");
    System.out.println("ReadMessage (FN_ID: 5)");
    System.out.println("DeleteMessage (FN_ID: 6)");
    exit(0);
}


            String[] arg = new String[args.length - 3];
            for (int i = 3; i < args.length; i++) {
                arg[i - 3] = args[i];
            }

            // Converting additional arguments array to a string for display



            // connect to the RMI registry

            Registry rmiRegistry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));

            if(Objects.equals(FN_ID, "1")) {

            if(arg.length==0)
            {
                System.out.println("Enter a valid name" );
                System.out.println("Tip:java client <ip> <port number> 1 <username>" );
                exit(0);
            }

                AccountManagerInt stub = (AccountManagerInt) rmiRegistry.lookup("Create");
                // get reference for remote object

             if(stub.CreateAccount(arg[0]) )
                {
                    System.out.println("User Created :"+stub.getAuth());
                }
             else
             {
                 System.out.println("Username Already Exists !\nNote:Accepted names must contain only  alphanumeric characters and hyphen '-' !");
             }
            }


            else if (Objects.equals(FN_ID, "2"))
            {
                if(arg.length==0)
                {
                    System.out.println("Empty authToken" );
                    System.out.println("Tip:java client <ip> <port number> 2 <authToken>");
                    exit(0);
                }

                System.out.println("Showing Accounts...");

                ShowAccountsInt stub = (ShowAccountsInt) rmiRegistry.lookup("Show");
                 HashMap b=stub.show();
                 if(!b.containsKey(Integer.parseInt(arg[0])))
                {
                    System.out.println("Wrong authToken ");
                    exit(0);
                }
                int i=0;
                for (Object value : b.values())
                {
                    System.out.println(i+"."+value);
                    i++;
                }
            }


            else if (Objects.equals(FN_ID, "3"))
            {
                if(arg.length<2)
                {
                    System.out.println("Empty authToken or recipient" );
                    System.out.println("Tip:java client <ip> <port number> 3 <authToken> <recipient> <message_body>");
                    exit(0);
                }
                System.out.println("Sending Message...");
                if(arg.length==2)
                {
                    System.out.println("Enter Message --> \"message\"!");
                    exit(0);
                }
                SendMessageInt stub = (SendMessageInt)  rmiRegistry.lookup("Send");
               if( stub.sendMessage(Integer.parseInt(arg[0]),arg[1],arg[2]))
                {
                    System.out.println("Message Send");
                }
               else
               {
                   System.out.println("Wrong info!Check Sender and Receiver!");
               }


            }
            else if (Objects.equals(FN_ID, "4"))
            {

                System.out.println("Showing Inbox...");
                if(arg.length==0)
                {
                    System.out.println("Empty  authToken");
                    System.out.println("Tip:java client <ip> <port number> 4 <authToken>");

                    exit(0);
                }

                ShowInboxInt stub = (ShowInboxInt)  rmiRegistry.lookup("Inbox");
                if(stub.show()==null )
                {
                    System.out.println("Users have not been initialized");
                    exit(0);
                }

                if(stub.show().get(Integer.parseInt(arg[0]))==null )
                {
                    System.out.println("Wrong User!");
                    exit(0);
                }
                List<Message> a = stub.show().get(Integer.parseInt(arg[0]));

                for(Message b: a)
                  {

                      String read="";
                      if(!b.isRead()) read="*";
                      System.out.println(b.getMessage_ID()+".  from:"+b.getSender()+read);
                  }

            }
            else if (Objects.equals(FN_ID, "5"))
            {
                if(arg.length<2)
                {
                    System.out.println("Empty authToken or message_id" );
                    System.out.println("Tip:java client <ip> <port number> 5 <authToken> <message_id>");

                    exit(0);
                }
                System.out.println("Showing Account Message...");
                ReadMessageInt stub = (ReadMessageInt)  rmiRegistry.lookup("Open");
                if(stub.showMessage(Integer.parseInt(arg[0]),Integer.parseInt(arg[1]))==null)
                {
                    System.out.println("Message "+arg[1]+" does not exist");
                }
                else
                {
                    System.out.println(stub.showMessage(Integer.parseInt(arg[0]),Integer.parseInt(arg[1])));
                }
            }
            else if (Objects.equals(FN_ID, "6"))
            {
                if(arg.length<2)
                {
                    System.out.println("Empty authToken or message_id" );
                    System.out.println("Tip:java client <ip> <port number> 6 <authToken> <message_id>");
                    exit(0);
                }
                System.out.println("Deleting Account's Message...");
               DeleteMessageInt stub = (DeleteMessageInt)  rmiRegistry.lookup("Delete");
              System.out.println(stub.Delete_Message(Integer.parseInt(arg[0]),Integer.parseInt(arg[1])));
            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Manual:");
            System.out.println("Create Account (FN_ID: 1)--> java client <ip> <port number> 1 <username> ");
            System.out.println("Show Accounts (FN_ID: 2)-->java client <ip> <port number> 2 <authToken>");
            System.out.println("Send Message (FN_ID: 3)-->java client <ip> <port number> 3 <authToken> <recipient> <message_body>");
            System.out.println("Show Inbox (FN_ID: 4)-->java client <ip> <port number> 4 <authToken>");
            System.out.println("ReadMessage (FN_ID: 5)-->java client <ip> <port number> 5 <authToken> <message_id>");
            System.out.println("DeleteMessage (FN_ID: 6)-->java client <ip> <port number> 6 <authToken> <message_id>");
            System.out.println("Notes:");
            System.out.println("ip is String  etc localhost ,127.0.0.1 ...");
            System.out.println("port number is Integer");
            System.out.println("username is String");
            System.out.println("authToken is Integer");
            System.out.println("recipient is String");
            System.out.println("message body is String ,use \"text\" ");
            System.out.println("message_id is Integer");
        }
    }
}