/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemajavagui;

import dao.ConexaoUtil;

/**
 *
 * @author Matheus
 */
public class SistemaJavaGUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConexaoUtil.getConnection().Conn();
    }
    
}