package it.FrancescoValentini.unicam.EsercizioRESTService.Models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Produttore {
	@Id
	String id;
	String nome;
	String cognome;

	/*
	 * Relazione Uno a molti
	 * 
	 */
	@OneToMany(mappedBy = "produttore")
	private List<Product> products;

	public Produttore() {
	}

	public Produttore(String id, String nome, String cognome) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
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
}
