package Projeto.View;

import Projeto.Executar;
import Projeto.Main;
import Projeto.Model.Usuario;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class DadosView {

    private Executar exec;

    private String titulo;

    private Usuario usuario;

    private long r;
    private final TextField Fieldname = new TextField();
    private final TextField FieldRA = new TextField();
    private final TextField Fieldemail = new TextField();
    private final TextField FielddtNasc = new TextField();
    private final TextField Fieldrg = new TextField();
    private final TextField Fieldcpf = new TextField();
    private final TextField FieldTel = new TextField();

    public Pane render() {
        Pane pane = new Pane();


        Label labelcadastroTilt = new Label("Dados do "+titulo);
        labelcadastroTilt.setStyle("-fx-font-size: 4em;");
        labelcadastroTilt.relocate(20, 10);

        Label labelname = new Label("Nome:");
        labelname.setStyle("-fx-font-size: 20px;");
        labelname.relocate(30, 100);

        String registro;
        if (titulo.equalsIgnoreCase("Aluno")){
            registro = "RA:";
        }else {
            registro = "RP:";
        }
        Label labelR= new Label(registro);
        labelR.setStyle("-fx-font-size: 20px;");
        labelR.relocate(400, 100);

        Label labelemail = new Label("E-mail:");
        labelemail.setStyle("-fx-font-size: 20px;");
        labelemail.relocate(30, 150);

        Label labeldtNasc = new Label("Data Nasc.:");
        labeldtNasc.setStyle("-fx-font-size: 20px;");
        labeldtNasc.relocate(400, 150);

        Label labelrg = new Label("RG:");
        labelrg.setStyle("-fx-font-size: 20px;");
        labelrg.relocate(30, 200);

        Label labelcpf = new Label("CPF:");
        labelcpf.setStyle("-fx-font-size: 20px;");
        labelcpf.relocate(200, 200);

        Label labeltel = new Label("Tel.:");
        labeltel.setStyle("-fx-font-size: 20px;");
        labeltel.relocate(400, 200);

        Fieldname.setPrefSize(240, 20);
        Fieldname.relocate(150,103);
        Fieldname.setEditable(false);
        Fieldname.setMouseTransparent(true);
        Fieldname.setFocusTraversable(false);

        FieldRA.setPrefSize(155, 20);
        FieldRA.relocate(435, 103);
        FieldRA.setEditable(false);
        FieldRA.setMouseTransparent(true);
        FieldRA.setFocusTraversable(false);

        Fieldemail.setPrefSize(300, 20);
        Fieldemail.relocate(95, 153);

        FielddtNasc.setPrefSize(90, 20);
        FielddtNasc.relocate(500, 153);

        Fieldrg.setPrefSize(133, 20);
        Fieldrg.relocate(63, 203);
        Fieldrg.setEditable(false);
        Fieldrg.setMouseTransparent(true);
        Fieldrg.setFocusTraversable(false);

        Fieldcpf.setPrefSize(155, 20);
        Fieldcpf.relocate(240, 203);
        Fieldcpf.setEditable(false);
        Fieldcpf.setMouseTransparent(true);
        Fieldcpf.setFocusTraversable(false);

        FieldTel.setPrefSize(152, 20);
        FieldTel.relocate(440, 203);

        popularRestrito(usuario, r);

        Button update = new Button("Salvar");
        update.setPrefSize(320, 50);
        update.relocate(165, 315);
        update.setOnAction(e -> {
            try {
                usuario.setNome(Fieldname.getText());
                usuario.setEmail(Fieldemail.getText());
                usuario.setCpf(Fieldcpf.getText());
                usuario.setRg(Fieldrg.getText());
                usuario.setDatNasc(FielddtNasc.getText());
                usuario.setRg(Fieldrg.getText());
                usuario.setTel(FieldTel.getText());
                usuario.update(usuario, r);
                DadosView tela = new DadosView();
                tela.setR(r);
                tela.setTitulo(titulo);
                tela.setUsuario(usuario);
            }catch (Exception e2){
                DadosView tela = new DadosView();
                tela.setR(r);
                tela.setTitulo(titulo);
                tela.setUsuario(usuario);
            }
        });

        Button voltar = new Button("Voltar");
        voltar.setPrefSize(320, 50);
        voltar.relocate(165, 365);
        voltar.setOnAction(e -> {
            exec = new Main();
            exec.exec("home");
        });

        pane.getChildren().addAll(labelcadastroTilt, labelname, labelR, labeldtNasc, labelemail, labelrg, labelcpf,labeltel
                , Fieldname, FieldRA, Fieldemail, FielddtNasc, Fieldrg, Fieldcpf, FieldTel, update, voltar);

        pane.setMaxWidth(650);
        pane.setMaxHeight(500);
        pane.setStyle("-fx-border-color: Black;" +
                " -fx-border-width: 4; " +
                "-fx-background-color:white;" +
                " -fx-border-radius:5px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 5, 0, 0, 0);" +
                "-fx-background-radius: 6px;");
        return pane;
    }

    private void popularRestrito(Usuario usuario, long r) {
        Fieldname.setText(usuario.getNome());
        FieldRA.setText(String.valueOf(r));
        Fieldemail.setText(usuario.getEmail());
        FielddtNasc.setText(usuario.getDatNasc());
        Fieldrg.setText(usuario.getRg());
        Fieldcpf.setText(usuario.getCpf());
        FieldTel.setText(usuario.getTel());
        FieldRA.setText(String.valueOf(r));
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setR(long r) {
        this.r = r;
    }
}
