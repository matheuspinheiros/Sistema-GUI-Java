/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Matheus
 */
public class ProdutoSolido extends Produto {
    
    private double valorFinal;

    public ProdutoSolido(int id, String nome, float medida) {
        super(id, nome, medida);
    }

    @Override
    public double getPreco() { 
        return valorFinal;
    }
    
    @Override
    public void setPreco(double valorOriginal) {
        this.valorFinal = valorOriginal + (valorOriginal * 0.30);
    }

    @Override
    public String getTipo() {
        return "solido";
    }
    
}
