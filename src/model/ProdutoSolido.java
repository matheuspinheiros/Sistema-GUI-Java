package model;

/**
 *
 * @author Matheus
 */
public class ProdutoSolido extends Produto {
    
    private double valorOriginal;
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
        this.valorOriginal = valorOriginal;          
        this.valorFinal = valorOriginal + (valorOriginal * 0.30); // calcula o valor final
    }
    
    @Override
    public void setPreco(double valorOriginal, boolean diferencial) {
        this.valorOriginal = valorOriginal; // salva o valor original

        if (diferencial) {
            this.valorFinal = valorOriginal;
        } else {
            this.valorFinal = valorOriginal + (valorOriginal * 0.30); // calcula com imposto
        }
    }

    @Override
    public String getTipo() {
        return "solido";
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
