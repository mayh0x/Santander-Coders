public class Jogo extends Produto {
    private String distribuidora;
    private String genero;
    private String estudio;

    public Jogo(String nome, double preco, int quantidade, String distribuidora, String genero, String estudio, boolean publicoAdulto) {
        super(nome, preco, quantidade, "Jogo", publicoAdulto);
        this.distribuidora = distribuidora;
        this.genero = genero;
        this.estudio = estudio;
    }

    public String getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(String distribuidora) {
        this.distribuidora = distribuidora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    @Override
    public String toString() {
        return  " \n" + "---------------------------------JOGO------------------------------" + " \n"
                + super.toString() + " \n" +
                "  - distribuidora: " + distribuidora + " \n" +
                "  - genero: " + genero + " \n" +
                "  - estudio: " + estudio + " \n" +
                "-------------------------------------------------------------------" + " \n";
    }
}
