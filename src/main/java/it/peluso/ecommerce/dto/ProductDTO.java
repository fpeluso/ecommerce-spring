package it.peluso.ecommerce.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="catalogue")
public class ProductDTO {
	@Id private String id;
	
	private String name;
	private String description;
	private Double price;
	private Integer quantity;
	
	public ProductDTO() {
		this.name = "";
		this.description = "";
		this.quantity = 0;
		this.price = 0.0;
	}
	
	public ProductDTO(String name, String description, Double price) {
		this();
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public ProductDTO(String name, String description, Double price, Integer quantity) {
		this(name, description, price);
		this.quantity = quantity;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return this.name + ": " + this.description + " (" + this.quantity + ", €" + this.price + " cad.)";
	}
}
