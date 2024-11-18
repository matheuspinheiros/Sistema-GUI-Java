package control;

import dao.produtoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Produto;
import view.ProdutosView;

/**
 *
 * @author Matheus
 */
public class ProdutoControl {
    protected ProdutosView produtosView = new ProdutosView();
    
    public void showJanela() {
        //deixar o JFrame visível
        produtosView.setVisible(true);
    }
    
    public void addNaTabela() throws ClassNotFoundException, SQLException {
        ArrayList<Produto> listaProdutos = new ArrayList();
        listaProdutos = produtoDAO.read();
        
        // Forma de Simples de Manipular a tabela
        DefaultTableModel produtosTblModel = (DefaultTableModel) produtosView.getProdutosTbl().getModel();
        produtosTblModel.setNumRows(0);
        
        // toda vez que percorrer vai adicionar uma nova linha com as colunas abaixo
        for(Produto produto : listaProdutos) {
            produtosTblModel.addRow(new Object[] {
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getMedida(),
                produto.getTipo()
            });
        }
    }
}
