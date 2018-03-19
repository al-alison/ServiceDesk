package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Usuario;

@Repository
public class UsuarioDAO {
private Connection conn;
	
	@Autowired
	public UsuarioDAO(DataSource dataSource) throws IOException{
		try{
			this.conn = dataSource.getConnection(); 
			} 
		catch (SQLException e)
		{ 
			throw new IOException(e); 
		} 
	}
	
	public boolean logarUsuario(Usuario usuario) throws IOException {
		String query = "select * from usuario u where u.username ='"+usuario.getUsername()+"' and u.password ='"+usuario.getPassword()+"';";
		System.out.println(query);
		try(PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();){
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}
			
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
}
