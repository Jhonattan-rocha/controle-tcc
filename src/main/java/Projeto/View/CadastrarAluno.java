package Projeto.View;

import Projeto.Executar;
import Projeto.Main;
import Projeto.Model.Aluno;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;


public class CadastrarAluno{

    private Executar exec;

    public Node render() {
        Pane pane = new Pane();

        Label labelcadastroTilt = new Label("Cadastro Aluno");
        labelcadastroTilt.setStyle("-fx-font-size: 4em;");
        labelcadastroTilt.relocate(100, 10);

        Label labelname = new Label("Nome:");
        labelname.setStyle("-fx-font-size: 20px;");
        labelname.relocate(30, 100);

        Label labelRA= new Label("RA:");
        labelRA.setStyle("-fx-font-size: 20px;");
        labelRA.relocate(240, 100);

        Label labelemail = new Label("E-mail:");
        labelemail.setStyle("-fx-font-size: 20px;");
        labelemail.relocate(30, 150);

        Label labeldtNasc = new Label("Data Nasc.:");
        labeldtNasc.setStyle("-fx-font-size: 20px;");
        labeldtNasc.relocate(280, 150);

        Label labelrg = new Label("RG:");
        labelrg.setStyle("-fx-font-size: 20px;");
        labelrg.relocate(30, 200);

        Label labelcpf = new Label("CPF:");
        labelcpf.setStyle("-fx-font-size: 20px;");
        labelcpf.relocate(170, 200);

        Label labeltel = new Label("Tel.:");
        labeltel.setStyle("-fx-font-size: 20px;");
        labeltel.relocate(340, 200);

        Label labelsenha = new Label("Senha: ");
        labelsenha.setStyle("-fx-font-size: 20px;");
        labelsenha.relocate(30, 245);

        TextField Fieldsenha = new TextField();
        Fieldsenha.setPrefSize(240, 20);
        Fieldsenha.relocate(100,250);

        TextField Fieldname = new TextField();
        Fieldname.setPrefSize(140, 20);
        Fieldname.relocate(95,103);

        TextField FieldRA = new TextField();
        FieldRA.setPrefSize(200, 20);
        FieldRA.relocate(275, 103);

        TextField Fieldemail = new TextField();
        Fieldemail.setPrefSize(180, 20);
        Fieldemail.relocate(95, 153);

        TextField FielddtNasc = new TextField();
        FielddtNasc.setPrefSize(100, 20);
        FielddtNasc.relocate(380, 153);

        TextField Fieldrg = new TextField();
        Fieldrg.setPrefSize(100, 20);
        Fieldrg.relocate(63, 203);

        TextField Fieldcpf = new TextField();
        Fieldcpf.setPrefSize(120, 20);
        Fieldcpf.relocate(210, 203);

        TextField FieldTel = new TextField();
        FieldTel.setPrefSize(100, 20);
        FieldTel.relocate(380, 203);

        Button buttonCadastrar = new Button("Cadastrar");
        buttonCadastrar.setPrefSize(250, 40);
        buttonCadastrar.relocate(130, 300);
        buttonCadastrar.setOnAction(e->{
            Aluno a = new Aluno();
            a.addAluno(FieldRA, Fieldname, Fieldemail, FielddtNasc, Fieldrg, Fieldcpf, FieldTel, Fieldsenha);
            exec = new Main();
            exec.exec("logintela");
        });

        Button voltar = new Button("Voltar");
        voltar.setPrefSize(250, 40);
        voltar.relocate(130, 345);
        voltar.setOnAction(e->{
            exec = new Main();
            exec.exec("logintela");
        });

        pane.getChildren().addAll(labelcadastroTilt, labelname, labelRA, labeldtNasc, labelemail, labelrg, labelcpf,labeltel
                , Fieldname, FieldRA, Fieldemail, FielddtNasc, Fieldrg, Fieldcpf, FieldTel, labelsenha, Fieldsenha, buttonCadastrar, voltar);

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

