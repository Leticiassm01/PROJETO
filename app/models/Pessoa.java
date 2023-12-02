package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import play.data.validation.Max;
import play.data.validation.MaxSize;
import play.data.validation.Min;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Pessoa extends Model {
    
	@Required
	public String nome;
	
	@Required
	public String email;
	
    @Required
	public String senha;
	
	@Required
	public String perfil;
	
	@ManyToMany
	@JoinTable(name="pessoa_filme")
	public List<Filme> filmes;
	
	@Override
	public String toString() {
		return nome + " " + email;
	}
}
