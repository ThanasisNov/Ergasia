import Accounts.AccountManager;
import Accounts.ShowAccounts;

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
            int i=0;
            rmiRegistry.rebind("Create", stub);//creating account

            ShowAccounts stub1 = new ShowAccounts(stub.getAccounts());
            rmiRegistry.rebind("Show", stub1);//showing account



            System.out.println("Server is ready");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}