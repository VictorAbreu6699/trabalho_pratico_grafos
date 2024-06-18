package trabalho_grafos;

public class GraphListAdjacencyNode {
	private int vertex;
    private int weight;
    private GraphListAdjacencyNode successor;

    public int getVertex() {
		return vertex;
	}

	public void setVertex(int vertex) {
		this.vertex = vertex;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public GraphListAdjacencyNode getSuccessor() {
		return successor;
	}

	public void setSuccessor(GraphListAdjacencyNode successor) {
		this.successor = successor;
	}

	GraphListAdjacencyNode(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}
