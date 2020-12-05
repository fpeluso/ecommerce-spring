package it.peluso.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="catalogue")
public class Product {
	@Id private String id;
	
	private String nome;
	private String descrizione;
	private Double prezzo;
	private Integer quantita;
	
	public Product() {
		this.nome = "";
		this.descrizione = "";
		this.quantita = 0;
		this.prezzo = 0.0;
	}
	
	public Product(String nome, String descrizione, Double prezzo) {
		this();
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
	}
	
	public Product(String nome, String descrizione, Double prezzo, Integer quantita) {
		this(nome, descrizione, prezzo);
		this.quantita = quantita;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	
	@Override
	public String toString() {
		return this.nome + ": " + this.descrizione + " (" + this.quantita + ", €" + this.prezzo + " cad.)"; 
	}
}
