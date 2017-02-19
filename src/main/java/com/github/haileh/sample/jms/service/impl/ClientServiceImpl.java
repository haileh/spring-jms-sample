package com.github.haileh.sample.jms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.github.haileh.sample.jms.constants.Consts;
import com.github.haileh.sample.jms.model.Product;
import com.github.haileh.sample.jms.service.ClientService;

/**
 * <p>This class sends a new Product to the JMS queue.</p>
 * It uses JmsTemplate.
 * 
 * @author haile
 *
 */
@Service
public class ClientServiceImpl implements ClientService {	
	
	/**
	 * The JmsTemplate used to send to the Jms queue.
	 */
	private final JmsTemplate jmsTemplate;
	
	/**
	 * Logging.
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Default constructor.
	 * 
	 * @param jmsTemplate The JmsTemplate
	 */
	@Autowired
	public ClientServiceImpl(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	/**
	 * The JmsTemplate is used for converting Product instance and sent it to the Jms queue.
	 */
	@Override
	public void addProduct(Product product) {
		log.info("SENDING: " + product.toString());
		this.jmsTemplate.convertAndSend(Consts.QUEUE_NAME, product);
	}

}
