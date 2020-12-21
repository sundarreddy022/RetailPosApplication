package com.application.exceptions;

import org.apache.log4j.Logger;

/*
 * Created a custom Exception if the item Number that is entered is out
 * of the list of category items
 *
 */
public class IncorrectItemCategoryException extends Exception {

	Logger log = Logger.getLogger(this.getClass().getName());

	private static final long serialVersionUID = 101L;
	String message;

	public IncorrectItemCategoryException() {
		super();
	}

	public IncorrectItemCategoryException(String msg) {
		super(msg);
		log.error(msg);
		this.message = msg;

	}

	@Override
	public String getMessage() {

		return this.message;
	}

}
