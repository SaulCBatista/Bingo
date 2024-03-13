package com.Bingo.domain.sorteio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.Bingo.domain.cartela.Cartela;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "sorteio")
@Entity(name = "Sorteio")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Sorteio {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@OneToMany(mappedBy = "sorteio", cascade = CascadeType.ALL)
	private List<Cartela> cartelas = new ArrayList<>();
	
	private int quantidadeDeCartelas;
	
	private List<Integer> numerosSorteados = new ArrayList<>();
	
	private int quantidadeDeNumerosSorteados;
	
	private LocalDateTime dataInicio;
	
	private LocalDateTime dataSorteio;
	
	private boolean ativo;

	public void setCartelas(List<Cartela> cartelas) {
		this.cartelas = cartelas;
	}

	public Sorteio(@Valid DadosCasdatroSorteio dados) {
		this.tipo = dados.tipo();
		this.quantidadeDeCartelas = dados.quantidadeDeCartelas();
		this.quantidadeDeNumerosSorteados = 0;
		this.dataInicio = LocalDateTime.now();
		this.dataSorteio = dados.dataSorteio();
		this.ativo = true;
	}
	
}
