package Projeto.View;

import Projeto.Executar;
import Projeto.Main;
import Projeto.Model.Aluno;
import Projeto.Model.Connect.Connect;
import Projeto.Model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.sql.*;
import java.util.ArrayList;

public class GrupoAluno {
    public boolean edit = false;
    public ArrayList<String> nomes = new ArrayList<>();
    public ObservableList<String> items = FXCollections.observableArrayList(nomes);
    ListView<String> alunos = new ListView<>();
    private Executar exec;
    public TextField nomeGrupo = new TextField();


    public Node render(){
        GridPane gridPane = new GridPane();

        alunos.setItems(items);

        nomeGrupo.setPrefSize(150, 40);
        nomeGrupo.setPromptText("Nome do grupo: ");

        TextField nome = new TextField();
        nome.setPrefSize(150, 40);
        nome.setPromptText("Nome: ");

        Button salvarAluno = new Button("Adicionar");
        salvarAluno.setPrefSize(150, 40);
        salvarAluno.setOnMouseClicked(e->{
            if (nomes.size() == 3){
                return;
            }
            Aluno aluno = new Aluno();
            Usuario u = aluno.buscarPorEmail(nome.getText());
            System.out.println(u.getNome());
            if (u.getNome() != null){
                nomes.add(u.getNome());
                items = FXCollections.observableArrayList(nomes);
                alunos.setItems(items);
            }else{
                Dialog<String> dialog = new Dialog<String>();
                dialog.setTitle("Erro: ");
                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Email ou nome do aluno invalido");
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
            }
        });

        Button save = new Button("Salvar");
        save.setPrefSize(150, 40);
        save.setOnMouseClicked(e->{
            Aluno a = new Aluno();
            if(edit) {
                a.editSave(nomes, nomeGrupo.getText());
                edit =false;
            }else{
                a.SalvarGrupo(nomes, nomeGrupo.getText());
            }

        });

        Button voltar = new Button("Voltar");
        voltar.setPrefSize(360, 50);
        voltar.setOnAction(e -> {
            exec = new Main();
            exec.exec("hAluno");
        });

        Button deletar = new Button("Remover");
        deletar.setPrefSize(150, 40);
        deletar.setOnAction(e->{
            nomes.remove(alunos.getSelectionModel().getSelectedItem());
            items.remove(alunos.getSelectionModel().getSelectedItem());
        });

        gridPane.add(nome, 1, 0);
        gridPane.add(nomeGrupo, 1, 1);
        gridPane.add(alunos, 1, 2);
        gridPane.add(salvarAluno, 1, 3);
        gridPane.add(deletar, 1, 4);
        gridPane.add(save, 1, 5);
        gridPane.add(voltar, 1, 6);

        gridPane.setMaxWidth(320);
        gridPane.setMaxHeight(400);
        gridPane.setStyle("-fx-border-color: Black;" +
                " -fx-border-width: 4; " +
                "-fx-background-color:white;" +
                " -fx-border-radius:5px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 5, 0, 0, 0);" +
                "-fx-background-radius: 6px;");

        return gridPane;
    }

    public ArrayList<String> getNomes() {
        return nomes;
    }

    public void setNomes(ArrayList<String> nomes) {
        this.nomes = nomes;
    }

    public ObservableList<String> getItems() {
        return items;
    }

    public void setItems(ObservableList<String> items) {
        this.items = items;
    }
}
