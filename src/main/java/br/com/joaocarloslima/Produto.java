package br.com.joaocarloslima;

public abstract class Produto {
    private int tamanho;
    private int tempoDeVida;
    private int tempoDeCrescimento;

    public Produto(int tempoDeCrescimento) {
        this.tamanho = 1;
        this.tempoDeVida = 1;
        this.tempoDeCrescimento = tempoDeCrescimento;
    }

    public void crescer() {
        tempoDeVida++;

        if (tamanho < 4 && tempoDeCrescimento > 0) {
            if ((tempoDeVida - 1) % tempoDeCrescimento == 0) {
                tamanho++;
            }
        }
    }

    public boolean podeColher() {
        return tamanho >= 4;
    }


    public int getTamanho() {
        return tamanho;
    }

    public int getTempoDeVida() {
        return tempoDeVida;
    }

    public int getTempoDeCrescimento() {
        return tempoDeCrescimento;
    }

    public abstract String getImagem();
    public abstract String getTipo();
}
