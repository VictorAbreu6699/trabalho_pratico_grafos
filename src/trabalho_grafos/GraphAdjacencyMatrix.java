package trabalho_grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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
            	graphString += (this.graph[i][j] != null ? this.graph[i][j] : "0") + ", ";
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
	
	@Override
	public boolean hasEdge(int vertexOut, int vertexIn) {		
		if (!hasVertex(vertexOut) && !hasVertex(vertexIn)) {
	        return false;
	    }
		
        return this.graph[vertexOut][vertexIn] != null;
	}

	@Override
	public String getAdjacentEdges(int vertexOut, int vertexIn) {
		if (!hasVertex(vertexOut)) {
	        return "Vértice de saída não encontrado.";
	    }
		
		if (!hasVertex(vertexIn)) {
	        return "Vértice de entrada não encontrado.";
	    }
		
		if (!hasEdge(vertexOut, vertexIn)) {
	        return "Aresta não encontrada.";
	    }
		
	    String result = "";
	    for (int i = 0; i < this.graph[vertexOut].length; i++) {
	        if (this.graph[vertexOut][i] != null && i != vertexIn) {
	            result += "(" + vertexOut + " -> " + i + ", peso: " + this.graph[vertexOut][i] + ") ";
	        }
	        
	        if (this.graph[i][vertexOut] != null && i != vertexIn) {
	            result += "(" + i + " -> " + vertexOut + ", peso: " + this.graph[i][vertexOut] + ") ";
	        }
	    }
	    
	    for (int i = 0; i < this.graph.length; i++) {
	        if (this.graph[vertexIn][i] != null && i != vertexOut) {
	            result += "(" + vertexIn + " -> " + i + ", peso: " + this.graph[vertexIn][i] + ") ";
	        }
	        
	        if (this.graph[i][vertexIn] != null && i != vertexOut) {
	            result += "(" + i + " -> " + vertexIn + ", peso: " + this.graph[i][vertexIn] + ") ";
	        }
	    }
	    
	    if(result.length() == 0)
	    {
	    	result = "Não foram encontradas arestas adjacentes.";
	    }

	    return result;
	}

	@Override
	public String getAdjacentVertices(int vertex) {
		if (!hasVertex(vertex)) {
	        return "Vértice não encontrado.";
	    }
		
		String result = "";
		for (int i = 0; i < this.graph.length; i++) {
	        if (this.graph[vertex][i] != null && i != vertex) {
	            result += "(" + i + ")";
	        }
	        
	        if (this.graph[i][vertex] != null && i != vertex) {
	            result += "(" + i + ") ";
	        }
	    }
		
		
		if(result.length() == 0)
	    {
	    	result = "Não foram encontrados vertices adjacentes.";
	    }

	    return result;
	}

	@Override
	public String getIncidentEdgesToVertex(int vertex) {
		if (!hasVertex(vertex)) {
	        return "Vértice não encontrado.";
	    }
		
		String result = "";
		for (int i = 0; i < this.graph.length; i++) {
	        if (this.graph[vertex][i] != null && i != vertex) {
	            result += "(" + vertex + " -> " + i + ", peso: " + this.graph[vertex][i] + ") ";
	        }
	        
	        if (this.graph[i][vertex] != null && i != vertex) {
	        	result += "(" + i + " -> " + vertex + ", peso: " + this.graph[i][vertex] + ") ";
	        }
	    }
		
		
		if(result.length() == 0)
	    {
	    	result = "Não foram encontrados vertices incidentes.";
	    }

	    return result;
	}

	@Override
	public int getDegreeVertex(int vertex) {
		int degree = 0;
		
		for (int i = 0; i < this.graph.length; i++) {
	        if (this.graph[vertex][i] != null && i != vertex) {
	        	degree++;
	        }
	        
	        if (this.graph[i][vertex] != null && i != vertex) {
	        	degree++;
	        }
	    }
		
		return degree;
	}

	@Override
	public boolean isAdjacent(int vertex, int vertexB) {
		if(hasEdge(vertex, vertexB) || hasEdge(vertexB, vertex)) {
			return true;
		}
		
		return false;
	}

	@Override
	public void alterEdgeWeight(int vertexOut, int vertexIn, int weight) {
		setEdge(vertexOut, vertexIn, weight);
	}

	@Override
	public void replaceVertex(int vertex, int vertexB) {		
		
		for (int i = 0; i < this.graph.length; i++) {
	        Integer tempOut = this.graph[vertex][i];
	        this.graph[vertex][i] = this.graph[vertexB][i];
	        this.graph[vertexB][i] = tempOut;
	    }

	    for (int i = 0; i < this.graph.length; i++) {
	        Integer tempIn = this.graph[i][vertex];
	        this.graph[i][vertex] = this.graph[i][vertexB];
	        this.graph[i][vertexB] = tempIn;
	    }
	}

	public String buscaEmLargura(int initVertex) {
		int numVertex = this.graph.length;
        // Array para rastrear os vértices visitados
        boolean[] visited = new boolean[numVertex];

        // Array para armazenar o nível de cada vértice na árvore de busca
        int[] level = new int[numVertex];

        // Array para armazenar o predecessor de cada vértice na árvore de busca
        int[] predecessor = new int[numVertex];
        
        // Inicializa todos os predecessores com -1 (nenhum predecessor)
        Arrays.fill(predecessor, -1);

        // Fila para gerenciar a ordem de visitação dos vértices
        Queue<Integer> queue = new LinkedList<>();
        
        // Adiciona o vértice inicial na fila
        queue.add(initVertex);
        
        // Marca o vértice inicial como visitado
        visited[initVertex] = true;
        
        // Define o nível do vértice inicial como 0 (raiz da árvore de busca)
        level[initVertex] = 0;

        // Loop enquanto houver vértices na fila
        while (!queue.isEmpty()) {
            // Remove o primeiro vértice da fila
            int vertex = queue.poll();
            
            // Obtém a lista de vizinhos do vértice atual
            List<Integer> neighbors = getNeighbors(vertex);
            
            // Ordena os vizinhos em ordem numérica crescente
            Collections.sort(neighbors);

            // Itera sobre cada vizinho
            for (int neighbor : neighbors) {
                // Se o vizinho não foi visitado
                if (!visited[neighbor]) {
                    // Marca o vizinho como visitado
                    visited[neighbor] = true;
                    
                    // Adiciona o vizinho na fila para processamento futuro
                    queue.add(neighbor);
                    
                    // Define o nível do vizinho (nível do vértice atual + 1)
                    level[neighbor] = level[vertex] + 1;
                    
                    // Define o predecessor do vizinho como o vértice atual
                    predecessor[neighbor] = vertex;
                }
            }
        }

        // Constrói a string de resultado
        String result = "Vértice | Nível | Predecessor \n";
        // Itera sobre todos os vértices para adicionar seus níveis e predecessores à string
        for (int i = 0; i < numVertex; i++) {
            result += String.format("%7d | %5d | %11d%n", i, level[i], predecessor[i]);
        }

        // Retorna a string de resultado
        return result;
    }

    private List<Integer> getNeighbors(int vertex) {
        // Lista para armazenar os vizinhos do vértice
        List<Integer> neighbors = new ArrayList<>();
        
        // Itera sobre todas as colunas da matriz na linha do vértice
        for (int i = 0; i < this.graph.length; i++) {
            // Se houver uma aresta entre o vértice atual e o vértice i então adiciona i à lista de vizinhos
            if (graph[vertex][i] != null) {
                neighbors.add(i);
            }
        }

        return neighbors;
    }
    
    public String buscaEmProfundidade(int startVertex) {
        int numVertex = this.graph.length;
        // Array para rastrear os vértices visitados
        boolean[] visited = new boolean[numVertex];

        // Array para armazenar o tempo de descoberta de cada vértice na árvore de busca
        int[] discoveryTime = new int[numVertex];

        // Array para armazenar o tempo de finalização de cada vértice na árvore de busca
        int[] finishTime = new int[numVertex];

        // Inicializa o tempo inicial (está sendo criado como array para poder atualizar os valores dentro das chamadas para as funções)
        int[] time = {0};

        // Array para armazenar o predecessor de cada vértice na árvore de busca
        int[] predecessor = new int[numVertex];

        // Inicializa todos os predecessores com -1 (nenhum predecessor)
        Arrays.fill(predecessor, -1);

        // Chama o método auxiliar de busca em profundidade recursivamente
        buscaEmProfundidadeAux(startVertex, visited, time, discoveryTime, finishTime, predecessor);

        String result = "Vértice | Descoberta | Finalização | Predecessor\n";
        for (int i = 0; i < numVertex; i++) {
            result += String.format("%7d | %10d | %12d | %11d%n", i, discoveryTime[i], finishTime[i], predecessor[i]);
        }

        return result;
    }

    private void buscaEmProfundidadeAux(int vertex, boolean[] visited, int[] time, int[] discoveryTime, int[] finishTime, int[] predecessor) {
        // Marca o vértice como visitado
        visited[vertex] = true;

        // Atualiza o tempo de descoberta do vértice
        discoveryTime[vertex] = ++time[0];

        // Obtém a lista de vizinhos do vértice atual e ordena em ordem numérica crescente
        List<Integer> neighbors = getNeighbors(vertex);
        Collections.sort(neighbors);

        // Itera sobre cada vizinho
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                predecessor[neighbor] = vertex;
                buscaEmProfundidadeAux(neighbor, visited, time, discoveryTime, finishTime, predecessor);
            }
        }

        // Atualiza o tempo de finalização do vértice
        finishTime[vertex] = ++time[0];
    }

    @Override
    public String dijkstra(int vertexSource, int vertexDestination) {        
    	int numVertex = this.graph.length;
        // Array para armazenar a distância mínima de source até cada vértice
        int[] dist = new int[numVertex];
        // 	Inicializa todas as distâncias como infinito
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 	A distância de source até ele mesmo é zero
        dist[vertexSource] = 0; 

        //	Array para armazenar o predecessor de cada vértice no caminho mínimo
        int[] predecessor = new int[numVertex];
        //	Inicializa todos os predecessores como -1 (nenhum predecessor)
        Arrays.fill(predecessor, -1); 

        //	Fila de prioridade para selecionar o vértice com menor distância
        PriorityQueue<GraphListAdjacencyNode> pq = new PriorityQueue<>(Comparator.comparingInt(GraphListAdjacencyNode::getWeight));
        //	Adiciona o vértice de origem com distância 0
        pq.offer(new GraphListAdjacencyNode(vertexSource, 0)); 

        // Processamento do algoritmo de Dijkstra
        while (!pq.isEmpty()) {
            GraphListAdjacencyNode current = pq.poll();
            int vertex = current.getVertex();

            // Obtém os vizinhos do vértice atual
            List<Integer> neighbors = getNeighbors(vertex);

            // Itera sobre os vizinhos
            for (int neighbor : neighbors) {
                // Obtém o peso da aresta entre u e v
                int weight = getWeight(vertex, neighbor);

                // Relaxamento da aresta
                if (dist[neighbor] > dist[vertex] + weight) {
                    dist[neighbor] = dist[vertex] + weight;
                    predecessor[neighbor] = vertex;
                    pq.offer(new GraphListAdjacencyNode(neighbor, dist[neighbor]));
                }
            }
        }
        
        // Verifica se existe um caminho entre a origem e o destino
        if (dist[vertexDestination] == Integer.MAX_VALUE) {
            return "Não existe caminho entre " + vertexSource + " e " + vertexDestination;
        }

        // Reconstrói o caminho mínimo utilizando o array de predecessores
        return buildPath(predecessor, vertexSource, vertexDestination);
    }

    private String buildPath(int[] predecessor, int source, int destination) {
        // Lista para armazenar os vértices do caminho mínimo
        List<Integer> path = new ArrayList<>();
        
        // Reconstrói o caminho do destino até a origem
        int current = destination;
        while (current != -1) {
            path.add(current);
            current = predecessor[current];
        }

        // Inverte a lista para obter o caminho da origem até o destino
        Collections.reverse(path);

        // Monta a string de caminho
        String result = "";
        for (int i = 0; i < path.size() - 1; i++) {
            int u = path.get(i);
            int v = path.get(i + 1);
            int weight = getWeight(u, v);
            result += "{" + u + " -> " + v + " (peso: " + weight + ")} -> ";
        }
        // Adiciona o último vértice sem seta
        result += path.get(path.size() - 1);

        return result;
    }
    
    private int getWeight(int vertexOut, int vertexIn) {
    	Integer weight = this.graph[vertexOut][vertexIn]; 
        
        // Caso não encontre nenhuma aresta entre a origem e o destino informados, retorna 'infinito'.
        return weight != null ? weight : Integer.MAX_VALUE; 
    }

    @Override
    public String floydWarshall() {
    	int numVertex = this.graph.length;
        // Matriz de distâncias
        Integer[][] dist = new Integer[numVertex][numVertex];
        
        // Inicializa a matriz de distâncias
        for (int i = 0; i < numVertex; i++) {
        	//	Define a distância inicial como infinita
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            //	A distância de um vértice para ele mesmo é zero
            dist[i][i] = 0; 
        }

        // Inicializa a matriz de distâncias
        for (int i = 0; i < numVertex; i++) {
            for (int j = 0; j < numVertex; j++) {
            	if(this.graph[i][j] != null) {
            		dist[i][j] = this.graph[i][j];            		
            	}
            }
        }

        // Implementação do algoritmo de Floyd-Warshall
        for (int k = 0; k < numVertex; k++) {
            for (int i = 0; i < numVertex; i++) {
                for (int j = 0; j < numVertex; j++) {
                    // Se as distâncias intermediárias não são infinitas, atualiza a distância mínima
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        String result = "Distâncias mínimas:\n";
        for (int i = 0; i < numVertex; i++) {
            for (int j = 0; j < numVertex; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                	// Usa "∞" para representar a distância infinita
                    result += "∞, "; 
                } else {
                    result += dist[i][j] + ", ";
                }
            }
            result += "\n"; 
        }

        return result;
    }

}
