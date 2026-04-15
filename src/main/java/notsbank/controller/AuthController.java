package notsbank.controller;

import jakarta.validation.Valid;
import notsbank.dto.ApiResponse;
import notsbank.dto.request.LoginRequest;
import notsbank.dto.response.LoginResponse;
import notsbank.service.ContaService;
import notsbank.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final ContaService contaService;
    private final JwtService jwtService;

    public AuthController(ContaService contaService, JwtService jwtService) {
        this.contaService = contaService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {

        contaService.validarLogin(request.getNumero(), request.getSenha());

        String token = jwtService.gerarToken(request.getNumero());

        LoginResponse response = new LoginResponse(token);

        ApiResponse<LoginResponse> resposta = new ApiResponse<>(
                200,
                true,
                response,
                null
        );

        return ResponseEntity.ok(resposta);
    }
}