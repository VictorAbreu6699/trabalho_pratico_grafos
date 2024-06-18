package trabalho_grafos;

public interface Graph {
	public String getRepresentationName();
	public String getRepresentationString();	
	public void setEdge(int vertexOut, int vertexIn, int weight);
	public boolean hasVertex(int vertex);
	
}
