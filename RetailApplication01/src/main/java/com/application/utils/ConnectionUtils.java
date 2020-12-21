package com.application.utils;

import java.sql.*;

import java.util.Properties;

import javax.sql.DataSource;

/*import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;*/

import java.io.*;

public class ConnectionUtils {

	public static Connection getMySqlConnection() {

		Connection connection = null;
		try {
			String[] values = getPropsAsArray();
			connection = DriverManager.getConnection(values[0], values[1], values[2]);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}

		return connection;
	}
	
	
	/* Test 1 checking for throws exception */
	private static String[] getPropsAsArray() throws IOException {

		String fileName = "resources/DbConnection.properties";

		InputStream stream = ConnectionUtils.class.getClassLoader().getResourceAsStream(fileName);

		Properties props = new Properties();
		props.load(stream);

		try {
			Class.forName(props.getProperty("database.driverClass"));
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		String url = props.getProperty("database.url");
		String userName = props.getProperty("database.userName");
		String passWord = props.getProperty("database.passWord");

		return new String[] { url, userName, passWord };
	}

	public static void main(String[] args) {

		System.out.println(ConnectionUtils.getMySqlConnection());
	}

}
