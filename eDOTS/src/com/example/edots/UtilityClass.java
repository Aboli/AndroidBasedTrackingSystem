package com.example.edots;

import java.util.ArrayList;

import Jama.Matrix;

/**
 * Utility class used in the eDOTS Tracking System.
 * 
 * @author Neha Talavdekar
 * @author Ryan Rybarczyk
 */
public class UtilityClass {
	/**
	 * Provides the distance between two specified matricies.
	 * 
	 * @param Mat1
	 * @param Mat2
	 * @return double
	 */
	public static double findDistance(double[] Mat1, double [] Mat2) {
		if((Mat1 == null) || (Mat2 == null))
				return -1;
		if((Mat1.length != 4)||(Mat2.length != 4))
			return -1;
		double distance = 0;
		distance = Math.sqrt((Mat1[0]-Mat2[0])*(Mat1[0]-Mat2[0]) +
							(Mat1[1]-Mat2[1])*(Mat1[1]-Mat2[1]) + 
							(Mat1[2]-Mat2[2])*(Mat1[2]-Mat2[2]));
		return distance;
	}
	
	/**
	 * Provides simple averaging fusion on specified readings.
	 * 
	 * @param ArrayList<double []> readings
	 * @return double []
	 */
	public static double [] simpleAveragingFusion(ArrayList<double []> readings) {
		if(readings == null) {
			return null;
		}

		//TODO FIX ME -Ryan R. (07/07/2010)
		double [] result = new double[4];		
		result[0] = 0;
		result[1] = 0;
		result[2] = 0;
		result[3] = 1;
		
		int count=0;
		
		for(int i=0;i < readings.size();i++) {
			if(readings.get(i) == null) {
				System.out.println("Discarding null readings in fusion!");
				continue;
			}
			
			if(readings.get(i).length != 4) {
				System.out.println("Length not equal to 4 - junk data in readings!");
				continue;
			}
			
			for(int j=0;j<3;j++) {
				result[j] = result[j] + readings.get(i)[j];
			}	
			
			count++;
		}
		
		if(count > 0) {
			for(int j=0;j<3;j++) {
				result[j] = result[j]/count;
			}				
			return result;
		} else {
			System.out.println("All junk data supplied...Averaging fusion not possible!");
			return null;
		}
	}
	
	/**
	 * Multiplies a specified transformation matrix with the specified position.
	 * 
	 * @param transMat
	 * @param myPosition
	 * @return double []
	 */
	public static double [] multiplyTransMatWithPosition(double [] transMat, double [] myPosition) {
		if((transMat == null)||(myPosition == null)) {
			return null;
		}
			
		double [] transPosition = new double[4];
		
		for(int i=0;i<4;i++) {
			transPosition[i] = 0;
			for(int j=0;j<4;j++) {
				transPosition[i] = transPosition[i] + (transMat[4*j+i]) * myPosition[j];
			}
		}
		
		return transPosition;
	}
	
	/**
	 * Multiplies a specified transformation matrix with the specified position.
	 * 
	 * @param transMat
	 * @param myPosition
	 * @return
	 */
	public static double [] multiplyTransMatWithPositionOrientation(double [] transMat, double [] myPosition) {
		
		if((transMat == null)||(myPosition == null)) {
			return null;
		}
		   
		assert((transMat.length == 16)&&(myPosition.length == 16));
        
        double [] transPosition = new double[16];
        
        for(int i=0;i<4;i++) {
            for(int k = 0; k < 4; k++) {
                transPosition[4*k + i ] = 0;
                for(int j=0;j<4;j++) {
                    transPosition[4*k + i] = transPosition[4*k + i] + transMat[4*j + i] * myPosition[4*k + j];
                }
            }
        }
       
        return transPosition;
	}
	
	/**
	 * This returns the combination of two matrix transformations.
	 * 
	 * @param a
	 * @param b
	 * @return double []
	 */
	public static double [] twoMatrixTrans(double [] a, double [] b) {
		Matrix a1 = new Matrix(a,4);
		Matrix b1 = new Matrix(b,4);
		Matrix c1 = b1.inverse();
		Matrix d1 = a1.times(c1);
		return(d1.getColumnPackedCopy());		
	}
	
	/**
	 * This provides the inverse of a matrix.
	 * 
	 * @param a
	 * @return double []
	 */
	public static double [] Invert(double [] a) {
		Matrix a1 = new Matrix(a,4);
		Matrix c1 = a1.inverse();
		return(c1.getColumnPackedCopy());	
	}
	
	/**
	 * Returns the row representation of a column.
	 * 
	 * @param a
	 * @return double []
	 */
	public static double [] colToRow(double [] a) {
		double [] b = new double[a.length];
		
		//System.out.println("After declaring the array of length: " + a.length);
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				b[4*i+j] = a[4*j+i];
			}			
		}
		
		return b;
	}
	
	/**
	 * Returns the column representation of a row.
	 * 
	 * @param a
	 * @return double []
	 */
	public static double [] rowToCol(double [] a) {
		double [] b = new double[a.length];
		
		//System.out.println("After declaring the array of length: " + a.length);
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				b[4*j+i] = a[4*i+j];
			}			
		}
		
		return b;
	}
	
	/**
	 * Prints the results of a matrix to the console.
	 * 
	 * @param c
	 */
	public static void displayMat(double [] c) {
    	System.out.println("  |" + c[0] + "\t" + c[4] + "\t" + c[8] + "\t" + c[12] + "|");
		System.out.println("  |" + c[1] + "\t" + c[5] + "\t" + c[9] + "\t" + c[13] + "|");
		System.out.println("  |" + c[2] + "\t" + c[6] + "\t" + c[10] + "\t" + c[14] + "|");
		System.out.println("  |" + c[3] + "\t" + c[7] + "\t" + c[11] + "\t" + c[15] + "|\n");
	}
	
	/**
	 * This method determines the coordinate transformation when
	 * more than one world is currently seeing the same object.
	 * 
	 * @param AB
	 * @param DB
	 * @param DC
	 * @return transformedPosition
	 */
	public static double [] coordinateTransformation(double [] AB, double [] DB, double [] DC) {
		Matrix ab = new Matrix(AB,4);
		Matrix db = new Matrix(DB,4);
		Matrix dc = new Matrix(DC,4);
		
		Matrix bd = db.inverse();
		
		Matrix transformedPosition = dc.times(bd.times(ab));
		
		return (transformedPosition.getColumnPackedCopy());
	}
	
}
