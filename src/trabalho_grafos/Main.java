package trabalho_grafos;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {		
		Scanner scanner = new Scanner(System.in);
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
					graph = GraphBuilder.buildGraphWithConsole();
					validatedChoice = true;
					break;
				default:
					System.out.println("Opção invalida");
					break;
			}
		}
		
		
		menu(graph);
	}
	
	public static void menu(Graph graph) {
		Scanner scanner = new Scanner(System.in);
		Graph grafo = null;

        while (true) {
            System.out.println("\nMenu:");
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

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    // Imprimir todas as arestas adjacentes a uma aresta
                    System.out.println("Funcionalidade não implementada ainda.");
                    break;
                case 2:
                    // Imprimir todos os vértices adjacentes a um vértice
                    System.out.println("Funcionalidade não implementada ainda.");
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