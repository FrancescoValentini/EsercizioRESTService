package it.FrancescoValentini.unicam.EsercizioRESTService.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
	@Id
	private String id;
	private String name;
	private double price;

	/*
	 * Relazione Molti a Uno
	 * 
	 * produttore_id è il nome della colonna nel DB che contiene la FK del
	 * produttore
	 * 
	 */
    @JsonBackReference
	@ManyToOne
	@JoinColumn(name = "produttore_id", nullable = false)
	private Produttore produttore;

	public Product(String id, String name, double price, Produttore produttore) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.produttore = produttore;
	}

	public Product(String id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Product() {}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Produttore getProduttore() {
		return this.produttore;
	}

	public void setProduttore(Produttore p) {
		this.produttore = p;
	}
}
