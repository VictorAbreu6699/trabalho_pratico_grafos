package trabalho_grafos;

import java.util.Scanner;

public class GraphBuilder {
	
	public static Graph calculateGraphRepresentation(int numVertex, int numEdge) {
		 float densidade = numEdge / (numVertex * (numVertex - 1));
		 
		 if(densidade > 0.5) {
		 	return new GraphAdjacencyMatrix(numVertex);
		 }
		 else {
			 return new GraphAdjacencyList(numVertex);
		 }
	}
	
	public static Graph buildGraphWithConsole() {
		Scanner scanner = new Scanner(System.in);		
		
		System.out.println("Digite a quantidade de vertices");
		int vertexQtd = scanner.nextInt();		
		
		System.out.println("Digite a quantidade de arestas");
		int edgeQtd = scanner.nextInt();
		int vertexIn, vertexOut; 
		Graph graph = calculateGraphRepresentation(vertexQtd, edgeQtd);		
		
		for(int i = 0; i < edgeQtd; i++) 
		{
			System.out.println(graph.getRepresentationName());
			System.out.println(graph.getRepresentationString());
			
			System.out.println("Digite o vertice de saída da aresta " + i);
			vertexOut = scanner.nextInt();
			while(!graph.hasVertex(vertexOut)) {
				System.out.println("O vertice informado é invalido!");
				System.out.println("Digite o vertice de saída da aresta " + i);
				vertexOut = scanner.nextInt();	
			}
			
			System.out.println("Digite o vertice de entrada da aresta " + i);
			vertexIn = scanner.nextInt();
			while(!graph.hasVertex(vertexIn)) {
				System.out.println("O vertice informado é invalido!");
				System.out.println("Digite o vertice de saída da aresta " + i);
				vertexIn = scanner.nextInt();	
			}
			
			System.out.println("Digite o peso da aresta " + i);
			int weight = scanner.nextInt();			
			
			graph.setEdge(vertexOut, vertexIn, weight);
		}
		
		System.out.println(graph.getRepresentationName());
		System.out.println(graph.getRepresentationString());
		
		return graph;
	}
	
}
