import java.util.List;
public class Livro extends Produto {
    private List<String> generos;
    private String escritor;
    private String editora;

    public Livro(String nome, double preco, int quantidade, List<String> generos, String escritor, String editora, boolean publicoAdulto) {
        super(nome, preco, quantidade, "Livro", publicoAdulto);
        this.generos = generos;
        this.escritor = escritor;
        this.editora = editora;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public String getEscritor() {
        return escritor;
    }

    public void setEscritor(String escritor) {
        this.escritor = escritor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    @Override
    public String toString() {
        return  " \n" + "--------------------------------LIVRO-------------------------------" + " \n"
                + super.toString() + " \n" +
                "  - generos: " + generos + " \n" +
                "  - escritor: " + escritor + " \n" +
                "  - editora: " + editora + " \n" +
                "--------------------------------------------------------------------" + " \n";
    }
}
