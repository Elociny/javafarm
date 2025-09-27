package br.com.joaocarloslima;

public class Batata extends Produto {
    public Batata() {
        super(3);
    }

    @Override
    public String getTipo() {
        return "batata";
    }

    @Override
    public String getImagem() {
        return "images/batata" + getTamanho() + ".png";
    }
}
