package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Filme;
import models.Pessoa;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)

public class Pessoas extends Controller {
	
	public static void form() {
		render();
	}
	
	
	
	public static void editar(Long id) {
		Pessoa p = Pessoa.findById(id);
		
		List<Filme> filmes = Filme.findById(id);
		renderTemplate("Pessoas/form.html", p, filmes);
	}
	
	public static void remover(Long id) {
		Pessoa p = Pessoa.findById(id);
			p.delete();
			
			listar(null);
			}
		
	
	
	public static void listar(String termo) {
		List<Pessoa> pessoas = null;
		if (termo == null || termo.isEmpty()) {
			pessoas = Pessoa.findAll();			
		} else {
			pessoas = Pessoa.find("lower(nome) like ?1 or lower(email) like ?1",
					"%"+ termo.toLowerCase() +"%").fetch();
		}
		render(pessoas, termo);
	}
	
	public static void favoritar(Long id) {
		Pessoa pessoa = Pessoa.find("email = ?1", "admin@admin.com").first(); 
		Filme f = Filme.findById(id);
		if (pessoa.filmes == null) {
			pessoa.filmes = new ArrayList<>();
		}
		pessoa.filmes.add(f);
		pessoa.save();
	    Filmes.listar("");
	}
	
	public static void detalhar(Long id) {
		Pessoa pessoa = Pessoa.findById(id);
		render(pessoa);
	}
	
	public static void salvar(Pessoa pessoa, Long idfilme) {
		pessoa.nome = pessoa.nome.toUpperCase();
		pessoa.email = pessoa.email.toLowerCase();
		pessoa.save();
		
		//flash.success("A pessoa foi cadastrada com sucesso.");

		listar(null);
		editar(pessoa.id);
	}
}