public class Artigo {
    private String autor = "Sem informações do autor";
    private String tema;
    private String titulo;
    private String descricao;

    public Artigo(String tema, String titulo, String descricao) {
        this.tema = tema;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "* Artigo -> " + titulo + "\n" +
                "- Autor: " + autor + "\n" +
                "- Tema: " + tema + "\n" +
                "- Descrição: " + descricao + "\n";
    }
}
