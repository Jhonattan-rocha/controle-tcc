package Projeto.View;

import Projeto.Executar;
import Projeto.Main;
import Projeto.Model.Connect.Connect;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class upload {

    private Executar exec;
    byte[] bytes;
    String nomearq;

    public Node render(){
        GridPane gridPane = new GridPane();
        GridPane painelTitulo = new GridPane();
        GridPane vol = new GridPane();
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);
        borderPane.setTop(painelTitulo);
        borderPane.setBottom(vol);


        Label titulo = new Label("Enviar trabalho");
        titulo.setStyle("-fx-font-size: 3em; -fx-font-weight: bold;");

        TextArea descricao = new TextArea();
        descricao.setPrefSize(100, 300);
        descricao.setPromptText("Insira a sua descrição do trabalho");

        TextField arquivo = new TextField();
        arquivo.setPrefSize(150, 50);
        arquivo.setEditable(false);
        arquivo.setMouseTransparent(true);
        arquivo.setFocusTraversable(false);



        Button buttonarq = new Button("Upload");
        buttonarq.setPrefSize(130,50);
        buttonarq.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File file = fileChooser.showOpenDialog(new Stage());
            String filePath = file.getAbsolutePath();
            nomearq = file.getName();
            arquivo.setText(file.getName());

            try {
                bytes = Files.readAllBytes(Paths.get(filePath));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });

        Button btnsave = new Button("Salvar");
        btnsave.setPrefSize(130, 50);
        btnsave.relocate(20,20);
        btnsave.setOnAction(e -> {
            try{
                Connection conn=Connect.connect();
                PreparedStatement stmd = null;
                if (conn != null){
                    Class.forName("org.sqlite.JDBC");
                    conn.setAutoCommit(false);
                    hAluno h = new hAluno();
                    String nome = h.lbgrupo.getText();
                    stmd = conn.prepareStatement("UPDATE GrupoAlunos SET trabalho=?, nomeTrabalho=?, descricao=? where nomeGrupo like '%"+nome+"%';");
                    stmd.setBytes(1, bytes);
                    stmd.setString(2,nomearq);
                    stmd.setString(3, descricao.getText());
                    stmd.executeUpdate();
                    conn.commit();
                    stmd.close();
                    conn.close();

                    Dialog<String> dialog = new Dialog<String>();
                    dialog.setTitle("Confirmação: ");
                    ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    dialog.setContentText("Upload Salvo");
                    dialog.getDialogPane().getButtonTypes().add(type);
                    dialog.showAndWait();

                }  } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
    });

        Button voltar = new Button("Voltar");
        voltar.setPrefSize(260, 50);
        voltar.setOnAction(e -> {
            exec = new Main();
            exec.exec("hAluno");
        });

        painelTitulo.add(titulo, 1, 0);
        painelTitulo.add(descricao, 1, 1);
        painelTitulo.add(arquivo, 1, 2);

        gridPane.add(buttonarq, 1, 3);
        gridPane.add(btnsave, 2, 3);
        vol.add(voltar, 1, 1);

        borderPane.setMaxWidth(270);
        borderPane.setMaxHeight(400);
        borderPane.setStyle("-fx-border-color: Black;" +
                " -fx-border-width: 4; " +
                "-fx-background-color:white;" +
                " -fx-border-radius:5px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 5, 0, 0, 0);" +
                "-fx-background-radius: 6px;");

        return borderPane;
    }
}
