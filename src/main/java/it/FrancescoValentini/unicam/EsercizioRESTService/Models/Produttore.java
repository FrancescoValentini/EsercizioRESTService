package it.FrancescoValentini.unicam.EsercizioRESTService.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Produttore {
	@Id
	private String id;
	private String nome;
	private String cognome;
	private String username;
	
	@JsonIgnore
	private String password;

	/*
	 * Relazione Uno a molti
	 * 
	 */
	@OneToMany(mappedBy = "produttore")
	private List<Product> products;

	public Produttore() {
	}

	public Produttore(String id, String nome, String cognome, String password) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = nome + "." + cognome;
		this.password = password;
	}
	
	public Produttore(String id, String nome, String cognome,String username ,String password) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
