package br.usjt.arqsw.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw.entity.Usuario;
import br.usjt.arqsw.service.UsuarioService;

@Controller
public class ManterUsuarioController {
	private UsuarioService usuarioService;
	
	@Autowired
	public ManterUsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	private boolean realizarLogin(Usuario usuario) throws IOException{

		return usuarioService.logarUsuario(usuario);
	}
	

	@RequestMapping("/tela_principal")
	public String chamadoCadastrado(HttpSession session, String username, String password) {
		try {
			session.setAttribute("UsuarioLogado", "logado");
			System.out.println(session.getAttribute("UsuarioLogado"));
			Usuario usuario = new Usuario();
			usuario.setPassword(password);
			usuario.setUsername(username);
			if(realizarLogin(usuario)) {
				return "TelaPrincipal";		
			}	
			else
				return "Erro";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
}
