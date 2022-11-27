package Projeto.View;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Nota {

    public String grupo = "";

    public BorderPane render(){
        BorderPane bp = new BorderPane();
        GridPane gp = new GridPane();

        Label titulo = new Label("Qual a nota do grupo? ");

        TextField nota = new TextField();
        nota.setPromptText("Digite a nota do grupo");
        nota.setPrefSize(200, 40);

        Button bntNota = new Button("Salvar");
        bntNota.setPrefSize(150, 40);

        gp.add(titulo, 1, 0);
        gp.add(nota, 1, 1);
        gp.add(bntNota, 1, 3);

        bp.setMaxWidth(600);
        bp.setMaxHeight(300);
        bp.setStyle("-fx-border-color: Black;" +
                " -fx-border-width: 4; " +
                "-fx-background-color:white;" +
                " -fx-border-radius:5px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 5, 0, 0, 0);" +
                "-fx-background-radius: 6px;");
        return bp;
    }
}
