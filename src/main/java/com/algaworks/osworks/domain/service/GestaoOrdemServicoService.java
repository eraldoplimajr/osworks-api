package com.algaworks.osworks.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.domain.exception.EntidadeNaoEncotradaException;
import com.algaworks.osworks.domain.exception.NegocioException;
import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.model.Comentario;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.model.StatusOrdemServico;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.algaworks.osworks.domain.repository.ComentarioRepository;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;

/**
 *
 * @author Eraldo Lima
 *
 */
@Service
public class GestaoOrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId()).orElseThrow(() -> new NegocioException("Cliente não encontrado") );				
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}
	
	public List<OrdemServico> consultarTodasOrdensServicos(){
		return ordemServicoRepository.findAll();
	}
	
	public Optional<OrdemServico> buscarOrdemServicoPorId(Long ordemServicoId){
		return ordemServicoRepository.findById(ordemServicoId);		
	}
	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		
		OrdemServico ordemServico = buscarOrdemServico(ordemServicoId);
		
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);		
		
		return comentarioRepository.save(comentario);		
	}
	
	public Optional<Comentario> buscar(Long comentarioId) {
		return comentarioRepository.findById(comentarioId);		
	}
	
	public List<Comentario> listarTodosComentarios(Long ordemServicoId) {
		
		OrdemServico ordemServico = buscarOrdemServico(ordemServicoId);
		
		return ordemServico.getComentarios();	
		
	}
	
	public Comentario inserir(Comentario comentario) {
		return comentarioRepository.save(comentario);
	}
	
	public void excluir(Long comentarioId) {
		comentarioRepository.deleteById(comentarioId);
	}
	
	public void finalizar(Long ordemServicoId) {
		
		OrdemServico ordemServico = buscarOrdemServico(ordemServicoId);
		
		ordemServico.finalizar();
		
		ordemServicoRepository.save(ordemServico);
	}

	/**
	 * @param ordemServicoId
	 * @return
	 */
	private OrdemServico buscarOrdemServico(Long ordemServicoId) {
		return ordemServicoRepository.findById(ordemServicoId).orElseThrow(() -> new EntidadeNaoEncotradaException("Ordem de serviço não encontrada"));
	}
}
