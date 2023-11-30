package controllers;

import java.util.List;

import models.Categoria;
import models.Critica;
import models.Filme;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Filmes extends Controller {
	
	@Administrador
	public static void form() {
		List<Categoria> categorias = Categoria.findAll();
		
		render(categorias);
	
	}
	
	public static void editar(Long id) {
		Filme f = Filme.findById(id);
		List<Categoria> categorias = Categoria.findAll();
		renderTemplate("Filmes/form.html", f, categorias);
	}
	@Administrador
	public static void remover(Long id) {
	  Filme f = Filme.findById(id);
			f.delete();
			
			listar(null);
			}
	public static void detalhar( Long id) {
		Filme filme = Filme.findById(id);
		List<Critica> critica = Critica.findAll();
		render(critica, filme);
	}
	
	public static void listar(String termo) {
		List<Filme> filmes = null;
		if (termo == null || termo.isEmpty()) {
			filmes = Filme.findAll();			
		} else {
			filmes = Filme.find("lower(titulo) like ?1",
					"%"+ termo.toLowerCase() +"%").fetch();
		}
		render(filmes, termo);
	}
	
	
	public static void salvar(Filme filme) {
		
		filme.save();
		
		flash.success("Filme inserido no acervos");

		listar(null);
	}
}


