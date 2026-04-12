package notsbank.service;

import notsbank.dto.response.ExtratoResponse;
import notsbank.enums.TipoOperacao;
import notsbank.model.Conta;
import notsbank.model.Extrato;
import notsbank.repository.ExtratoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExtratoService {

    private final ExtratoRepository extratoRepository;

    public ExtratoService(ExtratoRepository extratoRepository) {
        this.extratoRepository = extratoRepository;
    }

    public void registrar(TipoOperacao tipoOperacao, Conta conta, double valor) {
        Extrato extrato = new Extrato(
                tipoOperacao,
                valor,
                LocalDateTime.now(),
                "Operação realizada",
                conta
        );

        extratoRepository.save(extrato);
    }

    public List<ExtratoResponse> buscarExtratoPorConta(int numeroConta) {

        List<Extrato> extratos = extratoRepository.findByContaNumeroOrderByDataHoraAsc(numeroConta);

        return extratos.stream()
                .map(e -> new ExtratoResponse(
                        e.getTipo().name(),
                        e.getValor(),
                        e.getDataHora(),
                        e.getDescricao()
                ))
                .toList();
    }
}