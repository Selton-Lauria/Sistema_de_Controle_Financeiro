package Arquivos.Java;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControleFinanceiro {
    private List<Lancamentos> lancamento = new ArrayList<>();
    private int excluidos;
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner sc = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);

    public void cadastrarLancamento() {
        System.out.println("Qual a descrição do seu lançamento? ");
        String desc = sc.nextLine();
        if (desc.isBlank()) {
            System.out.println("descrição não pode ser vazia...");
        } else {
            System.out.println("Qual o valor do seu lançamento? ");
            BigDecimal valor = sc.nextBigDecimal();
            if (valor.intValue() <= 0) {
                System.out.println("O valor não pode ser igual ou menor a zero...");
            } else {
                sc.nextLine();
                LocalDate data = verificarData();
                int id = indentificarId();
                Lancamentos lance = new Lancamentos(id, desc, valor, data);
                this.lancamento.add(lance);
                System.out.println("Cadastro feito!!!");
            }
        }
    }

    public void listarLancamentos() {
        if (this.lancamento.isEmpty()) {
            System.out.println("Sua lista está vazia...");
        } else {
            for (Lancamentos numero : this.lancamento) {
                System.out.println(numero.identificador +"° " + numero.descrição);
            }
        }
    }

    public void consultarPorId(int verId) {
        int cont = 0;
        for (Lancamentos numero : this.lancamento) {
            if (verId == numero.identificador) {
                System.out.println("O lançamento com id " + verId + " tem: ");
                System.out.println("Descrição: " + numero.descrição);
                System.out.println("Valor: R$" + numero.valor);
                System.out.println("Data: " + numero.data);
                System.out.println("Tipo do lançamento: " + numero.tipoLancamento);
                System.out.println("Categoria do lançamento: " + numero.categoria);
            } else {
                cont += 1;
            }
        }
        if (cont > lancamento.size()) {
            System.out.println("Não existe um lançamento com esse identificador...");
        }
    }

    public void alterarLance(int alterar) {
        int cont = 0;
        for (Lancamentos numero : this.lancamento) {
            if (alterar == numero.identificador) {
                int index = this.lancamento.indexOf(numero);
                System.out.println("Lançamento alterado!!!");
                break;
            } else {
                cont += 1;
            }
        }
        if (cont > lancamento.size()) {
            System.out.println("Não existe um lançamento com esse identificador...");
        }
    }

    public void excluirLance(int excluir) {
        int cont = 0;
        for (Lancamentos numero : this.lancamento) {
            if (excluir == numero.identificador) {
                int index = this.lancamento.indexOf(numero);
                this.lancamento.remove(index);
                cont = 0;
                this.excluidos += 1;
                System.out.println("Lançamento excluído!!!");
                break;
            } else {
                cont += 1;
            }
        }
        if (cont > lancamento.size()) {
            System.out.println("Não existe um lançamento com esse identificador...");
        }
    }

    public void filtrarPorTipo(String tipo) {
        if (tipo == "1") {
            for (Lancamentos numero : this.lancamento) {
                if (numero.tipoLancamento)
                System.out.println("Os lançamentos que são Receita: ");

            }
        }
    }

    public LocalDate verificarData() {
        LocalDate data = null;
        boolean dataValida = false;
        while (!dataValida) {
            System.out.print("Digite a data no formato dd/MM/yyyy: ");
            String entrada = sc2.nextLine().trim();
            try {
                data = LocalDate.parse(entrada, formatador);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido! Tente novamente.");
            }
        }
        return data;
    }

    public int indentificarId() {
        int quant = 1;
        for (Lancamentos numero : this.lancamento) {
            quant += 1;
        }
        return (quant + this.excluidos);
    }
}
