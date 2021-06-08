import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.rmi.Naming;

public class BancoImpl extends UnicastRemoteObject implements Banco {
	private int ultimaConta;

	public BancoImpl() throws RemoteException {
		super();
		ultimaConta = 1;
	}

	public Conta abrirConta(double saldo, String titular) throws RemoteException, MalformedURLException {
		Conta c = new ContaImpl(saldo, titular, ultimaConta);
		
		Naming.rebind("Conta" + ultimaConta, c); // registra conta
		System.out.println("Conta " + ultimaConta + " criada com sucesso!");
		System.out.println("Titular " + c.obterTitular());
		System.out.println("Saldo " + c.obterSaldo());

		ultimaConta++;

		return c;
	}

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		Banco b = new BancoImpl();
		Naming.rebind("Nubank", b); // registra o banco
		System.out.println("O Banco está ativo!");
	}
}
