package notsbank.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class TransferenciaRequest {

    @NotNull(message = "O número da conta destino é obrigatório.")
    @Min(value = 1, message = "O número da conta destino deve ser maior que zero.")
    private Integer numeroDestino;

    @NotNull(message = "O valor da transferência é obrigatório.")
    @DecimalMin(value = "0.01", message = "O valor da transferência deve ser maior que zero.")
    private Double valor;

    public TransferenciaRequest() {
    }

    public Integer getNumeroDestino() {
        return numeroDestino;
    }

    public void setNumeroDestino(Integer numeroDestino) {
        this.numeroDestino = numeroDestino;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}