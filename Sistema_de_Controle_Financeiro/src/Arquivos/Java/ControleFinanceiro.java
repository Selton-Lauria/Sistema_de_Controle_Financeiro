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
    private BigDecimal saldo;
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner sc = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);

    // [1] Cadastro de Lançamentos
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

    // [2] Listagem de lançamentos
    public void listarLancamentos() {
        if (this.lancamento.isEmpty()) {
            System.out.println("Sua lista está vazia...");
        } else {
            System.out.println("-ID- | -Descrição-");
            for (Lancamentos numero : this.lancamento) {
                System.out.println(numero.identificador +" | " + numero.descrição);
            }
        }
    }

    // [3] Consulta por id
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

   // [4] Alteração de lançamento
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
                    if (valor.intValue() <= 0) {
                        System.out.println("Valor inválido! Não pode ser menor ou igual a zero");
                    } else {
                        numero.setDescrição(desc);
                        numero.setValor(valor);
                        System.out.println("Digite a nova data: ");
                        numero.setData(verificarData());
                        numero.qtipo();
                        numero.qcate();
                        System.out.println("Lançamento alterado!!!");
                        break;
                    }
                }
            } else {
                cont += 1;
            }
        }
        if (cont >= lancamento.size()) {
            System.out.println("Não existe um lançamento com esse identificador...");
        }
    }

    // [5] Exclusão de lançamento
    public void excluirLance(int excluir) {
        int cont = 0;
        for (Lancamentos numero : this.lancamento) {
            if (excluir == numero.identificador) {
                int index = this.lancamento.indexOf(numero);
                this.lancamento.remove(index);
                cont = -1;
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

    // [6] Filtragem por tipo de lançamento
    public void filtrarPorTipo(int tipo) {
        int cont = 0;
        if (tipo == 1) {
            System.out.println("\nOs lançamentos que são Receitas: ");
            for (Lancamentos numero : this.lancamento) {
                if (numero.tipoLancamento == TipoLancamento.RECEITA) {
                    System.out.println("\nIdentificador: " + numero.identificador);
                    System.out.println("Descrição: " + numero.descrição);
                    System.out.println("Valor: R$" + numero.valor);
                    cont = 0;
                } else {
                    cont += 1;
                }
            }
        } else if (tipo == 2) {
            System.out.println("\nOs lançamentos que são Despesas: ");
            for (Lancamentos numero : this.lancamento) {
                if (numero.tipoLancamento == TipoLancamento.DESPESA) {
                    System.out.println("\nIdentificador: " + numero.identificador);
                    System.out.println("Descrição: " + numero.descrição);
                    System.out.println("Valor: R$" + numero.valor);
                    cont = 0;
                } else {
                    cont += 1;
                }
            }
        }
        if (cont >= lancamento.size()) {
            System.out.println("Não há nenhum lançamento com esse tipo...");
        }
    }

    // [7] Consulta por período de lançamento
    public void consultarPorPeriodo() {
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
                    System.out.println("\nIdentificador: " + numero.identificador);
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

    // [8] Resumo Financeiro das receitas e despesas
    public void consultarSaldo() {
        calcularSaldo();
        if (!lancamento.isEmpty()) {
            System.out.println("O saldo atual é: R$" + this.saldo);
        }
    }

    // soma das receitas + soma das despesas: calculo do saldo
    public void calcularSaldo() {
        if (lancamento.isEmpty()) {
            System.out.println("Não há nehhum lançamento até o momento... Cadastre algum para ver seu resumo financeiro!");
        } else {
            BigDecimal receita = BigDecimal.ZERO;
            BigDecimal despesa = BigDecimal.ZERO;
            for (Lancamentos numero : this.lancamento) {
                if (numero.tipoLancamento == TipoLancamento.RECEITA) {
                    receita = receita.add(numero.valor);
                } else {
                    despesa = despesa.add(numero.valor);
                }
            }
            System.out.println("O total da receita é: R$" + receita);
            System.out.println("O total de despesa é: R$" + despesa);
            saldo = receita.subtract(despesa);
        }

    }

    // Verificação de data
    public LocalDate verificarData() {
        LocalDate data = null;
        boolean dataValida = false;
        while (!dataValida) {
            System.out.print("Digite a data no formato DD/MM/YYYY: ");
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

    // Identificação do Id
    public int indentificarId() {
        int quant = 1;
        for (Lancamentos numero : this.lancamento) {
            quant += 1;
        }
        return (quant + this.excluidos);
    }
}
