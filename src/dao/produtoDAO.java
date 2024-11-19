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
    
    public static void create(Produto produto) throws ClassNotFoundException, SQLException {
        Connection con = ConexaoUtil.getConnection().Conn();
        String query = "INSERT INTO produtos (nome, preco, medida, tipo) "
                        + "VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(query); // setando as variaveis com o PreparedStatement
        stmt.setString(1, produto.getNome());
        stmt.setDouble(2, produto.getPreco());
        stmt.setFloat(3, produto.getMedida());
        stmt.setString(4, produto.getTipo());
        
        stmt.execute();
        con.close();
    }
    
    public static void update(Produto produto) throws ClassNotFoundException, SQLException {
        Connection con = ConexaoUtil.getConnection().Conn();
        String query = "UPDATE produtos SET "
                        + " nome = ?, "
                        + " preco = ?, "
                        + " medida = ?, "
                        + " tipo = ? "
                        + " WHERE id = ? ";
        PreparedStatement stmt = con.prepareStatement(query);
        
        stmt.setString(1, produto.getNome());
        stmt.setDouble(2, produto.getPreco());
        stmt.setFloat(3, produto.getMedida());
        stmt.setString(4, produto.getTipo());
        stmt.setInt(5, produto.getId());
        
        stmt.executeUpdate();
        con.close(); // fechando conexão
    }
    
    public static void delete() throws ClassNotFoundException, SQLException {
        Connection con = ConexaoUtil.getConnection().Conn();
        String query = "DELETE FROM produtos WHERE id = 1";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.execute();
        con.close();
    }
    
    public static Produto buscarProdutoPorId(int id) throws SQLException, ClassNotFoundException {
        Produto produto = null;
        try {
            Connection con = ConexaoUtil.getConnection().Conn();
            String query = "SELECT * FROM produtos WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            
            if (resultado.next()) {
                // preenchendo o objeto produto com os dados do banco
                String nome = resultado.getString("nome");
                Double preco = resultado.getDouble("preco");
                Float medida = resultado.getFloat("medida");
                String tipo = resultado.getString("tipo");
                
                if (tipo.equals("solido")) {
                produto = new ProdutoSolido(id, nome, medida);
                } else if (tipo.equals("liquido")) {
                produto = new ProdutoLiquido(id, nome, medida);
                }
                produto.setPreco(preco, false);  // Definir o preço original
            }
        con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return produto;   
    }
}
