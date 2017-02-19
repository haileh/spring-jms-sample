package com.github.haileh.sample.jms.listener;

import javax.jms.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.haileh.sample.jms.constants.Consts;
import com.github.haileh.sample.jms.model.Product;
import com.github.haileh.sample.jms.service.StoreService;

@Component
public class Listener {
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private ConnectionFactory connectionFactory;
	
	@Autowired
	private ErrorHandler myHandler;
	
	/**
	 * Logging.
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@JmsListener(containerFactory = "myContainerFactory", destination = Consts.QUEUE_NAME)
	public void receiveProductFromQueue(Product product) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		log.info("RECEIVING: " + mapper.writeValueAsString(product));
		storeService.registerOrder(product);
	}
	
	@Bean
	public DefaultJmsListenerContainerFactory myContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new 
				DefaultJmsListenerContainerFactory();		
		factory.setConnectionFactory(connectionFactory);
		factory.setErrorHandler(myHandler);
		return factory;
	}

}
