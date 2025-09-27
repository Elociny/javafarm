package br.com.joaocarloslima;

import br.com.joaocarloslima.exceptions.SemProdutoException;

public class Fazenda {
    private Terreno[][] terrenos;
    private Celeiro celeiro;
    public static final int LINHAS = 13;
    public static final int COLUNAS = 13;

    public Fazenda(Celeiro celeiro) {
        this.celeiro = celeiro;
        terrenos = new Terreno[LINHAS][COLUNAS];
        for (int x = 0; x < LINHAS; x++) {
            for (int y = 0; y < COLUNAS; y++) {
                terrenos[x][y] = new Terreno();
            }
        }
    }

    public void plantar(Produto p, int linha, int coluna) {
        validarPosicao(linha, coluna);
        Terreno t = terrenos[linha][coluna];
        if (t.estaOcupado()) {
            throw new RuntimeException("Terreno (" + linha + "," + coluna + ") já está ocupado.");
        }

        try {
            celeiro.consumir(p.getTipo());
        } catch (SemProdutoException e) {
            throw new RuntimeException("Não há " + p.getTipo() + " disponível no celeiro para plantar.");
        }
        t.plantar(p);
    }

    public void colher(int linha, int coluna) {
        validarPosicao(linha, coluna);
        Terreno t = terrenos[linha][coluna];
        if (!t.estaOcupado()) {
            throw new RuntimeException("Terreno vazio.");
        }
        t.colher(celeiro);
    }

    public Terreno getTerreno(int linha, int coluna) {
        validarPosicao(linha, coluna);
        return terrenos[linha][coluna];
    }

    private void validarPosicao(int linha, int coluna) {
        if (linha < 0 || linha >= LINHAS || coluna < 0 || coluna >= COLUNAS) {
            throw new IllegalArgumentException("Posição inválida: " + linha + "," + coluna);
        }
    }

    public Celeiro getCeleiro() {
        return celeiro;
    }

}

