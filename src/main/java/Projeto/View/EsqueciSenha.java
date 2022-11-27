package Projeto.View;

import Projeto.Controller.MudarTela.Mudanca;
import Projeto.Executar;
import Projeto.Main;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class EsqueciSenha {

    private Executar exec;

    public Node render(){
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        Mudanca mudanca = new Mudanca();
        Label labelEsqueciTilt = new Label("Esqueci Senha");
        labelEsqueciTilt.setStyle("-fx-font-size: 3em;");
        labelEsqueciTilt.relocate(130, 20);

        Label labelexplic = new Label("Digite o email ou telefone cadastrado para receber \n um link de redefinição.");
        labelexplic.setStyle("-fx-font-size: 20px;");
        labelexplic.relocate(30, 80);

        Label labelemail = new Label("E-mail:");
        labelemail.setStyle("-fx-font-size: 20px;");
        labelemail.relocate(30, 150);

        Label labelTelefone = new Label("Telefone:");
        labelTelefone.setStyle("-fx-font-size: 20px;");
        labelTelefone.relocate(30, 180);

//        TextField Fieldname = new TextField();
//        Fieldname.setPrefSize(210, 20);
//        Fieldname.relocate(180,103);

        TextField Fieldemail = new TextField();
        Fieldemail.setPromptText("Email@fatec.sp.gov.br");
        Fieldemail.setPrefSize(300, 20);
        Fieldemail.relocate(100, 150);

        TextField FieldTelefone = new TextField();
        FieldTelefone.setPromptText("(**) 9 ****-****");
        FieldTelefone.setPrefSize(160, 20);
        FieldTelefone.relocate(115, 183);

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setStyle("-fx-font-size: 20px;");
        btnVoltar.setPrefSize(200, 50);
        btnVoltar.relocate(130, 295);
        btnVoltar.setOnAction(e->{
            exec = new Main();
            exec.exec("logintela");
        });


        Button btnLink = new Button("Enviar link");
        btnLink.setStyle("-fx-font-size: 20px;");
        btnLink.setPrefSize(200, 50);
        btnLink.relocate(130, 230);

        pane.getChildren().addAll(labelEsqueciTilt, labelexplic, labelTelefone, labelemail, Fieldemail, FieldTelefone, btnVoltar, btnLink);

        pane.setMaxWidth(500);
        pane.setMaxHeight(400);
        pane.setStyle("-fx-border-color: Black;" +
                " -fx-border-width: 4; " +
                "-fx-background-color:white;" +
                " -fx-border-radius:5px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 5, 0, 0, 0);" +
                "-fx-background-radius: 6px;");

        return pane;
    }

}