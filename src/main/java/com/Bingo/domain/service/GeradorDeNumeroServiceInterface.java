package com.Bingo.domain.service;

import java.util.ArrayList;

public interface GeradorDeNumeroServiceInterface {

	public ArrayList<Integer> listarTodosOsNumeros();
	
	public int[] sorterNumerosDeCartela(ArrayList<Integer> todosOsNumeros);
	
	public int sortearNumero(ArrayList<Integer> todosOsNumeros);
	
}
