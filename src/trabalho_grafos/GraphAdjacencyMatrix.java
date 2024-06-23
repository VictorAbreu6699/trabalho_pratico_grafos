package trabalho_grafos;

public class GraphAdjacencyMatrix implements Graph{

	private Integer[][] graph;
	
	public GraphAdjacencyMatrix(int numVertex) {
		this.graph = new Integer[numVertex][numVertex];
//		Integer i [][] = {
//	            {null, 1, null, 1},
//	            {null, null, 1, null},
//	            {1, null, null, null},
//	            {null, null, 1, null}
//	        };
//		
//		this.graph = i;
	}	
	
	@Override
	public String getRepresentationName() {
		return "Matriz de Adjacência";
	}
	
	@Override
	public String getRepresentationString() {
		String graphString = "";
        for (int i = 0; i < this.graph.length; i++) {
            for (int j = 0; j < this.graph[i].length; j++) { 
            	graphString += this.graph[i][j] + " ";
            }
            graphString += "\n";
        }
        
        return graphString;
	}

	@Override
	public void setEdge(int vertexOut, int vertexIn, int weight) {
		this.graph[vertexOut][vertexIn] = weight;
	}

	@Override
	public boolean hasVertex(int vertex) {		 
        return (vertex >= 0 && vertex < this.graph.length);
	}
	
	@Override
	public boolean hasEdge(int vertexOut, int vertexIn) {		
		if (!hasVertex(vertexOut) && !hasVertex(vertexIn)) {
	        return false;
	    }
		
        return this.graph[vertexOut][vertexIn] != null;
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
	    for (int i = 0; i < this.graph[vertexOut].length; i++) {
	        if (this.graph[vertexOut][i] != null && i != vertexIn) {
	            result += "(" + vertexOut + " -> " + i + ", peso: " + this.graph[vertexOut][i] + ") ";
	        }
	        
	        if (this.graph[i][vertexOut] != null && i != vertexIn) {
	            result += "(" + i + " -> " + vertexOut + ", peso: " + this.graph[i][vertexOut] + ") ";
	        }
	    }
	    
	    for (int i = 0; i < this.graph.length; i++) {
	        if (this.graph[vertexIn][i] != null && i != vertexOut) {
	            result += "(" + vertexIn + " -> " + i + ", peso: " + this.graph[vertexIn][i] + ") ";
	        }
	        
	        if (this.graph[i][vertexIn] != null && i != vertexOut) {
	            result += "(" + i + " -> " + vertexIn + ", peso: " + this.graph[i][vertexIn] + ") ";
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
