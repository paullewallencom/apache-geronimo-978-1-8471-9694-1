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

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * A HttpSessionBindingListener.
 */
public class HelloworldHttpSessionBindingListener implements HttpSessionBindingListener {
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("valueBound: "+event.getName());
        event.getSession().setAttribute(HelloworldHttpSessionListener.HELLO_TXT, event.getSession().getAttribute(HelloworldHttpSessionListener.HELLO_TXT));
    }

    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("HttpSessionBindingEvent.valueUnound() :"+event.getName());
    }

    private String prepareMessage(String name) {
        StringBuffer sb = new StringBuffer();
        String message = "Hello "+name;
        sb.append("<h2>Packt Helloworld HttpSessionBindingListener</h2>");
        sb.append("HelloworldHttpSessionBindingListener says, "+message);
        sb.append("<hr>");

        return sb.toString();
    }
}
