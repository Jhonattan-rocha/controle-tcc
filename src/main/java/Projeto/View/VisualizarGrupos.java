package Projeto.View;

import Projeto.Executar;
import Projeto.Main;
import Projeto.Model.Connect.Connect;
import Projeto.Model.GrupoAlunos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VisualizarGrupos {
    public String nomegrupo = "";
    public BorderPane bp = new BorderPane();
    private Executar exec;

    public BorderPane render() {
        GridPane gridPane1 = new GridPane();

        Label textoTitulo = new Label("Trabalho do grupo "+nomegrupo);
        textoTitulo.setStyle("-fx-font-size: 3em;");

        ListView<String> listaAlunos = new ListView<>();
        GrupoAlunos ga = new GrupoAlunos();
        ga.buscarGrupos();
        ObservableList<String> items = FXCollections.observableArrayList(ga.getNomeGrupos());
        listaAlunos.setItems(items);

        Button voltar = new Button("Voltar");
        voltar.setPrefSize(360, 50);
        voltar.setOnAction(e->{
            exec = new Main();
            exec.exec("hProfessor");
        });

        gridPane1.add(voltar, 6, 1);

        bp.setTop(textoTitulo);
        bp.setCenter(listaAlunos);
        bp.setBottom(gridPane1);

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
