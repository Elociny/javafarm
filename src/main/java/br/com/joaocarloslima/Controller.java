package br.com.joaocarloslima;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.joaocarloslima.exceptions.SemProdutoException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Controller implements Initializable {

    private Fazenda fazenda = new Fazenda(new Celeiro(100));
    private List<ImageView> imageTerrenos = new ArrayList<>();
    private int sleepTime = 3000;

    @FXML
    GridPane grid;
    @FXML
    ToggleButton botaoBatata;
    @FXML
    ToggleButton botaoCenoura;
    @FXML
    ToggleButton botaoMorango;
    @FXML
    ToggleButton botaoColher;
    @FXML
    ProgressBar ocupacaoDoCeleiro;
    @FXML
    CheckBox ckbAcelerar;

    public void atualizar() {
        botaoBatata.setText("Batata x " + fazenda.getCeleiro().getQtdBatata());
        botaoCenoura.setText("Cenoura x " + fazenda.getCeleiro().getQtdCenoura());
        botaoMorango.setText("Morango x " + fazenda.getCeleiro().getQtdMorango());
        ocupacaoDoCeleiro.setProgress(fazenda.getCeleiro().getOcupacao() /100);

        for (int x = 0; x < Fazenda.LINHAS; x++) {
            for (int y = 0; y < Fazenda.COLUNAS; y++) {
                Terreno terreno = fazenda.getTerreno(x, y);
                ImageView imageView = imageTerrenos.get(x * Fazenda.COLUNAS + y);
                Produto p = terreno.getProdutoPlantado();
                if (p != null) {
                    imageView.setImage(new Image(getClass().getResourceAsStream(p.getImagem())));
                } else {
                    imageView.setImage(null);
                }
            }
        }
    }

    public void ciclo() {
        for (int x = 0; x < Fazenda.LINHAS; x++) {
            for (int y = 0; y < Fazenda.COLUNAS; y++) {
                Terreno terreno = fazenda.getTerreno(x, y);
                Produto p = terreno.getProdutoPlantado();
                if (p != null) {
                    p.crescer();
                }
            }
        }
        atualizar();
    }

    public void acelerar() {
        if (ckbAcelerar.isSelected()) {
            sleepTime = 1000;
        } else {
            sleepTime = 3000;
        }
    }

    public void clockThread() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(sleepTime);
                    Platform.runLater(() -> ciclo());
                    atualizar();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Celeiro celeiro = new Celeiro(100);
        celeiro.armazenar(new Batata());
        celeiro.armazenar(new Cenoura());
        celeiro.armazenar(new Morango());
        fazenda = new Fazenda(celeiro);

        for (int x = 0; x < Fazenda.LINHAS; x++) {
            for (int y = 0; y < Fazenda.COLUNAS; y++) {
                ImageView imageView = new ImageView();
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                grid.add(imageView, x, y);
                imageTerrenos.add(imageView);
            }
        }

        grid.setOnMouseClicked(e -> {
            int x = (int) (e.getX() / 50);
            int y = (int) (e.getY() / 50);

            try{
                if (botaoBatata.isSelected()) fazenda.plantar(new Batata(), x, y);
                if (botaoCenoura.isSelected()) fazenda.plantar(new Cenoura(), x, y);
                if (botaoMorango.isSelected()) fazenda.plantar(new Morango(), x, y);
                if (botaoColher.isSelected()) fazenda.colher(x, y);

                atualizar();

            }catch(SemProdutoException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(ex.getMessage());
                alert.showAndWait();
            }

        });

        atualizar();
        clockThread();
    }
}
