/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

public class Matrix {
	
	/* The different matrices */
	public static Matrix4D modelMatrix = new Matrix4D();
	public static Matrix4D viewMatrix = new Matrix4D();
	public static Matrix4D projectionMatrix = new Matrix4D();
	public static Matrix4D modelViewProjectionMatrix = new Matrix4D();
	
	/* The static method used to load an identity matrix */
	public static void loadIdentity(Matrix4D matrix) {
		//Load the identity matrix
		matrix.load(new float[][] {
				new float[] { 1, 0, 0, 0 },
				new float[] { 0, 1, 0, 0 },
				new float[] { 0, 0, 1, 0 },
				new float[] { 0, 0, 0, 1 },
		});
	}
	
	/* The static method used to add two matrices together */
	public static Matrix4D add(Matrix4D matrixA, Matrix4D matrixB) {
		//Create a new matrix
		Matrix4D matrix = new Matrix4D();
		//Go through each value
		for (int a = 0; a < matrix.values.length; a++)
			//Assign the current value
			matrix.values[a] = matrixA.values[a] + matrixB.values[a];
		//Return the matrix
		return matrix;
	}
	
	/* The static method used to subtract a matrix (B) from another (A) */
	public static Matrix4D subtract(Matrix4D matrixA, Matrix4D matrixB) {
		//Create a new matrix
		Matrix4D matrix = new Matrix4D();
		//Go through each value
		for (int a = 0; a < matrix.values.length; a++)
			//Assign the current value
			matrix.values[a] = matrixB.values[a] - matrixA.values[a];
		//Return the matrix
		return matrix;
	}
	
	/* The static method used to multiply two matrices together */
	//2D works here also (But translation doesn't work)
	public static Matrix4D multiply(Matrix4D matrixA, Matrix4D matrixB) {
		Matrix4D result = new Matrix4D();
		for (int a = 0; a < result.values.length; a++)
			result.values[a] = matrixA.values[a] * matrixB.values[a];
		return result;
	}
	
	/* The static method used to multiply two matrices together */
	public static Matrix4D multiplyMatrices(Matrix4D matrixA, Matrix4D matrixB) {
		//Create a new matrix
		Matrix4D matrix = new Matrix4D(new float[][] {
				new float[] {
						(matrixA.values[0] * matrixB.values[0]) + (matrixA.values[1] * matrixB.values[4]) + (matrixA.values[2] * matrixB.values[8]) + (matrixA.values[3] * matrixB.values[12]),
						(matrixA.values[0] * matrixB.values[1]) + (matrixA.values[1] * matrixB.values[5]) + (matrixA.values[2] * matrixB.values[9]) + (matrixA.values[3] * matrixB.values[13]),
						(matrixA.values[0] * matrixB.values[2]) + (matrixA.values[1] * matrixB.values[6]) + (matrixA.values[2] * matrixB.values[10]) + (matrixA.values[3] * matrixB.values[14]),
						(matrixA.values[0] * matrixB.values[3]) + (matrixA.values[1] * matrixB.values[7]) + (matrixA.values[2] * matrixB.values[11]) + (matrixA.values[3] * matrixB.values[15])
				},
				new float[] {
						(matrixA.values[4] * matrixB.values[0]) + (matrixA.values[5] * matrixB.values[4]) + (matrixA.values[6] * matrixB.values[8]) + (matrixA.values[7] * matrixB.values[12]),
						(matrixA.values[4] * matrixB.values[1]) + (matrixA.values[5] * matrixB.values[5]) + (matrixA.values[6] * matrixB.values[9]) + (matrixA.values[7] * matrixB.values[13]),
						(matrixA.values[4] * matrixB.values[2]) + (matrixA.values[5] * matrixB.values[6]) + (matrixA.values[6] * matrixB.values[10]) + (matrixA.values[7] * matrixB.values[14]),
						(matrixA.values[4] * matrixB.values[3]) + (matrixA.values[5] * matrixB.values[7]) + (matrixA.values[6] * matrixB.values[11]) + (matrixA.values[7] * matrixB.values[15])
				},
				new float[] {
						(matrixA.values[8] * matrixB.values[0]) + (matrixA.values[9] * matrixB.values[4]) + (matrixA.values[10] * matrixB.values[8]) + (matrixA.values[11] * matrixB.values[12]),
						(matrixA.values[8] * matrixB.values[1]) + (matrixA.values[9] * matrixB.values[5]) + (matrixA.values[10] * matrixB.values[9]) + (matrixA.values[11] * matrixB.values[13]),
						(matrixA.values[8] * matrixB.values[2]) + (matrixA.values[9] * matrixB.values[6]) + (matrixA.values[10] * matrixB.values[10]) + (matrixA.values[11] * matrixB.values[14]),
						(matrixA.values[8] * matrixB.values[3]) + (matrixA.values[9] * matrixB.values[7]) + (matrixA.values[10] * matrixB.values[11]) + (matrixA.values[11] * matrixB.values[15])
				},
				new float[] {
						(matrixA.values[12] * matrixB.values[0]) + (matrixA.values[13] * matrixB.values[4]) + (matrixA.values[14] * matrixB.values[8]) + (matrixA.values[15] * matrixB.values[12]),
						(matrixA.values[12] * matrixB.values[1]) + (matrixA.values[13] * matrixB.values[5]) + (matrixA.values[14] * matrixB.values[9]) + (matrixA.values[15] * matrixB.values[13]),
						(matrixA.values[12] * matrixB.values[2]) + (matrixA.values[13] * matrixB.values[6]) + (matrixA.values[14] * matrixB.values[10]) + (matrixA.values[15] * matrixB.values[14]),
						(matrixA.values[12] * matrixB.values[3]) + (matrixA.values[13] * matrixB.values[7]) + (matrixA.values[14] * matrixB.values[11]) + (matrixA.values[15] * matrixB.values[15])
				}
		});
		//Return the matrix
		return matrix;
	}
	
