package trabalho_grafos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
	
	public static Graph buildGraphWithDIMACSFile() throws Exception {
		return readDIMASCFile("src/trabalho_grafos/graph.dimacs");
	}
	
	public static Graph readDIMASCFile(String path) throws Exception {
		Graph graph = null;
		String row;
		int i = 0, vertexQtd, edgeQtd, vertexIn, vertexOut, weight;
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((row = br.readLine()) != null) {
            	String[] rowSplited = row.split(" ");
            	// Na primeira linha tem o numero de vertices e de arestas
                if(i == 0) {
            		vertexQtd = Integer.parseInt(rowSplited[0]);
            		edgeQtd = Integer.parseInt(rowSplited[1]);
            		
            		graph = calculateGraphRepresentation(vertexQtd, edgeQtd);            		
            		System.out.println(graph.getRepresentationName());
                }
                else {
                	// Nas demais linhas somente as arestas e seus respectivos pesos
                	vertexOut = Integer.parseInt(rowSplited[0]);
                	vertexIn = Integer.parseInt(rowSplited[1]);
                	weight = Integer.parseInt(rowSplited[2]);
            		
                	if(!graph.hasVertex(vertexOut)) {
                		throw new Exception("Vertice de saida invalido na Linha " + i);
                	}
                	
                	if(!graph.hasVertex(vertexIn)) {
                		throw new Exception("Vertice de entrada invalido na Linha " + i);
                	}
                	
                	graph.setEdge(vertexOut, vertexIn, weight);
                }
                
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
        	throw e;
        }
		
		System.out.println(graph.getRepresentationString());
		
		return graph;
	}
	
	
}
