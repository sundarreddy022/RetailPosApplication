package com.application.app;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.application.models.*;
import com.application.services.*;
import com.google.gson.Gson;

public class AddProducts {
	/* Add any new products here */
	/*
	 * Instead of maintaining all the data in a table, I have tried a new approach
	 * by adding these items into a TREE SET automatically every time when the
	 * application is run
	 */
	/*
	 * I wanted to work on the TreeSet once, So I have used this way. Please don't
	 * get me wrong
	 */

	Logger log = Logger.getLogger(this.getClass().getName());

	public boolean addProducts() {

		LocalDate dt = LocalDate.now();

		ProductService productservice = new ProductService();

		Edibles edibleprod1 = new Edibles(1, "Eggs", "NON-VEG", 0, 10, dt, dt.plusDays(10));
		Edibles edibleprod2 = new Edibles(2, "Chicken", "NON-VEG", 0, 20, dt, dt.plusDays(7));
		Edibles edibleprod3 = new Edibles(3, "Paneer", "VEG", 0, 30, dt, dt.plusDays(10));
		Edibles edibleprod4 = new Edibles(4, "Kaju", "VEG", 0, 40, dt, dt.plusDays(30));
		Edibles edibleprod5 = new Edibles(5, "Badam", "VEG", 0, 50, dt, dt.plusDays(30));

		List<Edibles> ediblesList = Arrays.asList(edibleprod1, edibleprod2, edibleprod3, edibleprod4, edibleprod5);

		productservice.createEdiblesTreeSet(ediblesList);

		log.info("Edible product list created");

		Electronics electronicprod1 = new Electronics(1, "Ipad", dt.plusYears(2), 0, 1000);
		Electronics electronicprod2 = new Electronics(2, "MacBook", dt.plusYears(2), 0, 2000);
		Electronics electronicprod3 = new Electronics(3, "Iphone", dt.plusYears(2), 0, 3000);
		Electronics electronicprod4 = new Electronics(4, "AppleWatch", dt.plusYears(2), 0, 4000);
		Electronics electronicprod5 = new Electronics(5, "Airpods", dt.plusYears(2), 0, 5000);

		List<Electronics> electronicsList = Arrays.asList(electronicprod1, electronicprod2, electronicprod3,
				electronicprod4, electronicprod5);

		productservice.createElectronicsTreeSet(electronicsList);

		log.info("Electronics product list Created ");

		Garments garment1 = new Garments(1, "Levis Jeans", "Male", 0, 1000);
		Garments garment2 = new Garments(2, "Bridal Wear", "Female", 0, 2000);
		Garments garment3 = new Garments(3, "Us Polo Shirt", "Male", 0, 3000);
		Garments garment4 = new Garments(4, "Allen Solly Blazer", "Male", 0, 4000);
		Garments garment5 = new Garments(5, "Inner wears", "Others", 0, 5000);

		List<Garments> garmentsList = Arrays.asList(garment1, garment2, garment3, garment4, garment5);

		productservice.createGarmentsTreeSet(garmentsList);

		log.info("Garments product list Created ");
		return true;
	}

}
