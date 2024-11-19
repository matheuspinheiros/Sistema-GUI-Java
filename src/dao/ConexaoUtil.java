package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


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
    
    //DriverManager para conversacao com o banco de dados
    public Connection Conn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Conectado com Sucesso!");
        return DriverManager.getConnection(URL, usuario, senha);
    }
}
