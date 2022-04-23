package br.gov.sp.fatec.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

@SpringBootTest
@Transactional
@Rollback
class SpringBootAppApplicationTests {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	private AutorizacaoRepository autRepo;

	@Autowired
	private SegurancaService segService;

	@Test
	void contextLoads() {
	}

	@Test
	void testaInsercao(){
		Usuario usuario = new Usuario();
		usuario.setNome("teste");
		usuario.setSenha("senhaTeste");
		usuario.setAutorizacoes(new HashSet<Autorizacao>());
		Autorizacao aut = new Autorizacao();
		aut.setNome("ROLE_USUARIO");
		autRepo.save(aut);
		usuario.getAutorizacoes().add(aut);
		usuarioRepo.save(usuario);
		assertNotNull(usuario.getAutorizacoes().iterator().next().getId());
	}

	@Test
	void testaInsercaoAutorizacao(){
		Usuario usuario = new Usuario();
		usuario.setNome("teste2");
		usuario.setSenha("senhaTeste");
		usuarioRepo.save(usuario);
		Autorizacao aut = new Autorizacao();
		aut.setNome("ROLE_USUARIO2");
		aut.setUsuarios(new HashSet<Usuario>());
		aut.getUsuarios().add(usuario);
		autRepo.save(aut);
		assertNotNull(aut.getUsuarios().iterator().next().getId());
	}




	@Test
	void testaAutorizacao() {
		Usuario usuario = usuarioRepo.findById(1L).get();
		assertEquals("ROLE_ADMIN", usuario.getAutorizacoes().iterator().next().getNome());
	}

	@Test
	void testaUsuario() {
		Autorizacao aut = autRepo.findById(1L).get();
		assertEquals("Jose", aut.getUsuarios().iterator().next().getNome());
	}

	@Test
	void testaBuscaUsuarioNomeContains() {
		List<Usuario> usuario = usuarioRepo.findByNomeContainsIgnoreCase("O");
		assertFalse(usuario.isEmpty());
	}

	@Test
	void testaBuscaUsuarioPeloNome() {
		Usuario usuario = usuarioRepo.findByNome("Jose");
		assertNotNull(usuario);
	}

	@Test
	void testaBuscaUsuarioPeloNomeQuery() {
		Usuario usuario = usuarioRepo.BuscaPorNome("Jose");
		assertNotNull(usuario);
	}

	@Test
	void testaBuscaUsuarioPeloNomeSenha() {
		Usuario usuario = usuarioRepo.findByNomeAndSenha("Jose","Teste@123");
		assertNotNull(usuario);
	}

	@Test
	void testaBuscaUsuarioPeloNomeSenhaQuery() {
		Usuario usuario = usuarioRepo.BuscaPorNomeSenha("Jose","Teste@123");
		assertNotNull(usuario);
	}

	@Test
	void testaBuscaUsuarioPeloNomeAutorizacao() {
		List<Usuario> usuario = usuarioRepo.findByAutorizacoesNome("ROLE_ADMIN");
		assertFalse(usuario.isEmpty());
	}

	@Test
	void testaBuscaUsuarioPeloNomeAutorizacaoQuery() {
		List<Usuario> usuario = usuarioRepo.buscaPorNomeAutorizacao("ROLE_ADMIN");
		assertFalse(usuario.isEmpty());
	}

	@Test
	void testeServicoCriaUsuario(){
		Usuario usuario = segService.criarUsuario("normal", "teste", "ROLE_USUARIO");
		assertNotNull(usuario);
	}

}
