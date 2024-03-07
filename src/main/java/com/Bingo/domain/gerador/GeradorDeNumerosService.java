package com.Bingo.domain.gerador;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.Bingo.domain.service.GeradorDeNumeroServiceInterface;

@Service
public class GeradorDeNumerosService implements GeradorDeNumeroServiceInterface{

	@Override
	public ArrayList<Integer> listarTodosOsNumeros() {
		ArrayList<Integer> numeros = new ArrayList<>();
		for(int i = 0; i < 76; i++) {
			numeros.add(i);
		}
		return numeros;
	}

	@Override
	public int[] sorterNumerosDeCartela(ArrayList<Integer> todosOsNumeros) {
		ArrayList<Integer> numerosSorteados = new ArrayList<>();
		int[] numerosSorteadosOrdenados = new int[25];
		
		for (int i = 1; i < 26; i++) {
			int numeroSorteado = sortearNumero(todosOsNumeros);
			numerosSorteados.add(todosOsNumeros.get(numeroSorteado));
			todosOsNumeros.remove(numeroSorteado);
		}
		
		numerosSorteados.sort(Comparator.naturalOrder());
		for(int i = 0; i < numerosSorteados.size(); i++) {
			numerosSorteadosOrdenados[i] = numerosSorteados.get(i);
		}
		
		return numerosSorteadosOrdenados;
	}

	@Override
	public int sortearNumero(ArrayList<Integer> todosOsNumeros) {
		Random gerador = new Random();
		int numeroSorteado = gerador.nextInt(todosOsNumeros.size());
		return numeroSorteado;
	}

}
