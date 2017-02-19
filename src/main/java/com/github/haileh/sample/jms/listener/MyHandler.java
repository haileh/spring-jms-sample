package com.github.haileh.sample.jms.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

/**
 * Error handler class for any error during the execution.
 * 
 * @author haile
 *
 */
@Component
public class MyHandler implements ErrorHandler {

	/**
	 * Logging.
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handleError(Throwable t) {
		log.error("ERROR in Listener: " + t);
	}

}
