package agencia.agencia.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agencia.agencia.repository.ClienteRepository;
import agencia.agencia.repository.DepositoRepository;
import agencia.agencia.repository.HistoricoTransacoesRepository;
import agencia.agencia.service.model.Cliente;

/*
 * Service geral da aplicação
 */

@Service
public class ServiceGeral {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DepositoRepository depositoRepository;

    @Autowired
    private HistoricoTransacoesRepository historicoTransacoesRepository;

    public List<Cliente> realizarConsulta1() {
        return clienteRepository.consulta1();
    }

    public List<String> realizarConsulta2() {
        return clienteRepository.consulta2();
    }
    
    public List<Object[]> realizarConsulta3(String nomecliente) {
        return historicoTransacoesRepository.consulta3(nomecliente);

    }

    public List<Object[]> realizarConsulta4() {

        // Define data limite (5 anos atrás)
        LocalDate date = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        LocalDate dataLimite = date.minusYears(5);

        // Realiza a consulta considerando a data limite
        List<Object[]> resultadosBrutos = depositoRepository.consulta4(dataLimite);

        List<Object[]> resultadosConvertidos = resultadosBrutos.stream()
                .map(arr -> new Object[] {
                        ((Number) arr[0]).intValue(), // Converte o ano para Integer
                        ((Number) arr[1]).intValue(), // Converte o mês para Integer
                        ((Number) arr[2]).intValue() // Converte o total de transações para Integer
                })
                .toList();

        return resultadosConvertidos;
    }

}
