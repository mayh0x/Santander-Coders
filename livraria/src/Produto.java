import java.util.UUID;

public abstract class Produto {
    private String nome;
    private String id;
    private double preco;
    private int quantidade;
    private String categoriaProduto;
    private boolean publicoAdulto;
    public Produto(String nome, double preco, int quantidade, String categoriaProduto, boolean publicoAdulto) {
        this.nome = nome;
        this.id = UUID.randomUUID().toString();
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoriaProduto = categoriaProduto;
        this.publicoAdulto = publicoAdulto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(String categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public boolean getPublicoAdulto() {
        return publicoAdulto;
    }

    public void setPublicoAdulto(boolean publicoAdulto) {
        this.publicoAdulto = publicoAdulto;
    }

    @Override
    public String toString() {
        String ehPublicoAdulto = "";
        if (this.publicoAdulto) {
            ehPublicoAdulto = "OBS: Item destinado ao público adulto. \n";
        }
        return  "id: " + id + " \n" +
                "nome: " + nome + " \n" +
                "preco: R$" + String.format("%.2f", (float)preco) + " \n" +
                "quantidade em estoque: " + quantidade + " unidades \n" + ehPublicoAdulto;
    }
}
