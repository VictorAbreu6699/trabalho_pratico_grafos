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
	
	@Override
	public boolean hasEdge(int vertexOut, int vertexIn) {
	    if (this.graph.containsKey(vertexOut)) {
	        GraphListAdjacencyNode current = this.graph.get(vertexOut);
	        while (current != null) {
	            if (current.getVertex() == vertexIn) {
	                return true;
	            }
	            current = current.getSuccessor();
	        }
	    }
	    return false;
	}

	@Override
	public String getAdjacentEdges(int vertexOut, int vertexIn) {
		if (!hasVertex(vertexOut)) {
	        return "Vértice de saída não encontrado.";
	    }
		
		if (!hasVertex(vertexIn)) {
	        return "Vértice de entrada não encontrado.";
	    }
		
		if (!hasEdge(vertexOut, vertexIn)) {
	        return "Aresta não encontrada.";
	    }
		
		String result = "";
	    for (int i = 0; i < this.numVertex; i++) {
	    	GraphListAdjacencyNode current = this.graph.get(i);
	    	while (current != null) {
	    		if(i == vertexOut && current.getVertex() != vertexIn) {
	    			result += "(" + vertexOut + " -> " + current.getVertex() + ", peso: " + current.getWeight() + ") ";	
	    		}	    		
	    		else if(i == vertexIn && current.getVertex() != vertexOut) {
	    			result += "(" + vertexIn + " -> " + current.getVertex() + ", peso: " + current.getWeight() + ") ";	
	    		}
	    		else if(i != vertexIn && i != vertexOut && (current.getVertex() == vertexOut || current.getVertex() == vertexIn)) {	    			
    				result += "(" + i + " -> " + current.getVertex() + ", peso: " + current.getWeight() + ") ";	    			
    			}	    		
	            
	            current = current.getSuccessor();
	        }
		}
	    
	    if(result.length() == 0)
	    {
	    	result = "Não foram encontradas arestas adjacentes.";
	    }
	    
	    return result;
	}

	@Override
	public String getAdjacentVertices(int vertexOut, int vertexIn) {
		// TODO Auto-generated method stub
		return null;
	}


}
