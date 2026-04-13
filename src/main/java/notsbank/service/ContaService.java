package notsbank.service;

import notsbank.dto.response.ContaResponse;
import notsbank.dto.response.ExtratoResponse;
import notsbank.dto.response.TransferenciaResponse;
import notsbank.enums.TipoOperacao;
import notsbank.exception.ContaNaoEncontradaException;
import notsbank.exception.SaldoInsuficienteException;
import notsbank.exception.ValorInvalidoException;
import notsbank.model.Conta;
import notsbank.model.Extrato;
import notsbank.repository.ContaRepository;
import notsbank.repository.ExtratoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import notsbank.dto.request.CriarContaRequest;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ExtratoRepository extratoRepository;
    private final PasswordEncoder passwordEncoder;

    public ContaService(ContaRepository contaRepository,
                        ExtratoRepository extratoRepository,
                        PasswordEncoder passwordEncoder) {
        this.contaRepository = contaRepository;
        this.extratoRepository = extratoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<ContaResponse> listarTodas() {
        return contaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ContaResponse buscarPorNumero(Integer numero) {
        Conta conta = contaRepository.findById(numero)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada."));

        return toResponse(conta);
    }

    public ContaResponse criar(CriarContaRequest request) {

        Conta conta = new Conta();
        conta.setTitular(request.getTitular());
        conta.setSaldo(request.getSaldo());
        conta.setSenha(passwordEncoder.encode(request.getSenha()));

        Conta contaSalva = contaRepository.save(conta);

        return toResponse(contaSalva);
    }

    public String login(Integer numero, String senha) {

        Conta conta = contaRepository.findById(numero)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada."));

        if (!passwordEncoder.matches(senha, conta.getSenha())) {
            throw new ValorInvalidoException("Senha incorreta.");
        }

        return "Login realizado com sucesso.";
    }

    private ContaResponse toResponse(Conta conta) {
        return new ContaResponse(
                conta.getNumero(),
                conta.getTitular(),
                conta.getSaldo()
        );
    }

    private ExtratoResponse toExtratoResponse(Extrato extrato) {
        return new ExtratoResponse(
                extrato.getTipo().name(),
                extrato.getValor(),
                extrato.getDataHora(),
                extrato.getDescricao()
        );
    }

    @Transactional
    public TransferenciaResponse transferir(Integer numeroOrigem, Integer numeroDestino, Double valor) {
        Conta contaOrigem = contaRepository.findById(numeroOrigem)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta de origem não encontrada."));

        Conta contaDestino = contaRepository.findById(numeroDestino)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta de destino não encontrada."));

        if (numeroOrigem.equals(numeroDestino)) {
            throw new ValorInvalidoException("Não é permitido transferir para a mesma conta.");
        }

        if (valor == null || valor <= 0) {
            throw new ValorInvalidoException("Valor de transferência inválido.");
        }

        if (contaOrigem.getSaldo() < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente para transferência.");
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
        contaDestino.setSaldo(contaDestino.getSaldo() + valor);

        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        registrarExtrato(
                contaOrigem,
                TipoOperacao.TRANSFERENCIA_ENVIO,
                valor,
                "Transferência de " + valor + " enviada para a conta " + numeroDestino
        );

        registrarExtrato(
                contaDestino,
                TipoOperacao.TRANSFERENCIA_RECEBIMENTO,
                valor,
                "Transferência de " + valor + " recebida da conta " + numeroOrigem
        );


        return new TransferenciaResponse(
                contaOrigem.getNumero(),
                contaDestino.getNumero(),
                valor,
                contaOrigem.getSaldo()
        );
    }

    public ContaResponse sacar(Integer numero, Double valor) {
        Conta conta = contaRepository.findById(numero)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada."));

        if (valor == null || valor <= 0) {
            throw new ValorInvalidoException("Valor de saque inválido.");
        }

        double taxa = valor * 0.02;
        double total = valor + taxa;

        if (conta.getSaldo() < total) {
            throw new SaldoInsuficienteException("Saldo insuficiente para saque.");
        }

        conta.setSaldo(conta.getSaldo() - total);
        contaRepository.save(conta);

        registrarExtrato(
                conta,
                TipoOperacao.SAQUE,
                valor,
                "Saque de " + valor + " na conta " + numero + " | Taxa: " + taxa
        );

        return toResponse(conta);
    }

    public ContaResponse depositar(Integer numero, Double valor) {
        Conta conta = contaRepository.findById(numero)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada."));

        if (valor == null || valor <= 0) {
            throw new ValorInvalidoException("Valor de depósito inválido.");
        }

        conta.setSaldo(conta.getSaldo() + valor);
        contaRepository.save(conta);

        registrarExtrato(
                conta,
                TipoOperacao.DEPOSITO,
                valor,
                "Depósito de " + valor + " realizado na conta " + numero
        );

        return toResponse(conta);
    }

    private ContaResponse registrarExtrato(Conta conta, TipoOperacao tipo, Double valor, String descricao) {
        Extrato extrato = new Extrato(
                tipo,
                valor,
                LocalDateTime.now(),
                descricao,
                conta
        );

        extratoRepository.save(extrato);

        return toResponse(conta);
    }

    public List<ExtratoResponse> listarExtrato(Integer numero) {
        contaRepository.findById(numero)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada."));

        return extratoRepository.findByContaNumeroOrderByDataHoraAsc(numero)
                .stream()
                .map(this::toExtratoResponse)
                .toList();
    }

    public String alterarSenha(Integer numero, String senhaAtual, String novaSenha) {

        Conta conta = contaRepository.findById(numero)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada."));

        if (!passwordEncoder.matches(senhaAtual, conta.getSenha())) {
            throw new ValorInvalidoException("Senha atual incorreta.");
        }

        conta.setSenha(passwordEncoder.encode(novaSenha));
        contaRepository.save(conta);

        return "Senha alterada com sucesso.";
    }
}