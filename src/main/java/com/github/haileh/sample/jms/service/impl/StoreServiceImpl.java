package com.github.haileh.sample.jms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.haileh.sample.jms.model.Product;
import com.github.haileh.sample.jms.service.StoreService;

/**
 * The implementation of {@link StoreService}
 * 
 * @author haile
 *
 */
@Service
public class StoreServiceImpl implements StoreService {
	
	/**
	 * List contains product instances.
	 */
	private final List<Product> receivedProducts = new ArrayList<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerOrder(Product product) {
		this.receivedProducts.add(product);
		// Or we can use another service to save this product instance to the DB (e.g MySQL...)
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Product> getReceiveProduct(String id) {
		return receivedProducts.stream().filter(p -> p.getProductId().equals(id)).findFirst();
		// Similarly to the above, we can use other service to retrieve the product from DB (e.g MySQL...)
	}

}
