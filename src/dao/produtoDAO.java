/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Matheus
 */
public class produtoDAO {
    // estatico por que só a instancia uma vez
    public static void read() throws ClassNotFoundException, SQLException{
        Connection con = ConexaoUtil.getConnection().Conn(); //criou a conexão com banco de dados
        String query = "SELECT * FROM produtos WHERE 1"; // criei a Query que eu queria
        PreparedStatement stmt = con.prepareStatement(query); //preparei a Query que eu queria
        ResultSet resultado = stmt.executeQuery(); // fiz ir e voltar com resultado do DB
        
        while(resultado.next()) {
            System.out.println("ID: "+ resultado.getString("id"));
            System.out.println("NOME: "+ resultado.getString("nome"));
            System.out.println("PREÇO: "+ resultado.getString("preco"));
        }
        
        con.close();
    }
    
    public static void create() throws ClassNotFoundException, SQLException {
        Connection con = ConexaoUtil.getConnection().Conn();
        String query = "INSERT INTO produtos (nome, preco, medida, tipo) "
                        + "VALUES ('Carne', 25.00, 1.0, 'Solido')";
        PreparedStatement stmt = con.prepareStatement(query);
        
        stmt.execute();
        con.close();
    }
    
    public static void update() throws ClassNotFoundException, SQLException {
        Connection con = ConexaoUtil.getConnection().Conn();
        String query = "UPDATE produtos SET nome ='sabao',"
                        + " preco = 1.20, "
                        + " medida = 400.0, "
                        + " tipo = 'solido' "
                        + " WHERE id = 2 ";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.execute();
        con.close(); // fechando conexão
    }
    
    public static void delete() throws ClassNotFoundException, SQLException {
        Connection con = ConexaoUtil.getConnection().Conn();
        String query = "DELETE FROM produtos WHERE id = 1";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.execute();
        con.close();
    }
}
