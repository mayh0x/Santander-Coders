public class Brinquedo extends Produto{
    private String tipo;

    public Brinquedo(String nome, double preco, int quantidade, String tipo, boolean publicoAdulto) {
        super(nome, preco, quantidade, "Brinquedo", publicoAdulto);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return  "-----------------------------BRINQUEDO---------------------------" + " \n"
                + super.toString() +
                " \n" + "  - tipo: " + tipo + " \n" +
                "-------------------------------------------------------------------" + " \n";
    }
}
