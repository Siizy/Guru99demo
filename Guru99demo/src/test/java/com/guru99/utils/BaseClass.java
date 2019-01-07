package com.guru99.utils;

public class BaseClass extends Initialize {
	public void fish() {
	log.info(this.getClass().getSimpleName() + "is invoked");
	driver.close();
	}
}
