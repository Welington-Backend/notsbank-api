package notsbank.exception;

import notsbank.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> tratarValidacao(MethodArgumentNotValidException ex) {

        String mensagemErro = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(erro -> erro.getDefaultMessage())
                .orElse("Dados inválidos.");

        ApiResponse<Object> resposta = new ApiResponse<>(
                400,
                false,
                null,
                mensagemErro
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<ApiResponse<Object>> tratarSaldoInsuficiente(SaldoInsuficienteException ex) {

        ApiResponse<Object> resposta = new ApiResponse<>(
                400,
                false,
                null,
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    @ExceptionHandler(ContaNaoEncontradaException.class)
    public ResponseEntity<ApiResponse<Object>> tratarContaNaoEncontrada(ContaNaoEncontradaException ex) {

        ApiResponse<Object> resposta = new ApiResponse<>(
                404,
                false,
                null,
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

    @ExceptionHandler(ValorInvalidoException.class)
    public ResponseEntity<ApiResponse<Object>> tratarValorInvalido(ValorInvalidoException ex) {

        ApiResponse<Object> resposta = new ApiResponse<>(
                400,
                false,
                null,
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }
}