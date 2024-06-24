package trabalho_grafos;

import java.util.Scanner;

public class Main {
	
	private static Scanner scanner;

	public static void main(String[] args) throws Exception {		
		scanner = new Scanner(System.in);
		Graph graph = null; 		
		int choice;
		boolean validatedChoice = false; 
		
		while(!validatedChoice) {
			System.out.println("Como deseja construir o grafo?:");
			System.out.println("1. Via terminal");
			System.out.println("2. Acessando arquivo DIMACS");
			
			choice = scanner.nextInt();
			switch (choice) {
				case 1:
					graph = GraphBuilder.buildGraphWithConsole();
					validatedChoice = true;
					break;
				case 2:
					graph = GraphBuilder.buildGraphWithDIMACSFile();
					validatedChoice = true;
					break;
				default:
					System.out.println("Opção invalida");
					break;
			}
		}		
		
		menu(graph);
		scanner.close();
	}
	
	public static void menu(Graph graph) {
		int vertexIn, vertexOut, choice, vertex, vertexB;
		
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Imprimir todas as arestas adjacentes a uma aresta");
            System.out.println("2. Imprimir todos os vértices adjacentes a um vértice");
            System.out.println("3. Imprimir todas as arestas incidentes a um vértice");
            System.out.println("4. Imprimir todos os vértices incidentes a uma aresta");
            System.out.println("5. Imprimir o grau do vértice");
            System.out.println("6. Determinar se dois vértices são adjacentes");
            System.out.println("7. Substituir o peso de uma aresta");
            System.out.println("8. Trocar dois vértices");
            System.out.println("9. Busca em Largura");
            System.out.println("10. Busca em Profundidade");
            System.out.println("11. Implementar o Algoritmo de Dijkstra");
            System.out.println("12. Implementar o Algoritmo de Floyd Warshall");
            System.out.println("13. Sair");
            System.out.print("Escolha uma opção: ");

            choice = scanner.nextInt();              

            switch (choice) {
                case 1:                	
        			vertexOut = getVertexOutMenu(graph);
        			vertexIn = getVertexInMenu(graph);        			
        			
        			System.out.println(graph.getAdjacentEdges(vertexOut, vertexIn));
                    break;
                case 2:
                	vertex = getVertexMenu(graph);
        			
        			System.out.println(graph.getAdjacentVertices(vertex));        			
                    break;
                case 3:
                	vertex = getVertexMenu(graph);
        			
        			System.out.println(graph.getIncidentEdgesToVertex(vertex));        			
                    break;
                case 4:
                	vertexOut = getVertexOutMenu(graph);
        			vertexIn = getVertexInMenu(graph);
        			
        			System.out.println(vertexOut + " " + vertexIn);
        			
                    break;
                case 5:
            		vertex = getVertexMenu(graph);
            		
        			System.out.println(graph.getDegreeVertex(vertex));     
                    break;
                case 6:
                	System.out.println("Primeiro vertice:");
            		vertex = getVertexMenu(graph);
            		System.out.println("Segundo vertice:");
            		vertexB = getVertexMenu(graph);
            		
            		if(graph.isAdjacent(vertex, vertexB)) {
            			System.out.println(vertex + " e " + vertexB + " são adjacentes");
            		}
            		else {
            			System.out.println(vertex + " e " + vertexB + " não são adjacentes");
            		}
        			   
                    break;
                case 7:
                    // Substituir o peso de uma aresta
                    System.out.println("Funcionalidade não implementada ainda.");
                    break;
                case 8:
                    // Trocar dois vértices
                    System.out.println("Funcionalidade não implementada ainda.");
                    break;
                case 9:
                    // Busca em Largura
                    System.out.println("Funcionalidade não implementada ainda.");
                    break;
                case 10:
                    // Busca em Profundidade
                    System.out.println("Funcionalidade não implementada ainda.");
                    break;
                case 11:
                    // Implementar o Algoritmo de Dijkstra
                    System.out.println("Funcionalidade não implementada ainda.");
                    break;
                case 12:
                    // Implementar o Algoritmo de Floyd Warshall
                    System.out.println("Funcionalidade não implementada ainda.");
                    break;
                case 13:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println();
        }
	}
	
	public static int getVertexMenu(Graph graph) {
		System.out.println("Digite o vertice:");
		int vertex = scanner.nextInt();
		while(!graph.hasVertex(vertex)) {
			System.out.println("O vertice informado é invalido!");
			System.out.println("Digite o vertice:");
			vertex = scanner.nextInt();
		}
		
		return vertex;
	}
	
	public static int getVertexOutMenu(Graph graph) {
		System.out.println("Digite o vertice de saída da aresta:");
		int vertexOut = scanner.nextInt();
		while(!graph.hasVertex(vertexOut)) {
			System.out.println("O vertice informado é invalido!");
			System.out.println("Digite o vertice de saída da aresta:");
			vertexOut = scanner.nextInt();	
		}
		
		return vertexOut;
	}
	
	public static int getVertexInMenu(Graph graph) {
		System.out.println("Digite o vertice de entrada da aresta:");
		int vertexIn = scanner.nextInt();
		while(!graph.hasVertex(vertexIn)) {
			System.out.println("O vertice informado é invalido!");
			System.out.println("Digite o vertice de entrada da aresta:");
			vertexIn = scanner.nextInt();	
		}
		
		return vertexIn;
	}

}
