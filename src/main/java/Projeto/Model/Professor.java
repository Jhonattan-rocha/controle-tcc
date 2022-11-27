package Projeto.Model;

import Projeto.Main;
import Projeto.Model.Connect.Connect;
import Projeto.View.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;

public class Professor extends Usuario{
    private long RP;

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
                    sql = "select * from Professor where email='"+email+"';";
                }else {
                    sql = "select * from Professor where nome = '" + email + "';";
                }
                ResultSet dados = stmd.executeQuery(sql);
                Professor professor = new Professor();
                professor.setNome(dados.getString("nome"));
                professor.setCpf(dados.getString("cpf"));
                professor.setDatNasc(dados.getString("datNasc"));
                professor.setEmail(dados.getString("email"));
                professor.setRP(dados.getLong("RP"));
                professor.setTel(dados.getString("tel"));
                professor.setRg(dados.getString("rg"));

                dados.close();
                stmd.close();
                conn.close();
                return professor;
            }
        } catch (SQLException sql1){
            sql1.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void SalvarGrupo(ArrayList<String> integrantes, String nomeGrupo) {
        String sql;
        Connection conn = Connect.connect();
        PreparedStatement stmd = null;
        try {
            if (conn != null){
                Class.forName("org.sqlite.JDBC");
                conn.setAutoCommit(false);

                sql = "INSERT INTO GrupoProfessores (integrantes, nome) VALUES (?, ?);";


                stmd = conn.prepareStatement(sql);
                String integrantesConcatenados = String.join(",", integrantes);
                stmd.setString(1, integrantesConcatenados);
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
            Dialog<String> dialog = new Dialog<String>();
            dialog.setTitle("Confirmação: ");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Erro ao salvar o grupo");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }

    public void editSave(ArrayList<String> integrantes, String nomeGrupo) {
        String sql = "";
        Connection conn = Connect.connect();
        PreparedStatement stmd = null;
        try {
            if (conn != null) {
                Class.forName("org.sqlite.JDBC");
                conn.setAutoCommit(false);
                sql = "Update GrupoProfessores Set integrantes = ? where nome = ?";
            }
            assert conn != null;
            stmd = conn.prepareStatement(sql);
            String integrantesConcatenados = String.join(",", integrantes);
            stmd.setString(1, integrantesConcatenados);
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
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e2) {
            Dialog<String> dialog = new Dialog<String>();
            dialog.setTitle("Confirmação: ");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Erro ao salvar o grupo");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }
    public void addProfessor(String FieldRP, String Fieldname,
                             String Fieldemail, String FielddtNasc, String Fieldrg, String Fieldcpf,
                             String FieldTel, String Fieldsenha) {
        try {
            Professor professor = new Professor();
            professor.setRP(Long.parseLong(FieldRP));
            professor.setNome(Fieldname);
            professor.setEmail(Fieldemail);
            professor.setDatNasc(FielddtNasc);
            professor.setRg(Fieldrg);
            professor.setCpf(Fieldcpf);
            professor.setTel(FieldTel);
            professor.setSenha(Fieldsenha);
            professor.cadastro(professor);

            Dialog<String> dialog = new Dialog<String>();
            dialog.setTitle("Confirmação: ");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Usuario salvo");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();

        } catch (Exception e) {
            Dialog<String> dialog = new Dialog<String>();
            dialog.setTitle("Confirmação: ");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText(e.getLocalizedMessage());
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }

    public void cadastro(Professor professor) {
        Connection conn = Connect.connect();
        Statement stmd = null;
        try {
            if (conn != null){
                Class.forName("org.sqlite.JDBC");
                conn.setAutoCommit(false);
                stmd = conn.createStatement();
                String sql = "INSERT INTO Professor (RP, nome, email, datNasc, rg, cpf, tel, senha) " +
                        "VALUES ('"+professor.getRP()+"', '"+professor.getNome()+"', '"+professor.getEmail()+"'," +
                        "'"+professor.getDatNasc()+"','"+professor.getRg()+"','"+professor.getCpf()+"'," +
                        "'"+professor.getTel()+"', '"+professor.getSenha()+"');";
                stmd.executeUpdate(sql);
                conn.commit();
                stmd.close();
                conn.close();
            }
        }catch (IllegalArgumentException | SQLException e){
            Alert at = new Alert();
            at.setTexto(e.getMessage());
            at.setVoltar("logintela");
            Main.bp.setCenter(at.render());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            Alert at = new Alert();
            at.setTexto("Erro no sistema");
            at.setVoltar("logintela");
            Main.bp.setCenter(at.render());
        }
    }
    public boolean login(long RP, String senha){
        Connection conn = Connect.connect();
        Statement stmd = null;
        try {
            if (conn != null){
                Class.forName("org.sqlite.JDBC");
                conn.setAutoCommit(false);
                stmd = conn.createStatement();
                String sql = "select RP, senha from Professor where RP='"+RP+"';";
                ResultSet dados = stmd.executeQuery(sql);
                boolean result = false;
                try {
                    result = dados.getString("senha").equals(senha);
                } catch (NullPointerException e){
                    return false;
                }
                dados.close();
                stmd.close();
                conn.close();
                return result;
            }
        } catch (SQLException sql){
            sql.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public long getRP() {
        return RP;
    }

    public void setRP(long RP) {
        this.RP = RP;
    }

    @Override
    public Usuario buscar(long R) {
        Connection conn = Connect.connect();
        Statement stmd = null;
        try {
            if (conn != null){
                Class.forName("org.sqlite.JDBC");
                conn.setAutoCommit(false);
                stmd = conn.createStatement();
                String sql = "select * from Professor where RP='"+R+"';";
                ResultSet dados = stmd.executeQuery(sql);
                Professor professor = new Professor();
                try {
                    professor.setNome(dados.getString("nome"));
                    professor.setCpf(dados.getString("cpf"));
                    professor.setDatNasc(dados.getString("datNasc"));
                    professor.setEmail(dados.getString("email"));
                    professor.setRP(R);
                    professor.setTel(dados.getString("tel"));
                    professor.setRg(dados.getString("rg"));
                } catch (Exception e){
                    return null;
                }
                dados.close();
                stmd.close();
                conn.close();
                return professor;
            }
        } catch (SQLException sql){
            sql.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void update(Usuario usuario, long r){
        Connection conn = Connect.connect();
        Statement stmd = null;
        try {
            if (conn != null){
                Class.forName("org.sqlite.JDBC");
                conn.setAutoCommit(false);
                stmd = conn.createStatement();
                String sql = "UPDATE Professor SET RP='"+r+"', nome='"+usuario.getNome()+"', email='"+usuario.getEmail()+"', " +
                        "datNasc='"+usuario.getDatNasc()+"', rg='"+usuario.getRg()+"', cpf='"+usuario.getCpf()+"', tel='"+usuario.getTel()+"'" +
                        "WHERE RP='"+r+"'";
                stmd.executeUpdate(sql);
                conn.commit();
                stmd.close();
                conn.close();
            }
        }catch (IllegalArgumentException | SQLException e){
//            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception ignored){
        }
    }
}
