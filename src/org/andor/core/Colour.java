/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

public class Colour {
	
	/* The predefined colours */
	public static final Colour NONE = new Colour(0.0f, 0.0f, 0.0f, 0.0f);
	public static final Colour BLACK = new Colour(0.0f, 0.0f, 0.0f);
	public static final Colour GREY = new Colour(0.2f, 0.2f, 0.2f);
	public static final Colour LIGHT_GREY = new Colour(0.45f, 0.45f, 0.45f);
	public static final Colour RED = new Colour(1.0f, 0.0f, 0.0f);
	public static final Colour ORANGE = new Colour(1.0f, 0.6470588235294118f, 0.0f);
	public static final Colour YELLOW = new Colour(1.0f, 1.0f, 0.0f);
	public static final Colour PINK = new Colour(1.0f, 0.0f, 1.0f);
	public static final Colour GREEN = new Colour(0.0f, 1.0f, 0.f);
	public static final Colour BLUE = new Colour(0.0f, 0.0f, 1.0f);
	public static final Colour LIGHT_BLUE = new Colour(0.0f, 1.0f, 1.0f);
	public static final Colour WHITE = new Colour(1.0f, 1.0f, 1.0f);
	
	/* Some predefined arrays of colours */
	public static final Colour[] ARRAY_RGB = new Colour[] { RED, GREEN, BLUE };
	public static final Colour[] ARRAY_GREY = new Colour[] { GREY, LIGHT_GREY };
	public static final Colour[] ARRAY_BLUE = new Colour[] { BLUE, LIGHT_BLUE };
	public static final Colour[] ARRAY_SUNSET = new Colour[] { RED, ORANGE, YELLOW };
	
	/* The colour values */
	public float r;
	public float g;
	public float b;
	public float a;
	
	/* The constructor */
	public Colour() {
		//Assign the variables
		this.r = 0.0f;
		this.g = 0.0f;
		this.b = 0.0f;
		this.a = 0.0f;
	}
	
	/* The constructor with another colour given */
	public Colour(Colour colour) {
		//Assign the variables
		this.r = colour.r;
		this.g = colour.g;
		this.b = colour.b;
		this.a = colour.a;
	}
	
	/* The constructor with the r, g, b values given */
	public Colour(float r, float g, float b) {
		//Assign the variables
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = 1.0f;
	}
	
	/* The constructor with the r, g, b, a values given */	
	public Colour(float r, float g, float b, float a) {
		//Assign the variables
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	/* The constructor with another colour and the alpha values given */	
	public Colour(Colour colour, float a) {
		//Assign the variables
		this.r = colour.r;
		this.g = colour.g;
		this.b = colour.b;
		this.a = a;
	}
	
	/* The method used to create a new colour with the same values as
	 * this one and then return it (Clones this colour) */
	public Colour clone() {
		return new Colour(this);
	}
	
	/* The method used to set all of the values */
	public void set(float r, float g, float b, float a) {
		//Assign the values
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	/* The method used to add another colour to this one and return the result */
	public Colour addRGB(Colour other) {
		return new Colour(this.r + other.r, this.g + other.g, this.b + other.b);
	}
	
	/* The method used to add another colour to this one and return the result */
	public Colour addRGBA(Colour other) {
		return new Colour(this.r + other.r, this.g + other.g, this.b + other.b, this.a + other.a);
	}
	
	/* The method used to subtract another colour from this one and return the result */
	public Colour subtractRGB(Colour other) {
		return new Colour(this.r - other.r, this.g - other.g, this.b - other.b);
	}
	
	/* The method used to subtract another colour from this one and return the result */
	public Colour subtractRGBA(Colour other) {
		return new Colour(this.r - other.r, this.g - other.g, this.b - other.b, this.a - other.a);
	}
	
	/* The method used to multiply this colour by another one and return the result */
	public Colour multiplyRGB(float value) {
		return new Colour(this.r * value, this.g * value, this.b * value);
	}
	
	/* The method used to multiply this colour by another one and return the result */
	public Colour multiplyRGBA(float value) {
		return new Colour(this.r * value, this.g * value, this.b * value, this.a * value);
	}
	
	/* The method used to return a float array containing the rgb values */
	public float[] getValuesRGB() { return new float[] { this.r, this.g, this.b }; }
	/* The method used to return a float array containing the rgba values */
	public float[] getValuesRGBA() { return new float[] { this.r, this.g, this.b, this.a }; }
	
	/* The methods used to set the values */
	public void setR(float r) { this.r = r; }
	public void setG(float g) { this.g = g; }
	public void setB(float b) { this.b = b; }
	public void setA(float a) { this.a = a; }
	
	/* The 'getter' methods */
	public float getR() { return this.r; }
	public float getG() { return this.g; }
	public float getB() { return this.b; }
	public float getA() { return this.a; }
	
	public String toString() {
		return "(" + r + "," + g + "," + b + "," + a + ")";
	}
	
	/* The method used to create a colour from integer values (0 - 255) */
	public static Colour create(int r, int g, int b) {
		return new Colour(((float) r) / 255f, ((float) g) / 255f, ((float) b) / 255f);
	}
	
	/* The method used to create a colour from integer values (0 - 255) */
	public static Colour create(int r, int g, int b, int a) {
		return new Colour(((float) r) / 255f, ((float) g) / 255f, ((float) b) / 255f, ((float) a) / 255f);
	}
	
}