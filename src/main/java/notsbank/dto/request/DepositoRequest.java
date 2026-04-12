package notsbank.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class DepositoRequest {

    @NotNull(message = "O valor do depósito é obrigatório.")
    @DecimalMin(value = "0.01", message = "O valor do depósito deve ser maior que zero.")
    private Double valor;

    public DepositoRequest() {
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}