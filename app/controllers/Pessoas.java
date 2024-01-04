package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Filme;
import models.Pessoa;
import play.data.validation.Valid;
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
	
	@Administrador
	public static void remover(Long id) {
		Pessoa p = Pessoa.findById(id);
			p.delete();
			
			flash.success("A pessoa " + p.nome + " foi removida com sucesso.");
			
			listar(null);
			}
		
	public static void detalhar(Long id) {
		Pessoa p = Pessoa.findById(id);
		render(p);
	}
	
	public static void listar(String termo) {
	/*	List<Pessoa> pessoas = null;
		if (termo == null || termo.isEmpty()) {
			pessoas = Pessoa.findAll();			
		} else {
			pessoas = Pessoa.find("lower(nome) like ?1 or lower(email) like ?1",
					"%"+ termo.toLowerCase() +"%").fetch();
	}*/	
		render();
	}
	
	public static void favoritar(Long id) {
		
		String email = session.get("emailLogado");
		Pessoa pessoa = Pessoa.find("email = ?1", email).first(); 
		Filme f = Filme.findById(id);
		
		if (pessoa.filmes == null) {
			pessoa.filmes = new ArrayList<>();
		}
		pessoa.filmes.add(f);
		
		pessoa.save();
		flash.success("O filme " + f.titulo + " foi favoritado com sucesso!");
	    Filmes.listar("");
	}
	
	
	public static void salvar(@Valid Pessoa pessoa, Long idfilme) {
	
		if(validation.hasErrors()) {
			validation.keep();
			flash.error("Preencha os campos corretamente");
			form();
			
		}
		
		pessoa.nome = pessoa.nome.toUpperCase();
		pessoa.email = pessoa.email.toLowerCase();
		
		pessoa.save();
		
		flash.success("A pessoa foi cadastrada com sucesso.");
		
		listar(null);
		
		editar(pessoa.id);
	}
	
	public static void listarAjax(String termo) {
		List<Pessoa> p = Collections.emptyList();
		if (termo == null || termo.trim().isEmpty()) {
			p = Pessoa.findAll();
		} else {
			p = Pessoa.find("nome like ?1 or email like ?1", "%"+termo+"%").fetch();
		}

		renderJSON(p);
	}
}
