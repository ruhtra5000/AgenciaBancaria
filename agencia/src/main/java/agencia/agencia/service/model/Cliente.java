package agencia.agencia.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cliente {
    @Id
    private String nomecliente;
    private String enderecocliente;
    private String cidadecliente;

    public Cliente() {
    }

    public Cliente(String nomecliente, String enderecocliente, String cidadecliente) {
        this.nomecliente = nomecliente;
        this.enderecocliente = enderecocliente;
        this.cidadecliente = cidadecliente;
    }

    public String getNomecliente() {
        return this.nomecliente;
    }
    public void setNomecliente(String nomecliente) {
        this.nomecliente = nomecliente;
    }

    public String getEnderecocliente() {
        return this.enderecocliente;
    }
    public void setEnderecocliente(String enderecocliente) {
        this.enderecocliente = enderecocliente;
    }

    public String getCidadecliente() {
        return this.cidadecliente;
    }
    public void setCidadecliente(String cidadecliente) {
        this.cidadecliente = cidadecliente;
    }
}
