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
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.geronimo.gbean.AbstractName;
import org.apache.geronimo.gbean.AbstractNameQuery;
import org.apache.geronimo.kernel.Kernel;
import org.apache.geronimo.kernel.KernelRegistry;

import packtsamples.MySampleInterface;

/**
 * GBean servlet
 */
public class GBeanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Kernel kernel;

    @Override
    public void init(ServletConfig config) {
    	kernel = KernelRegistry.getSingleKernel();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.setContentType("text/plain");
    	PrintWriter out = response.getWriter();
    	
    	out.println("GBeanServlet");
    	
    	out.println("Query using interface name MySampleInterface...");
    	try {
    		AbstractNameQuery query = new AbstractNameQuery(MySampleInterface.class.getName());
    		Set gbeanNames = kernel.listGBeans(query);
    		out.println("number of gbeans = "+gbeanNames.size());
    		for(Iterator itr = gbeanNames.iterator(); itr.hasNext(); ) {
    			out.println(itr.next());
    		}
    		gbeanNames = kernel.listGBeans(query);
    		AbstractName gbeanName = (AbstractName) gbeanNames.iterator().next();
    		out.println("\nObtaining gbean instance using abstract name "+gbeanName);
    		MySampleInterface gbean = (MySampleInterface)kernel.getGBean(gbeanName);
    		out.println(gbean);
    		out.println("\nInvoking gbean method via proxy object...");
    		out.println((String)gbean.myMethod1());
    		out.println("\nInvoking set attribute on gbean "+gbeanName);
    		kernel.setAttribute(gbeanName, "name", ((String)kernel.getAttribute(gbeanName, "name")).toUpperCase());
    		kernel.setAttribute(gbeanName, "name2", ((String)kernel.getAttribute(gbeanName, "name2")).toUpperCase());
    		out.println("\nInvoking invoke on gbean "+gbeanName);
    		out.println((String)kernel.invoke(gbeanName, "myMethod1"));
		} catch (Exception e) {
			out.println(e);
		}
    	
    	out.flush();
    }
    
    @Override
    public void destroy() {
    }
}
