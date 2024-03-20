package com.Bingo.domain.cartela;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartelaRepository extends JpaRepository<Cartela, Long> {

	Page<Cartela> findAllByDisponivelTrue(Pageable paginacao);

}
