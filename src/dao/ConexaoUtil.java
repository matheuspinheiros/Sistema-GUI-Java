/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.DriverManager;


public class ConexaoUtil {
    private String caminho = "localhost";
    private String porta = "3306";
    private String nome = "sistemaDb";
    private String usuario = "root";
    private String senha = "root";
    
    private String URL = "jdbc:mysql://"+caminho+":"+porta+"/"+nome+"?useTimezone=true&serverTimezone=GMT";
    
    // "jdbc:mysql://localhost:3306/sistemaDb"
    
    public static ConexaoUtil getConnection() {
        ConexaoUtil conexaoUtil = null;
        if(conexaoUtil == null) {
            conexaoUtil = new ConexaoUtil();
            return conexaoUtil;
        } else {
            return null;
        }
    }
    
    public void Conn() {
        try {
            // cria a classe com o driver e faz a conexão com o Banco
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.getConnection(URL, usuario, senha);
            System.out.println("Conectado com Sucesso!");
        } catch(Exception ex) {
            System.err.println("ERRO AO CONECTAR COM O DB: \n" + ex);
        }
    }
}
