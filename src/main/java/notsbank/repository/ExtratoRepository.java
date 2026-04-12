package notsbank.repository;

import notsbank.model.Extrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtratoRepository extends JpaRepository<Extrato, Long> {

    List<Extrato> findByContaNumeroOrderByDataHoraAsc(Integer numeroConta);
}