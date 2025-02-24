package agencia.agencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agencia.agencia.service.model.Agencia;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, String>{

}
