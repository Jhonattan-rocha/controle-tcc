package Projeto.View;

import Projeto.Controller.LimiteField.SenhaLoginTextField;
import Projeto.Executar;
import Projeto.Main;
import Projeto.Model.Login;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class LoginTela {

    private Executar exec;

    public Node render(){
        Pane painel = new Pane();


        Text txtTilulo = new Text("Login");
        txtTilulo.setStyle("-fx-font-size: 4em;");
        txtTilulo.relocate(100, 70);


        TextField nomeField = new TextField();
        nomeField.setPromptText("RA / RP");
        nomeField.setPrefSize(200, 32);
        nomeField.relocate(60, 120);


        SenhaLoginTextField senhaField = new SenhaLoginTextField(10);
        senhaField.setPrefSize(200, 40);
        senhaField.setPromptText("Senha");
        senhaField.relocate(60, 170);
        senhaField.setOnMouseClicked(e->{

        });


        final ToggleGroup user = new ToggleGroup();

        RadioButton rbAluno = new RadioButton("Aluno");
        rbAluno.relocate(85, 220);
        rbAluno.setStyle("-fx-cursor:hand;");
        rbAluno.setToggleGroup(user);
        rbAluno.setSelected(true);

        RadioButton rbProf = new RadioButton("Professor");
        rbProf.relocate(170, 220);
        rbProf.setStyle("-fx-cursor:hand;");
        rbProf.setToggleGroup(user);

        Label labelEsqSenha = new Label("Redefinir senha");
        labelEsqSenha.relocate(120, 240);
        labelEsqSenha.setStyle("-fx-text-fill: blue; -fx-cursor:hand;");
        labelEsqSenha.setOnMouseEntered(e -> labelEsqSenha.setStyle("-fx-underline:true; -fx-text-fill: blue; -fx-cursor:hand;"));
        labelEsqSenha.setOnMouseExited(e -> labelEsqSenha.setStyle("-fx-underline:false; -fx-text-fill: blue;"));
        labelEsqSenha.setPrefSize(140, 30);
        labelEsqSenha.setOnMouseClicked(e->{
            exec = new Main();
            exec.exec("esquecisenha");
        });


        Label labelCadAluno = new Label("Cadastrar-se Aluno");

        labelCadAluno.relocate(110, 260);
        labelCadAluno.setStyle("-fx-text-fill: blue; -fx-cursor:hand;");
        labelCadAluno.setOnMouseEntered(e -> labelCadAluno.setStyle("-fx-underline:true; -fx-text-fill: blue; -fx-cursor:hand;"));
        labelCadAluno.setOnMouseExited(e -> labelCadAluno.setStyle("-fx-underline:false; -fx-text-fill: blue;"));
        labelCadAluno.setPrefSize(140, 30);

        labelCadAluno.setOnMouseClicked(e->{
            exec = new Main();
            exec.exec("cadastroaluno");
        });

        Label labelCadProf = new Label("Cadastrar-se Professor");

        labelCadProf.relocate(100, 280);
        labelCadProf.setStyle("-fx-text-fill: blue; -fx-cursor:hand;");
        labelCadProf.setOnMouseEntered(e -> labelCadProf.setStyle("-fx-underline:true; -fx-text-fill: blue; -fx-cursor:hand;"));
        labelCadProf.setOnMouseExited(e -> labelCadProf.setStyle("-fx-underline:false; -fx-text-fill: blue;"));
        labelCadProf.setPrefSize(140, 30);

        labelCadProf.setOnMouseClicked(e->{
            exec = new Main();
            exec.exec("cadastroprofessor");
        });

        Button logar = new Button("Logar");
        logar.relocate(60, 320);
        logar.setPrefSize(200, 30);
        logar.setOnAction(e -> {
            Login login = new Login();

            login.logar(nomeField, senhaField, rbAluno.isSelected());
        });

        painel.getChildren().addAll(logar, labelEsqSenha, labelCadAluno,
                labelCadProf, txtTilulo, senhaField, nomeField, rbAluno, rbProf);

        painel.setMaxWidth(320);
        painel.setMaxHeight(400);
        painel.setStyle("-fx-border-color: Black;" +
                " -fx-border-width: 4; " +
                "-fx-background-color:white;" +
                " -fx-border-radius:5px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 5, 0, 0, 0);" +
                "-fx-background-radius: 6px;");
        return painel;
    }

}
