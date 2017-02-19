package com.github.haileh.sample.jms.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.haileh.sample.jms.model.Product;
import com.github.haileh.sample.jms.service.ClientService;
import com.github.haileh.sample.jms.service.StoreService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestSpringJms {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private StoreService storeService;
	
	@Test
	public void testSendSimpleProduct() throws InterruptedException {
		System.out.println("Sending product instance 1 to the Jms queue");
		clientService.addProduct(new Product("1", "Product 1", "Product 1 Description"));
		
		Thread.sleep(2000);
		
		System.out.println("Receiving a product instance 1 from Jms queue");

		Optional<Product> product = storeService.getReceiveProduct("1");
		Assert.assertTrue(product.isPresent());
		Assert.assertEquals("Product 1", product.get().getProductName());
		
		System.out.println("Sending a product instance 2 to the Jms queue");
		
		clientService.addProduct(new Product("2", "Product 2", "Product 2 Description"));
		
		Thread.sleep(2000);
		
		System.out.println("Receiving a product instance 2 from Jms queue");

		Optional<Product> product2 = storeService.getReceiveProduct("1");
		Assert.assertTrue(product2.isPresent());
		Assert.assertEquals("Product 2", product2.get().getProductName());
	}

}
