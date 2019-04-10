package com.asc.game.dao;

import com.asc.game.model.Records;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RecordsDAO {
	/**
	 * @return All existing records.
	 */
	public List<Records> findAll(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbConnection");
		EntityManager em = emf.createEntityManager();
		try{
			@SuppressWarnings("unchecked")
			List<Records> results = em
					.createQuery("from Records order by wintime asc")
					.getResultList();
			return results;
		}finally{
			em.close();
			emf.close();
		}
		

	}
	/**
	 * Inserts a new record into the table.
	 * @param record A {@link Records} to be inserted.
	 */
	public void insert(Records record){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbConnection");
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(record);
			em.getTransaction().commit();
		}finally{
			em.close();
			emf.close();
		}
	}
	
}
