package agencia.agencia.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Conta {
    @Id
    private int numero;
    private double saldo;

    @ManyToOne
    private Agencia agencia;

    public Conta() {
    }

    public Conta(int numero, double saldo, Agencia agencia) {
        this.numero = numero;
        this.saldo = saldo;
        this.agencia = agencia;
    }

    public int getNumero() {
        return this.numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return this.saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Agencia getAgencia() {
        return this.agencia;
    }
    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }
}