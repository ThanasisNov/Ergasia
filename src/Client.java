import Accounts.AccountManagerInt;
import Accounts.ShowAccounts;
import Accounts.ShowAccountsInt;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
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
                System.out.println("Showing Accounts...");
            }
            else if (Objects.equals(FN_ID, "4"))
            {
                System.out.println("Showing Inbox...");
            }
            else if (Objects.equals(FN_ID, "5"))
            {
                System.out.println("Showing Message...");
            }
            else if (Objects.equals(FN_ID, "6"))
            {
                System.out.println("Deleting Message...");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}