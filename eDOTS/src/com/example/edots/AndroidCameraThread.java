package com.example.edots;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.ListIterator;
import java.util.Vector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import edu.dhbw.andar.ARObject;
import edu.dhbw.andar.ARToolkit;
import edu.dhbw.andar.AndARActivity;
import edu.dhbw.andar.AndARRenderer;
import edu.dhbw.andar.exceptions.AndARException;
import android.os.AsyncTask;

public class AndroidCameraThread extends AsyncTask{

	//MyARObject object_hiro;
	CustomObject object_hiro;
	CustomObject object_kanji;
	ARToolkit artoolkit;


	public transient boolean seeingatleastonepattern;
	private boolean keepRunning = true;
	private Vector<PatternInfo> patterns = null;
	private String [] patternURLs = null;
	private String [] patternNames = null;
	private boolean verbose = false;	
	private Hashtable<String, Object> results; 
	private Hashtable<String, Object> resultsBeingRefreshed;
	private Hashtable<String, String> patternsForLookup;
	private int dataArray[];
	private byte idArray[];//Aboli:changed from int to byte for AndAR toolkit
	private double matrix1[];
	private SensorInfo sensorInfo = null;
	private int [] patt_ids = null;


	public AndroidCameraThread()
	{
		results = new Hashtable<String, Object>(10);
		resultsBeingRefreshed = new Hashtable<String, Object>(10);
		patternsForLookup = new Hashtable<String, String>();
		patterns = new Vector<PatternInfo>();

		this.sensorInfo = sensorInfo;		
		this.dataArray = dataArray;
		this.idArray = idArray;
		seeingatleastonepattern = false;
	}

	/**
	 * Keeps the thread running...
	 */
	synchronized public void setKeepRunning() {
		keepRunning = true;
	}

	/**
	 * Resets the thread...
	 */
	synchronized public void resetKeepRunning() {
		keepRunning = false;
	}

	/**
	 * This method is responsible for running the camera thread. Since the visual tracking runs as
	 * an independent thread, even if we minimize the application, tracking continues.
	 * This design is done deliberately so that we can carry out different modalities of tracking
	 * in different threads independently. 
	 */

	protected Object doInBackground(Object... args) {
		//Log.e("abc", "asd5");
		try {
			
			//register a object for each marker type

			artoolkit = (ARToolkit)args[0];
			
			object_hiro = new CustomObject("test_hiro", "hiro.patt", 80.0, new double[]{0,0});
			object_kanji = new CustomObject("test_kanji", "kanji.patt", 80.0, new double[]{0,0});
			//Log.e("abc", "abc7"+object_hiro.getPatternName());
			artoolkit.registerARObject(object_hiro);			
			artoolkit.registerARObject(object_kanji);
		} catch (AndARException ex){
			//handle the exception, that means: show the user what happened
			System.out.println("");
		}
		 /* To note the battery level after every minute to study battery drain trend:START */
		 
		 /*pre-req to write to SD card
		 File root = android.os.Environment.getExternalStorageDirectory(); 
	    	Log.e("eDOTS","\nExternal file system root: "+root);

	        // See http://stackoverflow.com/questions/3551821/android-write-to-sd-card-folder

	        File dir = new File (root.getAbsolutePath() + "/download");
	        //filePath = "/mnt/sdcard/download/myData.txt";
	        dir.mkdirs();
	     
	        final File file = new File(dir, "myData.txt");
	        /*To write to sd card
			
	        try {
	        	final FileOutputStream f = new FileOutputStream(file);
		         final PrintWriter pw = new PrintWriter(f);
	           
	        	 pw.println("Hi test write to sd cardssssssssssssssssssss");	
	        	 pw.println("jahfshfksfksbksbdhsdbfvhsdf");
	        	 pw.println("jahfshfksfksbksbdhsdbfvhsdf");
	        	 pw.println("jahfshfksfksbksbdhsdbfvhsdf");
	        	 pw.println("jahfshfksfksbksbdhsdbfvhsdf");
	            pw.flush();*/
	            
	        
	        
		 
		 /*final BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
           // int scale = -1;
            int level = -1;
           // int voltage = -1;
           // int temp = -1;
            @Override
            public void onReceive(Context context, Intent intent) {
                level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                //scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                //temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
                //voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
                Log.e("BatteryManager", "level is "+level);
					
					/*To write to sd card
					
			        try {
			        	final FileOutputStream f = new FileOutputStream(file);
				         final PrintWriter pw = new PrintWriter(f);
			           
			        	 pw.println(""+level);	
			        	 pw.println();
			            pw.flush();
			            
			        } catch (FileNotFoundException e) {
			            e.printStackTrace();
			            Log.i("Media", "******* File not found. Did you" +
			                    " add a WRITE_EXTERNAL_STORAGE permission to the manifest?");
			        } catch (IOException e) {
			            e.printStackTrace();
			        }   
					/*to write to sd card: ends
            }
        };
        final IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);*/
		try {
			//while(keepRunning == true){
			for(int i=0;i<20;i++){
				//Log.e("abc", "asd6");
				//double[] transMatrix = getTransMatrix(this.object_hiro);
				Log.e("eDOTS","-------------------------------------------------");
				Log.e("eDOTS","andar transMatrix for hiro");
				getTransMatrix(object_hiro);
				
				Log.e("eDOTS","andar transMatrix for kanji");
				getTransMatrix(object_kanji);
				//Log.e("abc", "asd100000--++--"+transMatrix[5]);
				Thread.sleep(2000);
				//Thread.sleep(60000); if battery-sd card logic used
			      // Do some stuff
			    //to write battery level to SD card
				// registerReceiver(batteryReceiver, filter);
				keepRunning=true;
			}	
			Thread.sleep(20);
			
			//cancel(true);
		} catch(Exception ex) {
			ex.printStackTrace();
			System.err.println("Camera Sampler Thread is dead...");
		}
		/*try for write-to-SD-card ends. 2 catch start 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	            Log.i("Media", "******* File not found. Did you" +
	                    " add a WRITE_EXTERNAL_STORAGE permission to the manifest?");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }   
			/*to write to sd card: ends*/
		
		//Log.e("abc", "asd11");
		return null;
	}

