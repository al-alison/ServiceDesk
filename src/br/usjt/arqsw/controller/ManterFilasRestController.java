package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.FilaService;

@RestController
public class ManterFilasRestController {

	private FilaService fs;

	@Autowired
	public ManterFilasRestController(FilaService filaService) {
		this.fs = filaService;
	}
	@RequestMapping(method = RequestMethod.GET, value = "rest/filas")
	public @ResponseBody List<Fila> listagemFila() {
		List<Fila> filas = null;
		try {
			filas = fs.listarFilas();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filas;
	}
}
