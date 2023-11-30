package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;


@Entity
public class Categoria extends Model {
	
	
	public String titulo;
}
