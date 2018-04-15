package br.usjt.arqsw.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.usjt.arqsw.dao.FilaDAO;
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
	public void remover(Fila fila) throws Exception{
		dao.remover(fila);
	}
	
	public void gravarImagem(ServletContext servletContext, Fila fila, MultipartFile file)
	throws IOException{
		if (!file.isEmpty()) {
			BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
			String path = servletContext.getRealPath(servletContext.getContextPath());
			path = path.substring(0, path.lastIndexOf(File.separatorChar));
			String nomeArquivo = "img"+fila.getId()+".jpg";
			fila.setImagem(nomeArquivo);
			atualizarFila(fila);
			File destination = new File(path + File.separatorChar + "img" + File.separatorChar + nomeArquivo);
			System.out.println(destination);
			System.out.println(src);
			if(destination.exists()){
				destination.delete();
			}
			ImageIO.write(src, "jpg", destination);
		}
		
	}
}
