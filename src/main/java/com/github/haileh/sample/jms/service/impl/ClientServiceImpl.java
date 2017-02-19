package com.github.haileh.sample.jms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.haileh.sample.jms.constants.Consts;
import com.github.haileh.sample.jms.model.Product;
import com.github.haileh.sample.jms.service.ClientService;

/**
 * The implementation of {@link ClientService}
 * 
 * @author haile
 *
 */
@Service
public class ClientServiceImpl implements ClientService {	
	
	/**
	 * The JmsTemplate used to send to the Jms queue.
	 */
	@Autowired
	private JmsTemplate jmsTemplate;
	
	/**
	 * Logging.
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * For JSON.
	 */
	private final ObjectMapper mapper = new ObjectMapper();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addProduct(Product product) {
		try {
			log.info("SENDING: " + mapper.writeValueAsString(product));		
			this.jmsTemplate.convertAndSend(Consts.QUEUE_NAME, product);
		} catch (JsonProcessingException jsonEx) {
			log.error("ERROR SENDING: " + jsonEx.getMessage());
		} catch (JmsException jmsEx) {
			log.error("ERROR SENDING: " + jmsEx.getMessage());
		}
	}

}
