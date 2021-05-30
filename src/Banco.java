import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Banco extends Remote {
	public Conta abrirConta(double saldo, String titular) throws RemoteException, MalformedURLException;
}
