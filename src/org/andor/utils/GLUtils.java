/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import org.andor.core.Settings;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import android.opengl.GLES20;

public class GLUtils {
	
	/* ------------------------------- METHODS FOR CROSS PLATFORM SUPPORT (OPENGL AND OPENGLES) -------------------------------*/
	
	public static void activeTexture(int texture) {
		if (onPC())
			GL13.glActiveTexture(GL13.GL_TEXTURE0 + texture);
		else
			GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + texture);
	}
	
	public static void useProgram(int program) {
		if (onPC())
			GL20.glUseProgram(program);
		else
			GLES20.glUseProgram(program);
	}
	
	public static void attachShader(int program, int shader) {
		if (onPC())
			GL20.glAttachShader(program, shader);
		else
			GLES20.glAttachShader(program, shader);
	}
	
	public static void linkProgram(int program) {
		if (onPC())
			GL20.glLinkProgram(program);
		else
			GLES20.glLinkProgram(program);
	}
	
	public static void validateProgram(int program) {
		if (onPC())
			GL20.glValidateProgram(program);
		else
			GLES20.glValidateProgram(program);
	}
	
	public static void detachShader(int program, int shader) {
		if (onPC())
			GL20.glDetachShader(program, shader);
		else
			GLES20.glDetachShader(program, shader);
	}
	
	public static void uniform1f(int location, float v1) {
		if (onPC())
			GL20.glUniform1f(location, v1);
		else
			GLES20.glUniform1f(location, v1);
	}
	
	public static void uniform2f(int location, float v1, float v2) {
		if (onPC())
			GL20.glUniform2f(location, v1, v2);
		else
			GLES20.glUniform2f(location, v1, v2);
	}
	
	public static void uniform3f(int location, float v1, float v2, float v3) {
		if (onPC())
			GL20.glUniform3f(location, v1, v2, v3);
		else
			GLES20.glUniform3f(location, v1, v2, v3);
	}
	
	public static void uniform4f(int location, float v1, float v2, float v3, float v4) {
		if (onPC())
			GL20.glUniform4f(location, v1, v2, v3, v4);
		else
			GLES20.glUniform4f(location, v1, v2, v3, v4);
	}
	
	public static void uniform1(int location, float[] values) {
		if (onPC())
			GL20.glUniform1(location, BufferUtils.createFlippedBuffer(values));
		else
			//Might not need to be flipped
			GLES20.glUniform1fv(location, 1, BufferUtils.createFlippedBuffer(values));
	}
	
	public static void uniform1i(int location, int v1) {
		if (onPC())
			GL20.glUniform1i(location, v1);
		else
			GLES20.glUniform1i(location, v1);
	}
	
	public static void uniform2i(int location, int v1, int v2) {
		if (onPC())
			GL20.glUniform2i(location, v1, v2);
		else
			GLES20.glUniform2i(location, v1, v2);
	}
	
	public static void uniform3i(int location, int v1, int v2, int v3) {
		if (onPC())
			GL20.glUniform3i(location, v1, v2, v3);
		else
			GLES20.glUniform3i(location, v1, v2, v3);
	}
	
	public static void uniform4i(int location, int v1, int v2, int v3, int v4) {
		if (onPC())
			GL20.glUniform4i(location, v1, v2, v3, v4);
		else
			GLES20.glUniform4i(location, v1, v2, v3, v4);
	}
	
	public static void uniform1(int location, int[] values) {
		if (onPC())
			GL20.glUniform1(location, BufferUtils.createFlippedBuffer(values));
		else
			//Might not need to be flipped
			GLES20.glUniform1iv(location, 1, BufferUtils.createFlippedBuffer(values));
	}
	
	public static void uniformMatrix4(int location, boolean transpose, float[] values) {
		if (onPC())
			GL20.glUniformMatrix4(location, transpose, BufferUtils.createFlippedBuffer(values));
		else
			GLES20.glUniformMatrix4fv(location, 1, transpose, values, 0);
	}
	
	public static int getUniformLocation(int program, String name) {
		if (onPC())
			return GL20.glGetUniformLocation(program, name);
		else
			return GLES20.glGetUniformLocation(program, name);
	}
	
	public static int getAttributeLocation(int program, String name) {
		if (onPC())
			return GL20.glGetAttribLocation(program, name);
		else
			return GLES20.glGetAttribLocation(program, name);
	}
	
	public static void deleteShader(int shader) {
		if (onPC())
			GL20.glDeleteShader(shader);
		else
			GLES20.glDeleteShader(shader);
	}
	
	public static void deleteProgram(int program) {
		if (onPC())
			GL20.glDeleteProgram(program);
		else
			GLES20.glDeleteProgram(program);
	}
	
	public static void bindBuffer(int target, int buffer) {
		if (onPC())
			GL15.glBindBuffer(target, buffer);
		else
			GLES20.glBindBuffer(target, buffer);
	}
	
	public static void bufferData(int target, FloatBuffer data, int usage) {
		if (onPC())
			GL15.glBufferData(target, data, usage);
		else
			GLES20.glBufferData(target, Float.BYTES * data.remaining(), data, usage);
	}
	
	public static void bufferData(int target, ShortBuffer data, int usage) {
		if (onPC())
			GL15.glBufferData(target, data, usage);
		else
			GLES20.glBufferData(target, Short.BYTES * data.remaining(), data, usage);
	}
	
	public static void enableVertexAttribArray(int index) {
		if (onPC())
			GL20.glEnableVertexAttribArray(index);
		else
			GLES20.glEnableVertexAttribArray(index);
	}
	
	public static void vertexAttribPointer(int index, int size, int type, boolean normalized, int stride, int pointer) {
		if (onPC())
			GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointer);
		else
			GLES20.glVertexAttribPointer(index, size, type, normalized, stride, pointer);
	}
	
	public static void drawElements(int mode, int count, int type, int indicesOffset) {
		if (onPC())
			GL11.glDrawElements(mode, count, type, indicesOffset);
		else
			GLES20.glDrawElements(mode, count, type, indicesOffset);
	}
	
	public static void drawArrays(int mode, int first, int count) {
		if (onPC())
			GL11.glDrawArrays(mode, first, count);
		else
			GLES20.glDrawArrays(mode, first, count);
	}
	
	public static void disableVertexArrtibArray(int index) {
		if (onPC())
			GL20.glDisableVertexAttribArray(index);
		else
			GLES20.glDisableVertexAttribArray(index);
	}
	
	public static int genBuffers() {
		if (onPC())
			return GL15.glGenBuffers();
		else {
			int[] h = new int[1];
			GLES20.glGenBuffers(1, h, 0);
			return h[0];
		}
	}
	
	public static void deleteBuffers(int buffer) {
		if (onPC())
			GL15.glDeleteBuffers(buffer);
		else
			GLES20.glGenBuffers(1, new int[] { buffer }, 0);
	}
	
	public static int createProgram() {
		if (onPC())
			return GL20.glCreateProgram();
		else
			return GLES20.glCreateProgram();
	}
	
	public static int createShader(int type) {
		if (onPC())
			return GL20.glCreateShader(type);
		else
			return GLES20.glCreateShader(type);
	}
	
	public static void shaderSource(int shader, String source) {
		if (onPC())
			GL20.glShaderSource(shader, source);
		else
			GLES20.glShaderSource(shader, source);
	}
	
	public static void compileShader(int shader) {
		if (onPC())
			GL20.glCompileShader(shader);
		else
			GLES20.glCompileShader(shader);
	}
	
	public static void enable(int target) {
		if (onPC())
			GL11.glEnable(target);
		else
			GLES20.glEnable(target);
	}
	
	public static void disable(int target) {
		if (onPC())
			GL11.glDisable(target);
		else
			GLES20.glDisable(target);
	}
	
	public static void blendFunc(int sfactor, int dfactor) {
		if (onPC())
			GL11.glBlendFunc(sfactor, dfactor);
		else
			GLES20.glBlendFunc(sfactor, dfactor);
	}
	
	public static void depthMask(boolean flag) {
		if (onPC())
			GL11.glDepthMask(flag);
		else
			GLES20.glDepthMask(flag);
	}
	
	public static void depthFunc(int func) {
		if (onPC())
			GL11.glDepthFunc(func);
		else
			GLES20.glDepthFunc(func);
	}
	
	public static void bindTexture(int target, int texture) {
		if (onPC())
			GL11.glBindTexture(target, texture);
		else
			GLES20.glBindTexture(target, texture);
	}
	
	public static void texImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels) {
		if (onPC())
			GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
		else
			GLES20.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
	}
	
	public static void texParameteri(int target, int pname, int param) {
		if (onPC())
			GL11.glTexParameteri(target, pname, param);
		else
			GLES20.glTexParameteri(target, pname, param);
	}
	
	public static void texEnvi(int target, int pname, int param) {
		if (onPC())
			GL11.glTexEnvi(target, pname, param);
	}
	
	public static int genTextures() {
		if (onPC())
			return GL11.glGenTextures();
		else {
			int[] h = new int[1];
			GLES20.glGenTextures(1, h, 0);
			return h[0];
		}
	}
	
	public static void deleteTextures(int texture) {
		if (onPC())
			GL11.glDeleteTextures(texture);
		else
			GLES20.glDeleteTextures(1, new int[] { texture }, 0);
	}
	
	/* The static method used to return whether android is enabled */
	public static boolean onPC() { return ! Settings.AndroidMode; }
	public static boolean onAndroid() { return Settings.AndroidMode; }
	
}
