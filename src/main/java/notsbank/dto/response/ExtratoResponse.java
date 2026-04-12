package notsbank.dto.response;

import java.time.LocalDateTime;

public class ExtratoResponse {

    private String tipo;
    private Double valor;
    private LocalDateTime dataHora;
    private String descricao;

    public ExtratoResponse() {
    }

    public ExtratoResponse(String tipo, Double valor, LocalDateTime dataHora, String descricao) {
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = dataHora;
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getValor() {
        return valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getDescricao() {
        return descricao;
    }
}