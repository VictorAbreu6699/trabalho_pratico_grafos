package trabalho_grafos;

public class GraphAdjacencyMatrix implements Graph{

	private Integer[][] graph;
	
	public GraphAdjacencyMatrix(int numVertex) {
		this.graph = new Integer[numVertex][numVertex];
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

}
