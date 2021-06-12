package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.api.model.ComentarioInput;
import com.algaworks.osworks.api.model.ComentarioModel;
import com.algaworks.osworks.domain.model.Comentario;
import com.algaworks.osworks.domain.service.GestaoOrdemServicoService;

/**
 *
 * @author Eraldo Lima
 *
 */
@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<ComentarioModel> listar(@PathVariable Long ordemServicoId){
		
		List<Comentario> comentarios = gestaoOrdemServico.listarTodosComentarios(ordemServicoId);
				
		return toCollectionModel(comentarios);
		
	}
	
	/**
	 * @param comentarios
	 * @return
	 */
	private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios) {
		
		return comentarios.stream()
			.map(comentario -> toModel(comentario))
			.collect(Collectors.toList());		
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioModel adicionar(@PathVariable Long ordemServicoId, @Valid @RequestBody ComentarioInput comentarioInput) {
		
		Comentario comentario = gestaoOrdemServico.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());
				
		return toModel(comentario);
	}
	
	private Comentario toEntity(ComentarioInput comentarioInput) {
		return modelMapper.map(comentarioInput, Comentario.class);
	}
	
	private ComentarioModel toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
	}
}
