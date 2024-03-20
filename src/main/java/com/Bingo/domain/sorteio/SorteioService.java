package com.Bingo.domain.sorteio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Bingo.domain.cartela.Cartela;
import com.Bingo.domain.cartela.CartelaService;
import com.Bingo.domain.gerador.GeradorDeNumerosService;
import com.Bingo.domain.service.SorteioServiceInterface;
import com.Bingo.domain.usuario.Usuario;
import com.Bingo.domain.usuario.UsuarioService;

@Service
public class SorteioService implements SorteioServiceInterface{
	
	@Autowired
	private CartelaService cartela;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GeradorDeNumerosService geradorService;
	
	@Autowired
	private SorteioRepository repository;
	
	@Override
	public Sorteio cadastrarSorteio(Sorteio sorteio, Usuario administrador) {
		List<Cartela> cartelas = gerarCartelas(sorteio.getQuantidadeDeCartelas(), sorteio.getCartelas(), sorteio);
		sorteio.setCartelas(cartelas);
		sorteio.setAdministrador(administrador);
		return repository.save(sorteio);
	}

	private List<Cartela> gerarCartelas(int quantidadeDeCartelas, List<Cartela> cartelas, Sorteio sorteio) {
		for(int i = 0; i < quantidadeDeCartelas;i++) {
			Cartela cartelaSorteada = cartela.gerar();
			cartelaSorteada.setSorteio(sorteio);
			cartelas.add(cartelaSorteada);
		}
		
		return cartela.salvarTodas(cartelas);
	}
	
	public int sortearNumero(Long id) {
		Sorteio sorteio = repository.getReferenceById(id);
		ArrayList<Integer> numerosSorteados = (ArrayList<Integer>) sorteio.getNumerosSorteados();
		ArrayList<Integer> todosOsNumeros = geradorService.listarTodosOsNumeros();
		
		int numeroSorteado = geradorService.sortearNumero(numerosPossiveisASeremSorteados(numerosSorteados, todosOsNumeros));
		sorteio.setNumerosSorteados(numeroSorteado);
		
		return numeroSorteado;
	}

	private ArrayList<Integer> numerosPossiveisASeremSorteados(List<Integer> numerosSorteados, ArrayList<Integer> todosOsNumeros) {
		for(int i = 0; i < numerosSorteados.size(); i++) {
			for(int j = 0; j < todosOsNumeros.size(); j++) {
				if(todosOsNumeros.get(j) == numerosSorteados.get(i)) {
					todosOsNumeros.remove(j);
				}
			}
		}
		return todosOsNumeros;
	}

	public Page<Sorteio> listarPorAdministrador(Long id, Pageable paginacao) {
		return repository.findByAdministradorId(id, paginacao);
	}
	
	public Usuario buscarAdministrador(Long id) {
		return usuarioService.buscarUsuario(id);
	}

}
