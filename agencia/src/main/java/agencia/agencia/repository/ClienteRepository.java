package agencia.agencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import agencia.agencia.service.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    @NativeQuery(value = "SELECT * FROM Cliente" +
                "WHERE nomeCliente IN (" +
                "(SELECT nomeCliente FROM Depositante)" +
                "EXCEPT" +
                "(SELECT nomeCliente FROM Tomador))")
    List<Cliente> consulta1();
} 