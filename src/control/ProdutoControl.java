package control;

import dao.produtoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Produto;
import model.ProdutoLiquido;
import model.ProdutoSolido;
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
    
    public void novoProduto() {
        produtosView.getBtnSalvar().addActionListener(new ActionListener() {
            // sempre que capturar a ação do mouse ele vai fazer essa ação
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nome = produtosView.getTfdNome().getText();
                String preco = produtosView.getTfdPreco().getText();
                String medida = produtosView.getTfdMedida().getText();
                String tipo = produtosView.getcomBoxTipo().getSelectedItem().toString(); // item selecionado e transforma para String
                Produto produto = null;
                
                if(tipo.equals("Sólido")) {
                    produto = new ProdutoSolido(0, nome, Float.parseFloat(medida)); // faz a conversão de string para float
                    produto.setPreco(Double.parseDouble(preco)); // faz a conversão de String para Double
                } else if(tipo.equals("Líquido")) {
                    produto = new ProdutoLiquido(0, nome, Float.parseFloat(medida));
                    produto.setPreco(Double.parseDouble(preco));
                }
                
                try {
                    produtoDAO.create(produto);
                    System.out.println("Produto Criado com Sucesso.");
                } catch (Exception e) {
                    System.err.println("Houve erro ao tentar criar o Produto");
                }
            }
        });
    }
}
