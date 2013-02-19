package com.example.edots;

import java.io.Serializable;

/**
 * This class is responsible for information regarding the Camera Parameters for QoS.
 * 
 * @author Ryan Rybarczyk
 */
public class CameraParameters_bak implements Serializable {
	private static final long serialVersionUID = 6839986537524282075L;
	public String resolution;
	public long distortion;
	public int frameRate;
	public long distance;
	public long clockDrift;

	/**
	 * Constructor
	 * 
	 * @param resolution
	 * @param distortion
	 * @param frameRate
	 * @param distance
	 * @param clockDrift
	 */
	public CameraParameters_bak(String resolution, long distortion, int frameRate, long distance, long clockDrift) {
		this.resolution = resolution;
		this.distortion = distortion;
		this.frameRate = frameRate;
		this.distance = distance;
		this.clockDrift = clockDrift;
	}

}
