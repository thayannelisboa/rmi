import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

public class ContaImpl extends UnicastRemoteObject implements Conta {
	private int numero;
	private double saldo;
	private String titular;

	public ContaImpl(double s, String t, int numero) throws RemoteException {
		super();
		saldo = s;
		setTitular(t);
		setNumero(numero);
	}

	public double obterSaldo() throws RemoteException {
		return saldo;
	}

	public String obterTitular() throws RemoteException {
		return titular;
	}

	// retornar mensagem a cada operação
	public void creditarValor(double valor) throws RemoteException {
		saldo = saldo + valor;

		System.out.println("Número da conta: " + numero);
		System.out.println("Titular: " + obterTitular());
		System.out.println("Saldo: " + obterSaldo());
		System.out.println("Crédito de: " + valor);
	}

	public void debitarValor(double valor) throws RemoteException {
		if (valor > saldo) {
			System.out.println("Saldo insuficiente!");
		}
		else {
			saldo = saldo - valor;
			System.out.println("Número da conta: " + numero);
			System.out.println("Titular: " + obterTitular());
			System.out.println("Saldo: " + obterSaldo());
			System.out.println("Débito de: " + valor);
		}
	}

	public void aplicarCorrecao(double indice) throws RemoteException {
		saldo = saldo + (saldo * (indice / 100));
	}

	public void transferirValor(double valor, Conta c) throws RemoteException {
		this.debitarValor(valor);
		c.creditarValor(valor);
	}

	public void encerrarConta() throws RemoteException, MalformedURLException, NotBoundException {
		Naming.unbind("Número da conta: " + numero);
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}
}
