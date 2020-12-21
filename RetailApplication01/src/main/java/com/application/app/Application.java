package com.application.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.application.exceptions.IncorrectItemCategoryException;
import com.application.models.Edibles;
import com.application.models.Electronics;
import com.application.models.Garments;
import com.application.models.Products;
import com.application.services.ProductService;
import com.application.services.SellProduct;



public class Application {

	static Scanner scan = new Scanner(System.in);

	/*
	 * To run this exercise perfectly, 
	 * 1. change the credentials in DbConnection.properties (keep the database as test to avoid confusion)
	 * 2. create a table in your database with the following statement
	 * create table dailysalesreport(Slno integer,ItemName varchar(20),UnitPrice
	 * double, Quantity double,Amount double,Category varchar(20),dateofsale date
	 * not null);
	 * 
	 */

	/*
	 * The Thought Process behind this Design is to make the "app" as user-friendly
	 * as possible with minimal data requirements from an Bill-Counter Employee and
	 * to increase the Abstraction as much as possible, by asking the user only what
	 * is required.
	 * 
	 * 
	 */

	// SellAnItem() is used to make a call and take note of the item that is sold
	public static void SellAnItem(int itemCategory) {
		ProductService service = new ProductService();

		int itemCode = 0, quantity = 0;
		System.out.println("Enter the item you would Like to Buy: ");
		itemCode = scan.nextInt();
		System.out.println("Enter the quantity of the selected item: ");
		quantity = scan.nextInt();

		boolean itemSold = service.SellItem(itemCode, quantity, itemCategory);
		System.out.println("Item Added: " + itemSold);
		return;
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		ProductService productservice = new ProductService();
		/*
		 * Used a Thread because, we want to add all the products into the treeset only
		 * once because this is going to be a recurring sales activity throught the day.
		 * Otherwise we will lose count of the quantity and items sold.
		 */

		Thread t1 = new Thread() {
			
			public void run() {
				AddProducts ap = new AddProducts();
				ap.addProducts();
			}
		};

		t1.start();
		try {
			t1.join(); /*
						 * Used join to pause the execution of main thread Until all the Product Lists
						 * are added in the Collections
						 */
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
		
		

		/*
		 * The design is done keeping in mind of a day long sales activity where, an
		 * employee is required to enter the items sold in a recurring manner,so the use
		 * of endless for loop. Once the day is done, the employee clicks EXIT and Daily
		 * Activity Report is Generated.
		 */
		for (;;) {
			
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			Date dateTimeNow = new Date();
			
			
			/*
			 * This is an important Check method to ensure that the program 
			 * is stopped after Working-Hours(say) i.e., 10PM
			 * Even if anyone tried to access the program after 10PM the program shouldn't let them
			 */
			if(dateTimeNow.getHours()==22) {
				System.exit(0);
			}
			
			SellProduct sellProduct = new SellProduct();

			System.out.println("=====================================================");
			System.out.println("Press 4 to Exit and print the Daily and Monthly Sales Report");
			System.out.println(
					"Choose the Category Number" + "1. Edibles " + "2. Electronics " + "3. Garments " + "4. Exit");

			int itemCategory = scan.nextInt();

			if (itemCategory == 4) {

				System.out.println("Exiting For Loop");

				break;
			}

			Products prod = null;
			try {

				// Getting the reference of the Category based on the User Entry/Selection
				// Instead of Hard-wiring the Object types
				prod = sellProduct.getReference(itemCategory);
			} catch (IncorrectItemCategoryException e) {
				e.getMessage();
				e.printStackTrace();
			}

			// Checking for the ItemCategory="Edibles"

			if (prod instanceof Edibles) {

				System.out.println("Pick one of the itemCode and Quantity from the EDIBLES list seperated by commas: ");
				productservice.printAll("EDIBLES");

				SellAnItem(itemCategory);
			}

			// Checking for the ItemCategory="Electronics"

			else if (prod instanceof Electronics) {

				System.out.println("Pick one of the items from the Electronics list: ");
				productservice.printAll("ELECTRONICS");

				SellAnItem(itemCategory);

			}

			// Checking for the ItemCategory="Garments"

			else if (prod instanceof Garments) {

				System.out.println("Pick one of the items from the Garments list: ");
				productservice.printAll("GARMENTS");

				SellAnItem(itemCategory);

			}

		}
		// Outside the For Loop, Once the user enters EXIT
		// Here we call for the Final Day's Report and MonthlyReport
		System.out.println("Daily Report of Edibles: ");
		System.out.println("========================================================");
		productservice.generateDailyReport("Edibles", new java.sql.Date(2020, 12, 21));

		System.out.println("Daily Report of Electronics: ");
		System.out.println("========================================================");
		productservice.generateDailyReport("Electronics", new java.sql.Date(2020, 12, 21));

		System.out.println("Daily Report of Electronics: ");
		System.out.println("========================================================");
		productservice.generateDailyReport("Garments", new java.sql.Date(2020, 12, 21));

	}
}
