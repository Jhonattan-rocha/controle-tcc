package Projeto;

import Projeto.Model.Aluno;
import Projeto.Model.Professor;
import Projeto.Model.Usuario;
import Projeto.View.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application implements Executar{
    private static final LoginTela lt = new LoginTela();
    private static final  avaliar av = new avaliar();
    private static final  EsqueciSenha eq = new EsqueciSenha();
    private static final  DadosView dv = new DadosView();
    private static final  CadastrarAluno ca = new CadastrarAluno();
    private static final  CadastroProfessor cp = new CadastroProfessor();
    private static final  hAluno hA = new hAluno();
    private static final GrupoAluno ga = new GrupoAluno();
    private static final upload up = new upload();
    private static final  hProfessor hP = new hProfessor();
    private static final grupos g = new grupos();
    private static final BancaProfessorView bancaProfessorView = new BancaProfessorView();
    private static final ListarGruposView listarGruposView = new ListarGruposView();
    public static long r;
    public static BorderPane bp = new BorderPane();

    public static boolean log = false;
    public static boolean us = false;

    @Override
    public void start(Stage stage) {
        bp.setStyle("-fx-background-image:url('image/fish.jpg')");
        Scene scn = new Scene(bp, 800, 600);
        menu();
        exec("logintela");
        stage.setScene(scn);
        stage.setTitle("Sistema de TCC");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void exec(String cmd) {
        menu();
        switch (cmd) {
            case "avaliacao":
                bp.setCenter(av.render());
                break;
            case "logintela":
                bp.setCenter(lt.render());
                break;
            case "esquecisenha":
                bp.setCenter(eq.render());
                break;
            case "dadosview":
                bp.setCenter(dv.render());
                break;
            case "cadastroaluno":
                bp.setCenter(ca.render());
                break;
            case "cadastroprofessor":
                bp.setCenter(cp.render());
                break;
            case "grupos":
                bp.setCenter(g.render());
                break;
            case "hAluno":
                bp.setCenter(hA.render());
                break;
            case "grupoAluno":
                bp.setCenter(ga.render());
                break;
            case "Upload":
                bp.setCenter(up.render());
                break;
            case "hProfessor":
                bp.setCenter(hP.render());
                break;
            case "bancaProfessor":
                bp.setCenter(bancaProfessorView.render());
                break;
            case "listarGruposAlunos":
                bp.setCenter(listarGruposView.render());
                break;
        }
    }

    public static void menu() {
        MenuBar mnuBar = new MenuBar();
        javafx.scene.control.Menu mnuTelas = new javafx.scene.control.Menu("Menu");
        mnuBar.getMenus().add(mnuTelas);

        MenuItem Login = new MenuItem("Login");
        MenuItem EsqueciSenha = new MenuItem("Recuperar senha");
        MenuItem cadastroA = new MenuItem("Cadastro de aluno");
        MenuItem cadastroP = new MenuItem("Cadastro de professor");


        MenuItem HomeProfessor = new MenuItem("Home Professor");
        MenuItem BancaProf = new MenuItem("Banca de Professores");
        MenuItem Avaliar = new MenuItem("Avaliar Grupos");


        MenuItem HomeAluno = new MenuItem("Home Aluno");
        MenuItem GroupAlunos = new MenuItem("Grupo de Alunos");
        MenuItem Finalizar = new MenuItem("Finalizar TCC");


        MenuItem DadosView = new MenuItem("Perfil");

        Login.setOnAction(e -> bp.setCenter(lt.render()));
        EsqueciSenha.setOnAction(e-> bp.setCenter(eq.render()));
        cadastroA.setOnAction(e-> bp.setCenter(ca.render()));

        GroupAlunos.setOnAction(e-> bp.setCenter(ga.render()));
        Finalizar.setOnAction(e-> bp.setCenter(up.render()));

        Usuario usuarioP = new Professor().buscar(Main.r);
        hP.setUsuario(usuarioP);
        hP.setR(Main.r);
        HomeProfessor.setOnAction(e->bp.setCenter(hP.render()));

        cadastroP.setOnAction(e-> bp.setCenter(cp.render()));
        Avaliar.setOnAction(e-> bp.setCenter(av.render()));

        Usuario usuario = new Aluno().buscar(Main.r);
        hA.setR(Main.r);
        hA.setUsuario(usuario);
        HomeAluno.setOnAction(e-> bp.setCenter(hA.render()));

        dv.setR(Main.r);
        dv.setUsuario(usuario);
        dv.setTitulo("Dados do aluno");
        if (usuario.getNome() == null){
            usuario = new Professor().buscar(Main.r);
            dv.setTitulo("Dados do professor");
        }
        DadosView.setOnAction(e-> bp.setCenter(dv.render()));


        //ADD VERIFICAÇÃO DE USUARIO
        if(log) {
            if(us){
                bp.setTop(mnuBar);
                mnuTelas.getItems().addAll(HomeAluno, GroupAlunos, DadosView, Finalizar);
            }else {
                bp.setTop(mnuBar);
                mnuTelas.getItems().addAll(HomeProfessor, BancaProf, Avaliar, DadosView);
            }

        } else {
            bp.setTop(mnuBar);
            mnuTelas.getItems().addAll(Login,cadastroA, cadastroP, EsqueciSenha);
        }
    }
}
