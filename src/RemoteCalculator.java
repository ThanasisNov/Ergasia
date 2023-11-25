import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteCalculator extends UnicastRemoteObject implements CalculatorInt {
    public RemoteCalculator() throws RemoteException {
        super();
    }

    public double add(double a, double b) throws RemoteException {

        return a + b;
    }

    public double sub(double a, double b) throws RemoteException {
        return a - b;
    }

    public double mul(double a, double b) throws RemoteException {
        return a * b;
    }

    public double div(double a, double b) throws RemoteException {
        return a / b;
    }
}