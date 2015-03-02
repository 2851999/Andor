/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.model;

import java.util.List;

import org.andor.core.Colour;
import org.andor.core.Image;
import org.andor.core.ImageLoader;
import org.andor.core.Vector3D;
import org.andor.utils.FileUtils;

public class MaterialLoader {
	
	/* The static method used to load a material  and return it */
	public static List<Material> loadMaterialFile(String filePath, String directory, boolean external, List<Material> materials) {
		//The file text
		List<String> fileText = FileUtils.read(filePath, external);
		//The current material
		Material currentMaterial = null;
		//Go though each line in the material file
		for (int a = 0; a < fileText.size(); a++) {
			//Get the current line
			String line = fileText.get(a);
			//Check the beginning of the line
			if (line.startsWith("newmtl ")) {
				//Split up the line using a space
				String[] split = line.split(" ");
				//Create the new material
				currentMaterial = new Material(split[1]);
				//Add the material
				materials.add(currentMaterial);
			} else if (line.startsWith("Ka ")) {
				//Get the vector values
				Vector3D values = ModelParserUtils.getVectorValue(line);
				//Set the ambient colour in the current material
				currentMaterial.ambientColour = new Colour(values.x, values.y, values.z);
			} else if (line.startsWith("Kd ")) {
				//Get the vector values
				Vector3D values = ModelParserUtils.getVectorValue(line);
				//Set the diffuse colour in the current material
				currentMaterial.diffuseColour = new Colour(values.x, values.y, values.z);
			} else if (line.startsWith("Ks ")) {
				//Get the vector values
				Vector3D values = ModelParserUtils.getVectorValue(line);
				//Set the specular colour in the current material
				currentMaterial.specularColour = new Colour(values.x, values.y, values.z);
			} else if (line.startsWith("Ns ")) {
				//Split up the current line
				String[] split = line.split(" ");
				//Get the shininess value
				float shininess = Float.parseFloat(split[1]);
				//Set the shininess colour in the current material
				currentMaterial.shininess = shininess;
			} else if (line.startsWith("d ") || line.startsWith("Tr ")) {
				//Split up the current line using a space
				String[] split = line.split(" ");
				//Get the alpha colour value
				float alphaColourValue = ModelParserUtils.getValue(split[1]);
				//Set the alpha colour value in the current material
				currentMaterial.alphaColourValue = alphaColourValue;
			} else if (line.startsWith("map_d")) {
				//Split up the current line using a space
				String[] split = line.split(" ");
				//Get the file path of the material file
				String textureFilePath = directory + split[1];
				//Load the texture map
				Image alphaTextureMap = ImageLoader.loadImage(textureFilePath.replace("\\", "/"), external);
				//Set the texture map in the current material
				currentMaterial.alphaTextureMap = alphaTextureMap;
			} else if (line.startsWith("map_Ka")) {
				//Split up the current line using a space
				String[] split = line.split(" ");
				//Get the file path of the material file
				String textureFilePath = directory + split[1];
				//Load the texture map
				Image ambientTextureMap = ImageLoader.loadImage(textureFilePath.replace("\\", "/"), external);
				//Set the texture map in the current material
				currentMaterial.ambientTextureMap = ambientTextureMap;
			} else if (line.startsWith("map_Kd")) {
				//Split up the current line using a space
				String[] split = line.split(" ");
				//Get the file path of the material file
				String textureFilePath = directory + split[1];
				//Load the texture map
				Image diffuseTextureMap = ImageLoader.loadImage(textureFilePath.replace("\\", "/"), external);
				//Set the texture map in the current material
				currentMaterial.diffuseTextureMap = diffuseTextureMap;
			} else if (line.startsWith("map_Ks")) {
				//Split up the current line using a space
				String[] split = line.split(" ");
				//Get the file path of the material file
				String textureFilePath = directory + split[1];
				//Load the texture map
				Image specularTextureMap = ImageLoader.loadImage(textureFilePath.replace("\\", "/"), external);
				//Set the texture map in the current material
				currentMaterial.specularTextureMap = specularTextureMap;
			}
		}
		//Return the materials
		return materials;
	}
	
}