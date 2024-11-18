/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Matheus
 */
public abstract class Produto {
    private int id;
    private String nome;
    private float medida;
    
    public Produto(int id, String nome, float medida) {
        this.id = id;
        this.nome = nome;
        this.medida = medida;
    }
    
    public abstract double getPreco();
    public abstract String getTipo();
}

