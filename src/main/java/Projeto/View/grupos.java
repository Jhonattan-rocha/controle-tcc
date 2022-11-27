package Projeto.View;

import Projeto.Executar;
import Projeto.Main;
import Projeto.Model.GrupoAlunos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;


public class grupos {

    private Executar exec;

    public Node render(){
        GridPane gp = new GridPane();

        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(20);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(60);
        ColumnConstraints c3 = new ColumnConstraints();
        c3.setPercentWidth(20);
        gp.getColumnConstraints().addAll(c1, c2, c3);

        Label titulo = new Label("Grupo de alunos");
        titulo.setStyle("-fx-font-size: 4em;");

        Button voltar = new Button("Voltar");
        voltar.setPrefSize(360, 50);
        voltar.setOnAction(e -> {
            exec = new Main();
            exec.exec("home");
        });

        ListView<String> grupos = new ListView<>();
        GrupoAlunos ga = new GrupoAlunos();
        ga.buscarGrupos();
        ObservableList<String> items = FXCollections.observableArrayList(ga.getNomeGrupos());
        grupos.setItems(items);
        grupos.setOnMouseClicked(e -> {
            avaliar avaliar = new avaliar();
            avaliar.nomegrupo = grupos.getSelectionModel().getSelectedItem();
            Main.bp.setCenter(avaliar.render());
        });

        gp.add(titulo, 1, 0);
        gp.add(grupos, 1, 1);
        gp.add(voltar, 1, 2);

        gp.setMaxWidth(320);
        gp.setMaxHeight(400);
        gp.setStyle("-fx-border-color: Black;" +
                " -fx-border-width: 4; " +
                "-fx-background-color:white;" +
                " -fx-border-radius:5px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 5, 0, 0, 0);" +
                "-fx-background-radius: 6px;");
        return gp;
    }
}
