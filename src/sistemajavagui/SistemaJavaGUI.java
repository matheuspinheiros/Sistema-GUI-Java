/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemajavagui;

import control.ProdutoControl;
import dao.ConexaoUtil;
import dao.produtoDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus
 */
public class SistemaJavaGUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Instanciando o controlador das funções
            ProdutoControl produtoControl = new ProdutoControl();
            
            // Mostra a Tabela
            produtoControl.addNaTabela();

            // configura as funções
            configurarProdutoControl(produtoControl);

            // abre a janela principal
            produtoControl.showJanela();
            
        } catch (ClassNotFoundException | SQLException ex) {
            // Loga o erro se algo falhar
            Logger.getLogger(SistemaJavaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // metódo para separar as funções do CRUD
    private static void configurarProdutoControl(ProdutoControl produtoControl) {
        produtoControl.novoProduto();         // Adiciona um novo produto
        produtoControl.selecionarProduto();   // Seleciona um produto
        produtoControl.atualizarProduto();    // Atualiza um produto
        produtoControl.removerProduto();      // Remove um produto
        produtoControl.configurarBotaoSair(); // Configura o botão de sair
    }
    
}
