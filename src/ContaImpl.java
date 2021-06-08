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
	
	public void creditarValor(double valor) throws RemoteException {
		saldo = saldo + valor;

		exibirDados("Crédito", valor);
	}

	public void debitarValor(double valor) throws RemoteException {
		if (valor > saldo) {
			System.out.println("Saldo insuficiente!");
		}
		else {
			saldo -= valor;

			exibirDados("Débito", valor);
		}
	}

	public void aplicarCorrecao(double indice) throws RemoteException {
		saldo = saldo + (saldo * (indice / 100));
		
		exibirDados2("Correção");
	}

	public void transferirValor(double valor, Conta c) throws RemoteException {
		this.debitarValor(valor);
		c.creditarValor(valor);
		
		exibirDados("Transferência", valor);
	}

	public void encerrarConta() throws RemoteException, MalformedURLException, NotBoundException {
		exibirDados2("Encerramento");
		Naming.unbind("Número da conta: " + numero);
	}

	public int obterNumero() {
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
	
	public void exibirDados2(String operacao) throws RemoteException {
		System.out.println(operacao);
		System.out.println("Número da conta: " + numero);
		System.out.println("Titular: " + obterTitular());
		System.out.println("Saldo: " + obterSaldo());		
	}
	
	private void exibirDados(String operacao, double valor) throws RemoteException {
		System.out.println(operacao + " de: R$" + valor + System.getProperty("line.separator"));
		System.out.println("Número da conta: " + numero);
		System.out.println("Titular: " + obterTitular());
		System.out.println("Saldo: " + obterSaldo());		
	}
}
