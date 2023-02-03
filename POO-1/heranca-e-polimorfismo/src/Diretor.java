import java.util.ArrayList;
import java.util.List;

public class Diretor extends Funcionario {
    private List<Professor> professoresOrientados = new ArrayList<>();

    public Diretor(String nome, String cpf, double salario, Agenda agenda) {
        super(nome, cpf, salario, agenda);
        this.professoresOrientados = new ArrayList<>();
    }

    public boolean orientarProfessor(Professor professor) {
        for (Professor prof : professoresOrientados) {
            if(professor.getCpf().equalsIgnoreCase(prof.getCpf())) {
                System.out.println("Erro: Não foi possível adicionar o professor " + professor.getNome() +
                                   " como orientado pois ele já está sendo orientado por esse diretor.");
                return false;
            }
        }
        professoresOrientados.add(professor);
        System.out.println("Professor " + professor.getNome() + " adicionado como orientado do diretor " +
                           this.getNome() + " com sucesso.");
        return true;
    }

    public String exibirProfessoresOrientados() {
        int i = 1;
        StringBuilder retorno = new StringBuilder();
        for (Professor professor : professoresOrientados) {
            retorno.append(i).append(". ").append(professor.getNome()).append("; \n");
            i++;
        }

        if (retorno.toString().equalsIgnoreCase("")) {
            return "Nenhum professor orientado pelo diretor.";
        }
        return "Lista de professores orientados pelo diretor " + this.getNome() + ": \n" + retorno.toString();
    }

    public List<Professor> getProfessoresOrientados() {
        return professoresOrientados;
    }

    public void setProfessoresOrientados(List<Professor> professoresOrientados) {
        this.professoresOrientados = professoresOrientados;
    }

    @Override
    public String toString() {
        return "Diretor " + super.toString() +
                "-> Professores orientados: " + exibirProfessoresOrientados() + "\n";
    }
}
