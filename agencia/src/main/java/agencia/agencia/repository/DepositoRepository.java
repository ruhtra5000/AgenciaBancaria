package agencia.agencia.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;

import agencia.agencia.service.model.Deposito;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {

    @NativeQuery(value = "SELECT EXTRACT(YEAR FROM d.datadeposito) AS ano, " +
            "EXTRACT(MONTH FROM d.datadeposito) AS mes, " +
            "COUNT(d.id) AS total_transacoes " +
            "FROM deposito d " +
            "WHERE d.datadeposito >= :dataLimite " +
            "GROUP BY EXTRACT(YEAR FROM d.datadeposito), EXTRACT(MONTH FROM d.datadeposito) " +
            "ORDER BY ano, mes")
    List<Object[]> consulta4(@Param("dataLimite") LocalDate dataLimite);
}
