package Projeto.View;

import Projeto.Executar;
import Projeto.Main;
import Projeto.Model.Connect.Connect;
import Projeto.Model.GrupoAlunos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class avaliar{
    public String nomegrupo = "";
    public BorderPane bp = new BorderPane();
    private Executar exec;

    public BorderPane render() {
        GridPane gridPane1 = new GridPane();


        Label textoTitulo = new Label("Grupo "+nomegrupo);
        textoTitulo.setStyle("-fx-font-size: 3em;");

        ListView<String> listaAlunos = new ListView<>();
        GrupoAlunos ga = new GrupoAlunos();
        String[] integrantes = ga.integrantes(nomegrupo).split(",");
        ObservableList<String> items = FXCollections.observableArrayList(integrantes[0], integrantes[1], integrantes[2]);
        listaAlunos.setItems(items);

        Button buttonbaixar = new Button("Download");
        buttonbaixar.setOnAction(e -> {
            // file to bytes[]
            try {

                Connection conn = Connect.connect();

                Statement stmd = null;
                if (conn != null){
                    conn.setAutoCommit(false);
                    stmd = conn.createStatement();
                    String sql = "select trabalho, nomeTrabalho from GrupoAlunos where nomeGrupo='"+nomegrupo+"';";
                    ResultSet dados = stmd.executeQuery(sql);
                    try {
                        byte[] result = dados.getBytes("trabalho");
                        String filePath = "C:/Users/"+ System.getProperty("user.name") +"/Downloads/"+dados.getString("nomeTrabalho");
                        System.out.println(filePath);
                        File arquivo = new File(filePath);
                        FileOutputStream in = new FileOutputStream(arquivo);
                        in.write(result);
                        in.close();

                    } catch (NullPointerException e2) {
                        e2.printStackTrace();
                    } finally {
                        conn.commit();
                        stmd.close();
                        conn.close();
                    }
                }
                System.out.println("Teste bem feito");

            } catch (IOException e2) {
                e2.printStackTrace();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        Button voltar = new Button("Voltar");
        voltar.setPrefSize(360, 50);
        voltar.setOnAction(e->{
            exec = new Main();
            exec.exec("grupos");
        });


        gridPane1.add(buttonbaixar, 5, 1);
        gridPane1.add(voltar, 6, 1);

        bp.setTop(textoTitulo);
        bp.setCenter(listaAlunos);
        bp.setBottom(gridPane1);
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
