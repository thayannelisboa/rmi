import java.rmi.NotBoundException;
import java.rmi.Naming;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClienteImpl {
	public static void main(String[] args) throws NotBoundException, NumberFormatException, IOException {
		Banco b = (Banco) Naming.lookup("//localhost/Nubank");

		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

		// pede o nome do cliente
		System.out.println("Informe o nome do cliente: ");
		String nome_cliente = teclado.readLine();

		System.out.println("Informe o saldo inicial: ");
		double saldo = Double.parseDouble(teclado.readLine());

		Conta c = b.abrirConta(saldo, nome_cliente);
		System.out.println("Titular:" + c.obterTitular());
		System.out.println("Saldo:" + c.obterSaldo());
	}
}
