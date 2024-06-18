package trabalho_grafos;

import java.util.HashMap;
import java.util.Map;

public class GraphAdjacencyList implements Graph{	
	
	private Map<Integer, GraphListAdjacencyNode> graph;
	private int numVertex; 
	
	public GraphAdjacencyList(int numVertex) {
		this.graph = new HashMap<>();
		this.numVertex = numVertex;
	}	

	@Override
	public String getRepresentationName() {
		return "Lista de Adjacência";
	}

	public Map<Integer, GraphListAdjacencyNode> getGraph() {
		return this.graph;
	}

	@Override
	public String getRepresentationString() {
		String graphString = "";
		
		for (int i = 0; i < this.numVertex; i++) {			
            graphString += "Vertice " + i + ":";
            if (this.graph.containsKey(i)) {
            	GraphListAdjacencyNode node = this.graph.get(i);
            	while (node != null) {
            		graphString += " -> " + node.getVertex() + "(" + node.getWeight() + ")";
            		node = node.getSuccessor();
            	}            	
            }
            graphString += "\n";   
		}
        
        return graphString;
	}

	@Override
	public void setEdge(int vertexOut, int vertexIn, int weight) {		
		GraphListAdjacencyNode newNode = new GraphListAdjacencyNode(vertexIn, weight);
        if (!this.graph.containsKey(vertexOut)) {
        	this.graph.put(vertexOut, newNode);
        } else {
            GraphListAdjacencyNode current = this.graph.get(vertexOut);
            while (current.getSuccessor() != null) {
                current = current.getSuccessor();
            }
            current.setSuccessor(newNode);
        }
	}
	
	@Override
	public boolean hasVertex(int vertex) {		
		return vertex >= 0 && vertex < this.numVertex;
	}

}
