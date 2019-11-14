package com.baggage.entity.httpRequests;

public class ProductRequest {

	private String name;
	private Double cost;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductRequest(String name, Double cost, String description) {
		this.name = name;
		this.cost = cost;
		this.description = description;
	}

	public ProductRequest() {

	}

}
