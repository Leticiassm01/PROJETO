package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Secure extends Controller {
	
	@Before (unless={"Pessoas.form"})
    static void autentinficar() {
    	if (session.get("Pessoalogada") == null) {
    		Logins.form();
    	}
    //	if(session.contains("Pessoalogada") == null){
    	//	Logins.form();
    //	}
    }

	@Before(unless="Pessoas.form")
	public void verificarAdm() {
		String perfil = session.get("perfilUsu");
		Administrador admAnot = getActionAnnotation(Administrador.class);
		if(admAnot != null && !"administrador".equals(perfil)) {
			forbidden("Acesso só para adms");
		}
	
	}

}
