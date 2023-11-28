import Accounts.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String args[]) {
        int port = Integer.parseInt(args[0]);
        try {
            Registry rmiRegistry = LocateRegistry.createRegistry(port);
            System.out.println("RMI registry ready on port " + port);

            AccountManager stub = new AccountManager();
            rmiRegistry.rebind("Create", stub);//creating account

            ShowAccounts stub1 = new ShowAccounts(stub.getAccounts());
            rmiRegistry.rebind("Show", stub1);//showing account

            SendMessage stub2 = new SendMessage(stub.getAccounts(),stub.getMessageBox());//
            rmiRegistry.rebind("Send", stub2);//sending Message*/

            ShowInbox stub3 = new ShowInbox(stub2.getMessagesb1());//
            rmiRegistry.rebind("Inbox", stub3);//showing Messages*/

            ReadMessage stub4 = new ReadMessage(stub3.show());//
            rmiRegistry.rebind("Open", stub4);//open Messages*/


            DeleteMessage stub5 = new DeleteMessage(stub3.show());//
            rmiRegistry.rebind("Delete", stub5);//Delete Messages*/

            System.out.println("Server is ready");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}