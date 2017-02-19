package com.github.haileh.sample.jms.service;

import com.github.haileh.sample.jms.model.Product;

/**
 * The ClientService is responsible for sending a new Product to the JMS queue.
 * 
 * @author haile
 *
 */
public interface ClientService {
	void addProduct(Product product);	

}
