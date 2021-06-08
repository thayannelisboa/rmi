import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Conta extends Remote {
	public int obterNumero() throws RemoteException;
	
	public double obterSaldo() throws RemoteException;

	public String obterTitular() throws RemoteException;

	public void creditarValor(double valor) throws RemoteException;

	public void debitarValor(double valor) throws RemoteException;
	
	public void aplicarCorrecao(double indice) throws RemoteException;

	// apenas a outra conta, pois, ja estou em uma
	public void transferirValor(double valor, Conta c) throws RemoteException;

	public void encerrarConta() throws RemoteException, MalformedURLException, NotBoundException;

	public void exibirDados2(String string) throws RemoteException;
}
