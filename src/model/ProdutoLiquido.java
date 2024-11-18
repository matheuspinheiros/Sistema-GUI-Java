/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Matheus
 */
public class ProdutoLiquido extends Produto {

    public ProdutoLiquido(int id, String nome, float medida) {
        super(id, nome, medida);
    }

    @Override
    public double getPreco(double valorOriginal) {
        double valorFinal = valorOriginal + (valorOriginal * 0.10);
        return valorFinal;
    }

    @Override
    public String getTipo() {
        return "liquido";
    }
    
}
