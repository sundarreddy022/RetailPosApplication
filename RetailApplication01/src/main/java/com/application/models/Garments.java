package com.application.models;



public class Garments extends Products implements Comparable<Garments> {

	private String category;

	public Garments() {
		super();

	}

	public Garments(int itemCode, String itemName, String category, double quantity, double unitPrice) {

		super(itemCode, itemName, quantity, unitPrice);

		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int compareTo(Garments o) {

		if (this.getItemCode() > o.getItemCode())
			return 1;
		if (this.getItemCode() < o.getItemCode())
			return -1;
		else
			return 0;
	}

	@Override
	public String toString() {
		return "Garments [itemCode=" + super.getItemCode() + ", itemName=" + super.getItemName() + ", category="
				+ category + ", quantity=" + super.getQuantity() + ", unitPrice=" + super.getUnitPrice() + "]";
	}

}
