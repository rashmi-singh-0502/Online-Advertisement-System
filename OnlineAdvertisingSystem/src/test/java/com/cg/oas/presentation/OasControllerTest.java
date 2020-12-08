package com.cg.oas.presentation;
import org.junit.BeforeClass;
import org.junit.Test;
import com.cg.oas.exceptions.NameIsBlankException;
import com.cg.oas.exceptions.AdvertiseNotFoundException;
import com.cg.oas.exceptions.DescriptionIsBlankException;
import com.cg.oas.exceptions.IdNotFoundException;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;


public class OasControllerTest 
{
	private static OasController oasController;
	private static Logger logger;
	@BeforeClass
	public static void setUp() 
	{
		logger = LogManager.getLogger(OasControllerTest.class.getName());
		oasController = new OasController();
	}
	
	@Test(expected = NameIsBlankException.class)
	public void testNameAddFailed() throws NameIsBlankException {
		logger.info("[START] testNameAddFailed() ");
		oasController.AddName(null);
		logger.info("[END] testNameAddFailed()");
	}
	@Test(expected = DescriptionIsBlankException.class)
	public void testDescAddFailed() throws DescriptionIsBlankException {
		logger.info("[START]  testDescAddFailed()");
		oasController.AddDescription(null);
		logger.info("[END]  testDescAddFailed()");
	}
	/*@Test(expected = IdNotFoundException.class)
	public void testDeleteByIdFailed() throws IdNotFoundException 
	{
		logger.info("[START]  testDeleteByIdFailed()");
		oasController.DeleteById(123);
		logger.info("[END]  testDeleteByIdFailed()");
	}*/
	@Test
	public void testAdvertiseSearchSuccess() throws AdvertiseNotFoundException {
		logger.info("[START] testAdvertiseSearchSuccess()");
		assertNotNull("Advertise Found", oasController.findAdvertiseById(1));
		logger.info("[END] testAdvertiseSearchSuccess()");
	}
	
	@Test(expected = AdvertiseNotFoundException.class)
	public void testAdvertiseSearchFailed() throws AdvertiseNotFoundException {
		logger.info("[START] testAdvertiseSearchFailed()");
		oasController.findAdvertiseById(-2);
		logger.info("[END] testAdvertiseSearchFailed()");
	}
}