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
    
    private double valorOriginal;
    private double valorFinal;

    public ProdutoLiquido(int id, String nome, float medida) {
        super(id, nome, medida);
    }

    @Override
    public double getPreco() { 
        return valorFinal;
    }
    
    @Override
    public void setPreco(double valorOriginal) {
        this.valorOriginal = valorOriginal;          // Armazena o valor original
        this.valorFinal = valorOriginal + (valorOriginal * 0.10); // Calcula o valor final
    }
    
    @Override
    public void setPreco(double valorOriginal, boolean diferencial) {
        this.valorOriginal = valorOriginal; // Sempre salva o valor original

        if (diferencial) {
            this.valorFinal = valorOriginal; // Define o valor final igual ao original
        } else {
            this.valorFinal = valorOriginal + (valorOriginal * 0.10); // Recalcula com imposto
        }
    }

    @Override
    public String getTipo() {
        return "liquido";
    }

    public double getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(double valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }
    
    
    
}
