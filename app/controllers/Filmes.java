package controllers;

import java.util.List;

import models.Categoria;
import models.Critica;
import models.Filme;
import play.data.validation.Valid;
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
			flash.success("Filme removido do acervo");

			listar(null);
			}
	
	public static void detalhar( Long id) {
		Filme filme = Filme.findById(id);
		List<Critica> criticas = Critica.find("filme.id = ?1", filme.id).fetch();
		render(criticas, filme);
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
	
	
	public static void salvar(@Valid Filme filme) {

		if(validation.hasErrors()) {
			validation.keep();
			flash.error("Preencha os campos corretamente");
			form();
			
		}

		filme.save();
		
		flash.success("Filme inserido no cartaz");

		listar(null);
	}
}


