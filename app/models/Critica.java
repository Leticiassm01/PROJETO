package models;



import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Critica extends Model {
	
	public String critica;
	
	@ManyToOne
	public Filme filme;
	
}
