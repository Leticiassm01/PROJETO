package controllers;

import java.util.List;

import models.Categoria;
import models.Pessoa;
import play.libs.Crypto;
import play.mvc.Controller;

public class Logins extends Controller{
	
	public static void form() {
			
		render(); }
	
	public static void logar(String email, String senha) {
		
		Pessoa pessoaLog = Pessoa.find("email = ?1 and senha = ?2", email, Crypto.passwordHash(senha)).first();
		
		if(pessoaLog == null) {
			form();
		}else {
			session.put("Pessoalogada", pessoaLog.nome);
			session.put("perfilUsu", pessoaLog.perfil);
			Pessoas.form();
		}
	/*	if (pessoaBanco != null) {
			session.put("usuarioLogado", pessoaBanco.nome);
			flash.success("Login realizado com sucesso!");
			Pessoas.listar("");
		}
		
		flash.error("Credenciais inválidas");
		login();
	}
		*/
	} 
	public static void sair() {
		session.clear();
		form();
	}
	

}
