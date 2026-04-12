package notsbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import notsbank.enums.TipoOperacao;

import java.time.LocalDateTime;

@Entity
@Table(name = "extrato")
public class Extrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoOperacao tipo;
    private Double valor;
    private LocalDateTime dataHora;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "conta_numero")
    @JsonIgnore
    private Conta conta;

    public Extrato() {
    }

    public Extrato(TipoOperacao tipo, Double valor, LocalDateTime dataHora, String descricao, Conta conta) {
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.conta = conta;
    }

    public Long getId() {
        return id;
    }

    public TipoOperacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoOperacao tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}