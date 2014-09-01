/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

public class Matrix {
	
	/* The static method used to translate a matrix given a vector */
	public static void translate(Matrix4D matrix, Vector3D vector) {
		//Create the translation matrix
		Matrix4D translation = new Matrix4D(new float[][] {
				new float[] { 1, 0, 0, vector.x },
				new float[] { 0, 1, 0, vector.y },
				new float[] { 0, 0, 1, vector.z },
				new float[] { 0, 0, 0, 1 }
		});
		//Apply the translation to the matrix given
		matrix = matrix.multiply(translation);
	}
	
	/* The static method used to scale a matrix given a vector */
	public static void scale(Matrix4D matrix, Vector3D vector) {
		//Create the scale matrix
		Matrix4D scale = new Matrix4D(new float[][] {
				new float[] { vector.x, 0, 0, 0 },
				new float[] { 0, vector.y, 0, 0 },
				new float[] { 0, 0, vector.y, 0 },
				new float[] { 0, 0, 0, 1 }
		});
		//Apply the scale to the matrix given
		matrix = matrix.multiply(scale);
	}
	
	/* The static method used to scale a matrix given a set of values */
	public static void rotate(Matrix4D matrix, float value, int x, int y, int z) {
		//Check the axis values
		if (x == 1) {
			//Create the rotation matrix
			Matrix4D rotation = new Matrix4D(new float[][] {
					new float[] { 1, 0, 0, 0 },
					new float[] { 0, (float) Math.cos(value), (float) -Math.sin(value), 0 },
					new float[] { 0, (float) Math.sin(value), (float) Math.cos(value), 0 },
					new float[] { 0, 0, 0, 1 },
			});
			//Apply the scale to the matrix given
			matrix = matrix.multiply(rotation);
		}
		if (y == 1) {
			//Create the rotation matrix
			Matrix4D rotation = new Matrix4D(new float[][] {
					new float[] { (float) Math.cos(value), 0, (float) Math.sin(value), 0 },
					new float[] { 0, 1, 0, 0 },
					new float[] { (float) -Math.sin(value), 0, (float) Math.cos(value), 0 },
					new float[] { 0, 0, 0, 1 },
			});
			//Apply the scale to the matrix given
			matrix = matrix.multiply(rotation);
		}
		if (z == 1) {
			//Create the rotation matrix
			Matrix4D rotation = new Matrix4D(new float[][] {
					new float[] { (float) Math.cos(value), (float) -Math.sin(value), 0, 0 },
					new float[] { (float) Math.sin(value), (float) Math.cos(value), 0, 0 },
					new float[] { 0, 0, 1, 0 },
					new float[] { 0, 0, 0, 1 },
			});
			//Apply the scale to the matrix given
			matrix = matrix.multiply(rotation);
		}
	}
	
	/* The static method used to scale a matrix given a vector */
	public static void rotate(Matrix4D matrix, Vector3D vector) {
		//Rotate the given matrix
		rotate(matrix, vector.x, 1, 0, 0);
		rotate(matrix, vector.y, 0, 1, 0);
		rotate(matrix, vector.z, 0, 0, 1);
	}
	
}