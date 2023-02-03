public class Coordenador extends Funcionario {
    public Coordenador(String nome, String cpf, double salario, Agenda agenda) {
        super(nome, cpf, salario, agenda);
    }

    public void alocarProfessor(Funcionario professor) {
        System.out.println("Professor " + professor.getNome() + " alocado com sucesso!");
    }
}
