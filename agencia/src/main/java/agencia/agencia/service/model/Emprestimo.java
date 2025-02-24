package agencia.agencia.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Emprestimo {
    @Id
    private int numero;
    private double valor;

    @ManyToOne
    private Agencia agencia;

    public Emprestimo() {
    }

    public Emprestimo(int numero, double valor, Agencia agencia) {
        this.numero = numero;
        this.valor = valor;
        this.agencia = agencia;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Agencia getAgencia() {
        return this.agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

}
