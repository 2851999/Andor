/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
	
	/* The different matrices */
	public static Matrix4D modelMatrix = new Matrix4D();
	public static Matrix4D viewMatrix = new Matrix4D();
	public static Matrix4D projectionMatrix = new Matrix4D();
	public static Matrix4D modelViewProjectionMatrix = new Matrix4D();
	public static Matrix4D normalMatrix = new Matrix4D();
	public static Matrix4D lightViewMatrix = new Matrix4D();
	public static Matrix4D lightProjectionMatrix = new Matrix4D();
	public static Matrix4D lightMatrix = new Matrix4D();
	
	/* The matrix stack */
	public static List<float[]> stack = new ArrayList<float[]>();
	
	/* The static methods used to push and pop matrices on and off the stack */
	public static void push(float[] data) {
		//Add the data to the stack
		stack.add(data);
	}
	
	public static float[] pop() {
		float[] values = stack.get(stack.size() - 1);
		stack.remove(stack.size() - 1);
		return values;
	}
	
	public static void push(Matrix4D matrix) {
		push(matrix.getValues());
	}
	
	public static void pop(Matrix4D matrix) {
		matrix.setValues(pop());
	}
	
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
	
	/* The static method used to calculate the matrices at render time */
	public static void calculateRenderMatrices() {
		//Multiply the projection and view matrices together
		Matrix4D projectionViewMatrix = multiply(projectionMatrix, viewMatrix);
		//Multiply the projection view and model matrices together then transpose the result to get the model view projection matrix
		modelViewProjectionMatrix = transpose(multiply(projectionViewMatrix, modelMatrix));
		//Calculate and assign the normal matrix
		normalMatrix = invert(transpose(modelMatrix));
		Matrix.lightMatrix = Matrix.transpose(Matrix.multiply(Matrix.multiply(Matrix.lightProjectionMatrix,  Matrix.lightViewMatrix), Matrix.modelMatrix));
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
	
	/* The static method used to multiply a matrix by a single scalar quantity */
	public static Matrix4D multiply(Matrix4D matrix, float value) {
		//The result matrix
		Matrix4D result = new Matrix4D();
		//Go through each value within the matrix
		for (int a = 0; a < matrix.values.length; a++)
			result.values[a] = matrix.values[a] * value;
		//Return the result
		return result;
	}
	
	/* The static method used to multiply two matrices together */
	public static Matrix4D multiply(Matrix4D matrixA, Matrix4D matrixB) {
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
	
	/* The static method used to transform a matrix */
	public static void transform(Matrix4D matrix, Vector3D translate, Vector3D rotate, Vector3D scale) {
		matrix = scale(matrix, scale);
		rotate(matrix, rotate);
		matrix = translate(matrix, translate);
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
		return multiply(matrix, transform);
	}
	
	/* The static method used to rotate a matrix */
	public static void rotate(Matrix4D matrix, Vector2D angles) {
		matrix = rotate(matrix, angles.getX(), 1, 0, 0);
		matrix = rotate(matrix, angles.getY(), 0, 1, 0);
	}
	
	/* The static method used to rotate a matrix */
	public static void rotate(Matrix4D matrix, Vector3D angles) {
		matrix = rotate(matrix, angles.getX(), 1, 0, 0);
		matrix = rotate(matrix, angles.getY(), 0, 1, 0);
		matrix = rotate(matrix, angles.getZ(), 0, 0, 1);
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
		return multiply(matrix, transform);
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
		return multiply(matrix, transform);
	}
	
	/* The static method used to return an orthographic projection matrix */
	public static Matrix4D ortho(float left, float right, float bottom, float top, float zNear, float zFar) {
		//Calculate the width and height
//		float width = (float) ((right - left));
//		float height = (float) ((bottom - top));
//		return new Matrix4D(new float[][] {
//				new float[] { 2 / width, 0, 0, -1 },
//				new float[] { 0, -2 / height, 0, 1 },
//				new float[] { 0, 0, 2 / (zFar - zNear), (zNear + zFar) / (zNear - zFar) },
//				new float[] { 0, 0, 0, 1 },
//		});
		return new Matrix4D(new float[][] {
				new float[] { (2 / (right - left)), 0, 0, - ((right + left) / (right - left)) },
				new float[] { 0, 2 / (top - bottom), 0, - ((top + bottom) / (top - bottom)) },
				new float[] { 0, 0, -2 / (zFar - zNear), -(zNear + zFar) / (zFar - zNear) },
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
	
	/* The static method used to calculate the inverse of a matrix */
	public static Matrix4D invert(Matrix4D matrix) {
		//Get the values of the matrix (Transposed)
		float mat0 = matrix.values[0];
		float mat4 = matrix.values[1];
		float mat8 = matrix.values[2];
		float mat12 = matrix.values[3];
		
		float mat1 = matrix.values[4];
		float mat5 = matrix.values[5];
		float mat9 = matrix.values[6];
		float mat13 = matrix.values[7];
		
		float mat2 = matrix.values[8];
		float mat6 = matrix.values[9];
		float mat10 = matrix.values[10];
		float mat14 = matrix.values[11];
		
		float mat3  = matrix.values[12];
		float mat7  = matrix.values[13];
		float mat11 = matrix.values[14];
		float mat15 = matrix.values[15];

		//Calculate the pairs
		float mp0 = mat10 * mat15;
		float mp1 = mat11 * mat14;
		float mp2 = mat9 * mat15;
		float mp3 = mat11 * mat13;
		float mp4 = mat9 * mat14;
		float mp5 = mat10 * mat13;
		float mp6 = mat8 * mat15;
		float mp7 = mat11 * mat12;
		float mp8 = mat8 * mat14;
		float mp9 = mat10 * mat12;
		float mp10 = mat8 * mat13;
		float mp11 = mat9 * mat12;
		
		float scof0 = mat2 * mat7;
		float scof1 = mat3 * mat6;
		float scof2 = mat1 * mat7;
		float scof3 = mat3 * mat5;
		float scof4 = mat1 * mat6;
		float scof5 = mat2 * mat5;
		float scof6 = mat0 * mat7;
		float scof7 = mat3 * mat4;
		float scof8 = mat0 * mat6;
		float scof9 = mat2 * mat4;
		float scof10 = mat0 * mat5;
		float scof11 = mat1 * mat4;
	
		//Calculate cofactors
		float fcof0 = (mp0 * mat5 + mp3 * mat6 + mp4 * mat7) - (mp1 * mat5 + mp2 * mat6 + mp5 * mat7);
		float fcof1 = (mp1 * mat4 + mp6 * mat6 + mp9 * mat7) - (mp0 * mat4 + mp7 * mat6 + mp8 * mat7);
		float fcof2 = (mp2 * mat4 + mp7 * mat5 + mp10 * mat7) - (mp3 * mat4 + mp6 * mat5 + mp11 * mat7);
		float fcof3 = (mp5 * mat4 + mp8 * mat5 + mp11 * mat6) - (mp4 * mat4 + mp9 * mat5 + mp10 * mat6);
		float fcof4 = (mp1 * mat1 + mp2 * mat2 + mp5 * mat3) - (mp0 * mat1 + mp3 * mat2 + mp4 * mat3);
		float fcof5 = (mp0 * mat0 + mp7 * mat2 + mp8 * mat3) - (mp1 * mat0 + mp6 * mat2 + mp9 * mat3);
		float fcof6 = (mp3 * mat0 + mp6 * mat1 + mp11 * mat3) - (mp2 * mat0 + mp7 * mat1 + mp10 * mat3);
		float fcof7 = (mp4 * mat0 + mp9 * mat1 + mp10 * mat2) - (mp5 * mat0 + mp8 * mat1 + mp11 * mat2);
	
		float fcof8 = (scof0 * mat13 + scof3 * mat14 + scof4 * mat15) - (scof1 * mat13 + scof2 * mat14 + scof5 * mat15);
		float fcof9 = (scof1 * mat12 + scof6 * mat14 + scof9 * mat15) - (scof0 * mat12 + scof7 * mat14 + scof8 * mat15);
		float fcof10 = (scof2 * mat12 + scof7 * mat13 + scof10 * mat15) - (scof3 * mat12 + scof6 * mat13 + scof11 * mat15);
		float fcof11 = (scof5 * mat12 + scof8 * mat13 + scof11 * mat14) - (scof4 * mat12 + scof9 * mat13 + scof10 * mat14);
		float fcof12 = (scof2 * mat10 + scof5 * mat11 + scof1 * mat9 ) - (scof4 * mat11 + scof0 * mat9 + scof3 * mat10);
		float fcof13 = (scof8 * mat11 + scof0 * mat8 + scof7 * mat10) - (scof6 * mat10 + scof9 * mat11 + scof1 * mat8);
		float fcof14 = (scof6 * mat9 + scof11 * mat11 + scof3 * mat8) - (scof10 * mat11 + scof2 * mat8 + scof7 * mat9);
		float fcof15 = (scof10 * mat10 + scof4 * mat8 + scof9 * mat9) - (scof8 * mat9 + scof11 * mat10 + scof5 * mat8);

		//Calculate the determinant
		float determinant = mat0 * fcof0 + mat1 * fcof1 + mat2 * fcof2 + mat3 * fcof3;
		
		//Make sure the determinant isn't 0
		if (determinant == 0.0f)
			//Return nothing
			return new Matrix4D();
		
		//The inverted matrix
		Matrix4D inverse = new Matrix4D();

		//Calculate the matrix inverse
		float value = 1.0f / determinant;
		inverse.values[0] = fcof0 * value;
		inverse.values[1] = fcof1 * value;
		inverse.values[2] = fcof2 * value;
		inverse.values[3] = fcof3 * value;

		inverse.values[4] = fcof4 * value;
		inverse.values[5] = fcof5 * value;
		inverse.values[6] = fcof6 * value;
		inverse.values[7] = fcof7 * value;

		inverse.values[8] = fcof8 * value;
		inverse.values[9] = fcof9 * value;
		inverse.values[10] = fcof10 * value;
		inverse.values[11] = fcof11 * value;

		inverse.values[12] = fcof12 * value;
		inverse.values[13] = fcof13 * value;
		inverse.values[14] = fcof14 * value;
		inverse.values[15] = fcof15 * value;
		return inverse;
	}
	
}