package Principal;

import RH.Funcionario;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author gabrielCosta
 */
public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18),new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12),new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 02),new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988,10,14),new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995,1,5),new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999,11,19),new BigDecimal("1282.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993,3,31),new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994,7,8),new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003,5,24),new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996,9,2),new BigDecimal("2799.93"), "Gerente"));

        //Imprimir lista de funcionarios
        System.out.println("Todos funcionários:");
        funcionarios.forEach(System.out::println);

        //Remover funcionario que o nome seja "João"
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
        System.out.println("\nTodos funcionários depois da remoção:");
        funcionarios.forEach(System.out::println);

        //Promocao de 10% para os funcionarios
        funcionarios.forEach(funcionario -> funcionario.aumentarSalario(new BigDecimal("0.10")));
        System.out.println("\nTodos funcionários depois da promoção de 10%:");
        funcionarios.forEach(System.out::println);

        //Agrupamento de funcionasrios por funcoes
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
        System.out.println("\nAgrupamento por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println(funcao + ":");
            lista.forEach(System.out::println);
        });

        //Funcionarios com aniversario no mes 10 e 12
        System.out.println("\nFuncionários com aniversário em outubro e dezembro:");
        DateTimeFormatter mesFormatter = DateTimeFormatter.ofPattern("MM");
        funcionarios.stream().filter(funcionario -> {
                    String mesNascimento = funcionario.getDataNascimento().format(mesFormatter);
                    return mesNascimento.equals("10") || mesNascimento.equals("12");
                }).forEach(System.out::println);

        //Funcionario com maior idade
        System.out.println("\nFuncionário com maior idade:");
        Optional<Funcionario> funcionarioMaisVelho = funcionarios.stream().max(Comparator.comparing(funcionario -> funcionario.getDataNascimento()));
        funcionarioMaisVelho.ifPresent(funcionario -> {
            int idade = LocalDate.now().getYear() - funcionario.getDataNascimento().getYear();
            System.out.printf("Nome: %s, Idade: %d%n", funcionario.getNome(), idade);
        });

        //Funcionarios em ordem alfabetica
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome)).forEach(System.out::println);

        //Total de salario dos funcionarios
        BigDecimal totalSalarios = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("\nSoma total de salário: %.2f%n", totalSalarios);

        //Quantidade de salario minimos ganho por cada funcionario
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nSalários mínimos ganhos por funcionário:");
        funcionarios.forEach(funcionario -> {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
            System.out.printf("Funcionário: %s, Salários Mínimos: %.2f%n", funcionario.getNome(), salariosMinimos);
        });
    }
}
