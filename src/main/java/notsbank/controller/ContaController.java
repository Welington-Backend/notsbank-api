package notsbank.controller;

import jakarta.validation.Valid;
import notsbank.dto.ApiResponse;
import notsbank.dto.request.CriarContaRequest;
import notsbank.dto.request.OperacaoRequest;
import notsbank.dto.request.SaqueRequest;
import notsbank.dto.request.TransferenciaRequest;
import notsbank.dto.response.ContaResponse;
import notsbank.dto.response.ExtratoResponse;
import notsbank.dto.response.TransferenciaResponse;
import notsbank.service.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import notsbank.dto.request.LoginRequest;
import notsbank.dto.request.CriarContaRequest;
import notsbank.dto.request.AlterarSenhaRequest;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ContaResponse>>> listar() {
        List<ContaResponse> contas = contaService.listarTodas();

        ApiResponse<List<ContaResponse>> resposta = new ApiResponse<>(
                200,
                true,
                contas,
                null
        );

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{numero}")
    public ResponseEntity<ApiResponse<ContaResponse>> buscar(@PathVariable Integer numero) {
        ContaResponse conta = contaService.buscarPorNumero(numero);

        ApiResponse<ContaResponse> resposta = new ApiResponse<>(
                200,
                true,
                conta,
                null
        );

        return ResponseEntity.ok(resposta);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ContaResponse>> criar(@Valid @RequestBody CriarContaRequest request) {
        ContaResponse contaCriada = contaService.criar(request);

        ApiResponse<ContaResponse> resposta = new ApiResponse<>(
                201,
                true,
                contaCriada,
                null
        );

        return ResponseEntity.status(201).body(resposta);
    }

    @PostMapping("/{numero}/deposito")
    public ResponseEntity<ApiResponse<ContaResponse>> depositar(
            @PathVariable Integer numero,
            @Valid @RequestBody OperacaoRequest request) {

        ContaResponse conta = contaService.depositar(numero, request.getValor());

        ApiResponse<ContaResponse> resposta = new ApiResponse<>(
                200,
                true,
                conta,
                null
        );

        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/{numero}/saque")
    public ResponseEntity<ApiResponse<ContaResponse>> sacar(
            @PathVariable Integer numero,
            @Valid @RequestBody SaqueRequest request) {

        ContaResponse conta = contaService.sacar(numero, request.getValor());

        ApiResponse<ContaResponse> resposta = new ApiResponse<>(
                200,
                true,
                conta,
                null
        );

        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/{numeroOrigem}/transferencia")
    public ResponseEntity<ApiResponse<TransferenciaResponse>> transferir(
            @PathVariable Integer numeroOrigem,
            @Valid @RequestBody TransferenciaRequest request) {

        TransferenciaResponse transferencia = contaService.transferir(
                numeroOrigem,
                request.getNumeroDestino(),
                request.getValor()
        );

        ApiResponse<TransferenciaResponse> resposta = new ApiResponse<>(
                200,
                true,
                transferencia,
                null
        );

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{numero}/extrato")
    public ResponseEntity<ApiResponse<List<ExtratoResponse>>> extrato(@PathVariable Integer numero) {

        List<ExtratoResponse> extrato = contaService.listarExtrato(numero);

        ApiResponse<List<ExtratoResponse>> resposta = new ApiResponse<>(
                200,
                true,
                extrato,
                null
        );

        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody LoginRequest request) {
        String mensagem = contaService.login(request.getNumero(), request.getSenha());

        ApiResponse<String> resposta = new ApiResponse<>(
                200,
                true,
                mensagem,
                null
        );

        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/{numero}/alterar-senha")
    public ResponseEntity<ApiResponse<String>> alterarSenha(
            @PathVariable Integer numero,
            @Valid @RequestBody AlterarSenhaRequest request) {

        String mensagem = contaService.alterarSenha(
                numero,
                request.getSenhaAtual(),
                request.getNovaSenha()
        );

        ApiResponse<String> resposta = new ApiResponse<>(
                200,
                true,
                mensagem,
                null
        );

        return ResponseEntity.ok(resposta);
    }
}