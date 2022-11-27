package Projeto.Model;

import Projeto.Model.Connect.Connect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GrupoProfessores {
    private ArrayList<String> nomeGrupo = new ArrayList<>();

    public void buscarGrupos(){
        Connection conn = Connect.connect();
        Statement stmd = null;
        try {
            if (conn != null){
                Class.forName("org.sqlite.JDBC");
                conn.setAutoCommit(false);
                stmd = conn.createStatement();
                String sql = "select nome, integrantes from GrupoProfessores;";
                ResultSet dados = stmd.executeQuery(sql);
                try {
                    while (dados.next()){
                        nomeGrupo.add(dados.getString("nome"));
                    }
                } catch (NullPointerException e){
                    e.printStackTrace();
                }
                dados.close();
                stmd.close();
                conn.close();
            }
        } catch (SQLException sql){
            sql.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String integrantes(String nome) {
        Connection conn = Connect.connect();
        Statement stmd = null;
        String result = " ";
        try {
            if (conn != null) {
                Class.forName("org.sqlite.JDBC");
                conn.setAutoCommit(false);
                stmd = conn.createStatement();
                String sql = "select integrantes from GrupoProfessores where nome='" + nome + "';";
                ResultSet dados = stmd.executeQuery(sql);
                try {
                    result = dados.getString("integrantes");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                dados.close();
                stmd.close();
                conn.close();
                return result;
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
