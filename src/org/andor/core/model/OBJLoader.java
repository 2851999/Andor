/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.andor.core.Settings;
import org.andor.core.android.BaseActivity;
import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.andor.core.model.MaterialLoader;
import org.andor.core.render.Renderer;
import org.andor.utils.FileUtils;

public class OBJLoader {

	/* The static method used to load and return an obj file */
	public static Model loadModel(String filePath, String directory, boolean external) {
		//The model
		Model model = new Model();
		IndexedModel indexedModel = new IndexedModel();
		//Try
		try {
			//The buffered reader
			BufferedReader bufferedReader = null;
			//Check to see whether the file is in a folder
			if (external) {
				//Create a file reader
				FileReader fileReader = new FileReader(FileUtils.getPath(filePath));
				//Create the buffered reader
				bufferedReader = new BufferedReader(fileReader);
			} else {
				//Check to see whether Andor is currently running on Android
				if (! Settings.AndroidMode)
					//Create the buffered reader
					bufferedReader = new BufferedReader(new InputStreamReader(FileUtils.class.getResourceAsStream(filePath)));
				else
					bufferedReader = new BufferedReader(new InputStreamReader(BaseActivity.instance.getAssets().open(filePath)));
			}

			//The current line
			String line = "";
			//The current part
			ModelPart currentPart = new ModelPart(Renderer.TRIANGLES);
			//Go through the file text
			while ((line = bufferedReader.readLine()) != null) {
				//Check the start of the current line
				if (line.startsWith("v "))
					//Add a vertex to the model
					indexedModel.addVertex(ModelParserUtils.getVectorValue3D(line));
				else if (line.startsWith("vn "))
					//Add a normal to the model
					indexedModel.addNormal(ModelParserUtils.getVectorValue3D(line));
				else if (line.startsWith("vt "))
					//Add a texture to the model
					indexedModel.addTextureCoordinate(ModelParserUtils.getVectorValue2D(line));
				else if (line.startsWith("f "))
					//Add a face to the model
					currentPart.addFace(getFace(line, indexedModel));
				else if (line.startsWith("mtllib ")) {
					//Split up the line
					String[] split = line.split(" ");
					//Get the this object's file name
					String fileName = new File(FileUtils.getPath(filePath)).getName();
					//Get the file path of the material file
					String materialFilePath = filePath.replace(fileName, split[1]);
					//Load the material
					indexedModel.materials = MaterialLoader.loadMaterialFile(materialFilePath, directory, external, indexedModel.materials);
				} else if (line.startsWith("usemtl ")) {
					//Split up the line
					String[] split = line.split(" ");
					//Add the current part and start a new one
					if (currentPart.getFaces().size() > 0)
						model.addPart(currentPart);
					currentPart = new ModelPart(Renderer.TRIANGLES);
					currentPart.setMaterial(indexedModel.getMaterial(split[1]));
				}
			}
			
			//Add the current part
			model.addPart(currentPart);

			//Close the buffer
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			//Log an error message
			Logger.log("OBJLoader loadModel()", "Error loading file (FileNotFoundExeption): " + filePath, Log.ERROR);
			//Print the error
			e.printStackTrace();
		} catch (IOException e) {
			//Log an error message
			Logger.log("Andor - OBJLoader loadModel()", "Error loading file (IOException): " + filePath, Log.ERROR);
			//Print the error
			e.printStackTrace();
		}
		//Return the model
		return model;
	}

	/* The static method used to get a face from a line */
	public static ModelFace getFace(String line, IndexedModel indexedModel) {
		//Split up the line using a space 1//2 1//2 1//2
		String[] split = line.split(" ");

		//Create the face
		ModelFace face = new ModelFace();
		
		//Go through the array
		for (int a = 1; a < split.length; a++) {
			//Get the current values
			float[] current = calculateFaceValues(split[a]);
			//Create the face vertex
			ModelVertex faceVertex = new ModelVertex();
			//Assign the values
			faceVertex.setVertex(indexedModel.getVertex((int) current[0] - 1));
			if (current[1] != 0)
				faceVertex.setNormal(indexedModel.getNormal((int) current[1] - 1));
			if (current[2] != 0)
				faceVertex.setTextureCoordinate(indexedModel.getTextureCoordinate((int) current[2] - 1));
			//Add the face vertex to the face
			face.addVertex(faceVertex);
		}
		//Return the face
		return face;
	}

	/* The static method used to calculate face values of a certain part of a face */
	public static float[] calculateFaceValues(String facePart) {
		//The values
		float[] values = new float[3];
		//Find the correct format of the current part of the face
		if (facePart.contains("//")) {
			//FORMAT V//VN

			//Split the face part using "//"
			String[] split = facePart.split("//");
			//Set the vertex and the normal
			values[0] = getValue(split[0]);
			values[1] = getValue(split[1]);
			values[2] = 0;
		} else if (facePart.contains("/")) {
			//FORMAT V/VT OR V/VT/VN

			//Split up the face part using a "/"
			String[] split = facePart.split("/");
			//Check the split length
			if (split.length == 2) {
				//FORMAT V/VT

				//Set the vertex and the texture
				values[0] = getValue(split[0]);
				values[1] = 0;
				values[2] = getValue(split[1]);
			} else if (split.length == 3) {
				//FORMAT V/VN/VT

				//Set the vertex, normal and the texture
				values[0] = getValue(split[0]);
				values[1] = getValue(split[2]);
				values[2] = getValue(split[1]);
			}
		} else {
			//FORMAT = V
			
			//Set the vertex
			values[0] = getValue(facePart);
			values[1] = 0;
			values[2] = 0;
		}
		//Return the values
		return values;
	}

	/* The static method used to get the float value of a string */
	public static float getValue(String value) {
		return ModelParserUtils.getValue(value);
	}
	
}