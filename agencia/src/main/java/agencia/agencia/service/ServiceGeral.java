package agencia.agencia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agencia.agencia.repository.ClienteRepository;
import agencia.agencia.service.model.Cliente;

/*
 * Service geral da aplicação
 */

@Service
public class ServiceGeral {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> realizarConsulta1() {
        return clienteRepository.consulta1();
    }
}
