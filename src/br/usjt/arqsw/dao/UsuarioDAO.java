package br.usjt.arqsw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Usuario;

@Repository
public class UsuarioDAO {
	@PersistenceContext
	EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuarios(){
		return manager.createQuery("select u from Usuario u").getResultList();
	}
	public Usuario logarUsuario(Usuario usuario) {
		System.out.println("select u.username from Usuario u where u.username='"+usuario.getUsername()+"' and u.password ='"+usuario.getPassword()+"';");
		return (Usuario)manager.createQuery("select u from Usuario u where u.username='"+usuario.getUsername()+"' and u.password ='"+usuario.getPassword()+"'").getSingleResult();
	
	}

	public void atualizar(Usuario usuario) {
		manager.merge(usuario);
	}

	public void remover(Usuario usuario) {
		manager.remove(usuario);
	}

	public Usuario selecionar(int id) {
		return manager.find(Usuario.class, id);
	}

	public void cadastrarChamado(Usuario usuario){
		manager.persist(usuario);
	}
}