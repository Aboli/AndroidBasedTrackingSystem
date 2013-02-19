package com.example.edots;

import java.io.Serializable;

/**
 * Responsible for storing information regarding a
 * ID Pattern.
 * 
 * @author Ryan Rybarczyk
 */
public class PatternInfo extends TrackingObject implements Serializable {
	private static final long serialVersionUID = 1495902560328320717L;
	
	private String patternURL;
		
		
	public void setPatternURL(String patternURL) {
		this.patternURL = patternURL;
	}

	public String getPatternURL() {
		return patternURL;
	}
}
