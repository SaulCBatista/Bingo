package com.Bingo.domain.cartela;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cartela")
@Entity(name = "Cartela")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cartela {


	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int[] numeros = new int[25];
	
	private boolean disponivel;
	
	public Cartela(int[] numerosDaCartela, boolean disponivel ) {
		this.numeros = numerosDaCartela;
		this.disponivel = disponivel;
	}
}
