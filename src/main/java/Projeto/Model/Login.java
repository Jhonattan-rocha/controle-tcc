package Projeto.Model;

import Projeto.Executar;
import Projeto.Main;
import Projeto.View.Alert;
import javafx.scene.control.TextField;


public class Login {
   private Executar exec;
    public void logar(TextField nomeField, TextField senhaField, boolean usuario){

        Aluno aluno = new Aluno();
        Professor professor = new Professor();
        try {
            boolean result;
            if(usuario){
                result = aluno.login(Long.parseLong(nomeField.getText()), senhaField.getText());
                if (result) {
                    exec = new Main();
                    Main.log = true;
                    Main.r = Long.parseLong(nomeField.getText());
                    Main.us = usuario;
                    exec.exec("hAluno");
                }  else {
                    Alert at = new Alert();
                    at.setTexto("Aluno ou senha incorreta");
                    at.setVoltar("logintela");
                    Main.bp.setCenter(at.render());
                }

            }else{
                result = professor.login(Long.parseLong(nomeField.getText()), senhaField.getText());
                if (result) {
                    exec = new Main();
                    Main.log = true;
                    Main.r = Long.parseLong(nomeField.getText());
                    exec.exec("hProfessor");
                } else {
                    Alert at = new Alert();
                    at.setTexto("Professor ou senha incorreta");
                    at.setVoltar("logintela");
                    Main.bp.setCenter(at.render());
                }

            }
        }catch (Exception e){
            Alert at = new Alert();
            at.setTexto("RA/RP em branco");
            at.setVoltar("logintela");
            Main.bp.setCenter(at.render());
        }
    }
}