	/* The static method used to transpose a matrix */
	public static Matrix4D transpose(Matrix4D matrix) {
		//Get the values from the matrix
		float[][] values = matrix.getValues2DArray();
		//The new values
		float[][] newValues = new float[4][4];
		//Go through the array
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				//Assign the new value
				newValues[y][x] = values[x][y];
			}
		}
		//Return the matrix
		return new Matrix4D(newValues);
	}
	
	/* The static method used to translate a matrix */
	public static Matrix4D translate(Matrix4D matrix, Vector3D vector) {
		//The transform matrix
		Matrix4D transform = new Matrix4D(new float[][] {
				new float[] { 1, 0, 0, vector.x },
				new float[] { 0, 1, 0, vector.y },
				new float[] { 0, 0, 1, vector.z },
				new float[] { 0, 0, 0, 1 },
		});
		//Add onto the matrix and return the result
		return multiplyMatrices(matrix, transform);
	}
	
	/* The static method used to rotate a matrix */
	public static Matrix4D rotate(Matrix4D matrix, float angle, int x, int y, int z) {
		//The transform matrix
		Matrix4D transform = new Matrix4D();
		//Calculate the values needed
		float cos = (float) Math.cos(Math.toRadians(angle));
		float sin = (float) Math.sin(Math.toRadians(angle));
		//Check the x y and z values
		if (x == 1) {
			transform.load(new float[][] {
					new float[] { 1, 0, 0, 0 },
					new float[] { 0, cos, -sin, 0 },
					new float[] { 0, sin, cos, 0 },
					new float[] { 0, 0, 0, 1 },
			});
		} else if (y == 1) {
			transform.load(new float[][] {
					new float[] { cos, 0, sin, 0 },
					new float[] { 0, 1, 0, 0 },
					new float[] { -sin, 0, cos, 0 },
					new float[] { 0, 0, 0, 1 },
			});
		} else if (z == 1) {
			transform.load(new float[][] {
					new float[] { cos, -sin, 0, 0 },
					new float[] { sin, cos, 0, 0 },
					new float[] { 0, 0, 1, 0 },
					new float[] { 0, 0, 0, 1 },
			});
		}
		//Add onto the matrix and return the result
		return multiplyMatrices(matrix, transform);
	}
	
	/* The static method used to scale a matrix */
	public static Matrix4D scale(Matrix4D matrix, Vector3D vector) {
		//The transform matrix
		Matrix4D transform = new Matrix4D(new float[][] {
				new float[] { vector.x, 0, 0, 0 },
				new float[] { 0, vector.y, 0, 0 },
				new float[] { 0, 0, vector.z, 0 },
				new float[] { 0, 0, 0, 1 },
		});
		//Add onto the matrix and return the result
		return multiplyMatrices(matrix, transform);
	}
	
	/* The static method used to return an orthographic projection matrix */
	public static Matrix4D ortho(float left, float right, float bottom, float top, float zFar, float zNear) {
		//Calculate the width and height
		float width = (float) ((right - left));
		float height = (float) ((bottom - top));
		return new Matrix4D(new float[][] {
				new float[] { 2 / width, 0, 0, -1 },
				new float[] { 0, -2 / height, 0, 1 },
				new float[] { 0, 0, 2 / (zFar - zNear), (zNear + zFar) / (zNear - zFar) },
				new float[] { 0, 0, 0, 1 },
		});
	}
	
	/* The static method used to return a perspective projection matrix */
	public static Matrix4D perspective(float fov, float aspect, float zNear, float zFar) {
		//Calculate the scale
		float scale = (float) (Math.tan(fov / 2 * (Math.PI / 360)));
		//Calaulate the right, left, top and bottom values
		float right = aspect * scale;
		float left = -right;
		float top = scale;
		float bottom = -top;
		//Return the result
		return frustum(left, right, bottom, top, zNear, zFar);
	}
	
	/* The static method used to return a frustum projection matrix */
	public static Matrix4D frustum(float left, float right, float bottom, float top, float zNear, float zFar) {
		return new Matrix4D(new float[][] {
				new float[] { 2 * zNear / (right - left), 0, 0, 0 },
				new float[] { 0, 2 * zNear / (top - bottom), 0, 0 },
				new float[] { (right + left) / (right - left), (top + bottom) / (top - bottom), -(zFar + zNear) / (zFar - zNear), -1 },
				new float[] { 0, 0, -2 * zFar * zNear / (zFar - zNear), 0 },
		});
	}
	
}