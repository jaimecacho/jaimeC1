package test;

import org.testng.annotations.Test;

import testdeclaration.testdeclarationAPI;

public class suiteAPI extends testdeclarationAPI{
	
	@Test
	public void APIvalidationALL() {
		super.APIvalidationALL();
	}
	@Test
	public void APIvalidationCountry1() {
		super.APIvalidationCountry("GB");
	}
	@Test
	public void APIvalidationCountry2() {
		super.APIvalidationCountry("US");
	}
	@Test
	public void APIvalidationCountry3() {
		super.APIvalidationCountry("DE");
	}
	@Test
	public void APIvalidationCountryNoValid() {
		super.APIvalidationCountry("XX");
	}

	@Test
	public void APIPostValidation() {
		super.APIPostValidation("Test Country","TC","TCY");
	}
	

}
