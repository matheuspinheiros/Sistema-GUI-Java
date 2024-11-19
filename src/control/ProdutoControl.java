package control;

import dao.produtoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
                    addNaTabela();    
                } catch (Exception e) {
                    System.err.println("Houve erro ao tentar criar o Produto");
                }
            }
        });
    }
    
    protected int id = -1;
    public void selecionarProduto() {
        produtosView.getProdutosTbl().addMouseListener(new MouseInputListener() {
        
            @Override
            public void mouseClicked(MouseEvent e) {
                // Setando a variavel id
                id = (int) produtosView.getProdutosTbl().getValueAt(
                        produtosView.getProdutosTbl().getSelectedRow(), 0); // Pega a linha selecionada

                // buscando produto no banco de dados pelo id
                Produto produto = null;
                try {
                    produto = produtoDAO.buscarProdutoPorId(id);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(ProdutoControl.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (produto != null) {
                    // Pegando os dados do produto diretamente do banco
                    String nome = produto.getNome();  
                    Float medida = produto.getMedida();  
                    String tipo;
                    double precoOriginal = 0.0;

                    // Verifica a instância das subclasses
                    if (produto instanceof ProdutoSolido) {
                        tipo = "solido";
                        precoOriginal = ((ProdutoSolido) produto).getValorOriginal(); // Downcast para acessar o método
                    } else if (produto instanceof ProdutoLiquido) {
                        tipo = "liquido";
                        precoOriginal = ((ProdutoLiquido) produto).getValorOriginal(); // Downcast para acessar o método
                    } else {
                        tipo = "desconhecido";
                    }

                    int indiceTipo = tipo.equals("solido") ? 0 : 1;

                    // Exibe o valor original no campo de preço
                    produtosView.getTfdNome().setText(nome);  
                    produtosView.getTfdPreco().setText(String.valueOf(precoOriginal));  // Preço original (sem imposto)
                    produtosView.getTfdMedida().setText(String.valueOf(medida));  
                    produtosView.getcomBoxTipo().setSelectedIndex(indiceTipo); 
                } else {
                    System.out.println("Produto não encontrado no banco de dados.");
                }
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
    
    public void atualizarProduto() {
        // Açao do Botão Atualizar
        produtosView.getBtnAtualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nome = produtosView.getTfdNome().getText();
                String preco = produtosView.getTfdPreco().getText();
                String medida = produtosView.getTfdMedida().getText();
                String tipo = (String) produtosView.getcomBoxTipo().getSelectedItem();
                Produto produto = null;
                    
                if(tipo.equals("Sólido")) {
                    produto = new ProdutoSolido(id, nome, Float.parseFloat(medida));
                    produto.setPreco(Double.parseDouble(preco), true);
                } else if(tipo.equals("Líquido")) {
                    produto = new ProdutoLiquido(id, nome, Float.parseFloat(medida));
                    produto.setPreco(Double.parseDouble(preco), true);
                }
                
                try {
                    produtoDAO.update(produto);
                    System.out.println("Produto Alterado com Sucesso.");
                    addNaTabela();
                } catch (Exception e) {
                    System.err.println("Houve erro ao tentar alterar o Produto");
                }
            }
        });
    }
    
    public void removerProduto() {
        produtosView.getBtnApagar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    produtoDAO.delete(id);
                    addNaTabela();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ProdutoControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void configurarBotaoSair() {
        produtosView.getBtnSair().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmacao = JOptionPane.showConfirmDialog(
                    null,
                    "Deseja realmente sair?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirmacao == JOptionPane.YES_OPTION) {
                    System.exit(0); // Encerra o programa
                }
            }
        });
    }
    
}
