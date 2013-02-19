package com.example.edots;

import java.io.Serializable;

/**
 * This class is responsible for maintaining information regarding
 * the tracking of a pattern.
 * 
 * @author Neha Talavdekar
 * @author Ryan Rybarczyk
 */
public class TrackingInformation implements Serializable {
	private static final long serialVersionUID = 1056234874320565645L;
	private SensorInfo sensorInfo;
	private TrackingItem [] results;
	
	/**
	 * Builds an object that contains tracking information based
	 * upon the number of patterns and the sensor information.
	 * 
	 * @param numOfPatterns
	 * @param sensorInfo
	 */
	public TrackingInformation(int numOfPatterns, SensorInfo sensorInfo) {
		this.sensorInfo = sensorInfo;
		results = new TrackingItem[numOfPatterns];
		
		for(int i = 0;i < numOfPatterns;i++) {
			results[i] = new TrackingItem();
		}	
	}
	
	/**
	 * Builds an object that contains tracking information based
	 * upon a tracking item object.
	 * 
	 * @param trackingItem
	 */
	public TrackingInformation(TrackingItem trackingItem) {
		if(null == trackingItem.getTransMatrix()) {
			results = null;
		} else {
			results = new TrackingItem[trackingItem.getTransMatrix().length];
			
			for(int i=0;i < trackingItem.getTransMatrix().length;i++) {
				results[i] = new TrackingItem();
				results[i].setTransMatrix(trackingItem.getTransMatrix());
				results[i].setTimestamp(trackingItem.getTimestamp());
			}
		}
	}

	public SensorInfo getSensorInfo() {
		return sensorInfo;
	}
	
	public TrackingItem [] getResults() {
		return results;
	}

}
