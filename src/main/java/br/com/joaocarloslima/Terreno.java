package br.com.joaocarloslima;

public class Terreno {
    private Produto produtoPlantado;

    public Terreno() {
        this.produtoPlantado = null;
    }

    public boolean estaOcupado() {
        return produtoPlantado != null;
    }

    public void plantar(Produto p) {
        if (estaOcupado()) {
            throw new RuntimeException("Terreno já está ocupado!");
        }

        Produto novaPlanta;

        switch (p.getTipo()) {
            case "batata":
                novaPlanta = new Batata();
                break;
            case "cenoura":
                novaPlanta = new Cenoura();
                break;
            case "morango":
                novaPlanta = new Morango();
                break;
            default:
                throw new RuntimeException("Tipo desconhecido: " + p.getTipo());
        }

        this.produtoPlantado = novaPlanta;
    }

    public void crescerPlanta() {
        if (produtoPlantado != null) {
            produtoPlantado.crescer();
        }
    }

    public void colher(Celeiro celeiro) {
        if (produtoPlantado == null) {
            throw new RuntimeException("Terreno está vazio.");
        }
        if (produtoPlantado.podeColher()) {
            celeiro.armazenar(produtoPlantado);
            produtoPlantado = null;
        } else {
            throw new RuntimeException("Produto ainda não pode ser colhido.");
        }
    }

    public Produto getProdutoPlantado() {
        return produtoPlantado;
    }
}
