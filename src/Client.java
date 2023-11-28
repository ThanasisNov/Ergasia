import Accounts.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

import static java.lang.System.exit;
// To change the 2 input parameters (double numbers) in Intellij go to: Edit Configurations -> Client -> Built and Run

public class Client {
    public static void main(String args[]) {
        try {
            String ip =  args[0];;
            String  port = args[1];;
            String FN_ID =  args[2];

            String[] arg = new String[args.length - 3];
            for (int i = 3; i < args.length; i++) {
                arg[i - 3] = args[i];
            }

            // Converting additional arguments array to a string for display

          //  System.out.println(ip+" "+port +" "+FN_ID+"-->"+ Arrays.toString(arg));

            // connect to the RMI registry

            Registry rmiRegistry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));

            if(Objects.equals(FN_ID, "1")) {


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
                System.out.println("Showing Accounts...");
                ShowAccountsInt stub = (ShowAccountsInt) rmiRegistry.lookup("Show");
                 HashMap b=stub.show();
                int i=0;
                for (Object value : b.values())
                {
                    System.out.println(i+"."+value);
                    i++;
                }
            }


            else if (Objects.equals(FN_ID, "3"))
            {
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
                    System.out.println("EMPTY AUTHTOKEN!");
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
                System.out.println("Deleting Accounts.Message...");
               DeleteMessageInt stub = (DeleteMessageInt)  rmiRegistry.lookup("Delete");
              System.out.println(stub.Delete_Message(Integer.parseInt(arg[0]),Integer.parseInt(arg[1])));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}