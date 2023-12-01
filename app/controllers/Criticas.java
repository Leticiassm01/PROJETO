package controllers;

import java.util.List;

import models.Categoria;
import models.Critica;
import models.Filme;
import models.Pessoa;
import play.mvc.Controller;

public class Criticas extends Controller {
	
	public static void form(Long id) {
		Filme f = Filme.findById(id);
		render(f);
	
	}
	public static void listar(Long termo) {
		List<Critica> criticas = null;
		if (termo == null) {
			criticas = Filme.findAll();			
		} else {
			criticas = Filme.find("lower(critica.filme.id) like ?1").fetch();
		}
		render(criticas);
	}

	@Administrador
	public static void remover(Long id) {
	  Critica c = Critica.findById(id);
			c.delete();
			flash.success("Critica removido do acervo");

			Filmes.detalhar(id);
			}

		public static void salvar(Critica critica) {
			
			critica.save();
			Filmes.listar(null);

			
		}
	
}
