package controllers;

import java.util.List;

import models.Categoria;
import play.mvc.Before;
import play.mvc.Controller;

public class Secure extends Controller {
	
	
	@Before (unless={"Pessoas.form", "Pessoas.salvar"})
    static void autentinficar() {
    	if (session.get("Pessoalogada") == null ) {
    		Logins.form();
    	} 

    }

	@Before(unless="Pessoas.form")
	public void verificarAdm() {
		String perfil = session.get("perfilUsu");
		Administrador admAnot = getActionAnnotation(Administrador.class);
		if(admAnot != null && !"administrador".equalsIgnoreCase(perfil)) {
			forbidden("Acesso só para adms");

			
		}
	
	}

}
