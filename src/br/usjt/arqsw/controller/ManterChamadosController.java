package br.usjt.arqsw.controller;
import java.util.ArrayList;
import java.util.Date;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;
/**
 * 
 * @author Alison Almeida -818119557 - SIN3AN-MCA1
 *
 */
@Controller
public class ManterChamadosController {
	private FilaService filaService;
	private ChamadoService chamadoService;
	
	@Autowired
	public ManterChamadosController(FilaService filaService, ChamadoService chamadoService) {
		this.filaService = filaService;
		this.chamadoService = chamadoService;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("index")
	public String inicio() {
		return "index";
	}

	@RequestMapping("/tela_inicio")
	public String inicio2() {
		return "TelaPrincipal";
	}
	
	@Transactional
	private List<Fila> listarFilas() throws IOException{
			return filaService.listarFilas();
	}
	@Transactional

	private List<Chamado> listarChamados(Fila fila) throws IOException{
		return chamadoService.listarChamados(fila);
	}
	
	@Transactional
	private List<Chamado> listarChamadosAbertos(Fila fila) throws IOException{
		return chamadoService.listarChamadosAbertos(fila);
	}
	
	@Transactional
	private Chamado carregar(int id) throws IOException{
		return chamadoService.listarChamado(id);
	}
	
	@Transactional
	private void cadastrarChamado(String desc, Fila fila) throws IOException{
		Chamado c = new Chamado();
		Date d = new Date();
		c.setDescricao(desc);
		c.setFila(fila);
		c.setStatus("aberto");
		c.setDt_abertura(d);
		chamadoService.cadastrarChamado(c);
	}
	
	@Transactional
	private void fecharChamado(Chamado c) throws IOException{
		Date d = new Date();
		c.setDt_fechamento(d);
		c.setStatus("fechado");
		chamadoService.fecharChamado(c);
	}
	
	/**
	 * 
	 * @param model Acesso Ã  request http
	 * @return JSP de Listar Chamados
	 */
	@Transactional
	@RequestMapping("/listar_filas_exibir")
	public String listarFilasExibir(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "ChamadoListar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@Transactional
	@RequestMapping("/listar_filas_exibir_fechamento")
	public String listarFilasExibirFechamento(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "ChamadoListarFechamento";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	/**
	 * 
	 * @param model lista as filas para cadastro do chamado
	 * @return JSP de cadastro
	 */
	@Transactional
	@RequestMapping("/cadastrar_chamado")
	public String cadastrarChamado(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "CadastrarChamado";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	/**
	 * 
	 * @param desc descrição do chamado
	 * @param fila do chamado a ser cadastrado
	 * @return JSP para informar o cadastro
	 */
	@Transactional
	@RequestMapping("/chamado_cadastrado")
	public String chamadoCadastrado(String desc, Fila fila) {
		try {
			cadastrarChamado(desc, fila);
			return "ChamadoCadastrado";		
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@Transactional
	@RequestMapping("/fechar_chamados")
	public String fecharChamado(int[] selected) {
		System.out.println(selected);
		for (int i = 0; i<selected.length; i++) {
			try {
				Chamado c = carregar(selected[i]);
				System.out.println(selected[i]);
				fecharChamado(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "ChamadoCadastrado";
	}
	
	/**
	 * 
	 * @param fila lista de filas
	 * @param result resultado da requisição
	 * @param model parametros
	 * @return JSP de chamados
	 */
	@Transactional
	@RequestMapping("/listar_chamados_exibir")
	public String listarChamadosExibir(@Valid Fila fila, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors("id")) {
				model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				return "ChamadoListar";
				//return "redirect:listar_filas_exibir";
			}
			fila = filaService.carregar(fila.getId());
			model.addAttribute("chamados", listarChamados(fila));
			
			return "ChamadoListarExibir";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@Transactional
	@RequestMapping("/listar_chamados_exibir_fechamento")
	public String listarChamadosExibirFechamento(@Valid Fila fila, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors("id")) {
				model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				return "ChamadoListar";
				//return "redirect:listar_filas_exibir";
			}
			fila = filaService.carregar(fila.getId());
			model.addAttribute("chamados", listarChamadosAbertos(fila));
			
			return "ChamadoListarExibirFechamento";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

}
