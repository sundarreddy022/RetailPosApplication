package com.application.models;

import java.time.LocalDate;

public class Electronics extends Products implements Comparable<Electronics> {

	private LocalDate warranty;

	public Electronics() {
		super();

	}

	public Electronics(int itemCode, String itemName, LocalDate warranty, double quantity, double unitPrice) {
		super(itemCode, itemName, quantity,unitPrice);

		this.warranty = warranty;

	}

	public LocalDate getWarranty() {
		return warranty;
	}

	public void setWarranty(LocalDate warranty) {
		this.warranty = warranty;
	}

	

	@Override
	public int compareTo(Electronics o) {

		if (this.getItemCode() > o.getItemCode())
			return 1;
		if (this.getItemCode() < o.getItemCode())
			return -1;
		else
			return 0;
	}

	@Override
	public String toString() {
		return "Electronics [itemCode=" + super.getItemCode() + ", itemName=" + super.getItemName() + ", warranty="
				+ warranty + ", quantity=" + super.getQuantity() + ", unitPrice=" + super.getUnitPrice() + "]";
	}

}
