package com.guru99.pagefiles;

import org.testng.annotations.Test;

import com.guru99.utils.BaseClass;

public class HomePage extends BaseClass {

	@Test
	public void cinema() {
		driver.get(Config.getProperty("URL"));
		System.out.println("cinema is executed");
		
	}
	
	@Test
	public void cinema2() {
		driver.get(Config.getProperty("URL"));
		System.out.println("cinema2 is executed");
		
	}
}
