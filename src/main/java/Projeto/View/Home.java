package Projeto.View;

import Projeto.Executar;
import Projeto.Main;
import Projeto.Model.Aluno;
import Projeto.Model.Professor;
import Projeto.Model.Usuario;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Home{

    public Executar exec;

    public Node render() {
        GridPane pane = new GridPane();

        Label reg = new Label(String.valueOf(Main.r));
        reg.setVisible(false);

        Text titulo = new Text("Menu");
        titulo.relocate(200, 100);
        titulo.setStyle("-fx-font-size: 3em;");

        Button voltar = new Button("Voltar");
        voltar.setPrefSize(360, 50);
        voltar.setOnAction(e -> {
            Main.log = false;
            exec = new Main();
            exec.exec("logintela");
        });

        Button perfil = new Button("Perfil");
        perfil.setPrefSize(360, 50);
        perfil.setOnAction(e ->{
            DadosView dp = new DadosView();
            Usuario usuario = new Aluno().buscar(Main.r);
            dp.setTitulo("Dados do aluno");
            if (usuario.getNome() == null){
                usuario = new Professor().buscar(Main.r);
                dp.setTitulo("Dados do professor");
            }
            dp.setR(Main.r);
            dp.setUsuario(usuario);
            Main.bp.setCenter(dp.render());
        });

        Button grupos = new Button("Grupos");
        grupos.setPrefSize(360, 50);
        grupos.setOnAction(e->{
            Main.bp.setCenter(new grupos().render());
        });

        pane.add(titulo, 1, 0);
        pane.add(reg, 0, 1);
        pane.add(perfil, 1, 3);
        pane.add(grupos, 1, 4);
        pane.add(voltar, 1, 5);

        return pane;

    }

}
