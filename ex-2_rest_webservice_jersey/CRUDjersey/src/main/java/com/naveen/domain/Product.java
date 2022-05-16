package com.naveen.domain;

import java.util.Objects;

public class Product {
	private int id;
    private String name;
    private float price;

	public Product(int id) {
        this.setId(id);
    }
 
    public Product() {
    }
 
    public Product(int id, String name, float price) {
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id != other.id)
            return false;
        return true;
    } 
 
//	@Override
//	public int hashCode() {
//		return Objects.hash(id, name, price);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Product other = (Product) obj;
//		return id == other.id && Objects.equals(name, other.name)
//				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price);
//	}
    
}
