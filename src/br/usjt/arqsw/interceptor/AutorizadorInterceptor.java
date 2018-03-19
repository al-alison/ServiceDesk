package br.usjt.arqsw.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {
		
        String uri = request.getRequestURI();
		if (uri.endsWith("loginForm") || uri.contains("css") || uri.contains("js")
				|| uri.contains("img") ||  uri.contains("index") || uri.contains("tela_principal")) {
			return true;
		}
		if (request.getSession().getAttribute("UsuarioLogado") != null) {
			System.out.println(request.getSession().getAttribute("UsuarioLogado"));
			return true;
		}
		response.sendRedirect("index");
		return false;
	}
}