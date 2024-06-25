package trabalho_grafos;

public interface Graph {
	public String getRepresentationName();
	public String getRepresentationString();	
	public void setEdge(int vertexOut, int vertexIn, int weight);
	public boolean hasVertex(int vertex);
	public boolean hasEdge(int vertexOut, int vertexIn);
	public String getAdjacentEdges(int vertexOut, int vertexIn);
	public String getAdjacentVertices(int vertex);
	public String getIncidentEdgesToVertex(int vertex);
	public int getDegreeVertex(int vertex);
	public boolean isAdjacent(int vertex, int vertexB);
	public void alterEdgeWeight(int vertexOut, int vertexIn, int weight);
	public void replaceVertex(int vertex, int vertexB);
	public String buscaEmLargura(int vertex);
	public String buscaEmProfundidade(int vertex);
	public String dijkstra(int vertexSource, int vertexDestination);
}
