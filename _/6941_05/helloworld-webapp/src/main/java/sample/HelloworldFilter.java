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

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Helloworld filter
 */
public class HelloworldFilter implements Filter {
	private String param1;
	
    public void init(FilterConfig filterConfig) throws ServletException {
    	param1 = filterConfig.getInitParameter("param1");
    	if(param1 == null) {
    		param1 = "Hello";
    	}
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String name = request.getParameter("name");
        
        String message = param1 + " " + name;

        Writer out = response.getWriter();
        out.write("<html><head><title>Packt Helloworld Web sample</title></head><body>");
        
        out.write("<h2>Packt Helloworld Filter</h2>");
        out.write("HelloworldFilter says, "+message);
        out.write("<br>");
        
        chain.doFilter(request, response);

        out.write("</body></html>");
        out.flush();
    }

    public void destroy() {
    }
}
