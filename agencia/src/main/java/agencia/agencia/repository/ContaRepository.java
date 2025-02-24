package agencia.agencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agencia.agencia.service.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{
   
} 