package com.example.edots;

import java.io.Serializable;

/**
 * This class is responsible for the information regarding a tracked pattern.
 * 
 * @author Neha Talavdekar
 * @author Ryan Rybarczyk
 */
public class TrackingItem implements Serializable {
	private static final long serialVersionUID = -7576108897643639731L;
	
	private double [] transMatrix;
	private long timestamp;
	private boolean isSeeing;

	/**
	 * Constructor
	 */
	public TrackingItem() {
		setTransMatrix(null);
		setTimestamp(0);
		setSeeing(false);
	}
	
	/**
	 * Constructor
	 * 
	 * @param transMatrix
	 * @param timestamp
	 * @param isSeeing
	 */
	public TrackingItem(double [] transMatrix, long timestamp, boolean isSeeing) {
		setTransMatrix(transMatrix);
		setTimestamp(timestamp);
		setSeeing(isSeeing);
	}

	public double [] getTransMatrix() {
		return transMatrix;
	}

	public void setTransMatrix(double [] transMatrix) {
		this.transMatrix = transMatrix;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isSeeing() {
		return isSeeing;
	}

	public void setSeeing(boolean isSeeing) {
		this.isSeeing = isSeeing;
	}
}
