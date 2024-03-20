package com.Bingo.domain.cartela;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.aspectj.weaver.ast.Instanceof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Bingo.domain.gerador.GeradorDeNumerosService;
import com.Bingo.domain.service.CartelaServiceInterface;
import com.Bingo.domain.usuario.UsuarioService;
import com.Bingo.infra.exception.sytemExeption.BusinessRuleExeption;

@Service
public class CartelaService implements CartelaServiceInterface{

	@Autowired
	private CartelaRepository repository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GeradorDeNumerosService geradorDeNumerosService;
	
	@Override
	public Cartela gerar() {
		int[] numerosDaCartela = gerarNumeros();
		Cartela cartela = new Cartela(numerosDaCartela, true);
		return cartela;
	}

	public List<Cartela> salvarTodas(List<Cartela> cartelas) {
		return repository.saveAll(cartelas);
	}
	
	public Page<Cartela> listar(Pageable paginacao) {
		return repository.findAllByDisponivelTrue(paginacao);
	}
	
	@Override
	public int[] gerarNumeros() {
		ArrayList<Integer>todosOsNumeros = geradorDeNumerosService.listarTodosOsNumeros();
		int[] numerosSorteadosDaCartela = geradorDeNumerosService.sorterNumerosDeCartela(todosOsNumeros);
		return numerosSorteadosDaCartela;
	}

	public Cartela comprarCartela(Long id,DadosCompraCartela dados) {
		var comprador = usuarioService.buscarUsuario(dados.idDoComprador());
		var cartela = repository.getReferenceById(id);
		
		if(comprador.getId() == cartela.getSorteio().getAdministrador().getId()) {
			throw new BusinessRuleExeption("Administrador não pode comprar cartela do proprio sorteio");
		}
		cartela.setComprador(comprador);
		cartela.setDisponivel(false);
		
		return cartela;
	}
	
}
