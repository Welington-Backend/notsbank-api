package notsbank.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class SaqueRequest {

    @NotNull(message = "O valor do saque é obrigatório.")
    @DecimalMin(value = "0.01", message = "O valor do saque deve ser maior que zero.")
    private Double valor;

    public SaqueRequest() {
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}