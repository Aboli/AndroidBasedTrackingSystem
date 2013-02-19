/**
 * The user starts CamServiceActivity from android in order to start tracking by camera.
 * This class in turn spawns the AndroidCameraThread thread.  
 */
package com.example.edots;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.widget.TextView;
import edu.dhbw.andar.ARToolkit;
import edu.dhbw.andar.AndARActivity;

/**
 * @author Aboli Phadke
 *
 */
public class CamServiceActivity extends AndARActivity implements SurfaceHolder.Callback {

	//MyARObject arObject;
	ARToolkit artoolkit;


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initialize();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		/*if (cam != null) {
	        cam.release();
	        cam = null;
	    }*/
	}

	
	@SuppressLint("NewApi")
	void initialize()
	{	
		/*Camera cam = camHolder.tryOpen();
		android.hardware.Camera.Parameters parameters = cam.getParameters();
		if(parameters.isZoomSupported())
		{
			System.out.println("zoom supported. "+"zoom value is: "+parameters.getZoom());
			//System.out.println("max zoom: "+parameters.getMaxZoom());
			parameters.setZoom(8);
			cam.setParameters(parameters);
			System.out.println("New zoom value is: "+parameters.getZoom());
		}*/
		setOrientation();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		/*if (cam != null) {
	        cam.release();

	        cam = null;
	    }*/
		
	}

	//Camera cam;
	
	@SuppressWarnings("unchecked")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView tv = new TextView(this);
		tv.setText("Calling android camera thread");

		/*initialize tracking application and experimentation related stuff*/
		initialize();		

		//AndARRenderer arRenderer = new AndARRenderer();//optional, may be set to null

		//setNonARRenderer(renderer);//or might be omited
		try {
			//register a object for each marker type

			//setOrientation();
			artoolkit = getArtoolkit();
			//someObject = new CustomObject("test", "hiro.patt", 80.0, new double[]{0,0});

			//artoolkit.registerARObject(someObject);
		} catch (Exception ex){
			//handle the exception, that means: show the user what happened
			System.out.println("");
		}		
		
		AndroidCameraThread sampler = new AndroidCameraThread();

		sampler.execute(this.artoolkit);

		//cam.release();
	}

	@Override
	public void uncaughtException(Thread arg0, Throwable arg1) {
		// TODO Auto-generated method stub

	}

	public void setOrientation()
	{
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		

	}



}
