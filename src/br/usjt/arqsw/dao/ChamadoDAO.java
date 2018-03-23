package br.usjt.arqsw.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;

/**
 * 
 * @author Alison Almeida -818119557 - SIN3AN-MCA1
 *
 */
@Repository
public class ChamadoDAO {
	@PersistenceContext
	EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<Chamado> listarChamados(){
		return manager.createQuery("select c from Chamado c").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Chamado> listarChamados(Fila fila){
		return manager.createQuery("select c from Chamado c where id_fila ="+fila.getId()).getResultList();
	}
	
	public void atualizar(Chamado chamado) {
		manager.merge(chamado);
	}

	public void remover(Chamado chamado) {
		manager.remove(chamado);
	}

	public Chamado selecionar(int id) {
		return manager.find(Chamado.class, id);
	}

	public void cadastrarChamado(Chamado chamado){
		manager.persist(chamado);
	}
}
