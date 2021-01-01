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

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import sample.ejb3.Library;

/**
 * Library servlet
 */
public class LibraryServlet extends HttpServlet {
    @EJB
    Library library;
    
    @Override
    public void init(ServletConfig config) {
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	UserTransaction tx = null;
    	try {
			InitialContext ic = new InitialContext();
			tx = (UserTransaction)ic.lookup("java:comp/UserTransaction");
		} catch (NamingException e) {
			e.printStackTrace();
		}
    	response.setContentType("text/plain");
    	PrintWriter out = response.getWriter();
		try {
			tx.begin();
	    	String op = request.getParameter("op");
	    	library.addMember("vamsi");
	    	library.addBook("Jungle Book", "Junglee");
	    	library.addMember("simmi");
	    	
	    	if(op != null && op.equals("commit")) {
	    		tx.commit();
	        	out.println("Books and members added");    		
	    	} else {
	    		tx.rollback();
	        	out.println("Books and members NOT added");
	    	}
		} catch (NotSupportedException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			e.printStackTrace();
		} catch (RollbackException e) {
			e.printStackTrace();
		}
    	
    	out.println("End");
    }
    
    @Override
    public void destroy() {
    }
}
