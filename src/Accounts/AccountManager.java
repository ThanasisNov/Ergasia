package Accounts;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class AccountManager  extends UnicastRemoteObject implements AccountManagerInt {


    private  int randomNum;

     HashMap<Integer,String> Accounts= new HashMap<>();
     HashMap<Integer, List<Message>> MessageBox= new HashMap<>();
    public AccountManager() throws RemoteException {
    }

    @Override
    public boolean CreateAccount(String a) throws RemoteException {
        String regex = "^[a-zA-Z0-9-]+$";



        if (Accounts.containsValue(a) || !a.matches(regex)) {
            System.out.println("Failed to create user");
            return false;
        }
        Random random = new Random();


        do {
            randomNum = random.nextInt(9999 + 1);
        } while ( Accounts.containsKey(randomNum));
        setAuth(randomNum);
        System.out.println("User Created");
        Accounts.put(randomNum,a);
        List<Message> b= new ArrayList<>();
        MessageBox.put(randomNum,b);
        return true;
    }

    @Override
    public int getAuth() throws RemoteException {

        return randomNum;

    }

    @Override
    public void setAuth(Integer a) throws RemoteException
    {
    randomNum=a;
    }

    @Override
    public HashMap<Integer,String> getAccounts() throws RemoteException {
        return Accounts;
    }

    public HashMap<Integer,List<Message>> getMessageBox() throws RemoteException {
        return MessageBox;
    }

}



