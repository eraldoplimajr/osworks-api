package com.algaworks.osworks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.osworks.domain.model.OrdemServico;

/**
 *
 * @author Eraldo Lima
 *
 */
@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{

	
}
