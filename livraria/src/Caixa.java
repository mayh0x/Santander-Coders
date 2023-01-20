import java.util.ArrayList;
import java.util.List;

public class Caixa <T extends Produto>{
    private List<T> produtos = new ArrayList<>();
    private double valorTotal;

    public Caixa() {
        this.produtos = new ArrayList<>();
        this.valorTotal = 0;
    }

    public List<T> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<T> produtos) {
        this.produtos = produtos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void venderProduto(String nomeProduto, int quantidade) {
        for (T produto : this.produtos) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                boolean vazio = estoqueProdutoEsgotado(produto);
                if (vazio) {
                    return;
                }
                if (produto.getQuantidade() < quantidade) {
                    System.out.println("Quantidade desejada ultrapassa quantidade em estoque.");
                } else {
                    if (produto.getQuantidade() - quantidade == 0) {
                        if (produto.getPreco() * quantidade > 200 && produto.getCategoriaProduto().equalsIgnoreCase("Livro")) {
                            System.out.println("Parabéns, você ganhou 15% de desconto devido a compra em mais de R$200 em livros!");
                            this.valorTotal += (produto.getPreco() * quantidade) * 0.85;
                        } else {
                            this.valorTotal += produto.getPreco() * quantidade;
                        }
                        produto.setQuantidade(0);
                        System.out.println("Foram vendidas todas as unidades do produto " + produto.getNome());
                    }else{
                        if (produto.getPreco() * quantidade > 200 && produto.getCategoriaProduto().equalsIgnoreCase("Livro")) {
                            System.out.println("Parabéns, você ganhou 15% de desconto devido a compra em mais de R$200 em livros!");
                            this.valorTotal += (produto.getPreco() * quantidade) * 0.85;
                        } else {
                            this.valorTotal += produto.getPreco() * quantidade;
                        }
                        produto.setQuantidade(produto.getQuantidade()-quantidade);
                        System.out.println("Venda de " + quantidade + " unidades realizada com sucesso.");
                    }
                }
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    public void adicionarProdutoEstoque(T produto) {
        for (T produtoAtual : this.produtos) {
            if (produtoAtual.getNome().equalsIgnoreCase(produto.getNome())) {
                System.out.println("Produto já existente no estoque.");
                return;
            }
        }
        this.produtos.add(produto);
        System.out.println("Produto adicionado com sucesso.");
    }

    public void adicionarQuantidadeProdutoEstoque(String nomeProduto, int quantidade) {
        for (T produto : this.produtos) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                produto.setQuantidade(produto.getQuantidade()+quantidade);
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    public void removerQuantidadeProdutoEstoque(String nomeProduto, int quantidade){
        for (T produto : this.produtos) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                boolean vazio = estoqueProdutoEsgotado(produto);
                if (vazio) {
                    return;
                }
                if (produto.getQuantidade() < quantidade) {
                    System.out.println("Quantidade desejada para remover ultrapassa quantidade em estoque.");
                } else if (produto.getQuantidade() - quantidade == 0) {
                    produto.setQuantidade(0);
                    System.out.println("Foram removidas todas as unidades do produto " + produto.getNome());
                } else {
                    produto.setQuantidade(produto.getQuantidade()-quantidade);
                    System.out.println("Remoção de " + quantidade + " unidades realizada com sucesso.");
                }
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    public boolean estoqueProdutoEsgotado(T produto) {
        if (produto.getQuantidade() == 0) {
            System.out.println("O produto encontra-se esgotado no momento.");
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return " \n" + "--------------------------------CAIXA-------------------------------" + " \n" +
                "ESTOQUE: \n" + produtos + " \n" +
                "VALOR TOTAL EM VENDAS" +
                ": R$" + String.format("%.2f", (float)valorTotal) + " \n";
    }
}
