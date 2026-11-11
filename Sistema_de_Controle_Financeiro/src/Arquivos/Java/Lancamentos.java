package Arquivos.Java;

import java.util.Scanner;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Lancamentos {
    private int identificador;
    private String descrição;
    private BigDecimal valor;
    private LocalDate data;
    private TipoLancamento tipoLancamento;
    private Categoria categoria;

    public Lancamentos(int identificador, String descrição, BigDecimal valor, LocalDate data) {
        this.identificador = identificador;
        this.descrição = descrição;
        this.valor = valor;
        this.data = data;
        this.categoria = qcate();
        this.tipoLancamento = qtipo();
    }

    public TipoLancamento qtipo() {
        Scanner sc = new Scanner(System.in);
        boolean RD = true;
        TipoLancamento tipo = null;
        do {
            System.out.println("Qual tipo de lançamento você deseja");
            System.out.println("[1] Receita / [2] Despesa");
            String opcao = sc.next();
            switch (opcao) {
                case "1":
                    System.out.println("Seu tipo de lançamento é Receita!!");
                    tipo = TipoLancamento.RECEITA;
                    RD = false;
                    break;
                case "2":
                    System.out.println("Seu tipo de lançamento é Despesa!!");
                    tipo = TipoLancamento.DESPESA;
                    RD = false;
                    break;
                default:
                    System.out.println("Digite um número correspondente!!");
                    break;
            }
        } while (RD == true);
        sc.close();
        return tipo;
    }

    public Categoria qcate() {
        Scanner sc = new Scanner(System.in);
        boolean CT = true;
        Categoria cate = null;
        do {
            System.out.println("Qual Categoria é seu lançamento? ");
            System.out.println("[1] Receita / [2] Despesa");
            String opcao = sc.next();
            switch (opcao) {
                case "1":
                    System.out.println("Seu tipo de lançamento é Receita!!");
                    cate = Categoria.;
                    CT = false;
                    break;
                case "2":
                    System.out.println("Seu tipo de lançamento é Despesa!!");
                    cate = TipoLancamento.;
                    CT = false;
                    break;
                default:
                    System.out.println("Digite um número correspondente!!");
                    break;
            }
        } while (CT == true);
        sc.close();
        return cate;
    }
}
