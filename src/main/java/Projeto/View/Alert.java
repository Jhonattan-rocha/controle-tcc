package Projeto.View;

import Projeto.Executar;
import Projeto.Main;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Alert{

    private String texto;

    private String voltar;

    private Executar executar;

    public Node render() {
        Pane pane = new Pane();

        Label label = new Label(texto);
        if(texto.equals("RA/RP em branco")){
            label.relocate(40, 60);
            label.setStyle("-fx-font-size: 2em");
        }else{
            label.relocate(20, 60);
            label.setStyle("-fx-font-size: 2em");
        }

        Button buttonOK = new Button("OK");
        buttonOK.setPrefSize(200, 50);
        buttonOK.relocate(50, 100);
        buttonOK.setOnAction(e->{
            executar = new Main();
            executar.exec(voltar);
        });

        pane.getChildren().addAll(label, buttonOK);

        pane.setMaxWidth(320);
        pane.setMaxHeight(200);
        pane.setStyle("-fx-border-color: Black;" +
                " -fx-border-width: 4; " +
                "-fx-background-color:white;" +
                " -fx-border-radius:5px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 5, 0, 0, 0);" +
                "-fx-background-radius: 6px;");
        return pane;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getVoltar() {
        return voltar;
    }

    public void setVoltar(String voltar) {
        this.voltar = voltar;
    }
}
