package notsbank.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class CriarContaRequest {

    @NotBlank(message = "O titular é obrigatório.")
    private String titular;

    @NotNull(message = "O saldo é obrigatório.")
    @PositiveOrZero(message = "O saldo não pode ser negativo.")
    private Double saldo;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 4, message = "A senha deve ter pelo menos 4 caracteres.")
    private String senha;

    public CriarContaRequest() {
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}