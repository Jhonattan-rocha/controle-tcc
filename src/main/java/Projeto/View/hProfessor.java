package Projeto.View;

import Projeto.Executar;
import Projeto.Main;
import Projeto.Model.Connect.Connect;
import Projeto.Model.GrupoAlunos;
import Projeto.Model.GrupoProfessores;
import Projeto.Model.Usuario;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class hProfessor {
    private Executar exec;
    private Usuario usuario;

    private long r;
    private final Label lbNome = new Label();
    private final Label lbRP = new Label();
    private final Label lbEmail = new Label();
    private final Label lbDtNasc = new Label();
    public final Label lbBanca = new Label();

    // Flag para saber se o professor já possui banca
    private boolean isProfessorGrouped;



    public Node render() {
        BorderPane bp = new BorderPane();
        Pane painel = new Pane();
        Pane painel2 = new Pane();

        Label labTilt = new Label("Home Professor");
        labTilt.setStyle("-fx-font-size:3em;");
        labTilt.relocate(20,30);
        Label labNome = new Label("Nome Professor:");
        labNome.relocate(20,100);
        Label labEmail = new Label("Email FATEC:");
        labEmail.relocate(20,120);
        Label labRA = new Label("Registro Professor:");
        labRA.relocate(20,140);
        Label labDataNasc = new Label("Data Nascimento:");
        labDataNasc.relocate(20,160);
        Label labGroup = new Label("Banca Avaliativa:");
        labGroup.relocate(20,180);

        Label labDisciplina = new Label("Disciplinas:");
        labDisciplina.relocate(20,210);

        ListView<String> lista = new ListView<>();
        lista.setPrefSize(200,100);
        lista.relocate(20, 240);

        lbNome.relocate(130,100);

        lbRP.relocate(140, 140);

        lbEmail.relocate(110, 120);

        lbDtNasc.relocate(135, 160);


        popularRestrito(usuario, r);

        lbBanca.setText(busgrupo());
        lbBanca.relocate(140, 180);

        Button btnDeslogar = new Button("Logout");
        btnDeslogar.setPrefSize(150, 20);
        btnDeslogar.relocate(25, 200);
        btnDeslogar.setOnAction(e->{
            exec = new Main();
            exec.exec("logintela");
        });

        Button btneditperfil = new Button("Editar Perfil");
        btnDeslogar.setPrefSize(150, 20);
        btnDeslogar.relocate(25, 200);
        btnDeslogar.setOnAction(e->{
            exec = new Main();
            exec.exec("logintela");
        });

        Button btnGruposAlunos  = new Button("Grupos Alunos");
        btnGruposAlunos.setPrefSize(150, 20);
        btnGruposAlunos.relocate(25, 20);
        btnGruposAlunos.setOnAction(e->{
            exec = new Main();
            exec.exec("listarGruposAlunos");
        });

        Button btnedit  = new Button("Editar Banca");

        btnedit.setPrefSize(150, 20);
        btnedit.relocate(25, 60);
        btnedit.setOnAction(e->{
            GrupoProfessores g = new GrupoProfessores();
            GrupoProfessor ga = new GrupoProfessor();
            String[] integrantes = g.integrantes(lbBanca.getText()).split(",");
            ArrayList<String> aux = new ArrayList<>(Arrays.asList(integrantes));
            ga.edit = true;
            ga.nomes = aux;
            ga.items.setAll(aux);
            ga.nomeGrupo.setText(lbBanca.getText());
            ga.nomeGrupo.setEditable(false);
            Main.bp.setCenter(ga.render());
        });

        Button btnCriarBanca  = new Button("Criar Banca");

        btnCriarBanca.setPrefSize(150, 20);
        btnCriarBanca.relocate(25, 100);
        btnCriarBanca.setOnAction(event -> {
            exec = new Main();
            exec.exec("bancaProfessor");});

        Button btnAvaliarGrupos  = new Button("Avaliar Grupos");

        btnAvaliarGrupos.setPrefSize(150, 20);
        btnAvaliarGrupos.relocate(25, 140);
        btnAvaliarGrupos.setOnAction(event -> {
            exec = new Main();
            exec.exec("avaliacao");
        });


        painel2.setPrefWidth(200);
        painel2.getChildren().addAll(btnDeslogar, btnGruposAlunos, btnedit, btnCriarBanca, btnAvaliarGrupos);

        painel.getChildren().addAll(labTilt,labNome,labEmail,labRA,labDataNasc,labGroup,labDisciplina, lista, lbNome,lbDtNasc,lbBanca,lbRP,lbEmail);
        painel2.setStyle("-fx-background-color:#F0F0F0");
        bp.setLeft(painel);
        bp.setRight(painel2);

        bp.setMaxWidth(500);
        bp.setMaxHeight(400);
        bp.setStyle("-fx-border-color: Black;" +
                " -fx-border-width: 4; " +
                "-fx-background-color:white;" +
                " -fx-border-radius:5px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 5, 0, 0, 0);" +
                "-fx-background-radius: 6px;");


        // FILTROS PARA BOTÕES
        // Checar se o professor possui grupo
        var result = busgrupo();
        isProfessorGrouped = result != null && !result.equals("Sem Grupo") ? true : false;

        // Desativar botão se o professor estiver sem banca
        if(!isProfessorGrouped) {
            // Desativar botão de edição
            btnedit.setDisable(true);
            // Desativar botão de avaliação
            btnAvaliarGrupos.setDisable(true);
        } else {
            // Desativar botão de criar banca
            btnCriarBanca.setDisable(true);
        }

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
                String sql = "Select nome from GrupoProfessores where integrantes like '%"+lbNome.getText()+"%'";
                ResultSet dados = stmd.executeQuery(sql);
                String nome = dados.getString("nome");
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
        lbRP.setText(String.valueOf(r));
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
