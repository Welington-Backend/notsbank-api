package notsbank.dto;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private int status;
    private boolean sucesso;
    private T dados;
    private String erro;

    public ApiResponse(int status, boolean sucesso, T dados, String erro) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.sucesso = sucesso;
        this.dados = dados;
        this.erro = erro;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public T getDados() {
        return dados;
    }

    public String getErro() {
        return erro;
    }
}