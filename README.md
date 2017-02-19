## Synopsis

This small project demonstrates the simple usage of sending message to a JMS Message Broker and receiving the message from this Message Broker.

The application uses Spring's __JmsTemplate__ to post a single message (a POJO in this case) and subscribes to it with a __@JmsListener__ annonated method of a managed bean.

Spring core is used and the Spring Boot feature is also used as well to leverage the automatic configuration of all the dependencies we add to the project.

First, take a look at the project structure:

spring-boot-jms-sample
¦   .gitignore
¦   pom.xml
¦   README.md
¦
+---src
¦   +---main
¦   ¦   +---java
¦   ¦   ¦   +---com
¦   ¦   ¦       +---github
¦   ¦   ¦           +---haileh
¦   ¦   ¦               +---sample
¦   ¦   ¦                   +---jms
¦   ¦   ¦                       ¦   ApplicationConfig.java
¦   ¦   ¦                       ¦
¦   ¦   ¦                       +---constants
¦   ¦   ¦                       ¦       Consts.java
¦   ¦   ¦                       ¦
¦   ¦   ¦                       +---listener
¦   ¦   ¦                       ¦       Listener.java
¦   ¦   ¦                       ¦       MyHandler.java
¦   ¦   ¦                       ¦
¦   ¦   ¦                       +---model
¦   ¦   ¦                       ¦       Product.java
¦   ¦   ¦                       ¦
¦   ¦   ¦                       +---service
¦   ¦   ¦                           ¦   ClientService.java
¦   ¦   ¦                           ¦   StoreService.java
¦   ¦   ¦                           ¦
¦   ¦   ¦                           +---impl
¦   ¦   ¦                                   ClientServiceImpl.java
¦   ¦   ¦                                   StoreServiceImpl.java
¦   ¦   ¦
¦   ¦   +---resources
¦   +---test
¦       +---java
¦       ¦   +---com
¦       ¦       +---github
¦       ¦           +---haileh
¦       ¦               +---sample
¦       ¦                   +---jms
¦       ¦                       +---test
¦       ¦                               TestSpringJms.java
¦       ¦
¦       +---resources

A simple [Product](./src/main/java/com/github/haileh/sample/jms/model/Product.java) POJO is defined.

The Message Sender [ClientServiceImpl](./src/main/java/com/github/haileh/sample/jms/service/impl/ClientServiceimpl.java)
It simply uses `jmsTemplate.convertAndSend(<destination>, <message object>)` to send message (the Product instance) to Jms queue.

The message driven POJO receiver [Listener](./src/main/java/com/github/haileh/sample/jms/listener/Listener.java)
The `@JmsListener` annotation defines the name of the `Destination` of Jms queue that this method should listen to and reference to the `DefaultJmsListenerContainerFactory` to use to create the underlying message listener container. An customized error handler is also used.

## Code Example

**Sending message**
`Product product = new Product("1", "Product 1", "Product 1 Description");
jmsTemplate.convertAndSend("haileh.queue", product);
`

**When message is retrieved from queue**
`@JmsListener(containerFactory = "myContainerFactory", destination = "haileh.queue')
public void receiveProductFromQueue(Product product) {
		// save the Product to the DB
}`

## Installation

From the root of the project, execute the Maven command line
`mvn install`

This is a "Spring boot" application means a "stand-alone" application, we can easily run it as:
`cd target`
`java -jar spring-boot-jms-sample.jar`

## Tests

A Spring Boot test class is provided [TestSpringJms](./src/test/java/com/github/haileh/sample/jms/test/TestSpringJms.java)