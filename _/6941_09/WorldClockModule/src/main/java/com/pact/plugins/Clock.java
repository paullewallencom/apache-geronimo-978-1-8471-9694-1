package com.pact.plugins;

import java.util.Date;
import java.util.Locale;

public interface Clock {

	public void setTimeZone(String timeZone);

	public String getTime();
}
