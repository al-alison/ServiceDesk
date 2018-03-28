package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw.dao.ChamadoDAO;
import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
/**
 * 
 * @author Alison Almeida -818119557 - SIN3AN-MCA1
 *
 */
@Service
public class ChamadoService {
	private ChamadoDAO dao;
	
	@Autowired
	public ChamadoService(ChamadoDAO dao){
		this.dao = dao;
	}
	@Transactional
	public List<Chamado> listarChamados(Fila fila) throws IOException{
		return dao.listarChamados(fila);
	}
	
	@Transactional
	public List<Chamado> listarChamados() throws IOException{
		return dao.listarChamados();
	}
	
	@Transactional
	public void cadastrarChamado(Chamado chamado) throws IOException{
		dao.cadastrarChamado(chamado);
	}
	
	@Transactional
	public void fecharChamado(Chamado chamado) throws IOException{
		dao.atualizar(chamado);
	}
	
	@Transactional
	public Chamado mostrar(Chamado chamado) throws IOException{
		return dao.selecionar(chamado.getId());
	}

}
