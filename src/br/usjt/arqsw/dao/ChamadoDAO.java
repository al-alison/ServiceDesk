package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
/**
 * 
 * @author Mauro de Melo Pires - 816125689 - SIN3AN-MCA1
 *
 */
@Repository
public class ChamadoDAO {
	/**
	 * Busca dos chamados no BD de acordo com as filas
	 * @param fila
	 * @return
	 * @throws IOException
	 */
	private Connection conn;
	
	@Autowired
	public ChamadoDAO(DataSource dataSource) throws IOException{
		try{
			this.conn = dataSource.getConnection(); 
			} 
		catch (SQLException e)
		{ 
			throw new IOException(e); 
		} 
	}
	public ArrayList<Chamado> listarChamados(Fila fila) throws IOException {
		String query = "select c.id_chamado,c.descricao,c.status,dt_abertura,dt_fechamento from chamado c where c.id_fila="+fila.getId();
		ArrayList<Chamado> lista = new ArrayList<>();
		try(PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();){

			while(rs.next()) {
				Chamado chamado = new Chamado();
				Fila filac = new Fila();
				chamado.setId(rs.getInt("id_chamado"));
				chamado.setDescricao(rs.getString("descricao"));
				chamado.setStatus(rs.getString("status"));
				chamado.setDt_abertura(rs.getDate("dt_abertura"));
				chamado.setDt_fechamento(rs.getDate("dt_fechamento"));
				chamado.setFila(filac);
				lista.add(chamado);
			}
			
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return lista;
	}
	/**
	 * Cadastro de chamados
	 * @param descricao
	 * @param idFila
	 * @return
	 * @throws IOException
	 */
	public int cadastrarChamado(String descricao, int idFila) throws IOException{
		String query = "insert into chamado "
				+ "values (null,'"+descricao+"', 'aberto', NOW(),null,"+idFila+")";
		try(PreparedStatement pst = conn.prepareStatement(query);){
			return pst.executeUpdate();
		}catch (SQLException e) {
			throw new IOException(e);
		}
	}
}
