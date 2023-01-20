import java.util.List;

public class AlbumDeMusica extends Produto {
    private List<String> musicos;
    private List<String> generos;
    private List<String> selos;

    public AlbumDeMusica(String nome, double preco, int quantidade, List<String> musicos, List<String> generos, List<String> selos, boolean publicoAdulto) {
        super(nome, preco, quantidade, "Album", publicoAdulto);
        this.musicos = musicos;
        this.generos = generos;
        this.selos = selos;
    }

    public List<String> getMusicos() {
        return musicos;
    }

    public void setMusicos(List<String> musicos) {
        this.musicos = musicos;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public List<String> getSelos() {
        return selos;
    }

    public void setSelos(List<String> selos) {
        this.selos = selos;
    }

    @Override
    public String toString() {
        return  " \n" + "------------------------ALBUM-DE-MUSICA----------------------------" + " \n"
                + super.toString() + " \n" +
                "  - musicos: " + musicos + " \n" +
                "  - generos: " + generos + " \n" +
                "  - selos: " + selos + " \n" +
                "-------------------------------------------------------------------" + " \n";
    }
}
