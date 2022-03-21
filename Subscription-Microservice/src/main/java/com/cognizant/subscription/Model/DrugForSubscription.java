package com.cognizant.subscription.Model;
public class DrugForSubscription {
	private String name;
	public DrugForSubscription(String string, int x) {
		name=string;
		quantity=x;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	private int quantity;
	public DrugForSubscription() {
		super();
	}
	
}