package com.algaworks.osworks.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;

/**
 *
 * @author Eraldo Lima
 *
 */
@Service
public class CadastroClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;	
	
	public Optional<Cliente> consultarCliente(Long clienteId) {
		return clienteRepository.findById(clienteId);
	}
	
	public List<Cliente> consultarClientes() {
		return clienteRepository.findAll();
	}
	
	public boolean existsCliente(Long clienteId) {
		boolean retorno = false;
		
		Optional<Cliente> cliente = consultarCliente(clienteId);
		
		if(cliente.isPresent()) {
			retorno = true;
		}
		
		return retorno;
	}

	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
}
