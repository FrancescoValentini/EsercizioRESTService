package it.FrancescoValentini.unicam.EsercizioRESTService.DTO;

public class ProductDTO {
	private String id;
	private String name;
	private double prezzo;
	/**
	 * @param id
	 * @param name
	 * @param prezzo
	 */
	public ProductDTO(String id, String name, double prezzo) {
		this.id = id;
		this.name = name;
		this.prezzo = prezzo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	
}
