import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Caixa<Livro> caixaLivro = new Caixa<>();
        Caixa<Jogo> caixaJogo = new Caixa<>();
        Caixa<Filme> caixaFilme = new Caixa<>();
        Caixa<AlbumDeMusica> caixaAlbum = new Caixa<>();
        Caixa<Brinquedo> caixaBrinquedo = new Caixa<>();

        // Populando
        caixaLivro.adicionarProdutoEstoque(criarLivros().get(0));
        caixaLivro.adicionarProdutoEstoque(criarLivros().get(1));
        caixaJogo.adicionarProdutoEstoque(criarJogos().get(0));
        caixaJogo.adicionarProdutoEstoque(criarJogos().get(1));
        caixaFilme.adicionarProdutoEstoque(criarFilmes().get(0));
        caixaFilme.adicionarProdutoEstoque(criarFilmes().get(1));
        caixaAlbum.adicionarProdutoEstoque(criarAlbuns().get(0));
        caixaAlbum.adicionarProdutoEstoque(criarAlbuns().get(1));
        caixaBrinquedo.adicionarProdutoEstoque(criarBrinquedos().get(0));
        caixaBrinquedo.adicionarProdutoEstoque(criarBrinquedos().get(1));
        caixaBrinquedo.adicionarProdutoEstoque(criarBrinquedos().get(1));
        System.out.println("\n");

        List<Caixa> caixas = new ArrayList<>();
        caixas.add(caixaLivro);
        caixas.add(caixaJogo);
        caixas.add(caixaFilme);
        caixas.add(caixaAlbum);
        caixas.add(caixaBrinquedo);

        boolean continuar = true;

        System.out.println("Bem-vindo(a) à livraria!");
        while (continuar) {
            System.out.println("O que deseja comprar? \nEscolha uma opção (digite o número): ");
            System.out.println("1 - Livro\n2 - Jogo\n3 - Filme\n4 - Album de Musica\n5 - Brinquedo\n6 - Mostrar estoque total\n7 - Sair");
            int escolhaCategoria = input.nextInt();
            input.nextLine();
            switch (escolhaCategoria) {
                case 1:
                    while (true) {
                        System.out.println(caixaLivro);
                        System.out.println("O que deseja comprar? (digite o nome corretamente como mostra na lista): ");
                        String nomeProduto = input.nextLine();
                        boolean existe = false;
                        for (Livro livro : caixaLivro.getProdutos()) {
                            if (livro.getNome().equalsIgnoreCase(nomeProduto)) {
                                existe = true;
                                if (livro.getPublicoAdulto()) {
                                    boolean verificaIdade = ehDeMaior(input);
                                    if (!verificaIdade) {
                                        System.out.println("Não é possível vender esse produto para você pois, verificando seus dados, você não pode realizar essa compra.");
                                        break;
                                    }
                                }
                                System.out.println("Temos essa quantidade em estoque: " + livro.getQuantidade());
                                System.out.println("Digite quantos você deseja comprar (OBS: Digite um valor abaixo ou igual à quantidade atual): ");
                                int quantidadeProduto = input.nextInt();
                                caixaLivro.venderProduto(nomeProduto, quantidadeProduto);
                                System.out.println("Verifique abaixo o novo estoque da livraria em livros.");
                                System.out.println(caixaLivro);
                                break;
                            }
                        }
                        if (!existe) {
                            System.out.println("O nome do produto digitado não foi encontrado no estoque.");
                        }
                        System.out.println("Deseja continuar comprando em livros? (escolha um número): ");
                        System.out.println("1 - Sim \n2 - Não");
                        int aux = input.nextInt();
                        input.nextLine();
                        if (aux == 2) {
                            break;
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println(caixaJogo);
                        System.out.println("O que deseja comprar? (digite o nome corretamente como mostra na lista): ");
                        String nomeProduto = input.nextLine();
                        boolean existe = false;
                        for (Jogo jogo : caixaJogo.getProdutos()) {
                            if (jogo.getNome().equalsIgnoreCase(nomeProduto)) {
                                existe = true;
                                if (jogo.getPublicoAdulto()) {
                                    boolean verificaIdade = ehDeMaior(input);
                                    if (!verificaIdade) {
                                        System.out.println("Não é possível vender esse produto para você pois, verificando seus dados, você não pode realizar essa compra.");
                                        break;
                                    }
                                }
                                System.out.println("Temos essa quantidade em estoque: " + jogo.getQuantidade());
                                System.out.println("Digite quantos você deseja comprar (OBS: Digite um valor abaixo ou igual à quantidade atual): ");
                                int quantidadeProduto = input.nextInt();
                                caixaJogo.venderProduto(nomeProduto, quantidadeProduto);
                                System.out.println("Verifique abaixo o novo estoque da livraria em jogos.");
                                System.out.println(caixaJogo);
                                break;
                            }
                        }
                        if (!existe) {
                            System.out.println("O nome do produto digitado não foi encontrado no estoque.");
                        }
                        System.out.println("Deseja continuar comprando em jogos? (escolha um número): ");
                        System.out.println("1 - Sim \n2 - Não");
                        int aux = input.nextInt();
                        input.nextLine();
                        if (aux == 2) {
                            break;
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        System.out.println(caixaFilme);
                        System.out.println("O que deseja comprar? (digite o nome corretamente como mostra na lista): ");
                        String nomeProduto = input.nextLine();
                        boolean existe = false;
                        for (Filme filme : caixaFilme.getProdutos()) {
                            if (filme.getNome().equalsIgnoreCase(nomeProduto)) {
                                existe = true;
                                if (filme.getPublicoAdulto()) {
                                    boolean verificaIdade = ehDeMaior(input);
                                    if (!verificaIdade) {
                                        System.out.println("Não é possível vender esse produto para você pois, verificando seus dados, você não pode realizar essa compra.");
                                        break;
                                    }
                                }
                                System.out.println("Temos essa quantidade em estoque: " + filme.getQuantidade());
                                System.out.println("Digite quantos você deseja comprar (OBS: Digite um valor abaixo ou igual à quantidade atual): ");
                                int quantidadeProduto = input.nextInt();
                                caixaFilme.venderProduto(nomeProduto, quantidadeProduto);
                                System.out.println("Verifique abaixo o novo estoque da livraria em filmes.");
                                System.out.println(caixaFilme);
                                break;
                            }
                        }
                        if (!existe) {
                            System.out.println("O nome do produto digitado não foi encontrado no estoque.");
                        }
                        System.out.println("Deseja continuar comprando em filmes? (escolha um número): ");
                        System.out.println("1 - Sim \n2 - Não");
                        int aux = input.nextInt();
                        input.nextLine();
                        if (aux == 2) {
                            break;
                        }
                    }
                    break;
                case 4:
                    while (true) {
                        System.out.println(caixaAlbum);
                        System.out.println("O que deseja comprar? (digite o nome corretamente como mostra na lista): ");
                        String nomeProduto = input.nextLine();
                        boolean existe = false;
                        for (AlbumDeMusica album : caixaAlbum.getProdutos()) {
                            if (album.getNome().equalsIgnoreCase(nomeProduto)) {
                                existe = true;
                                if (album.getPublicoAdulto()) {
                                    boolean verificaIdade = ehDeMaior(input);
                                    if (!verificaIdade) {
                                        System.out.println("Não é possível vender esse produto para você pois, verificando seus dados, você não pode realizar essa compra.");
                                        break;
                                    }
                                }
                                System.out.println("Temos essa quantidade em estoque: " + album.getQuantidade());
                                System.out.println("Digite quantos você deseja comprar (OBS: Digite um valor abaixo ou igual à quantidade atual): ");
                                int quantidadeProduto = input.nextInt();
                                caixaAlbum.venderProduto(nomeProduto, quantidadeProduto);
                                System.out.println("Verifique abaixo o novo estoque da livraria em álbuns de música.");
                                System.out.println(caixaAlbum);
                                break;
                            }
                        }
                        if (!existe) {
                            System.out.println("O nome do produto digitado não foi encontrado no estoque.");
                        }
                        System.out.println("Deseja continuar comprando em álbuns de música? (escolha um número): ");
                        System.out.println("1 - Sim \n2 - Não");
                        int aux = input.nextInt();
                        input.nextLine();
                        if (aux == 2) {
                            break;
                        }
                    }
                    break;
                case 5:
                    while (true) {
                        System.out.println(caixaBrinquedo);
                        System.out.println("O que deseja comprar? (digite o nome corretamente como mostra na lista): ");
                        String nomeProduto = input.nextLine();
                        boolean existe = false;
                        for (Brinquedo brinquedo : caixaBrinquedo.getProdutos()) {
                            if (brinquedo.getNome().equalsIgnoreCase(nomeProduto)) {
                                existe = true;
                                if (brinquedo.getPublicoAdulto()) {
                                    boolean verificaIdade = ehDeMaior(input);
                                    if (!verificaIdade) {
                                        System.out.println("Não é possível vender esse produto para você pois, verificando seus dados, você não pode realizar essa compra.");
                                        break;
                                    }
                                }
                                System.out.println("Temos essa quantidade em estoque: " + brinquedo.getQuantidade());
                                System.out.println("Digite quantos você deseja comprar (OBS: Digite um valor abaixo ou igual à quantidade atual): ");
                                int quantidadeProduto = input.nextInt();
                                caixaBrinquedo.venderProduto(nomeProduto, quantidadeProduto);
                                System.out.println("Verifique abaixo o novo estoque da livraria em brinquedos.");
                                System.out.println(caixaBrinquedo);
                                break;
                            }
                        }
                        if (!existe) {
                            System.out.println("O nome do produto digitado não foi encontrado no estoque.");
                        }
                        System.out.println("Deseja continuar comprando em brinquedos? (escolha um número): ");
                        System.out.println("1 - Sim \n2 - Não");
                        int aux = input.nextInt();
                        input.nextLine();
                        if (aux == 2) {
                            break;
                        }
                    }
                    break;
                case 6:
                    System.out.println("Aqui está o estoque total da livraria em todas as categorias.");
                    System.out.println(caixas);
                    double valorTotalCategorias = caixaLivro.getValorTotal() + caixaJogo.getValorTotal() + caixaFilme.getValorTotal() + caixaAlbum.getValorTotal() + caixaBrinquedo.getValorTotal();
                    System.out.printf("O valor total vendido contando com todas as categorias é de: R$%.2f \n", (float)valorTotalCategorias);
                    break;
                case 7:
                    System.out.println("Espero que tenha gostado da livraria! Até a próxima!");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção escolhida inválida.");
                    break;
            }
        }
    }

    public static boolean ehDeMaior(Scanner input) {
        System.out.println("Pelo que consta no sistema, o produto é restritamente destinado ao público adulto.");
        System.out.println("Preciso que você digite os seus dados para confirmar sua idade.");
        System.out.println("Digite seu nome: ");
        String nome = input.nextLine();
        System.out.println("Digite seu RG: ");
        String rg = input.nextLine();
        System.out.println("Digite seu CPF: ");
        String cpf = input.nextLine();
        System.out.println("Digite sua data de nascimento (formato dd/mm/aa): ");
        String dataNascimento = input.nextLine();
        try {
            int idade = pegaIdade(dataNascimento);
            return idade >= 18;
        } catch (Exception e) {
            System.out.println("Erro: Data de nascimento informada de forma inválida.");
            return false;
        }
    }

    public static int pegaIdade(String dataNascimento) {
        String[] dataSeparada = dataNascimento.split("/");
        int ano = Integer.parseInt(dataSeparada[2]);
        int mes = Integer.parseInt(dataSeparada[1]);
        int dia = Integer.parseInt(dataSeparada[0]);

        Calendar dataAtual = Calendar.getInstance();
        int diferencaMes = (dataAtual.get(Calendar.MONTH)+1) - mes;
        int diferencaDia = (dataAtual.get(Calendar.DAY_OF_MONTH)) - dia;
        int idade = dataAtual.get(Calendar.YEAR) - ano;

        if(diferencaMes < 0 || (diferencaMes == 0 && diferencaDia < 0)) {
            idade--;
        }
        return idade;
    }

    public static List<Livro> criarLivros() {
        List<String> generosLivro1 = new ArrayList<>();
        generosLivro1.add("Romance");
        generosLivro1.add("Drama");
        List<String> generosLivro2 = new ArrayList<>();
        generosLivro2.add("Comedia");
        generosLivro2.add("Acao");
        Livro livro1 = new Livro("Alice no pais das maravilhas", 2.55, 3, generosLivro1, "Clarice Lispector", "Atena", false);
        Livro livro2 = new Livro("Dom Casmurro", 200, 5, generosLivro2, "Machado de Assis", "PANINI", true);
        List<Livro> listaLivros = new ArrayList<>();
        listaLivros.add(livro1);
        listaLivros.add(livro2);

        return listaLivros;
    }

    public static List<Jogo> criarJogos() {
        Jogo jogo1 = new Jogo("VALORANT", 70.00, 7, "Riot Games", "MOBA", "Riot Games", true);
        Jogo jogo2 = new Jogo("New World", 134.00, 4, "Amazon", "MMORPG", "Amazon Games", false);
        List<Jogo> listaJogos = new ArrayList<>();
        listaJogos.add(jogo1);
        listaJogos.add(jogo2);

        return listaJogos;
    }

    public static List<Filme> criarFilmes() {
        List<String> diretoresFilme1 = new ArrayList<>();
        diretoresFilme1.add("Steven Spielberg");
        diretoresFilme1.add("Stanley Kubrick");
        List<String> diretoresFilme2 = new ArrayList<>();
        diretoresFilme2.add("Bernardo Bertolucci");
        diretoresFilme2.add("Jean-Luc Godard");
        List<String> generosFilme1 = new ArrayList<>();
        generosFilme1.add("Sobrenatural");
        generosFilme1.add("Animacao");
        List<String> generosFilme2 = new ArrayList<>();
        generosFilme2.add("Romance");
        generosFilme2.add("Comedia");
        List<String> produtoresFilme1 = new ArrayList<>();
        produtoresFilme1.add("Dreamwork");
        produtoresFilme1.add("Paramount");
        List<String> produtoresFilme2 = new ArrayList<>();
        produtoresFilme2.add("Universal Pictures");
        produtoresFilme2.add("Walt Disney");
        Filme filme1 = new Filme("Sherk", 89.00, 3, "Illumination", diretoresFilme1, generosFilme1, produtoresFilme1, false);
        Filme filme2 = new Filme("Minions", 76.00, 6, "Illumination", diretoresFilme2, generosFilme2, produtoresFilme2, true);
        List<Filme> listaFilmes = new ArrayList<>();
        listaFilmes.add(filme1);
        listaFilmes.add(filme2);

        return listaFilmes;
    }

    public static List<AlbumDeMusica> criarAlbuns() {
        List<String> musicosAlbum1 = new ArrayList<>();
        musicosAlbum1.add("Ariana Grande");
        musicosAlbum1.add("The Weeknd");
        List<String> musicosAlbum2 = new ArrayList<>();
        musicosAlbum2.add("Arctic Monkeys");
        musicosAlbum2.add("The Neighborhood");
        List<String> generosAlbum1 = new ArrayList<>();
        generosAlbum1.add("Pop");
        generosAlbum1.add("Eletronica");
        List<String> generosAlbum2 = new ArrayList<>();
        generosAlbum2.add("Indie");
        generosAlbum2.add("Rock");
        List<String> selosAlbum1 = new ArrayList<>();
        selosAlbum1.add("Sony");
        selosAlbum1.add("Universal Music");
        List<String> selosAlbum2 = new ArrayList<>();
        selosAlbum2.add("Virgin Records");
        selosAlbum2.add("Atlantic");
        AlbumDeMusica album1 = new AlbumDeMusica("Sweetener", 57.00, 5, musicosAlbum1, generosAlbum1, selosAlbum1, true);
        AlbumDeMusica album2 = new AlbumDeMusica("AM", 35.00, 8, musicosAlbum2, generosAlbum2, selosAlbum2, false);
        List<AlbumDeMusica> listaAlbuns = new ArrayList<>();
        listaAlbuns.add(album1);
        listaAlbuns.add(album2);

        return listaAlbuns;
    }

    public static List<Brinquedo> criarBrinquedos() {
        Brinquedo brinquedo1 = new Brinquedo("Amoeba", 7.00, 15, "Massinha de modelar", false);
        Brinquedo brinquedo2 = new Brinquedo("Furby", 120.00, 3, "Boneca a pilha", true);
        List<Brinquedo> listaBrinquedos = new ArrayList<>();
        listaBrinquedos.add(brinquedo1);
        listaBrinquedos.add(brinquedo2);

        return listaBrinquedos;
    }
}