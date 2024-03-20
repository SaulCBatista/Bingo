package com.Bingo.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import com.Bingo.domain.usuario.UsuarioService;
import com.Bingo.domain.usuario.Usuario;
import com.Bingo.domain.usuario.UsuarioRepository;
import com.Bingo.infra.exception.sytemExeption.AuthenticationException;
import com.Bingo.infra.exception.sytemExeption.BusinessRuleExeption;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

	@SpyBean
	UsuarioService service;
	
	@MockBean
	UsuarioRepository repository;
	
	@Test
	public void autenticarUsuario() {
		String email = "usuario@email.com";
		String senha = "senha";
		
		Usuario usuario = new Usuario(1l, "nome", email, senha, null, null);
		Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));
		
		Usuario resultado = service.auteticar(email, senha);
		
		assertThat(resultado).isNotNull();
	}
	
	@Test
	@DisplayName("Deveria cadastrar um usuario")
	public void cadastrarUmUsuarioCenario1() {
		Mockito.doNothing().when(service).validarEmail(Mockito.anyString());
		Usuario usuario = new Usuario(1l, "nome", "usuario@email.com", "senha", null, null);
		Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
		
		Usuario usuarioSalvo = service.cadastrar(usuario);
		
		assertThat(usuarioSalvo).isNotNull();
		assertThat(usuarioSalvo.getId()).isEqualTo(1l);
		assertThat(usuarioSalvo.getNome()).isEqualTo("nome");
		assertThat(usuarioSalvo.getEmail()).isEqualTo("usuario@email.com");
		assertThat(usuarioSalvo.getSenha()).isEqualTo("senha");
	}
	
	@Test
	@DisplayName("Não deveria cadastrar um usuario com um email já cadastrado")
	public void cadastrarUmUsuarioCenario2 () {
		String email = "usuario@email.com";
		Usuario usuario = new Usuario(1l, "nome", email, "senha", null, null);
		Mockito.doThrow(BusinessRuleExeption.class).when(service).validarEmail("usuario@email.com");;
		
		assertThrows(BusinessRuleExeption.class, () -> service.cadastrar(usuario));
		Mockito.verify(repository, Mockito.never()).save(usuario);
	}
	
	@Test
	@DisplayName("Deve retornar um erro quando não encontrar um usuario")
	public void cadastrarUmUsuarioCenario3() {
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
		Throwable exception = catchThrowable(() -> service.auteticar("usuario@email.com", "senha"));
		assertThat(exception).isInstanceOf(AuthenticationException.class).hasMessage("couldn't possible find a user");
	}
	
}
