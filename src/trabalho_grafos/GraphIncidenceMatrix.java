package trabalho_grafos;

public class GraphIncidenceMatrix implements Graph{
	
	private int[][] graph;
	
	public GraphIncidenceMatrix(int numVertex, int numEdge) {
		this.graph = new int[numVertex][numVertex];
	}

	@Override
	public String getRepresentationName() {
		return "Matriz de Incidência";
	}

	public int[][] getGraph() {
		return graph;
	}

	@Override
	public String getRepresentationString() {
		String graphString = "";
        for (int i = 0; i < this.graph.length; i++) {
            for (int j = 0; j < this.graph[i].length; j++) { 
            	graphString = this.graph[i][j] + " \n";
            }
        }
        
        return graphString;
	}

	@Override
	public void setEdge(int vertexIn, int vertexOut, int weight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasVertex(int vertex) {
		// TODO Auto-generated method stub
		return false;
	}

}
