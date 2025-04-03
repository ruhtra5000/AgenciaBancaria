package agencia.agencia.service.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class HistoricoTransacoes {

    @Id
    private int id;
    private int numeroconta;
    private String nomecliente;
    private LocalDate datatransacao;
    private Double valor;
    private String tipotransacao;

    public HistoricoTransacoes() { 
    }

    public HistoricoTransacoes(int numeroconta, String nomeCliente, LocalDate datatransacao, Double valor, String tipotransacao, int id) { 
        this.numeroconta = numeroconta;
        this.nomecliente = nomeCliente;
        this.datatransacao = datatransacao;
        this.valor = valor;
        this.tipotransacao = tipotransacao;
        this.id = id;
    }

    public String getNomecliente() {
        return nomecliente;
    }

    public void setNomecliente(String nomecliente) {
        this.nomecliente = nomecliente;
    }

    public int getNumeroconta() { 
        return numeroconta;
    }

    public void setNumeroconta(int numeroconta) { 
        this.numeroconta = numeroconta;
    }

    public LocalDate getDatatransacao() {
        return datatransacao;
    }

    public void setDatatransacao(LocalDate datatransacao) {
        this.datatransacao = datatransacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTipotransacao() {
        return tipotransacao;
    }

    public void setTipotransacao(String tipoTransacao) {
        this.tipotransacao = tipoTransacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
