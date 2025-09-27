package br.com.joaocarloslima;

public class Morango extends Produto {
    public Morango() {
        super(1);
    }

    @Override
    public String getTipo() {
        return "morango";
    }

    @Override
    public String getImagem() {
        return "images/morango" + getTamanho() + ".png";
    }
}

