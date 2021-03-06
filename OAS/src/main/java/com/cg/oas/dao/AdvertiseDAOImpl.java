package com.cg.oas.dao;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import javax.management.Query;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.cg.oas.entity.AdvertiseEntity;
import com.cg.oas.exceptions.AdvertiseNotFound;
import com.cg.oas.exceptions.AdvertiseNotFoundException;
import com.cg.oas.exceptions.InvalidDataFormatException;



public class AdvertiseDAOImpl implements AdvertiseDAO {

	private static Logger logger = LogManager.getLogger(AdvertiseDAOImpl.class.getName());	
	private static EntityManager entityManager;
	
	static {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("OnlineAdvertisePU");
		entityManager = entityManagerFactory.createEntityManager();
	}
	public AdvertiseEntity findAdvertiseTitle(String  title) throws AdvertiseNotFound {
	
		//Query query = (Query) entityManager.createQuery("select title from advertise where ad_id=?");
		Query query = (Query) entityManager.createNamedQuery("select title from advertise where ad_id=1");
		Object addentity = ((javax.persistence.Query) query).getSingleResult();
		//for(AdTitleDao ad: addlist) {
			//System.out.println("ad");
			
		//}
		return (AdvertiseEntity) addentity;
		
		//AdvertiseEntity advertiseEntity = ((Object) entityManager).query(AdvertiseEntity.class,"select title from advertise where ad_id=?");
		//logger.info("Seacrh Result : "    + advertiseEntity +   "Search Successful!!");
		//if(advertiseEntity==null)
			//throw new AdTitleNotFoundException("  Wrong AdvertiseId: " +  title);
	
	//return advertiseEntity;
		
	}
	
	// Delete by Advertisement ID
	
	public AdvertiseEntity DeleteById(int advertiseId) throws AdvertiseNotFound {
		entityManager.getTransaction().begin();
		AdvertiseEntity advertiseEntity = entityManager.find(AdvertiseEntity.class, advertiseId);
		entityManager.remove(advertiseEntity);
		entityManager.getTransaction().commit();
		System.out.println("Advertise deleted succssfully!!");
		logger.info("Database returned AdvertiseEntity: " + advertiseEntity);
		if(advertiseEntity==null)
			throw new AdvertiseNotFound("AdvertiseId: " + advertiseId);
		return advertiseEntity;// TODO Auto-generated method stub
}
	
	//function for reading advertise by id
		public AdvertiseEntity readAdvertiseById(int advertiseId) throws AdvertiseNotFoundException {
			AdvertiseEntity advertiseEntity = entityManager.find(AdvertiseEntity.class, advertiseId);
			logger.info("Database returned AdvertiseEntity: " + advertiseEntity);
			if(advertiseEntity==null)
				throw new AdvertiseNotFoundException("AdvertiseId: " + advertiseId);
			return advertiseEntity;
		}
		
		//function for editing advertise
		public AdvertiseEntity editAdvertise(int advertiseId) throws NullPointerException, AdvertiseNotFoundException {
			AdvertiseEntity advertiseEntity = entityManager.find(AdvertiseEntity.class,advertiseId);
			advertiseEntity.setTitle("Chair");
			advertiseEntity.setCategory("Furniture");
			advertiseEntity.setDescription("Adjustable height, foam padding for seat");
			advertiseEntity.setPrice(3500.00);
			entityManager.persist(advertiseEntity);
			entityManager.getTransaction().commit();
			logger.info("Database returned AdvertiseEntity: " + advertiseEntity);
			if(advertiseEntity==null)
				throw new AdvertiseNotFoundException("AdvertiseId: " + advertiseId);
			return advertiseEntity;
		}
		
		//function for reading all advertises
		public List<AdvertiseEntity> viewAllAdvertises() throws AdvertiseNotFoundException {
			
			List<AdvertiseEntity> list;
			Query query = entityManager.createQuery("SELECT advertises from AdvertiseEntity advertises");
			list = query.getResultList();
			if(list==null)
				throw new AdvertiseNotFoundException("No Entry in Database");
			return list;
		}

		public AdvertiseEntity postNewAdvertise(AdvertiseEntity adEntity) throws InvalidDataFormatException 
		{
				EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("OnlineAdvertisePU");
				entityManager = entityManagerFactory.createEntityManager();
				
				//check for advertise title
				if(AdvertiseDAOImpl.validateLetters(adEntity.getTitle())) 
				{
					//check for advertise category
					if (AdvertiseDAOImpl.validateLetters(adEntity.getCategory())) 
					{
						//check for advertise description
						if(AdvertiseDAOImpl.validateLetters(adEntity.getDescription()))
						{
						
							entityManager.getTransaction().begin();
							entityManager.persist(adEntity);
							entityManager.getTransaction().commit();

							
						}else {
							throw new InvalidDataFormatException("Invalid Description format should be in format xyz]");
						}
						
					} else {
						throw new InvalidDataFormatException("Invalid category name should contain only characters]");
					}
				}else {
					throw new InvalidDataFormatException("Invalid title name should contain only characters]");
				}

				return null;
			}
			
			public static boolean validateLetters(String txt) {

				String regx = "^[a-zA-Z\\s]+$";
				Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(txt);
				return matcher.find();

			}
	
	

}