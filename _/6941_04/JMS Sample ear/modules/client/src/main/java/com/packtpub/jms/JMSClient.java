package com.pactpub.jms;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

public class JMSClient {

	@Resource(name = "jms/ChatConnectionFactory")
	private static ConnectionFactory connectionFactory;

	@Resource(name = "jms/ChatTopic")
	private static Topic topic;
	
	public static void main(String[] args){
		StringBuffer messageBuf = new StringBuffer();
		for(String arg:args){
			messageBuf.append(arg).append(" ");
		}
		sendRecvAndPrintMessage(messageBuf.toString());
	}
	
	
	private static void sendRecvAndPrintMessage(String message){
		MessageProducer producer = null;
		MessageConsumer consumer = null;
		Connection connection = null;
		Session session = null;		
		try {
			if (message != null) {
				connection = connectionFactory.createConnection();
				connection.start();
				session = connection.createSession(false,
						Session.AUTO_ACKNOWLEDGE);
				producer = session.createProducer(topic);
				consumer = session.createConsumer(topic);
				
				consumer.setMessageListener( new MessageListener(){
					public void onMessage(Message msg) {						
						try {
							System.out.println(((TextMessage)msg).getText());							
						} catch (JMSException e) {							
							e.printStackTrace(System.out);
						}						
					}					
				});

				TextMessage txtMessage = session.createTextMessage();
				txtMessage.setText(message);				
				producer.send(txtMessage);				
				Thread.sleep(1000);
			}

		} catch (JMSException e) {
			e.printStackTrace(System.out);
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e1) {
					e1.printStackTrace(System.out);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace(System.out);
		}
	}

}
