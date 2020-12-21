package com.application.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.application.Ifaces.DataAccess;
import com.application.models.Edibles;
import com.application.models.Electronics;
import com.application.models.Garments;
import com.application.models.Products;
import com.application.utils.ConnectionUtils;
import com.application.utils.ProdQuantityComparators;

public class ProductService implements DataAccess<Products> {

	Logger log = Logger.getLogger(this.getClass().getName());

	private static ProdQuantityComparators compareByQuantity = new ProdQuantityComparators();

	private static TreeSet<Edibles> ediblesTreeSet = new TreeSet<Edibles>();

	private static TreeSet<Electronics> electronicsTreeSet = new TreeSet<Electronics>();

	private static TreeSet<Garments> garmentsTreeSet = new TreeSet<Garments>();

	Connection connection = ConnectionUtils.getMySqlConnection();

	DatabaseService dbService = new DatabaseService(connection);
	
	/*
	 * Creating the Edibles Tree Set
	 */
	
	public boolean createEdiblesTreeSet(List<Edibles> ediblesList) {

		Iterator<Edibles> itr = ediblesList.iterator();

		while (itr.hasNext()) {
			ediblesTreeSet.add(itr.next());

		}

		System.out.println("*EdibleTreeSet successfully Created and contains :" + ediblesTreeSet.size() + " items*");

		return true;

	}

	/*
	 * Creating the Electronics Tree Set
	 */
	
	public boolean createElectronicsTreeSet(List<Electronics> electronicsList) {

		Iterator<Electronics> itr = electronicsList.iterator();

		while (itr.hasNext()) {
			electronicsTreeSet.add(itr.next());

		}

		System.out.println(
				"*ElectronicsTreeSet successfully Created and contains :" + electronicsTreeSet.size() + " items*");

		return true;

	}
	
	/*
	 * Creating the Garments TreeSet
	 */
	
	public boolean createGarmentsTreeSet(List<Garments> garmentsList) {
		Iterator<Garments> itr = garmentsList.iterator();

		while (itr.hasNext()) {
			garmentsTreeSet.add(itr.next());

		}

		System.out.println(
				"*GarmentsTreeSet successfully Created and contains :" + electronicsTreeSet.size() + " items*");

		return true;

	}
	
	/*
	 * prinAll is a generalized method to print the items of any required category
	 */

	public void printAll(String Category) {

		String msg = Category.toUpperCase();
		log.info("******Printing the "+Category+" Category items******");
		switch (msg) {

		case "EDIBLES":
			for (Edibles eachEdibles : ediblesTreeSet) {
				System.out.println(eachEdibles + " ");
			}

			break;
		case "ELECTRONICS":

			for (Electronics eachElectronics : electronicsTreeSet) {
				System.out.println(eachElectronics + " ");
			}
			break;
		case "GARMENTS":

			for (Garments eachGarments : garmentsTreeSet) {
				System.out.println(eachGarments + " ");
			}
			break;
		default:
			System.err.println("ERR-992 Enter a Valid Category: from 1)EDIBLES" + "2)ELECTRONICS" + "3)GARMENTS");
			System.err.println("ERR-592 OR Enter the Full Name of the Category");
			break;

		}

	}
	
	/*
	 * This method is used for generating a daily report of certain category
	 * on a particular date
	 */
	
	@Override
	public void generateDailyReport(String category, Date date) {

		printAll(category);
		return;
	}

	@Override
	public void generateMonthlyTopThree(String category) {
		
		
		return ;
	}
	
	
	/*
	 * Code to sell the selected option of item and make changes to the Tree set 
	 * and the database about the quantity etc.,
	 */
	@Override
	public boolean SellItem(int itemCode, int quantity, int category) {

		
		boolean result = false;
		double quan = 0;
		switch (category) {
		case 1: // 1-->Edibles Category as was entered by the User
			log.info("Edible item being sold");
			for (Edibles eachEdible : ediblesTreeSet) {
				if (eachEdible.getItemCode() == itemCode) {
					quan = eachEdible.getQuantity();
					quan += quantity;
					eachEdible.setQuantity(quan);
					result = true;
					System.out.println("Total quantity of " + eachEdible.getItemName() + " sold today is: " + quan);
						
					//Adding the sold item to the Database as part of the compiled dailysales report
					dbService.add(eachEdible,"Edibles");
				}
			}

			break;
		case 2: // 2 --> Electronics as was entered by the User
			log.info("Electronics item being sold");
			for (Electronics eachElectronics : electronicsTreeSet) {
				if (eachElectronics.getItemCode() == itemCode) {
					quan = eachElectronics.getQuantity();
					quan += quantity;
					eachElectronics.setQuantity(quan);
					result = true;
					System.out
							.println("Total quantity of " + eachElectronics.getItemName() + " sold today is: " + quan);
						
					//Adding the sold item to the Database as part of the compiled dailysales report
					dbService.add(eachElectronics,"Electronics");
				}
			}
			break;
		case 3: // 3 --> Garments as was selected by the user
			log.info("Garments item being sold");
			for (Garments eachGarments : garmentsTreeSet) {
				if (eachGarments.getItemCode() == itemCode) {
					quan = eachGarments.getQuantity();
					quan += quantity;
					eachGarments.setQuantity(quan);
					result = true;
					System.out.println("Total quantity of " + eachGarments.getItemName() + " sold today is: " + quan);
					
					//Adding the sold item to the Database as part of the compiled dailysales report
					dbService.add(eachGarments,"Garments"); 
				}
			}
			break;
		default:

			break;
		}

		return result; // Check the test code to see if BuyItem is executed Successfully
	}

	public Connection getConnection() {
		return connection;
	}

}
