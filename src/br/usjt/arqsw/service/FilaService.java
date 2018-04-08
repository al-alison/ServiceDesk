package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw.dao.FilaDAO;
import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
/**
 * 
 * @author Alison Almeida -818119557 - SIN3AN-MCA1
 *
 */
@Service
public class FilaService {
	private FilaDAO dao;
	
	@Autowired
	public FilaService(FilaDAO dao) {
		this.dao = dao;
	}
	public List<Fila> listarFilas() throws IOException{
		return dao.listarFilas();
	}
	public Fila carregar(int id) throws IOException{
		return dao.selecionar(id);
	}
	@Transactional
	public void cadastrarFila(Fila fila) throws IOException{
		dao.cadastrarChamado(fila);
	}
	@Transactional
	public void atualizarFila(Fila fila) throws IOException{
		dao.atualizar(fila);
	}
	@Transactional
	public void remover(Fila fila) throws IOException{
		dao.remover(fila);
	}
}
