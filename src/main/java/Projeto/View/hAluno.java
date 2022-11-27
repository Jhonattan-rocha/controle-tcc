package Projeto.View;

import Projeto.Executar;
import Projeto.Main;
import Projeto.Model.Connect.Connect;
import Projeto.Model.GrupoAlunos;
import Projeto.Model.Usuario;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class hAluno {
    private Executar exec;
    private Usuario usuario;

    private long r;
    private final Label lbNome = new Label();
    private final Label lbRA = new Label();
    private final Label lbEmail = new Label();
    private final Label lbDtNasc = new Label();
    public final Label lbgrupo = new Label();




    public Node render() {

        BorderPane bp = new BorderPane();
        Pane painel = new Pane();
        Pane painel2 = new Pane();

        Label labTilt = new Label("Home Aluno");
        labTilt.setStyle("-fx-font-size:3em;");
        labTilt.relocate(40,30);
        Label labNome = new Label("Nome Aluno:");
        labNome.relocate(40,100);
        Label labEmail = new Label("Email FATEC:");
        labEmail.relocate(40,120);
        Label labRA = new Label("Registro Aluno:");
        labRA.relocate(40,140);
        Label labDataNasc = new Label("Data Nascimento:");
        labDataNasc.relocate(40,160);
        Label labGroup = new Label("Grupo:");
        labGroup.relocate(40,180);

        lbNome.relocate(120,100);

        lbRA.relocate(126, 140);

        lbEmail.relocate(110, 120);

        lbDtNasc.relocate(140, 160);


        popularRestrito(usuario, r);

        lbgrupo.setText(busgrupo());
        lbgrupo.relocate(100, 180);

        Button btnDeslogar = new Button("Logout");
        btnDeslogar.setPrefSize(150, 20);
        btnDeslogar.relocate(25, 200);
        btnDeslogar.setOnAction(e->{
            exec = new Main();
            exec.exec("logintela");
        });

        Button btnVisualizarGrupo  = new Button("Visualizar Grupo");
        btnVisualizarGrupo.setPrefSize(150, 20);
        btnVisualizarGrupo.relocate(25, 20);
        btnVisualizarGrupo.setOnAction(e->{
            GrupoAlunos ga = new GrupoAlunos();
            ga.integrantes(lbgrupo.getText());
            avaliar av = new avaliar();
            av.nomegrupo = lbgrupo.getText();
            av.bp = av.render();
            av.bp.getBottom().setVisible(false);
            Main.bp.setCenter(av.bp);
            exec = new Main();
            exec.exec("avaliar");
        });

        Button btnedit  = new Button("Editar Grupo");
        btnedit.setPrefSize(150, 20);
        btnedit.relocate(25, 60);
        btnedit.setOnAction(e->{
            GrupoAlunos g = new GrupoAlunos();
            GrupoAluno ga = new GrupoAluno();
            String[] integrantes = g.integrantes(lbgrupo.getText()).split(",");
            ArrayList<String> aux = new ArrayList<>(Arrays.asList(integrantes));
            ga.edit = true;
            ga.nomes = aux;
            ga.items.setAll(aux);
            ga.nomeGrupo.setText(lbgrupo.getText());
            ga.nomeGrupo.setEditable(false);
            Main.bp.setCenter(ga.render());
        });

        Button btnCriarGrupo  = new Button("Criar Grupo");
        btnCriarGrupo.setPrefSize(150, 20);
        btnCriarGrupo.relocate(25, 100);
        btnCriarGrupo.setOnAction(event -> {
            exec = new Main();
            exec.exec("grupoAluno");});

        Button btnFimTcc  = new Button("Finalizar TCC");
        btnFimTcc.setPrefSize(150, 20);
        btnFimTcc.relocate(25, 140);
        btnFimTcc.setOnAction(event -> {
            exec = new Main();
            exec.exec("Upload");
        });

        if(Objects.equals(lbgrupo.getText(), "") || lbgrupo.getText() == null ) {
            btnVisualizarGrupo.setVisible(false);
            btnedit.setVisible(false);
            btnFimTcc.setVisible(false);
        }


        painel2.setPrefWidth(200);
        painel2.getChildren().addAll(btnDeslogar, btnVisualizarGrupo, btnedit, btnCriarGrupo, btnFimTcc);

        painel.getChildren().addAll(labTilt,labNome,labEmail,labRA,labDataNasc,labGroup,lbNome,lbDtNasc,lbRA,lbEmail,lbgrupo);
        painel2.setStyle("-fx-background-color:#F0F0F0");
        bp.setLeft(painel);
        bp.setRight(painel2);

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

    private String busgrupo() {
        Connection conn = Connect.connect();
        Statement stmd = null;
        try {
            if (conn != null){
                Class.forName("org.sqlite.JDBC");
                conn.setAutoCommit(false);
                stmd = conn.createStatement();
                String sql = "Select nomegrupo from GrupoAlunos where alunos like '%"+lbNome.getText()+"%'";
                ResultSet dados = stmd.executeQuery(sql);
                String nome = dados.getString("nomeGrupo");
                stmd.close();
                conn.close();
                dados.close();
                return nome;
            }
        } catch (ClassNotFoundException | SQLException e1) {
            throw new RuntimeException(e1);
        }
        return "Sem Grupo";
    }

    private void popularRestrito(Usuario usuario, long r) {
        lbNome.setText(usuario.getNome());
        lbRA.setText(String.valueOf(r));
        lbEmail.setText(usuario.getEmail());
        lbDtNasc.setText(usuario.getDatNasc());

    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setR(long r) {
        this.r = r;
    }
}
