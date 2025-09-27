package br.com.joaocarloslima;

public class Cenoura extends Produto {
    public Cenoura() {
        super(2);
    }

    @Override
    public String getTipo() {
        return "cenoura";
    }

    @Override
    public String getImagem() {
        return "images/cenoura" + getTamanho() + ".png";
    }
}
