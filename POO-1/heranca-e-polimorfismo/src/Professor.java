import java.util.ArrayList;
import java.util.List;

public class Professor extends Funcionario {
    private List<Artigo> artigosPublicados = new ArrayList<>();
    private List<Turma> turmasPeriodo = new ArrayList<>();

    public Professor(String nome, String cpf, double salario, Agenda agenda) {
        super(nome, cpf, salario, agenda);
        this.artigosPublicados = new ArrayList<>();
        this.turmasPeriodo = new ArrayList<>();
    }

    public boolean cadastrarArtigo(Artigo artigo) {
        for (Artigo art : artigosPublicados) {
            if(artigo.getTitulo().equalsIgnoreCase(art.getTitulo())) {
                System.out.println("Erro: Não foi possível cadastrar o artigo '" + artigo.getTitulo() +
                                   "' pois ele já está cadastrado para esse professor.");
                return false;
            }
        }
        artigosPublicados.add(artigo);
        artigo.setAutor(this.getNome());
        System.out.println("Artigo  '" + artigo.getTitulo() + "' cadastrado para o professor " +
                this.getNome() + " com sucesso.");
        return true;
    }

    public boolean cadastrarTurma(Turma turma) {
        for (Turma t : turmasPeriodo) {
            if(turma.getTitulo().equalsIgnoreCase(t.getTitulo())) {
                System.out.println("Erro: Não foi possível cadastrar a turma '" + turma.getTitulo() +
                        "' pois ela já está cadastrada para esse professor.");
                return false;
            }
        }
        turmasPeriodo.add(turma);
        System.out.println("Turma  '" + turma.getTitulo() + "' cadastrada para o professor " +
                this.getNome() + " com sucesso.");
        return true;
    }

    public List<Artigo> getArtigosPublicados() {
        return artigosPublicados;
    }

    public void setArtigosPublicados(List<Artigo> artigosPublicados) {
        this.artigosPublicados = artigosPublicados;
    }

    public List<Turma> getTurmasPeriodo() {
        return turmasPeriodo;
    }

    public void setTurmasPeriodo(List<Turma> turmasPeriodo) {
        this.turmasPeriodo = turmasPeriodo;
    }

    @Override
    public String toString() {
        return "Professor " + super.toString() +
                "-> Artigos publicados: " + artigosPublicados + "\n" +
                "-> Turmas período: " + turmasPeriodo + "\n";
    }
}
