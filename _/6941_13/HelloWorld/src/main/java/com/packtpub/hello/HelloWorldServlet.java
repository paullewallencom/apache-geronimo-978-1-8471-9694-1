package com.packtpub.hello;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

public class HelloWorldServlet extends HttpServlet
{
  Logger logger = Logger.getLogger(HelloWorldServlet.class.getName());
  protected void service(HttpServletRequest req, HttpServletResponse res)
  throws ServletException, IOException {

    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    out.print("<html>");
    logger.info("Printing out <html>");
    out.print("<head><title>Hello World Application</title></head>");
    logger.info("Printing out <head><title>Hello World Application</title></head>");
    out.print("<body>");
    logger.info("Printing out <body>");
    out.print("<b>Hello World</b><br>");    
    logger.info("Printing out <b>Hello World</b><br>");
    out.print("</body>");
    logger.info("Printing out </body>");
    out.print("</html>");
    logger.info("Printing out </html>");
    logger.warn("Sample Warning message");
    logger.error("Sample error message");
  }
}