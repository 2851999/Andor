/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldUtils {
	
	/* The static methods used to determine things about a field */
	public static boolean isPrivate(Field field) { return Modifier.isPrivate(field.getModifiers()); }
	public static boolean isPublicStaticFinal(Field field) {
		int modifier = field.getModifiers();
		return Modifier.isPublic(modifier) && Modifier.isStatic(modifier) && Modifier.isFinal(modifier);
	}
	
}