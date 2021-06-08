import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.io.BufferedReader;
import java.io.IOException;

public class CaixaImpl {
	public static void main(String[] args) throws NotBoundException, NumberFormatException, IOException {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

		do {
			System.out.println("Informe o número da conta: ");
			String conta = teclado.readLine();
			
			if (conta.equals("fim")) {
				break;
			}

			Conta c = (Conta) Naming.lookup("//localhost/Conta" + conta);

			System.out.println("Informe a operação: ");
			System.out.println("1 - Obter saldo");
			System.out.println("2 - Obter titular");
			System.out.println("3 - Creditar valor");
			System.out.println("4 - Debitar valor");
			System.out.println("5 - Aplicar correção");
			System.out.println("6 - Transferir valor");
			System.out.println("7 - Encerrar conta");						
			
			String operacao = teclado.readLine();			
			double valor = 0;
			
			if (operacao.equals("1")) {
				c.exibirDados2("Saldo");
			}
			else if (operacao.equals("2")) {
				c.exibirDados2("Titular");
			}
			else if (operacao.equals("3")) {
				System.out.println("Informe o valor: ");
				
				valor = Double.parseDouble(teclado.readLine());
				
				c.creditarValor(valor);
			}
			else if (operacao.equals("4")) {
				System.out.println("Informe o valor: ");
				
				valor = Double.parseDouble(teclado.readLine());
				
				c.debitarValor(valor);
			}
			else if (operacao.equals("5")) {
				System.out.println("Informe o índice: ");
				
				valor = Double.parseDouble(teclado.readLine());
				
				c.aplicarCorrecao(valor);
			}
			else if (operacao.equals("6")) {
				System.out.println("Informe o valor: ");
				
				valor = Double.parseDouble(teclado.readLine());
				
				System.out.println("Informe o número da conta: ");
				
				String conta2 = teclado.readLine();
				
				Conta c2 = (Conta) Naming.lookup("//localhost/Conta" + conta2);
				
				c.transferirValor(valor, c2);
			}
			else if (operacao.equals("7")) {
				c.encerrarConta();
			}
			else {
				System.out.println("Operação inválida.");
				System.out.println("Tente novamente..." + System.getProperty("line.separator"));
			}			
		}
		while(true);
	}
}
