/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

public class Matrix4D {
	
	/* The values within this matrix */
	public float[] values;
	
	/* The default constructor */
	public Matrix4D() {
		//Create the values
		this.values = new float[16];
	}
	
	/* The constructor with the values given */
	public Matrix4D(float[] values) {
		//Create the values
		this.values = values;
	}
	
	/* The constructor with the values given */
	public Matrix4D(float[][] values) {
		//Load the values
		load(values);
	}
	
	/* The method used to set the values given a 2 dimensional array */
	public void load(float[][] values) {
		this.values = new float[] {
				values[0][0], values[0][1], values[0][2], values[0][3],
				values[1][0], values[1][1], values[1][2], values[1][3],
				values[2][0], values[2][1], values[2][2], values[2][3],
				values[3][0], values[3][1], values[3][2], values[3][3]
		};
	}
	
	/* The method used to get a value using the coordinate within this matrix */
	public float get(int x, int y) {
		//Get the position
		int position = x + (y * 4);
		//Return the value
		return this.values[position];
	}
	
	/* The method used to return a string representation of this matrix */
	public String toString() {
		//Return the string
		return "[ " + this.values[0] + " " + this.values[1] + " " + + this.values[2] + " " + + this.values[3] + " ]" + "\n" +
			   "[ " + this.values[4] + " " + this.values[5] + " " + + this.values[6] + " " + + this.values[7] + " ]" + "\n" +
			   "[ " + this.values[8] + " " + this.values[9] + " " + + this.values[10] + " " + + this.values[11] + " ]" + "\n" +
			   "[ " + this.values[12] + " " + this.values[13] + " " + + this.values[14] + " " + + this.values[15] + " ]";
	}
	
	/* The method used to get the values */
	public float[] getValues() { return this.values; }
	
	/* The method used to get the values in a 2D array */
	public float[][] getValues2DArray() {
		//The array
		float[][] array = new float[4][4];
		//Go through each value
		int column = 0;
		int row = 0;
		while (column * row < array.length) {
			row ++;
			if (row >= 4) {
				column++;
				row = 0;
			}
			array[column][row] = this.values[column * row];
		}
		//Return the array
		return array;
	}
	
}