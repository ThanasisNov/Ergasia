package Accounts;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Random;

public class AccountManager  extends UnicastRemoteObject implements AccountManagerInt {


    private  int randomNum;
HashMap<Integer,String> Accounts= new HashMap<>();
    public AccountManager() throws RemoteException {
    }

    @Override
    public boolean CreateAccount(String a) throws RemoteException {
        String regex = "^[a-zA-Z0-9-]+$";

       System.out.println(a.matches(regex));
       System.out.println("What?");
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
        return true;
    }

    @Override
    public int getAuth() throws RemoteException {
        /*int i=0;
        for (String value : Accounts.values())
        {
            System.out.println(i+"."+value);
            i++;
        }*/
        return randomNum;

    }

    @Override
    public void setAuth(Integer a) throws RemoteException
    {
    randomNum=a;
    }

    @Override
    public HashMap getAccounts() throws RemoteException {
        return Accounts;
    }


}



