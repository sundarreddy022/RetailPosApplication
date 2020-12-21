package com.application.Ifaces;

import java.sql.Date;
import java.util.Set;

import com.application.models.Products;

public interface DataAccess<T> {

	public void printAll(String Category);

	boolean SellItem(int itemCode, int quantity, int category);


	void generateDailyReport(String category, Date date);

	void generateMonthlyTopThree(String category);

}
