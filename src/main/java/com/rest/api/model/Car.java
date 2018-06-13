package com.rest.api.model;

public class Car {
	public long id;
	public String brand;
	public String model;
	public int version;
	
	public Car() {
	}
	
	public Car(long id, String brand, String model, int version) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.version = version;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
}
