public class Turma {
    private String turno;
    private String titulo;

    public Turma(String turno, String titulo) {
        this.turno = turno;
        this.titulo = titulo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "> Turma " + titulo + "\n" +
                "- Turno: " + turno + "\n";
    }
}
