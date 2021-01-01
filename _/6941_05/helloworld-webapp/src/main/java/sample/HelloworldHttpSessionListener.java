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

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * A HttpSessionListener that sets a "hellotxt" attribute in the session.
 */
public class HelloworldHttpSessionListener implements HttpSessionListener {
    public static final String HELLO_TXT = "hellotxt";

    public void sessionCreated(HttpSessionEvent event) {
    	System.out.println("HelloworldHttpSessionListener.sessionCreated() "+event.getSession().getId());
        event.getSession().setAttribute(HELLO_TXT, prepareMessage("Indiana"));
        event.getSession().setAttribute("dummy", new HelloworldHttpSessionBindingListener());
        event.getSession().setAttribute("dummy2", new HelloworldHttpSessionActivationListener());
    }

    public void sessionDestroyed(HttpSessionEvent event) {
    	System.out.println("HelloworldHttpSessionListener.sessionDestroyed() "+event.getSession().getId());
    }

    private String prepareMessage(String name) {
        StringBuffer sb = new StringBuffer();
        String message = "Hello "+name;

        sb.append("<h2>Packt Helloworld HttpSessionListener</h2>");
        sb.append("HelloworldHttpSessionListener says, "+message);
        sb.append("<hr>");

        return sb.toString();
    }
}
