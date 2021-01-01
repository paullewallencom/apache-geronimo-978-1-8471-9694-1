package com.pact.plugins;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.geronimo.gbean.GBeanInfo;
import org.apache.geronimo.gbean.GBeanInfoBuilder;
import org.apache.geronimo.gbean.GBeanLifecycle;

import sun.util.calendar.CalendarDate;


public class ClockGBean implements GBeanLifecycle, Clock{

	public static final GBeanInfo GBEAN_INFO;
	private String name;
    private String timeZone;

	public String getTime() {		
	    GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone(timeZone));
	    int hour12 = cal.get(Calendar.HOUR);         // 0..11
	    int minutes = cal.get(Calendar.MINUTE);      // 0..59
	    int seconds = cal.get(Calendar.SECOND);      // 0..59
	    boolean am = cal.get(Calendar.AM_PM) == Calendar.AM;
	    return (timeZone +":"+hour12+":"+minutes+":"+seconds+":"+((am)?"AM":"PM"));
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public ClockGBean(String name){
		this.name = name;
		timeZone = TimeZone.getDefault().getID();
	}

	public void doFail() {
		System.out.println("Failed.............");
	}

	public void doStart() throws Exception {
		System.out.println("Started............"+name+" "+getTime());

	}

	public void doStop() throws Exception {
		System.out.println("Stopped............"+name);
	}
    static {
        GBeanInfoBuilder infoFactory = GBeanInfoBuilder.createStatic("ClockGBean",ClockGBean.class);
        infoFactory.addAttribute("name", String.class, true);
        infoFactory.addInterface(Clock.class);
        infoFactory.setConstructor(new String[] {"name"});
        GBEAN_INFO = infoFactory.getBeanInfo();
    }

    public static GBeanInfo getGBeanInfo() {
        return GBEAN_INFO;
    }

}
