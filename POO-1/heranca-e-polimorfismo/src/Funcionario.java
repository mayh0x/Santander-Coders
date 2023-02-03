public class Funcionario {
    private String nome;
    private String cpf;
    private double salario;
    private Agenda agenda;

    public Funcionario(String nome, String cpf, double salario, Agenda agenda) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.agenda = agenda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    @Override
    public String toString() {
        return  nome + ": \n" +
                "- CPF: " + cpf + "\n" +
                "- Salário: R$" + String.format("%.2f", (float)salario) + "\n" +
                "~ " + agenda + "\n";
    }
}
