/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {
	
	/* The static method to turn a string list into a string array */
	public static String[] toStringArray(List<String> list) {
		//Create the array
		String[] array = new String[list.size()];
		//Go through the new array
		for (int a = 0; a < array.length; a++)
			//Set the current line
			array[a] = list.get(a);
		//Return the array
		return array;
	}
	
	/* The static method to turn a string array into a string list */
	public static List<String> toStringList(String[] array) {
		//Create the list
		List<String> list = new ArrayList<String>();
		//Go through the array
		for (int a = 0; a < array.length; a++)
			//Add the current line to the list
			list.add(array[a]);
		//Return the list
		return list;
	}
	
	/* The static method to turn a float list into a float array */
	public static float[] toFloatArray(List<Float> list) {
		//Create the array
		float[] array = new float[list.size()];
		//Go through the new array
		for (int a = 0; a < array.length; a++)
			//Set the current line
			array[a] = list.get(a);
		//Return the array
		return array;
	}
	
	/* The static method to turn a float array into a float list */
	public static List<Float> toFloatList(float[] array) {
		//Create the list
		List<Float> list = new ArrayList<Float>();
		//Go through the array
		for (int a = 0; a < array.length; a++)
			//Add the current line to the list
			list.add(array[a]);
		//Return the list
		return list;
	}
	
	/* The static method to turn a short list into a short array */
	public static short[] toShortArray(List<Short> list) {
		//Create the array
		short[] array = new short[list.size()];
		//Go through the new array
		for (int a = 0; a < array.length; a++)
			//Set the current line
			array[a] = list.get(a);
		//Return the array
		return array;
	}
	
	/* The static method to turn a short array into a short list */
	public static List<Short> toShortList(short[] array) {
		//Create the list
		List<Short> list = new ArrayList<Short>();
		//Go through the array
		for (int a = 0; a < array.length; a++)
			//Add the current line to the list
			list.add(array[a]);
		//Return the list
		return list;
	}
	
	/* The static method to turn a integer list into a integer array */
	public static int[] toIntegerArray(List<Integer> list) {
		//Create the array
		int[] array = new int[list.size()];
		//Go through the new array
		for (int a = 0; a < array.length; a++)
			//Set the current line
			array[a] = list.get(a);
		//Return the array
		return array;
	}
	
	/* The static method to turn a integer array into a integer list */
	public static List<Integer> toIntegerList(int[] array) {
		//Create the list
		List<Integer> list = new ArrayList<Integer>();
		//Go through the array
		for (int a = 0; a < array.length; a++)
			//Add the current line to the list
			list.add(array[a]);
		//Return the list
		return list;
	}
	
}