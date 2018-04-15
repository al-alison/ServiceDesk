package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.usjt.arqsw.dao.ChamadoDAO;
import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.reqres.Cliente;
import br.usjt.arqsw.reqres.Resultado;
import br.usjt.arqsw.reqres.ResultadoUnico;

/**
 * 
 * @author Alison Almeida -818119557 - SIN3AN-MCA1
 *
 */
@Service
public class ChamadoService {
	private ChamadoDAO dao;

	@Autowired
	public ChamadoService(ChamadoDAO dao) {
		this.dao = dao;
	}

	@Transactional
	public Chamado listarChamado(int id) throws IOException {
		return dao.selecionar(id);
	}

	@Transactional
	public List<Chamado> listarChamados(Fila fila) throws IOException {
		return dao.listarChamados(fila);
	}

	@Transactional
	public List<Chamado> listarChamados() throws IOException {
		return dao.listarChamados();
	}

	@Transactional
	public List<Chamado> listarChamadosAbertos(Fila fila) throws IOException {
		return dao.listarChamadosAbertos(fila);
	}

	@Transactional
	public void cadastrarChamado(Chamado chamado) throws IOException {
		dao.cadastrarChamado(chamado);
	}

	@Transactional
	public void fecharChamado(Chamado chamado) throws IOException {
		dao.atualizar(chamado);
	}

	@Transactional
	public Chamado mostrar(Chamado chamado) throws IOException {
		return dao.selecionar(chamado.getId());
	}

	private String montaURL() {
		String url = "https://reqres.in/api/users";
		return url;
	}

	public List<Cliente> retornaClientes() {
		return buscaClientes();
	}

	public Cliente retornaCliente(int id) {
		return buscaCliente(id);
	}
	private Cliente buscaCliente(int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		// Resultado resultado = restTemplate.getForObject(
		// montaURL(), Resultado.class);
		ResponseEntity<ResultadoUnico> resultado = restTemplate.exchange(montaURL() + "/" + id, HttpMethod.GET, entity,
				ResultadoUnico.class);

		//System.out.println(resultado.getBody().getData());
		return resultado.getBody().getData();
	}

	private List<Cliente> buscaClientes() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		// Resultado resultado = restTemplate.getForObject(
		// montaURL(), Resultado.class);
		ResponseEntity<Resultado> resultado = restTemplate.exchange(montaURL() + "?per_page=15&page=1", HttpMethod.GET,
				entity, Resultado.class);

		System.out.println(resultado.getBody());
		return resultado.getBody().getData();
	}

}
