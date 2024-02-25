package com.Bingo.domain.sorteio;

import java.util.ArrayList;
import java.util.List;

import com.Bingo.domain.cartela.Cartela;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "bingo_draw")
@Entity(name = "Bingo_draw")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Sorteio {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@OneToMany(mappedBy = "id")
	private List<Cartela> quantidadeDeCartelas = new ArrayList<>();
	
}
