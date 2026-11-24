package Arquivos.Java;

import javax.swing.plaf.synth.SynthOptionPaneUI;
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
    private BigDecimal saldo;
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
            sc.nextLine();
            if (valor.intValue() <= 0) {
                System.out.println("O valor não pode ser igual ou menor a zero...");
            } else {
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
                cont = 0;
            } else {
                cont += 1;
            }
        }
        if (cont >= lancamento.size()) {
            System.out.println("Não existe um lançamento com esse identificador...");
        }
    }

    public void alterarLance(int alterar) {
        int cont = 0;
        for (Lancamentos numero : this.lancamento) {
            if (alterar == numero.identificador) {
                cont = 0;
                System.out.println("Digite a nova descrição: ");
                String desc = sc.nextLine();
                if (desc.isBlank()) {
                    System.out.println("A descrição não pode ser vazia...");
                } else {
                    System.out.println("Digite o novo valor: ");
                    BigDecimal valor = sc.nextBigDecimal();
                    sc.nextLine();
                    if (valor.intValue() <= 0 ) {
                        System.out.println("Valor inválido! Não pode ser menor ou igual a zero");
                    }
                    numero.setDescrição(desc);
                    numero.setValor(valor);
                    System.out.println("Digite a nova data: ");
                    numero.setData(verificarData());
                    numero.qtipo();
                    numero.qcate();
                    System.out.println("Lançamento alterado!!!");
                    break;
                }
            } else {
                cont += 1;
            }
        }
        if (cont >= lancamento.size()) {
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
        if (cont >= lancamento.size()) {
            System.out.println("Não existe um lançamento com esse identificador...");
        }
    }

    public void filtrarPorTipo(int tipo) {
        if (tipo == 1) {
            System.out.println("\nOs lançamentos que são Receitas: ");
            for (Lancamentos numero : this.lancamento) {
                if (numero.tipoLancamento == TipoLancamento.RECEITA) {
                    System.out.println("\nIdentificador: " + numero.identificador);
                    System.out.println("Descrição: " + numero.descrição);
                    System.out.println("Valor: R$" + numero.valor);

                    // System.out.println(numero.identificador + " " + numero.descrição);
                }
            }
        } else if (tipo == 2) {
            System.out.println("\nOs lançamentos que são Despesas: ");
            for (Lancamentos numero : this.lancamento) {
                if (numero.tipoLancamento == TipoLancamento.DESPESA) {
                    System.out.println("\nIdentificador: " + numero.identificador);
                    System.out.println("Descrição: " + numero.descrição);
                    System.out.println("Valor: R$" + numero.valor);

                    // System.out.println(numero.identificador + " " + numero.descrição);
                }
            }
        }
    }

    public void consultarPorPeríodo() {
        System.out.println("Digite a data Mínima");
        LocalDate dataMin = verificarData();
        System.out.println("Digite a data Máxima");
        LocalDate dataMax = verificarData();
        int cont = 0;
        if (dataMax.isBefore(dataMin)) {
            System.out.println("Data inválida! A data máxima antecede a data mínima!");
        } else {
            for (Lancamentos numero : this.lancamento) {
                if (numero.data.isAfter(dataMin) && numero.data.isBefore(dataMax)) {
                    System.out.println("Identificador: " + numero.identificador);
                    System.out.println("Descrição: " + numero.descrição);
                    System.out.println("Valor: R$" + numero.valor);
                    System.out.println("Data: " + numero.data);
                    cont = 0;
                } else {
                    cont += 1;
                }
            }
            if (cont >= this.lancamento.size()) {
                System.out.println("Não existe nenhuma data dentro desse período...");
            }
        }
    }

    public void consultarSaldo() {
        calcularSaldo();
        System.out.println("O saldo atual é: " + this.saldo);
    }

    public void calcularSaldo() {
        BigDecimal receita = BigDecimal.ZERO;
        BigDecimal despesa = BigDecimal.ZERO;
        for (Lancamentos numero : this.lancamento) {
            if (numero.tipoLancamento == TipoLancamento.RECEITA) {
                receita = receita.add(numero.valor);
            } else {
                despesa = despesa.add(numero.valor);
            }
        }
        System.out.println("O total da receita é: " + receita);
        System.out.println("O total de despesa é: " + despesa);
        saldo = receita.subtract(despesa);
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
