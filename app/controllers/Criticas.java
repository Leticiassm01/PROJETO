package controllers;

import java.util.List;

import models.Categoria;
import models.Critica;
import models.Filme;
import play.mvc.Controller;

public class Criticas extends Controller {
	public static void form(Long id) {
		Filme f = Filme.findById(id);
		render(f);
	
	}
	public static void listar(String termo) {
		List<Critica> criticas = null;
		if (termo == null || termo.isEmpty()) {
			criticas = Filme.findAll();			
		} else {
			criticas = Filme.find("lower(critica) like ?1",
					"%"+ termo.toLowerCase() +"%").fetch();
		}
		render(criticas, termo);
	}

	
		public static void remover(Long id) {
		 Critica c = Critica.findById(id);
				c.delete();
				
				listar(null);
				}
			
	
		public static void salvar(Critica critica) {
			
			critica.save();
			
			

			listar(null);
		}
	
}
