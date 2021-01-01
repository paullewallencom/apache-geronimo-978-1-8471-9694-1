package com.pactpub.jms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JMSServlet extends HttpServlet {

	@Resource(name = "jms/ChatConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(name = "jms/ChatTopic")
	private Topic topic;

	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		MessageProducer producer = null;
		MessageConsumer consumer = null;
		Connection connection = null;
		Session session = null;
		String message = req.getParameter("message");
		try {
			if (message != null) {
				connection = connectionFactory.createConnection();
				connection.start();
				session = connection.createSession(false,
						Session.AUTO_ACKNOWLEDGE);
				producer = session.createProducer(topic);
				consumer = session.createConsumer(topic);
				final PrintWriter pw = res.getWriter();
				consumer.setMessageListener( new MessageListener(){
					public void onMessage(Message msg) {						
						try {
							pw.write(((TextMessage)msg).getText());
							pw.close();
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