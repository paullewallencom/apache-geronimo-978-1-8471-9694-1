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

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * A HttpSessionAttributeListener.
 */
public class HelloworldHttpSessionAttributeListener implements HttpSessionAttributeListener {
    public void attributeAdded(HttpSessionBindingEvent event) {
        if(event.getName().equals(HelloworldHttpSessionListener.HELLO_TXT)) {
            event.getSession().setAttribute(event.getName(), event.getValue()+prepareMessage("Jones"));
        }
    }

    public void attributeRemoved(HttpSessionBindingEvent event) {
    	System.out.println("HelloworldHttpSessionAttributeListener.attributeRemoved() "+event.getName());
    }

    public void attributeReplaced(HttpSessionBindingEvent event) {
    	System.out.println("HelloworldHttpSessionAttributeListener.attributeReplaced() "+event.getName());
    }

    private String prepareMessage(String name) {
        StringBuffer sb = new StringBuffer();
        String message = "Hello "+name;
        sb.append("<h2>Packt Helloworld HttpSessionAttributeListener</h2>");
        sb.append("HttpSessionAttributeListener says, "+message);
        sb.append("<hr>");
        return sb.toString();
    }
}