	//public double[] getTransMatrix(CustomObject object_hiro)
	public void getTransMatrix(CustomObject patt_object)
	{
	/*	if(patternURLs == null) {
			return;
		}
		seeingatleastonepattern = false;
		   
		// detect markers
		artoolkit.detectMarkers(idArray);
		TrackingInformation transMat = new TrackingInformation(patt_ids.length, sensorInfo);
		
		//if we can see any marker
		if(idArray.length != 0) {
		
			for(int i = 0; i < idArray.length; i++) {
				for(int j = 0; j < patt_ids.length; j++) {
					if(idArray[i] == patt_ids[j]) {
						seeingatleastonepattern = true; */
		
						double[] transMatrix_patt = (double[]) patt_object.getTransMatrix();

								
						Log.e("eDOTS","     "+transMatrix_patt[3]+"     "+transMatrix_patt[7]+"     "+transMatrix_patt[11]);
						//System.out.println("");
						/*if(transMat.getResults().length == patt_ids.length) {
							transMat.getResults()[j] = new TrackingItem(transMatrix, System.currentTimeMillis(), true);
						
						}
						TrackingItem tempResult = new TrackingItem(transMatrix, System.currentTimeMillis(), true);
						   
						// Store the found matrix in the hash table...
						storeTransMatrix(patternNames[j], patternURLs[j], tempResult);
						if(verbose){
							System.out.println("Pattern-ID: " + idArray[i]);
							UtilityClass.displayMat(transMatrix);
						}
						
						
					}//if ends
				}//for f ends
			}//for i ends
		}//if idArray ends
		else{
			if(patternURLs != null) {
				for(int i=0;i<patternURLs.length;i++) {
					clearTransMatrix(patternNames[i],patternURLs[i]);
				}
			}
		}	
		
		// Sync up the results...
		synchronized(resultsBeingRefreshed) {
			synchronized(results) {
				results = resultsBeingRefreshed;
				resultsBeingRefreshed = new Hashtable<String, Object>();
			}	
		}
		
		Log.e("abc", "asd7");	   
		seeingatleastonepattern = false;		
		//return transMatrix;
	*/}

	/**
	 * This method is responsible for storing the transformation matrix for
	 * a pattern onto a hash map.
	 * 
	 * @param patternName
	 * @param patternURL
	 * @param dataArray
	 */
	synchronized private void storeTransMatrix(String patternName, String patternURL, TrackingItem dataArray) {
		if(resultsBeingRefreshed == null) {
			System.out.println("Results being refreshed is null...");
		}
	   
		// Remove the pattern from the hash map...
		resultsBeingRefreshed.remove(patternName+patternURL);
	   
		if(dataArray.getTransMatrix() == null) {
			System.out.println("Unable to store transformation matrix!");
		}
	   
		// Add the pattern and its associated tracking information to the hash map.
		resultsBeingRefreshed.put(patternName+patternURL, dataArray);	   
	}
	
	/**
	 * This method is responsible for removing a pattern from
	 * the hashmap.
	 * 
	 * @param patternName
	 * @param patternURL
	 */
	synchronized private void clearTransMatrix(String patternName, String patternURL) {
		resultsBeingRefreshed.remove(patternName+patternURL);		   
	}
	
	/**
	 * This method is responsible for maintaining a current vector 
	 * list of patterns to be recognized and tracked by the system.
	 */
//	synchronized private void refreshPatterns() {
//		ListIterator<PatternInfo> iter = patterns.listIterator();
//		int count = 0;
//		
////		if(patt_ids != null) {
////			for(int j = 0; j < patt_ids.length; j++) {
////				myJARToolKit.freePattern(j);
////			}
////		}
//		
//		patternURLs = new String[patterns.size()];
//		patternNames = new String[patterns.size()];
//		patt_ids = new int[patterns.size()];
//
//		while(iter.hasNext()) {
//		    PatternInfo patternInfo = (PatternInfo)iter.next();
//		    patternNames[count] = patternInfo.getName();		    
//		    patternURLs[count] = patternInfo.getPatternURL();		 
//			File patternFile = new File(patternURLs[count]);
//			
//			if(!patternFile.exists()) {				
//				boolean downloadSuccess = true;			
//			
//				if(!downloadSuccess) {
//					iter.remove();
//					patternsForLookup.remove(patternNames[count]+patternURLs[count]);
//					continue;
//				}
//			}
//			patt_ids[count] = object_hiro.loadPattern(patternURLs[count]);
//		    count++;		    		    
//		}
//	}
	
	
	
}
