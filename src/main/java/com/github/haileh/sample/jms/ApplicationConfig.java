package com.github.haileh.sample.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.haileh.sample.jms.model.Product;
import com.github.haileh.sample.jms.service.ClientService;
import com.github.haileh.sample.jms.service.StoreService;

@SpringBootApplication
public class ApplicationConfig {

	public static void main(String[] args) throws JsonProcessingException {
		ConfigurableApplicationContext context = 
				SpringApplication.run(ApplicationConfig.class, args);
		ClientService clientService = (ClientService) context.getBean(ClientService.class);
		StoreService storeService = (StoreService) context.getBean(StoreService.class);
		
		// for json
		final ObjectMapper mapper = new ObjectMapper();
		
		// A new Product pojo
		Product product = new Product("1", "Product 1", "Product 1 Description");
		
		// send to jms queue
		System.out.println("SENDING to Jms queue: " + mapper.writeValueAsString(product));
		clientService.addProduct(product);
		
		// receive from queue
		Product receivedProduct = storeService.getReceiveProduct("1").get();
		System.out.println("RECEIVING from Jms queue: " + mapper.writeValueAsString(receivedProduct));
	}

}
