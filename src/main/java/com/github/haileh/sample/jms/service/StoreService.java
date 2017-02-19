package com.github.haileh.sample.jms.service;

import java.util.Optional;

import com.github.haileh.sample.jms.model.Product;

/**
 * Receives the product instance and saves it the list of products.
 * 
 * @author haile
 *
 */
public interface StoreService {
	/**
	 * Receives from Jms queue and saves the product instance to list.
	 * 
	 * @param product The product instance from the queue
	 */
	void registerOrder(Product product);
	
	/**
	 * Get the product instance from the list.
	 * 
	 * @param id The product ID to retrieve
	 * @return The product instance
	 */
	Optional<Product> getReceiveProduct(String id);
}
