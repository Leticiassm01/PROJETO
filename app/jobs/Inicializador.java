package jobs;

import models.Categoria;
import models.Pessoa;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {
	
	@Override
	public void doJob() throws Exception {
		if (Pessoa.count() == 0) {
			
			Pessoa admin = new Pessoa();
			admin.nome = "Admin";
			admin.email = "admin@admin.com";
			admin.senha = "12345";
			admin.perfil ="administrador";
			admin.save();
			
			
			Categoria c = new Categoria();
			c.titulo = "terror";
			c.save();
			Categoria j = new Categoria();
			j.titulo = "romance";
			j.save();
			Categoria m = new Categoria();
			m.titulo = "drama queen";
			m.save();
			Categoria d = new Categoria();
			d.titulo = "suspense";
			d.save();
			Categoria r = new Categoria();
			r.titulo = "animação";
			r.save();
		}

	}
}
