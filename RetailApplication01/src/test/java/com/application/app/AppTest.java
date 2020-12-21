package com.application.app;

import static java.time.Duration.ofMillis;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.OffsetTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.application.exceptions.IncorrectItemCategoryException;
import com.application.models.Edibles;
import com.application.services.DatabaseService;
import com.application.services.ProductService;
import com.application.services.SellProduct;



class AppTest {
	
	ProductService prodService=new ProductService();
	
	
	
	@Disabled
	@Test
	void testCalculate() {
		fail("Not yet implemented");
	}
	
	
	
	
	
	/*         Test 1         */
	
	@BeforeAll
	@DisplayName("Asserting that Connection is not null")
	void testForConnectionNotNull() {
		//Checking for connection establishment before any tests
		assertNotNull(this.prodService.getConnection());
		
	}
	
	/*    TEST 2        */
	@Test
	@DisplayName("Test for Successful Item Sale")
	void testForSuccessfulItemSale() {
		//public boolean SellItem(int itemCode, int quantity, int category)
		assertTrue(this.prodService.SellItem(1, 10,1));
		
	}
	
	
	/*         Test 3         */
	
	@Test
	@DisplayName("Testing for IncorrectItemCategoryException")
	void testForCustomException(SellProduct sellProduct) {
	
		assertThrows(IncorrectItemCategoryException.class,() -> sellProduct.getReference(5));
		
	}
	
	/*         Test 4         */
	@Test
	@DisplayName("Test if Addition to DataBase completes in 3 seconds")
	void testCalculateForTimeout(DatabaseService dbService) {
		Edibles edibleProduct = new Edibles(10, "Banana", "VEG", 0, 50, LocalDate.now() , LocalDate.now().plusDays(30));
		assertTimeout(ofMillis(3000),()->dbService.add(edibleProduct, "Edibles"));
		
	}
	
	/*         Test 5         */
	@AfterAll
	@DisplayName("Test for Database Addition")
	void testForDatabaseAddition() {
		Edibles edibleProduct = new Edibles(10, "Banana", "VEG", 0, 50, LocalDate.now() , LocalDate.now().plusDays(30));
		
	}

}
