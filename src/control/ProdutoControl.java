package control;

import dao.produtoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.event.MouseInputListener;
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
                    produto.setPreco(Double.parseDouble(preco), true); // faz a conversão de String para Double
                } else if(tipo.equals("Líquido")) {
                    produto = new ProdutoLiquido(0, nome, Float.parseFloat(medida));
                    produto.setPreco(Double.parseDouble(preco), true);
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
    
    public void selecionarProduto() {
        produtosView.getProdutosTbl().addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // setando as variaveis da linha selecionada da tabela visual
                int valor = (int) produtosView.getProdutosTbl().getValueAt(
                        produtosView.getProdutosTbl().getSelectedRow(), 0); // pegue a linha selecionada
                String nome = (String) produtosView.getProdutosTbl().getValueAt(
                        produtosView.getProdutosTbl().getSelectedRow(), 1);
                Double preco = (Double) produtosView.getProdutosTbl().getValueAt(
                        produtosView.getProdutosTbl().getSelectedRow(), 2);
                Float medida = (Float) produtosView.getProdutosTbl().getValueAt(
                        produtosView.getProdutosTbl().getSelectedRow(), 3);
                String tipo = (String) produtosView.getProdutosTbl().getValueAt(
                        produtosView.getProdutosTbl().getSelectedRow(), 4);
                int indiceTipo = -1;
                
                if(tipo.equals("solido")) {
                    indiceTipo = 0;
                } else if(tipo.equals("liquido")) {
                    indiceTipo = 1;
                }
                
                produtosView.getTfdNome().setText(nome); // Nome no campo de nome
                produtosView.getTfdPreco().setText(String.valueOf(preco)); // Preço no campo de preço
                produtosView.getTfdMedida().setText(String.valueOf(medida)); // Medida no campo de medida
                produtosView.getcomBoxTipo().setSelectedIndex(indiceTipo);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            //    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            //    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            //    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseExited(MouseEvent e) {
            //    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            //    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            //    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }
    
}
