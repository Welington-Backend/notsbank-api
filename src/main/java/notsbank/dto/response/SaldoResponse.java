package notsbank.dto.response;

public class SaldoResponse {

    private Double saldo;

    public SaldoResponse(Double saldo) {
        this.saldo = saldo;
    }

    public Double getSaldo() {
        return saldo;
    }
}