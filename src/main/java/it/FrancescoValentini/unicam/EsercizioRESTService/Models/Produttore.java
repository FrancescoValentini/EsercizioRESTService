package it.FrancescoValentini.unicam.EsercizioRESTService.Models;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Produttore implements UserDetails {
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
    @JsonManagedReference
	@OneToMany(mappedBy = "produttore", fetch = FetchType.EAGER)
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
	@Override
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return Arrays.asList(new SimpleGrantedAuthority("Produttore"));	
	}
	
}
