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

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * A ServletRequestListener.
 */
public class HelloworldServletRequestListener implements ServletRequestListener {
    public static final String HELLO_TXT = "hellotxt";

    public void requestInitialized(ServletRequestEvent event) {
    	System.out.println("HelloworldServletRequestListener.requestInitialized() "+event.getServletRequest());
    	System.out.println(event.getServletRequest().getParameterMap());
        event.getServletRequest().setAttribute(HELLO_TXT, prepareMessage("James"));
    }

    public void requestDestroyed(ServletRequestEvent event) {
    	System.out.println("HelloworldServletRequestListener.requestDestroyed() "+event.getServletRequest());
    }

    private String prepareMessage(String name) {
        StringBuffer sb = new StringBuffer();
        sb.append("<h2>Packt Helloworld ServletRequestListener</h2>");
        sb.append("HelloworldServletRequestListener says, Hello "+name);
        sb.append("<hr>");

        return sb.toString();
    }
}
