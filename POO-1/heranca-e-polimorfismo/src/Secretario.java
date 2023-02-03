public class Secretario extends Funcionario {
    public Secretario(String nome, String cpf, double salario, Agenda agenda) {
        super(nome, cpf, salario, agenda);
    }

    public void listarAgenda(Funcionario funcionario) {
        System.out.println("Funcionário " + funcionario.getNome() + " -> " + funcionario.getAgenda().toString());
    }
}
