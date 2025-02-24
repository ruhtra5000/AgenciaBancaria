package agencia.agencia.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Agencia {
    @Id
    private String nomeagencia;
    private String cidadeagencia;
    private String ativos;

    public Agencia() {
    }

    public Agencia(String nomeagencia, String cidadeagencia, String ativos) {
        this.nomeagencia = nomeagencia;
        this.cidadeagencia = cidadeagencia;
        this.ativos = ativos;
    }

    public String getNomeagencia() {
        return this.nomeagencia;
    }
    public void setNomeagencia(String nomeagencia) {
        this.nomeagencia = nomeagencia;
    }

    public String getCidadeagencia() {
        return this.cidadeagencia;
    }
    public void setCidadeagencia(String cidadeagencia) {
        this.cidadeagencia = cidadeagencia;
    }

    public String getAtivos() {
        return this.ativos;
    }
    public void setAtivos(String ativos) {
        this.ativos = ativos;
    }
}
