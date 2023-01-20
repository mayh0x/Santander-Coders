import java.util.List;

public class Filme extends Produto {
    private String estudio;
    private List<String> diretores;
    private List<String> generos;
    private List<String> produtores;

    public Filme(String nome, double preco, int quantidade, String estudio, List<String> diretores, List<String> generos, List<String> produtores, boolean publicoAdulto) {
        super(nome, preco, quantidade, "Filme", publicoAdulto);
        this.estudio = estudio;
        this.diretores = diretores;
        this.generos = generos;
        this.produtores = produtores;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public List<String> getDiretores() {
        return diretores;
    }

    public void setDiretores(List<String> diretores) {
        this.diretores = diretores;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public List<String> getProdutores() {
        return produtores;
    }

    public void setProdutores(List<String> produtores) {
        this.produtores = produtores;
    }

    @Override
    public String toString() {
        return  " \n" + "--------------------------------FILME------------------------------" + " \n"
                + super.toString() + " \n" +
                "  - estudio: " + estudio + " \n" +
                "  - diretores: " + diretores + " \n" +
                "  - generos: " + generos + " \n" +
                "  - produtores: " + produtores + " \n" +
                "-------------------------------------------------------------------" + " \n";
    }
}
