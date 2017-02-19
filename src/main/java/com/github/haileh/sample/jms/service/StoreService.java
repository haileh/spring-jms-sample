package com.github.haileh.sample.jms.service;

import java.util.Optional;

import com.github.haileh.sample.jms.model.Product;

public interface StoreService {
	void registerOrder(Product product);
	Optional<Product> getReceiveProduct(String id);
}
