package com.example.edots;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

/**
 * This class is responsible for information regarding a given environment.
 * 
 * @author Ryan Rybarczyk
 */
public class EnvironmentInfo implements Serializable {
	private static final long serialVersionUID = 7325368252508061645L;
	
	private static String path = "data\\environment_data\\environment_data.txt";
	private String name = null;
	private String imageLocation = null;
	private double length = 0;
	private double width = 0;
	private int xDivisor = 0;
	private int yDivisor = 0;
	
	/**
	 * Sets the image location of the given environment.
	 * 
	 * @param imageLocation
	 */
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}
	
	/**
	 * Returns the directory where the corresponding image of a 
	 * given environment is stored.
	 * 
	 * @param name
	 * @return directory
	 */
	public String getImageLocation(String name) {
		if(imageLocation == null) {
			try {
				FileInputStream file = new FileInputStream(path);
				DataInputStream input = new DataInputStream(file);
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				String line;			
				
				while ((line = reader.readLine()) != null) {
					String [] split = line.split(",");
					if(split[0].contains(name)) {
						setImageLocation(split[1]);
						this.length = Double.valueOf(split[2]);
						this.width = Double.valueOf(split[3]);
						this.xDivisor = Integer.valueOf(split[4]);
						this.yDivisor = Integer.valueOf(split[5]);
					}
				}		
				
				input.close();
			} catch (Exception e) {
				System.err.println("Unable to located image!");
			}
		}
			
		return imageLocation;
	}
	
	/**
	 * Sets the name of a given environment.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name of a given environment.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the length of the environment.
	 * 
	 * @return length
	 */
	public double getLength() {
		return length;
	}

	/**
	 * Gets the width of the environment.
	 * 
	 * @return width
	 */
	public double getWidth() {
		return width;
	}

	public int getxDivisor() {
		return xDivisor;
	}


	public int getyDivisor() {
		return yDivisor;
	}
}
