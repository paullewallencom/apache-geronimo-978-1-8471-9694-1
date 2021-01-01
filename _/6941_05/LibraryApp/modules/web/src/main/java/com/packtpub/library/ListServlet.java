package com.packtpub.library;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.packtpub.library.ejb.MailBusinessLocal;

public class ListServlet extends HttpServlet {
	@Resource(name="jdbc/DataSource")
	private DataSource ds;
	
	@EJB
	private MailBusinessLocal mbLocal;

	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		List<Member> members = ListUtility.list(ds);
		req.setAttribute("members",members);
		if("true".equalsIgnoreCase(req.getParameter("sendMail"))){
		    mbLocal.sendMail();
		}
		req.getRequestDispatcher("list.jsp").forward(req, res);
	}
	
	
}
