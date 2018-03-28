package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;

@RestController
public class ManterChamadosRestController {

	private ChamadoService cs;
	private FilaService fs;

	@Autowired
	public ManterChamadosRestController(FilaService filaService, ChamadoService chamadoService) {
		this.fs = filaService;
		this.cs = chamadoService;
	}

	@RequestMapping(method = RequestMethod.GET, value = "rest/chamados/")
	public @ResponseBody List<Chamado> listagem() {
		List<Chamado> lista = null;
		try {
			lista = cs.listarChamados();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@RequestMapping(method = RequestMethod.GET, value = "rest/chamados/idFila={idFila}")
	public @ResponseBody List<Chamado> listagemFila(@PathVariable("idFila") Long id) {
		Fila param;
		List<Chamado> lista = null;
		try {
			param = new Fila();
			if (id.intValue() != 0) {
				param.setId(id.intValue());
				lista = cs.listarChamados(param);
			} else {
				lista = cs.listarChamados();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@RequestMapping(method = RequestMethod.GET, value = "rest/chamados/{id}")
	public @ResponseBody Chamado listaChamado(@PathVariable("id") Long id) {
		Chamado chamado = null, param;
		try {
			param = new Chamado();
			param.setId(id.intValue());
			chamado = cs.mostrar(param);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chamado;
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "rest/chamados")
	public ResponseEntity<Chamado> criarChamado(@RequestBody Chamado chamado) {
		try {
			cs.cadastrarChamado(chamado);
			return new ResponseEntity<Chamado>(chamado, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Chamado>(chamado, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	@RequestMapping(method = RequestMethod.PUT, value = "rest/chamados")
	public ResponseEntity<Chamado> fecharChamado(@RequestBody Chamado chamado) {
		try {
			cs.fecharChamado(chamado);
			return new ResponseEntity<Chamado>(chamado, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Chamado>(chamado, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
