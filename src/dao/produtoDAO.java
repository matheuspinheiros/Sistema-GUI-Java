/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Produto;
import model.ProdutoLiquido;
import model.ProdutoSolido;

/**
 *
 * @author Matheus
 */
public class produtoDAO {
    // estatico por que só a instancia uma vez
    public static ArrayList read() throws ClassNotFoundException, SQLException{
        ArrayList<Produto> listaDeProdutos = new ArrayList();
        Produto produto = null;
        Connection con = ConexaoUtil.getConnection().Conn(); //criou a conexão com banco de dados
        String query = "SELECT * FROM produtos WHERE 1"; // criei a Query que eu queria
        PreparedStatement stmt = con.prepareStatement(query); //preparei a Query que eu queria
        ResultSet resultado = stmt.executeQuery(); // fiz ir e voltar com resultado do DB
        
        // laço para consultar cada produto
        while(resultado.next()) {
            //tem-se if para caso o produto seja solido ou liquido, pois tem uma diferença no preço e no imposto
            if(resultado.getString("tipo").equals("solido")){
                produto = new ProdutoSolido(
                                resultado.getInt("id"), 
                                resultado.getString("nome"), 
                                resultado.getFloat("medida"));
                produto.setPreco(resultado.getDouble("preco"));
            } else if(resultado.getString("tipo").equals("liquido")) {
                produto = new ProdutoLiquido(
                                resultado.getInt("id"), 
                                resultado.getString("nome"), 
                                resultado.getFloat("medida"));
                produto.setPreco(resultado.getDouble("preco"));
            }
            listaDeProdutos.add(produto);
        }
        
        con.close();
        return listaDeProdutos;
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
