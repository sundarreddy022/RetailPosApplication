package com.application.services;


import com.application.exceptions.IncorrectItemCategoryException;
import com.application.models.Edibles;
import com.application.models.Electronics;
import com.application.models.Garments;
import com.application.models.Products;

public class SellProduct {
	
	//Instead of Instantiating for different Categories,we Return the Reference 
	//of the Category selected by the User
	public Products getReference(int key) throws IncorrectItemCategoryException {
		
		if(key>4) {
		throw new IncorrectItemCategoryException("Enter an Item from the given Category Fields");
		}
		
		switch(key) {
		
		case 1:
			return new Edibles();
		case 2:
			return new Electronics();
		case 3:
			return new Garments();
		default:
			return null;
		
		}
	}
	
	
}
