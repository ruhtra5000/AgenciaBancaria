package agencia.agencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import agencia.agencia.service.model.HistoricoTransacoes;

@Repository
public interface HistoricoTransacoesRepository extends JpaRepository<HistoricoTransacoes, Integer>{

    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY h.nomecliente) AS id_virtual, h.* " +
               "FROM HistoricoTransacoes h WHERE h.nomecliente = :nomecliente", 
       nativeQuery = true)
    List<Object[]> consulta3(@Param ("nomecliente") String nomecliente);

}
