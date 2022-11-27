package Projeto.View;

import Projeto.Executar;
import Projeto.Main;
import Projeto.Model.Aluno;
import Projeto.Model.Professor;
import Projeto.Model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class BancaProfessorView {
    public boolean edit = false;
    public ArrayList<String> integrantes = new ArrayList<>();
    public ObservableList<String> items = FXCollections.observableArrayList(integrantes);
    ListView<String> professores = new ListView<>();
    private Executar exec;
    public TextField nomeGrupo = new TextField();


    public Node render(){
        GridPane gridPane = new GridPane();

        professores.setItems(items);

        nomeGrupo.setPrefSize(150, 40);
        nomeGrupo.setPromptText("Nome da Banca: ");

        TextField nome = new TextField();
        nome.setPrefSize(150, 40);
        nome.setPromptText("Adicionar Professor Integrante: ");

        Button adicionarProfessor = new Button("Adicionar");
        adicionarProfessor.setPrefSize(150, 40);
        adicionarProfessor.setOnMouseClicked(e->{
            if (integrantes.size() == 3){
                return;
            }
            Professor professor = new Professor();
            Usuario u = professor.buscarPorEmail(nome.getText());
            System.out.println(u.getNome());
            if (u.getNome() != null){
                integrantes.add(u.getNome());
                items = FXCollections.observableArrayList(integrantes);
                professores.setItems(items);
            }else{
                Dialog<String> dialog = new Dialog<String>();
                dialog.setTitle("Erro: ");
                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Email ou nome do professor invalido");
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
            }
        });

        Button save = new Button("Salvar");
        save.setPrefSize(150, 40);
        save.setOnMouseClicked(e->{
            Professor professor = new Professor();
            if(edit) {
                professor.editSave(integrantes, nomeGrupo.getText());
                edit =false;
            }else{
                professor.SalvarGrupo(integrantes, nomeGrupo.getText());
            }

        });

        Button voltar = new Button("Voltar");
        voltar.setPrefSize(360, 50);
        voltar.setOnAction(e -> {
            exec = new Main();
            exec.exec("hProfessor");
        });

        Button deletar = new Button("Remover");
        deletar.setPrefSize(150, 40);
        deletar.setOnAction(e->{
            integrantes.remove(professores.getSelectionModel().getSelectedItem());
            items.remove(professores.getSelectionModel().getSelectedItem());
        });

        gridPane.add(nome, 1, 0);
        gridPane.add(nomeGrupo, 1, 1);
        gridPane.add(professores, 1, 2);
        gridPane.add(adicionarProfessor, 1, 3);
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

    public ArrayList<String> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(ArrayList<String> integrantes) {
        this.integrantes = integrantes;
    }

    public ObservableList<String> getItems() {
        return items;
    }

    public void setItems(ObservableList<String> items) {
        this.items = items;
    }
}
