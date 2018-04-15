package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.FilaService;

/**
 * 
 * @author Alison Almeida -818119557 - SIN3AN-MCA1
 *
 */
@Controller
public class ManterFilasController {
	@Autowired
	private ServletContext servletContext;
	private FilaService filaService;

	@Autowired
	public ManterFilasController(FilaService filaService) {
		this.filaService = filaService;
	}

	@Transactional
	private void cadastrarFila(Fila fila) throws IOException {
		filaService.cadastrarFila(fila);
	}
	
	@Transactional
	private void atualizarFila(Fila fila) throws IOException {
		filaService.atualizarFila(fila);
	}
	
	@Transactional
	private Fila carregar(int id) throws IOException {
		return filaService.carregar(id);
	}
	
	@Transactional
	private void deletar(Fila fila) throws Exception {
		filaService.remover(fila);
	}

	@Transactional
	@RequestMapping(value = "/cadastrar_fila", method = RequestMethod.POST)
	public String cadastrarFila(@Valid Fila fila, BindingResult result,Model model, @RequestParam("file") MultipartFile file) {
		try {
			filaService.cadastrarFila(fila);
			filaService.gravarImagem(servletContext, fila, file);
			return "FilaCadastrada";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@Transactional
	@RequestMapping("/tela_cadastrar_fila")
	public String cadastrarFila() {
		return "CadastrarFila";
	}

	public String atualizar(int id,String nome, String imagem) {
		try {
			Fila f = new Fila();
			f.setId(id);
			f.setNome(nome);
			f.setImagem(imagem);
			atualizarFila(f);
			return "FilaCadastrada";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@Transactional
	@RequestMapping("/deletar_fila")
	public String deletar(int btnExcluir) {
		try {
			Fila f = new Fila();
			f.setId(btnExcluir);
			deletar(f);
			return "FilaCadastrada";
		} catch (Exception e) {
			e.printStackTrace();
			return "ErroFilaComChamado";
		}
	}
	/**
	 * 
	 * @return
	 */
	@Transactional
	private List<Fila> listarFilas() throws IOException {
		return filaService.listarFilas();
	}

	/**
	 * 
	 * @param model
	 *            Acesso à request http
	 * @return JSP de Listar Chamados
	 */
	@Transactional
	@RequestMapping("/listar_filas_crud")
	public String listarFilasExibir(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "ListarFilasCrud";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@Transactional
	@RequestMapping("/tela_atualizar_fila")
	public String carregarFila(Model model,int btnAlterar) {
		try {
			model.addAttribute("fila", carregar(btnAlterar));
			return "AtualizarFila";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@Transactional
	@RequestMapping("/atualizar_fila")
	public String atualizarFila(Fila fila,Model model, @RequestParam("file") MultipartFile file) throws IOException{
		try {
			filaService.atualizarFila(fila);
			filaService.gravarImagem(servletContext, fila, file);
			return "FilaCadastrada";
		}
		catch(IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
		}
		return "erro";
	}
	
	@RequestMapping("/mostrar_fila")
	public String mostrar(Fila fila, Model model) {
		try {
			model.addAttribute("fila", filaService.carregar(fila.getId()));
			return "MostrarFila";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
		}
		return "erro";
	}

}
