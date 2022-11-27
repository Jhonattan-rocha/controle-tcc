package Projeto.View;

import Projeto.Executar;
import Projeto.Main;
import Projeto.Model.Professor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;


public class CadastroProfessor{

    private Executar exec;

    public Node render(){
        Pane pane = new Pane();

        Label labelcadastroTilt = new Label("Cadastro Professor");
        labelcadastroTilt.setStyle("-fx-font-size: 4em;");
        labelcadastroTilt.relocate(50, 10);

        Label labelname = new Label("Nome:");
        labelname.setStyle("-fx-font-size: 20px;");
        labelname.relocate(30, 100);

        Label labelRP= new Label("Registro Prof.:");
        labelRP.setStyle("-fx-font-size: 20px;");
        labelRP.relocate(250, 100);

        Label labelemail = new Label("E-mail:");
        labelemail.setStyle("-fx-font-size: 20px;");
        labelemail.relocate(30, 150);

        Label labeldtNasc = new Label("Data Nasc.:");
        labeldtNasc.setStyle("-fx-font-size: 20px;");
        labeldtNasc.relocate(275, 150);

        Label labelrg = new Label("RG:");
        labelrg.setStyle("-fx-font-size: 20px;");
        labelrg.relocate(30, 200);

        Label labelcpf = new Label("CPF:");
        labelcpf.setStyle("-fx-font-size: 20px;");
        labelcpf.relocate(170, 200);

        Label labeltel = new Label("Tel.:");
        labeltel.setStyle("-fx-font-size: 20px;");
        labeltel.relocate(315, 200);

        Label labelsenha = new Label("Senha: ");
        labelsenha.setStyle("-fx-font-size: 20px;");
        labelsenha.relocate(30, 245);

        TextField Fieldsenha = new TextField();
        Fieldsenha.setPrefSize(240, 20);
        Fieldsenha.relocate(100,250);

        TextField Fieldname = new TextField();
        Fieldname.setPrefSize(150, 20);
        Fieldname.relocate(95,103);

        TextField FieldRP = new TextField();
        FieldRP.setPrefSize(120, 20);
        FieldRP.relocate(380, 103);

        TextField Fieldemail = new TextField();
        Fieldemail.setPrefSize(180, 20);
        Fieldemail.setPromptText("Email@fatec.gov.sp.br");
        Fieldemail.relocate(95, 153);

        TextField FielddtNasc = new TextField();
        FielddtNasc.setPrefSize(120, 20);
        FielddtNasc.relocate(380, 153);

        TextField Fieldrg = new TextField();
        Fieldrg.setPrefSize(100, 20);
        Fieldrg.relocate(67, 203);

        TextField Fieldcpf = new TextField();
        Fieldcpf.setPrefSize(100, 20);
        Fieldcpf.relocate(210, 203);

        TextField FieldTel = new TextField();
        FieldTel.setPrefSize(142, 20);
        FieldTel.relocate(350, 203);

        Button buttonCadastrar = new Button("Cadastrar");
        buttonCadastrar.setPrefSize(300, 40);
        buttonCadastrar.relocate(105, 290);
        buttonCadastrar.setOnAction(e->{
            Professor p = new Professor();
            p.addProfessor(FieldRP.getText(), Fieldname.getText(), Fieldemail.getText(), FielddtNasc.getText(), Fieldrg.getText(), Fieldcpf.getText(), FieldTel.getText(), Fieldsenha.getText());
            exec = new Main();
            exec.exec("logintela");
        });

        Button voltar = new Button("Voltar");
        voltar.setPrefSize(300, 40);
        voltar.relocate(105, 335);
        voltar.setOnAction(e->{
            exec = new Main();
            exec.exec("logintela");
        });

        pane.getChildren().addAll(labelcadastroTilt, labelname, labelRP, labeldtNasc, labelemail, labelrg, labelcpf,labeltel
                , Fieldname, FieldRP, Fieldemail, FielddtNasc, Fieldrg, Fieldcpf, FieldTel, labelsenha, Fieldsenha, buttonCadastrar, voltar);

        pane.setMaxWidth(520);
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