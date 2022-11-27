package Projeto.Controller.MudarTela;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Mudanca{
    public void mudarTela(Button botao, Stage stage, Application tela) {
        botao.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    tela.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public void contruirTelaEsqueciSenha(Button botao, Stage stage, Pane pane, Button buttonenviarLink, Label labelEsqueciTilt, Node... itens){
        botao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (buttonenviarLink.getText().toLowerCase().equals("enviar link")){
                    for (Node item : itens) {
                        pane.getChildren().remove(item);
                    }

                    labelEsqueciTilt.setText("Link enviado com sucesso!!!");
                    labelEsqueciTilt.setStyle("-fx-font-size: 2em;");
                    labelEsqueciTilt.relocate(20, 10);

                    buttonenviarLink.setText("OK");
                    buttonenviarLink.setPrefSize(60, 20);
                    buttonenviarLink.relocate(150, 60);

                    stage.setHeight(150);
                    stage.setWidth(350);
                    stage.setMaxHeight(150);
                    stage.setMaxWidth(350);
                    stage.setMinHeight(150);
                    stage.setMinWidth(350);
                    stage.show();
                }else {
                    for (Node item : itens) {
                        pane.getChildren().add(item);
                    }

                    labelEsqueciTilt.setText("Esqueci Senha");
                    labelEsqueciTilt.setStyle("-fx-font-size: 4em;");
                    labelEsqueciTilt.relocate(200, 10);

                    buttonenviarLink.setText("Enviar Link");
                    buttonenviarLink.setStyle("-fx-font-size: 20px;");
                    buttonenviarLink.setPrefSize(300, 50);
                    buttonenviarLink.relocate(200, 203);
                    stage.setHeight(400);
                    stage.setWidth(720);
                    stage.setMaxHeight(400);
                    stage.setMaxWidth(720);
                    stage.setMinHeight(400);
                    stage.setMinWidth(720);
                    stage.show();
                }
            }
        });
    }
}
