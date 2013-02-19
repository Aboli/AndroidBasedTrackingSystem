package com.example.edots;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

/**
 * This class is responsible for information regarding a given sensor.
 * 
 * @author Ryan Rybarczyk
 */
public class SensorInfo implements Serializable {
	private static final long serialVersionUID = -2504462001029445371L;
	private EnvironmentInfo environmentInfo = new EnvironmentInfo();
	private double [] myPosition;
	private double x;
	private double y;
	private double z;
	private int rank;
	private String hostname = null;
	private String name = null;
	private String path = null;
	private String orientation = null;
	
	/**
	 * Sensor Information
	 * 
	 * @param name
	 * @param path
	 */
	public SensorInfo(String name, String sensorPath) {
		setName(name);
		path = sensorPath;
		loadSensorParameters(name);
	}
	
	/**
	 * Sensor Information
	 * 
	 * @param name
	 */
	public SensorInfo(String name) {
		setName(name);
	}
	
	/**
	 * Loads the parameters associated with a sensor.
	 * 
	 * @param name
	 */
	private void loadSensorParameters(String name) {
		try {
			FileInputStream file = new FileInputStream(path);
			DataInputStream input = new DataInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String line;			
			
			while ((line = reader.readLine()) != null) {
				String [] split = line.split(",");
				if(split[0].contains(name)) {
					getEnvironmentInfo().setName(split[1]);
					setX(Double.valueOf(split[2]));
					setY(Double.valueOf(split[3]));
					setZ(Double.valueOf(split[4]));
					setOrientation(split[5]);
				}
			}		
			
			input.close();
		} catch (Exception e) {
			System.err.println("Unable to locate sensor parameters!");
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getX() {
		return x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getY() {
		return y;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getZ() {
		return z;
	}

	public void setEnvironmentInfo(EnvironmentInfo environmentInfo) {
		this.environmentInfo = environmentInfo;
	}

	public EnvironmentInfo getEnvironmentInfo() {
		return environmentInfo;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getRank() {
		return rank;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getHostname() {
		return hostname;
	}

	public double [] getMyPosition() {
		return myPosition;
	}

	public void setMyPosition(double [] myPosition) {
		this.myPosition = myPosition;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getOrientation() {
		return orientation;
	}

}
