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
		int vertexIn, vertexOut, choice, vertex;
		
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
                	System.out.println("Digite o vertice de saída da aresta");
        			vertexOut = scanner.nextInt();
        			while(!graph.hasVertex(vertexOut)) {
        				System.out.println("O vertice informado é invalido!");
        				System.out.println("Digite o vertice de saída da aresta");
        				vertexOut = scanner.nextInt();	
        			}
        			
                	System.out.println("Digite o vertice de entrada da aresta");
        			vertexIn = scanner.nextInt();
        			while(!graph.hasVertex(vertexIn)) {
        				System.out.println("O vertice informado é invalido!");
        				System.out.println("Digite o vertice de entrada da aresta");
        				vertexIn = scanner.nextInt();	
        			}        			
        			
        			System.out.println(graph.getAdjacentEdges(vertexOut, vertexIn));
        			System.out.println();
                    break;
                case 2:
                	System.out.println("Digite o vertice");
        			vertex = scanner.nextInt();
        			while(!graph.hasVertex(vertex)) {
        				System.out.println("O vertice informado é invalido!");
        				System.out.println("Digite o vertice");
        				vertex = scanner.nextInt();
        			}
        			
        			System.out.println(graph.getAdjacentVertices(vertex));
        			System.out.println();
                    break;
                case 3:
                    // Imprimir todas as arestas incidentes a um vértice
                    System.out.println("Funcionalidade não implementada ainda.");
                    break;
                case 4:
                    // Imprimir todos os vértices incidentes a uma aresta
                    System.out.println("Funcionalidade não implementada ainda.");
                    break;
                case 5:
                    // Imprimir o grau do vértice
                    System.out.println("Funcionalidade não implementada ainda.");
                    break;
                case 6:
                    // Determinar se dois vértices são adjacentes
                    System.out.println("Funcionalidade não implementada ainda.");
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
        }
	}

}
