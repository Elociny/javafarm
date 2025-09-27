package br.com.joaocarloslima;

import br.com.joaocarloslima.exceptions.CeleiroCheioException;
import br.com.joaocarloslima.exceptions.SemProdutoException;

public class Celeiro {

    private int capacidade;
    private int qtdBatata;
    private int qtdCenoura;
    private int qtdMorango;

    public Celeiro(int capacidade) {
        if (capacidade < 0) {
            throw new IllegalArgumentException("Capacidade negativa");
        }

        this.capacidade = capacidade;
        this.qtdBatata = 0;
        this.qtdCenoura = 0;
        this.qtdMorango = 0;
    }

    public void armazenar(Produto p) {
        if (getEspacoDisponivel() < 2) {
            throw new CeleiroCheioException("Não há espaço suficiente no celeiro para armazenar 2 unidades.");
        }

        switch (p.getTipo()) {
            case "batata":
                qtdBatata = qtdBatata + 2;
                break;
            case "cenoura":
                qtdCenoura = qtdCenoura + 2;
                break;
            case "morango":
                qtdMorango = qtdMorango + 2;
                break;
            default:
                throw new RuntimeException("Tipo desconhecido ao armazenar: " + p.getTipo());
        }
    }

    public void consumir(String tipo) {
        switch (tipo) {
            case "batata":
                if (qtdBatata <= 0) throw new SemProdutoException("Sem batata no celeiro.");
                qtdBatata--;
                break;
            case "cenoura":
                if (qtdCenoura <= 0) throw new SemProdutoException("Sem cenoura no celeiro.");
                qtdCenoura--;
                break;
            case "morango":
                if (qtdMorango <= 0) throw new SemProdutoException("Sem morango no celeiro.");
                qtdMorango--;
                break;
            default:
                throw new RuntimeException("Tipo desconhecido ao consumir: " + tipo);
        }
    }

    public int getEspacoDisponivel() {
        return capacidade - getTotalUnidades();
    }

    public int getTotalUnidades() {
        return qtdBatata + qtdCenoura + qtdMorango;
    }

    public double getOcupacao() {
        if (capacidade == 0) return 100.0;
        return (getTotalUnidades() * 100.0) / capacidade;
    }

    public boolean celeiroCheio() {
        return getTotalUnidades() >= capacidade;
    }

    public int getQtdBatata() { return qtdBatata; }
    public int getQtdCenoura() { return qtdCenoura; }
    public int getQtdMorango() { return qtdMorango; }
    public int getCapacidade() { return capacidade; }
}
