package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Filme extends Model {
	
	@Required
	@ManyToOne
	@JoinColumn(name="idcategoria")
	public Categoria categoria;
	
	@ManyToMany(mappedBy="filmes")
	public List<Pessoa> pessoas;	
	
	@Required
	public String titulo;
	
	 @Required
	public String diretor;
	 
	 
	 @Required
	public String sinopse;
	 
	 @Required
	 @MinSize(1)
	 @MaxSize(2)
	public Integer classificacao;
	

	
	@Override
	public String toString() {
		return titulo + " / " + diretor + " - " + sinopse  + " / " + classificacao;
	}

}
