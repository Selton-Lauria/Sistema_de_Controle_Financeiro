package Arquivos.Java;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean menu = true;
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
            String opcao = sc.next().trim();
            switch (opcao) {
                case "1":
                    System.out.println("primeiro escolhido");
                    break;
                case "2":
                    System.out.println("Segundo escolhido");
                    break;
                case "3":
                    System.out.println("Terceiro escolhido");
                    break;
                case "4":
                    System.out.println("Quarto escolhido");
                    break;
                case "5":
                    System.out.println("Quinto escolhido");
                    break;
                case "6":
                    System.out.println("sexto escolhido");
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
    }
}
