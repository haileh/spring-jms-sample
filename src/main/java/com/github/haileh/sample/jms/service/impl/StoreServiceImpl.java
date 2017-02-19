package com.github.haileh.sample.jms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.haileh.sample.jms.model.Product;
import com.github.haileh.sample.jms.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {
	private final List<Product> receivedProducts = new ArrayList<>();

	@Override
	public void registerOrder(Product product) {
		this.receivedProducts.add(product);
		
	}

	@Override
	public Optional<Product> getReceiveProduct(String id) {
		return receivedProducts.stream().filter(p -> p.getProductId().equals(id)).findFirst();
	}

}
