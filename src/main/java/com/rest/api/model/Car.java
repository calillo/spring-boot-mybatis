package com.rest.api.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class Car {
	
	public long id;
	
	//@Size(min = 1, max = 10, message = "Size.car.brand")
	@Size(min = 1, max = 10)
	public String brand;
	
	@NotNull(message = "{my.notnull}" )
	public String model;
	
	//@PositiveOrZero(message = "PositiveOrZero.car.version")
	@PositiveOrZero
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
