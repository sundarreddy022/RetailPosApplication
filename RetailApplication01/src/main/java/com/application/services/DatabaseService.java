package com.application.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.application.models.Products;

public class DatabaseService {

	private Connection connection;

	public DatabaseService(Connection connection) {
		super();
		this.connection = connection;
	}

	/*
	 * Here we store our daily sales in our local Database and our monthly reports
	 * as well
	 */

	public boolean add(Products product, String category) {

		/*
		 * Schema of table "dailysalesreport"
		 * 
		 * | Field | Type | Null | Key | Default | Extra |
		 * +------------+-------------+------+-----+---------+-------+ | Slno | int | NO
		 * | PRI | NULL | | | ItemName | varchar(20) | YES | | NULL | | | UnitPrice |
		 * double | YES | | NULL | | | Quantity | double | YES | | NULL | | | Amount |
		 * double | YES | | NULL | | | dateofsale | date | NO | | NULL | |
		 * +------------+-------------+------+-----+---------+-------+
		 */

		Date todayDate = Date.valueOf(LocalDate.now());

		String sql = "insert into dailysalesreport values(?,?,?,?,?,?,?)";
		int rowAdded = 0;
		int serialNum = 1;
		try (PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setInt(1, serialNum);
			serialNum++;
			pstmt.setString(2, product.getItemName());
			pstmt.setDouble(3, product.getUnitPrice());
			pstmt.setDouble(4, product.getQuantity());
			pstmt.setDouble(5, (product.getQuantity() * product.getUnitPrice()));
			pstmt.setString(6, category);
			pstmt.setDate(7, todayDate);

			rowAdded = pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 

		return rowAdded == 1 ? true : false;
	}

	/*
	 * // Intentionally added throws for 'test' purpose using assertThrows public
	 * void getDailyReport(String category, Date date) throws SQLException {
	 * 
	 * String sql =
	 * "select Slno,ItemName,UnitPrice,Quantity,Amount from dailysalesreport where dateofsale=? and "
	 * ;
	 * 
	 * try (PreparedStatement pstmt = connection.prepareStatement(sql);) {
	 * pstmt.setDate(1, date);
	 * 
	 * ResultSet rs = pstmt.executeQuery();
	 * 
	 * while (rs.next()) {
	 * 
	 * } } return; }
	 */

}
