package com.pact.plugins;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.TimeZone;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.geronimo.kernel.GBeanNotFoundException;
import org.apache.geronimo.kernel.InternalKernelException;
import org.apache.geronimo.kernel.Kernel;
import org.apache.geronimo.kernel.KernelRegistry;

/**
 *
 * This portlet displays time from the WorldClock GBean
 */
public class ClockPortlet extends GenericPortlet {

    // called when user clicks on this portlets link
    public void doView(RenderRequest request, RenderResponse response)
            throws PortletException, IOException {

        // Set the response to read HTML
        response.setContentType("text/html;charset=UTF-8");

        // Get the writer to the response
        PrintWriter out = response.getWriter();
        Kernel kernel = KernelRegistry.getSingleKernel();
        try {
			Clock cl = (Clock)kernel.getGBean("ClockGBean");
			String[] ids = TimeZone.getAvailableIDs();
			for(int i=0;i<ids.length;i++){
				cl.setTimeZone(ids[i]);
				out.println(cl.getTime());
				out.println("<br>");
			}

		} catch (GBeanNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.out);
		} catch (InternalKernelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.out);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.out);
		}
    }
}