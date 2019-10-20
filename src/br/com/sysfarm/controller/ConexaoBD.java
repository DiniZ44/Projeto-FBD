/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.controller;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexaoBD {
    
     public static Statement stm; 
    public static ResultSet rs;
    public static final String URL_POSTGRES = "jdbc:postgresql://localhost:5432/sysfarm";
    public static final String LOGIN_POSTGRES = "postgres";
    public static final String SENHA_POSTGRES = "12345";

    public static Connection conect;  
    
    private ConexaoBD(){ // É privado para garantir apenas uma conexão 
    
    }
    public static Connection getInstance(){  
        try {
            if (conect == null || conect.isClosed()) { // verifica se já existi uma conexão existente
                conect = getConnection(URL_POSTGRES, LOGIN_POSTGRES, SENHA_POSTGRES); // chama a função de baixo 
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados");
        }
        
        return conect;
        
    }
       private static Connection getConnection(String url, String login, String senha) {
        try {
            return DriverManager.getConnection(url, login, senha); // faz a conexão com o banco de dados
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static void conectaSQL(String SQL){ // pra voçê fazer consulta com o banco de dados via sql. Não é usada
        try {
            stm= conect.createStatement(rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_READ_ONLY);
            rs= stm.executeQuery(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void desconectarConexao(){
        try {
            conect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexao: "+ ex.getMessage());
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
    public class ConxecaoBD {
    
    public Statement stm;
    public ResultSet rs;
    private String driver = "org.postgresql.Driver";
    private String caminho = "jdbc:postgresql://localhost:5433/farmacia";
    private String usuario = "postgres";
    private String senha= "Dsantos13";
    public Connection con;
    
    
    public void conexao (){
       
        try {
            System.setProperty("jbdc.Drivers", driver);
            con=DriverManager.getConnection(caminho, usuario, senha);
            JOptionPane.showMessageDialog(null, "Conectado com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(ConxecaoBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro de conexão:\n"+ ex.getMessage());
        }
    }
    
    public void desconecta(){
        try {
            con.close();
            JOptionPane.showMessageDialog(null, "Desconectado com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(ConxecaoBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro de desconexão:\n"+ ex.getMessage());
        }
    }
}

 */