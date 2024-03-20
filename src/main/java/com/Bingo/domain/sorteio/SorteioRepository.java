package com.Bingo.domain.sorteio;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SorteioRepository extends JpaRepository<Sorteio, Long>{

	Page<Sorteio> findByAdministradorId(Long id, Pageable paginacao);

}
