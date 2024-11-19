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
        ProdutoControl produtoControl = new ProdutoControl();
        try {
            produtoControl.addNaTabela();
            produtoControl.novoProduto();
            produtoControl.selecionarProduto();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SistemaJavaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        produtoControl.showJanela();
    }
    
}
