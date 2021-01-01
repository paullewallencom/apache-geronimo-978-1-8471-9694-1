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
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Helloworld servlet
 */
public class HelloworldServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
    	response.setContentType("text/plain");
        String name = request.getParameter("name");
        String message = "Hello "+name;
        
        PrintStream out = new PrintStream(response.getOutputStream());
        out.println("HelloworldServlet says, "+message);
        out.println();
        out.println("localName = "+request.getLocalName());
        out.println("localPort = "+request.getLocalPort());
        out.println("localAddr = "+request.getLocalAddr());
        out.println("remoteHost = "+request.getRemoteHost());
        out.println("remotePort = "+request.getRemotePort());
        out.println("remoteAddr = "+request.getRemoteAddr());
        out.println("serverName = "+request.getServerName());
        out.println("serverPort = "+request.getServerPort());
        out.println("server.name = "+System.getProperty("org.apache.geronimo.server.name", "default"));
        out.println("org.apache.geronimo.server.dir = "+System.getProperty("org.apache.geronimo.server.dir"));
        out.println("requestedSessionId = "+request.getRequestedSessionId());
        out.println("requestedSessionIdFromCookie? "+request.isRequestedSessionIdFromCookie());
        out.println("requestedSessionIdFromURL? "+request.isRequestedSessionIdFromURL());
        out.println("session = "+session);
        out.println("new session? "+session.isNew());
        Integer counter = (Integer) session.getAttribute("counter");
        if(counter == null) {
        	counter = 0;
        }
        ++counter;
        session.setAttribute("counter", counter);
        out.println("counter = "+counter);
    	System.out.println("Received request. counter = "+counter);
    	System.out.flush();
        out.close();
    }
}
