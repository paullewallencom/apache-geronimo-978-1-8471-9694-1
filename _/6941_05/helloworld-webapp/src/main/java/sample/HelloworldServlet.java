/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package sample;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sample.ejb3.HelloworldService;
import sample.ejb3.LanguageService;

/**
 * Helloworld servlet
 */
public class HelloworldServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	 @Resource(name="jms/TestQueue")
	 private Queue queue;

		@Resource(name="jms/TestConnectionFactory")
	    private ConnectionFactory factory;
	 
	 @Resource(name="const/myEnvEntry")
	 private String country; 
    
    private String greeting;
    
    @EJB(name="ejb/LanguageService")
    LanguageService langService;
    
    @EJB(name="ejb/HelloService")
    HelloworldService helloService;
    
    @Override
    public void init(ServletConfig config) {
    	greeting = config.getInitParameter("greeting");
    	if(greeting == null) {
    		greeting = "Hello";
    	}
    	System.out.println("HelloworldServlet.country = "+country);
    	System.out.println("HelloworldServlet.queue = "+queue);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        String message = greeting +" "+name;
        
        Writer out = response.getWriter();
        out.write("<h2>Packt Helloworld Servlet</h2>");
        out.write("HelloworldServlet says, "+message);
        out.write(helloService.getGreetings(name));
        out.flush();
        out.close();

        Connection connection = null;
        MessageProducer messageProducer = null;
        Session sess = null;
        try {
            if ( name != null && !name.equals("") ) {
                System.out.println("Sending name "+name+" to JMS queue.");
                connection = factory.createConnection();
                sess = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);   
                TextMessage msg = sess.createTextMessage(name);
                messageProducer = sess.createProducer(queue);
                messageProducer.send(msg);
                System.out.println("Name "+name+" sent to JMS queue");
            }
        }
        catch ( Exception e ) {
            System.out.println("Error "+e);
            e.printStackTrace();
        }
        finally {
            try {
                if ( messageProducer != null ) messageProducer.close();
                if ( sess != null )sess.close();
                if ( connection!= null )connection.close();
            }
            catch ( JMSException e ) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void destroy() {
    }
}
