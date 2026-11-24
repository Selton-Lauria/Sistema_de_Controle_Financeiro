package Arquivos.Java;

import java.util.Scanner;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Lancamentos {
    int identificador;
    String descrição;
    BigDecimal valor;
    LocalDate data;
    TipoLancamento tipoLancamento;
    Categoria categoria;
    Scanner sc = new Scanner(System.in);

    public Lancamentos(int identificador, String descrição, BigDecimal valor, LocalDate data) {
        this.identificador = identificador;
        this.descrição = descrição;
        this.valor = valor;
        this.data = data;
        this.tipoLancamento = qtipo();
        this.categoria = qcate();
    }

    public TipoLancamento qtipo() {
        boolean RD = true;
        TipoLancamento tipo = null;
        do {
            System.out.println("Qual tipo de lançamento você deseja");
            System.out.println("[1] Receita / [2] Despesa");
            int opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Seu tipo de lançamento é Receita!!\n");
                    tipo = TipoLancamento.RECEITA;
                    RD = false;
                    break;
                case 2:
                    System.out.println("Seu tipo de lançamento é Despesa!!\n");
                    tipo = TipoLancamento.DESPESA;
                    RD = false;
                    break;
                default:
                    System.out.println("Digite um número correspondente!!");
                    break;
            }
        } while (RD == true);
        return tipo;
    }

    public Categoria qcate() {
        boolean CT = true;
        Categoria cate = null;
        do {
            System.out.println("Qual Categoria é seu lançamento? ");
            for (Categoria c : Categoria.values()) {
                System.out.println("[" + c.ordinal() + "] " + c);
            }
            int opcao = sc.nextInt();
            switch (opcao) {
                case 0:
                    System.out.println("Sua Categoria é Alimentação!!");
                    cate = Categoria.ALIMENTACAO;
                    CT = false;
                    break;
                case 1:
                    System.out.println("Sua Categoria é Transporte!!");
                    cate = Categoria.TRANSPORTE;
                    CT = false;
                    break;
                case 2:
                    System.out.println("Sua Categoria é Moradia!!");
                    cate = Categoria.MORADIA;
                    CT = false;
                    break;
                case 3:
                    System.out.println("Sua Categoria é Saúde!!");
                    cate = Categoria.SAUDE;
                    CT = false;
                    break;
                case 4:
                    System.out.println("Sua Categoria é Educação!!");
                    cate = Categoria.EDUCACAO;
                    CT = false;
                    break;
                case 5:
                    System.out.println("Sua Categoria é Lazer!!");
                    cate = Categoria.LAZER;
                    CT = false;
                    break;
                case 6:
                    System.out.println("Sua Categoria é Salário!!");
                    cate = Categoria.SALARIO;
                    CT = false;
                    break;
                case 7:
                    System.out.println("Sua Categoria é Outra!!");
                    cate = Categoria.OUTROS;
                    CT = false;
                    break;
                default:
                    System.out.println("Digite um número correspondente!!");
                    break;
            }
        } while (CT == true);
        return cate;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
