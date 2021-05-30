import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.Naming;
import java.io.BufferedReader;
import java.io.IOException;

public class CaixaImpl {

	public static void main(String[] args) throws NotBoundException, NumberFormatException, IOException {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Informe o número da conta: ");
		String conta = teclado.readLine();

		Conta c = (Conta) Naming.lookup("//localhost/Conta" + conta);

		System.out.println("Titular:" + c.obterTitular());
		System.out.println("Saldo:" + c.obterSaldo());

		System.out.println("Exemplo da operação de creditar valor: ");

		System.out.println("Informe o valor: ");
		double valor = Double.parseDouble(teclado.readLine());
		c.creditarValor(valor);

		System.out.println("Exemplo de operação de debitar valor: ");

		System.out.println("Informe o valor: ");
		valor = Double.parseDouble(teclado.readLine());
		c.debitarValor(valor);
	}
}
