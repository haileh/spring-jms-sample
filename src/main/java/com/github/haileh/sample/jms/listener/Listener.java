package com.github.haileh.sample.jms.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.github.haileh.sample.jms.model.Product;
import com.github.haileh.sample.jms.service.StoreService;

@Component
public class Listener {
	
	@Autowired
	private StoreService storeService;
	
	/**
	 * Logging.
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@JmsListener(destination = "haileh.queue")
	public void receiveProductFromQueue(Product product) {
		log.info("RECEIVING: " + product.toString());
		storeService.registerOrder(product);
	}

}
