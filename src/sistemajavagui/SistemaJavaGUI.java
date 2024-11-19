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
            // Log para erro
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
