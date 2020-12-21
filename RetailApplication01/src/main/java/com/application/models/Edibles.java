package com.application.models;

import java.time.LocalDate;


public class Edibles extends Products implements Comparable<Products> {

	
	private String type;
	
	LocalDate dom, doe;

	public Edibles() {
		super();

	}

	public Edibles(int itemCode, String itemName, String type, double quantity, double unitPrice, LocalDate dom,
			LocalDate doe) {
		super(itemCode, itemName, quantity,unitPrice);
		
		this.type = type;
		this.dom = dom;
		this.doe = doe;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	


	public LocalDate getDom() {
		return dom;
	}

	public void setDom(LocalDate dom) {
		this.dom = dom;
	}

	public LocalDate getDoe() {
		return doe;
	}

	public void setDoe(LocalDate doe) {
		this.doe = doe;
	}

	@Override
	public int compareTo(Products o) {

		if (this.getItemCode() > o.getItemCode())
			return 1;
		if (this.getItemCode() < o.getItemCode())
			return -1;
		else
			return 0;
	}

	@Override
	public String toString() {
		return "Edibles [itemCode=" + super.getItemCode() + ", itemName=" + super.getItemName() + ", type=" + type
				+ ", quantity=" + super.getQuantity() + ", unitPrice=" + super.getUnitPrice() + ", dom=" + dom + ", doe=" + doe
				+ "]";
	}

}
