package Projeto.View;

import Projeto.Executar;
import Projeto.Main;
import Projeto.Model.GrupoAlunos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class ListarGruposView {
    private Executar exec;

    public Node render() {
        ListView listaGrupos = new ListView();
        BorderPane bp = new BorderPane();
        GridPane gridPane = new GridPane();

        Label lblTitulo = new Label("Grupos de Alunos");
        lblTitulo.setStyle("-fx-alignment: CENTER");

        // Buscando grupos existentes na base de dados
        GrupoAlunos grupoAlunos = new GrupoAlunos();
        grupoAlunos.buscarGrupos();

        listaGrupos.getItems().addAll(grupoAlunos.getNomeGrupos());
        Button voltar = new Button("Voltar");

        voltar.setPrefSize(120, 50);

        voltar.setStyle("-fx-cursor: HAND;");

        voltar.setOnAction(e->{
            exec = new Main();
            exec.exec("hProfessor");
        });

        gridPane.add(voltar, 6, 1);

        bp.setTop(lblTitulo);
        bp.setCenter(listaGrupos);
        bp.setBottom(gridPane);

        // Alinhando Grid de botões ao meio
        bp.getBottom().setStyle("-fx-alignment: CENTER");
        bp.setMaxWidth(500);
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
