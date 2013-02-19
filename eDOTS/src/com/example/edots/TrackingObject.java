package com.example.edots;


import java.io.Serializable;

/**
 * This class is responsible for holding information
 * regarding the object being tracked.
 * 
 * @author Ryan Rybarczyk
 */
public class TrackingObject implements Serializable {
	private static final long serialVersionUID = 224316123196078227L;
	
	public static final String CAMERA_OBJECT = "CAMERA";
	public static final String RFID_OBJECT = "RFID";
	public static final String WIFI_OBJECT = "WIFI";
	
	private String name;
	private String type;
	//private Color color;
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

//	public Color getColor() {
//		return color;
//	}
//
//	public void setColor(Color color) {
//		this.color = color;
//	}
}
