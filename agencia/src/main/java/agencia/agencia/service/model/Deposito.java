package agencia.agencia.service.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Deposito {

    @Id
    Long id;
    String nomeCliente;
    int numeroConta;
    double valor;
    LocalDate dataDeposito;

    public Deposito() {
    }

    public Deposito(Long id, String nomeCliente, int numeroConta, double valor, LocalDate dataDeposito) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.numeroConta = numeroConta;
        this.valor = valor;
        this.dataDeposito = dataDeposito;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataDeposito() {
        return dataDeposito;
    }

    public void setDataDeposito(LocalDate dataDeposito) {
        this.dataDeposito = dataDeposito;
    }

}
