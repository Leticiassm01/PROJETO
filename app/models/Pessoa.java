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

import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Pessoa extends Model {

	public String nome;
	public String email;
	public String senha;
	public String perfil;
	
	@ManyToMany
	@JoinTable(name="pessoa_filme")
	public List<Filme> filmes;
	
	public void setSenha(String s) {
		senha = Crypto.passwordHash(s);
	}

	@Override
	public String toString() {
		return nome + " " + email;
	}
}
