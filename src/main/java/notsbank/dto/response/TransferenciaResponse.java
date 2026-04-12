package notsbank.dto.response;

public class TransferenciaResponse {

    private Integer contaOrigem;
    private Integer contaDestino;
    private Double valorTransferido;
    private Double saldoAtualOrigem;

    public TransferenciaResponse() {
    }

    public TransferenciaResponse(Integer contaOrigem, Integer contaDestino, Double valorTransferido, Double saldoAtualOrigem) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valorTransferido = valorTransferido;
        this.saldoAtualOrigem = saldoAtualOrigem;
    }

    public Integer getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Integer contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Integer getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Integer contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Double getValorTransferido() {
        return valorTransferido;
    }

    public void setValorTransferido(Double valorTransferido) {
        this.valorTransferido = valorTransferido;
    }

    public Double getSaldoAtualOrigem() {
        return saldoAtualOrigem;
    }

    public void setSaldoAtualOrigem(Double saldoAtualOrigem) {
        this.saldoAtualOrigem = saldoAtualOrigem;
    }
}