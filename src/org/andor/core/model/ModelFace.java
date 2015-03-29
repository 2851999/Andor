/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.model;

import java.util.ArrayList;
import java.util.List;

public class ModelFace {
	
	/* The vertices in this face */
	public List<ModelVertex> vertices;
	
	/* The constructor */
	public ModelFace() {
		//Create the vertices list
		this.vertices = new ArrayList<ModelVertex>();
	}
	
	/* The method used to add a vertex to this face */
	public void addVertex(ModelVertex vertex) { this.vertices.add(vertex); }
	public List<ModelVertex> getVertices() { return this.vertices; }
	public ModelVertex getVertex(int index) { return this.vertices.get(index); }
	
}