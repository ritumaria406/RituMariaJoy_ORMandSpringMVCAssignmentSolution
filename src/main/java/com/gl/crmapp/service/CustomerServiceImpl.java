package com.gl.crmapp.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import com.gl.crmapp.entity.Customer;

@Repository
public class CustomerServiceImpl implements CustomerService{

	private SessionFactory sessionFactory;
	private Session session;
	
	
	public  CustomerServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.session = this.sessionFactory.openSession();
	}

	public List<Customer> findAll() {
		
		Transaction tx = session.beginTransaction();
		
		// from "EntityName"
		List<Customer> books = session.createQuery("from Customer", Customer.class).list();
		
		tx.commit();
		
		return books;
	}

	
	public Customer findById(int id) {
		Transaction tx = session.beginTransaction();
		Customer book = session.get(Customer.class,id);
		
		tx.commit();
		return book;
	}

	public void save(Customer book) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(book);
		tx.commit();
	}

	public void deleteById(int id) {
		Transaction tx = session.beginTransaction();
		
		
		try {
			Customer book = session.get(Customer.class, id);
		session.delete(book);
		} finally {
			tx.commit();
		}
		
	}
}
