package Arquivos.Java;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean menu = true;
        ControleFinanceiro cf = new ControleFinanceiro();
        do {
            System.out.println("\n----------MENU----------");
            System.out.println("O que você deseja fazer? ");
            System.out.println("1 - Cadastrar lançamento");
            System.out.println("2 - Listar lançamentos");
            System.out.println("3 - Consultar por identificador");
            System.out.println("4 - Alterar Lançamento");
            System.out.println("5 - Excluir lançamento");
            System.out.println("6 - Filtrar por tipo");
            System.out.println("7 - Consultar por período");
            System.out.println("8 - Exibir resumo financeiro");
            System.out.println("0 - Sair");
            String opcao = sc.nextLine().trim();
            switch (opcao) {
                case "1":
                    System.out.println("Cadastro de Lançamentos...");
                    cf.cadastrarLancamento();
                    break;
                case "2":
                    System.out.println("Listando lançamentos...");
                    cf.listarLancamentos();
                    break;
                case "3":
                    System.out.println("Digite o ID do lançamento que deseja consultar: ");
                    int verId = sc.nextInt();
                    sc.nextLine();
                    cf.consultarPorId(verId);
                    break;
                case "4":
                    System.out.println("Digite o identificador que deseja alterar: ");
                    int alterar = sc.nextInt();
                    sc.nextLine();
                    cf.alterarLance(alterar);
                    break;
                case "5":
                    System.out.println("Digite o Identificador do lançamento que deseja excluir: ");
                    int excluir = sc.nextInt();
                    sc.nextLine();
                    cf.excluirLance(excluir);
                    break;
                case "6":
                    System.out.println("Por qual tipo você deseja filtrar? [1] Receita / [2] Despesa");
                    String tipo = sc.nextLine().trim();
                    if (tipo != "1" && tipo != "2" ) {
                        System.out.println("Digite um número correspondente!");
                    } else {
                        cf.filtrarPorTipo(tipo);
                    }
                    break;
                case "7":
                    System.out.println("Sétimo escolhido");
                    break;
                case "8":
                    System.out.println("Oitavo escolhido");
                    break;
                case "0":
                    System.out.println("Fim do programa...");
                    menu = false;
                    break;
                default:
                    System.out.println("Escreva um número correspondente por favor!!");
                    break;
            }
        } while (menu == true);
        sc.close();
    }
}
