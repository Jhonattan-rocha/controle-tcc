package Projeto.Model;

import Projeto.Main;
import Projeto.Model.Connect.Connect;
import Projeto.View.Alert;
import javafx.scene.control.*;

import java.sql.*;
import java.util.ArrayList;

public class Aluno extends Usuario{

    private long RA;

    public void addAluno(TextField FieldRA, TextField Fieldname, TextField Fieldemail, TextField FielddtNasc, TextField Fieldrg, TextField Fieldcpf, TextField FieldTel, TextField Fieldsenha){
            try {
                Aluno aluno = new Aluno();
                aluno.setRA(Long.parseLong(FieldRA.getText()));
                aluno.setNome(Fieldname.getText());
                aluno.setEmail(Fieldemail.getText());
                aluno.setDatNasc(String.valueOf(FielddtNasc.getText()));
                aluno.setRg(Fieldrg.getText());
                aluno.setCpf(Fieldcpf.getText());
                aluno.setTel(FieldTel.getText());
                aluno.setSenha(Fieldsenha.getText());
                aluno.cadastro(aluno);

                Dialog<String> dialog = new Dialog<>();
                dialog.setTitle("Confirmação: ");
                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Usuario salvo");
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
            } catch (Exception e){
                Dialog<String> dialog = new Dialog<>();
                dialog.setTitle("Confirmação: ");
                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText(e.getLocalizedMessage());
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
            }
    }
    public void cadastro(Aluno aluno) {
        Connection conn = Connect.connect();
        Statement stmd;
        try {
            if (conn != null){
                Class.forName("org.sqlite.JDBC");
                conn.setAutoCommit(false);
                stmd = conn.createStatement();
                String sql = "INSERT INTO Aluno (RA, nome, email, datNasc, rg, cpf, tel, senha) " +
                        "VALUES ('"+aluno.getRA()+"', '"+aluno.getNome()+"', '"+aluno.getEmail()+"'," +
                        "'"+aluno.getDatNasc()+"','"+aluno.getRg()+"','"+aluno.getCpf()+"'," +
                        "'"+aluno.getTel()+"', '"+aluno.getSenha()+"');";

                stmd.executeUpdate(sql);
                conn.commit();
                stmd.close();
                conn.close();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            Alert at = new Alert();
            at.setTexto("Informações duplicadas");
            at.setVoltar("logintela");
            Main.bp.setCenter(at.render());
        }
    }

    public boolean login(long R, String senha) {
        Connection conn = Connect.connect();
        Statement stmd;
        try {
            if (conn != null) {
                Class.forName("org.sqlite.JDBC");
                conn.setAutoCommit(false);
                stmd = conn.createStatement();
                String sql = "select RA, senha from Aluno where RA='" + R + "';";
                ResultSet dados = stmd.executeQuery(sql);
                boolean result;
                try {
                    result = dados.getString("senha").equals(senha);
                } catch (NullPointerException e) {
                    return false;
                }
                dados.close();
                stmd.close();
                conn.close();
                return result;
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        } catch (ClassNotFoundException e) {
            Alert at = new Alert();
            at.setTexto("Erro no sistema");
            at.setVoltar("logintela");
            Main.bp.setCenter(at.render());
        }
        return false;
    }

    public long getRA() {
        return RA;
    }

    public void setRA(long RA) {
        this.RA = RA;
    }

    @Override
    public Usuario buscar(long R) {
        Connection conn = Connect.connect();
        Statement stmd;
        try {
            if (conn != null){
                Class.forName("org.sqlite.JDBC");
                conn.setAutoCommit(false);
                stmd = conn.createStatement();
                String sql = "select * from Aluno where RA='"+R+"';";
                ResultSet dados = stmd.executeQuery(sql);
                Aluno aluno = new Aluno();
                try {
                    aluno.setNome(dados.getString("nome"));
                    aluno.setCpf(dados.getString("cpf"));
                    aluno.setDatNasc(dados.getString("datNasc"));
                    aluno.setEmail(dados.getString("email"));
                    aluno.setRA(R);
                    aluno.setTel(dados.getString("tel"));
                    aluno.setRg(dados.getString("rg"));
                } catch (Exception e){
                    dados.close();
                    stmd.close();
                    conn.close();
                    return null;
                }
                dados.close();
                stmd.close();
                conn.close();
                return aluno;
            }
        } catch (SQLException sql){
            sql.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Usuario buscarPorEmail(String email){
        Connection conn = Connect.connect();
        Statement stmd = null;
        String sql;
        try {
            if (conn != null){
                Class.forName("org.sqlite.JDBC");
                conn.setAutoCommit(false);
                stmd = conn.createStatement();
                if(email.contains("@")){
                    sql = "select * from Aluno where email='"+email+"';";
                }else {
                    sql = "select * from Aluno where nome = '" + email + "';";
                }
                ResultSet dados = stmd.executeQuery(sql);
                Aluno aluno = new Aluno();
                    aluno.setNome(dados.getString("nome"));
                    aluno.setCpf(dados.getString("cpf"));
                    aluno.setDatNasc(dados.getString("datNasc"));
                    aluno.setEmail(dados.getString("email"));
                    aluno.setRA(dados.getLong("RA"));
                    aluno.setTel(dados.getString("tel"));
                    aluno.setRg(dados.getString("rg"));

                dados.close();
                stmd.close();
                conn.close();
                return aluno;
            }
        } catch (SQLException sql1){
            sql1.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void update(Usuario usuario, long r){
        Connection conn = Connect.connect();
        Statement stmd;
        try {
            if (conn != null){
                Class.forName("org.sqlite.JDBC");
                conn.setAutoCommit(false);
                stmd = conn.createStatement();
                String sql = "UPDATE Aluno SET RA='"+r+"', nome='"+usuario.getNome()+"', email='"+usuario.getEmail()+"', " +
                        "datNasc='"+usuario.getDatNasc()+"', rg='"+usuario.getRg()+"', cpf='"+usuario.getCpf()+"', tel='"+usuario.getTel()+"'" +
                        "WHERE RA='"+r+"'";
                stmd.executeUpdate(sql);
                conn.commit();
                stmd.close();
                conn.close();
            }
        }catch (IllegalArgumentException | SQLException e){
            Alert at = new Alert();
            at.setTexto("Erro ao atualizar");
            at.setVoltar("logintela");
            Main.bp.setCenter(at.render());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void SalvarGrupo(ArrayList<String> nomes, String nomeGrupo) {
        String sql;
        Connection conn = Connect.connect();
        PreparedStatement stmd = null;
        try {
            if (conn != null){
                Class.forName("org.sqlite.JDBC");
                conn.setAutoCommit(false);

                    sql = "INSERT INTO GrupoAlunos (alunos, nomeGrupo) VALUES (?, ?);";


                stmd = conn.prepareStatement(sql);
                String en = String.join(",", nomes);
                stmd.setString(1, en);
                stmd.setString(2, nomeGrupo);
                stmd.executeUpdate();
                conn.commit();
                stmd.close();
                conn.close();

                Dialog<String> dialog = new Dialog<String>();
                dialog.setTitle("Confirmação: ");
                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Grupo salvo com sucesso");
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
            }
        } catch (ClassNotFoundException e1) {
            throw new RuntimeException(e1);
        } catch (Exception e2){
            e2.printStackTrace();
            Dialog<String> dialog = new Dialog<String>();
            dialog.setTitle("Confirmação: ");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Erro ao salvar o grupo");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }
    public void editSave(ArrayList<String> nomes, String nomeGrupo) {
        String sql = "";
        Connection conn = Connect.connect();
        PreparedStatement stmd = null;
        try {
            if (conn != null){
                Class.forName("org.sqlite.JDBC");
                conn.setAutoCommit(false);
                    sql = "Update GrupoAlunos Set alunos = ? where nomeGrupo = ?";
                }
                stmd = conn.prepareStatement(sql);
                String en = String.join(",", nomes);
                stmd.setString(1, en);
                stmd.setString(2, nomeGrupo);
                stmd.executeUpdate();
                conn.commit();
                stmd.close();
                conn.close();

                Dialog<String> dialog = new Dialog<String>();
                dialog.setTitle("Confirmação: ");
                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Grupo salvo com sucesso");
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
            } catch (SQLException e) {
            throw new RuntimeException(e);
            } catch (ClassNotFoundException e1) {
            throw new RuntimeException(e1);
            } catch (Exception e2){
            Dialog<String> dialog = new Dialog<String>();
            dialog.setTitle("Confirmação: ");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Erro ao salvar o grupo");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }
}
