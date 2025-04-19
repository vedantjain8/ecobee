package com.ecommerce.beans;

public class Product {
	private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String image_url;
    
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image) {
		this.image_url = image;
	}

    
    
    // Getters and setters...
}
