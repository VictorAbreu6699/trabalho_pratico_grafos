package trabalho_grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class GraphAdjacencyList implements Graph{	
	
	private Map<Integer, GraphListAdjacencyNode> graph;
	private int numVertex; 
	
	public GraphAdjacencyList(int numVertex) {
		this.graph = new HashMap<>();
		this.numVertex = numVertex;
	}	

	@Override
	public String getRepresentationName() {
		return "Lista de Adjacência";
	}

	public Map<Integer, GraphListAdjacencyNode> getGraph() {
		return this.graph;
	}

	@Override
	public String getRepresentationString() {
		String graphString = "";
		
		for (int i = 0; i < this.numVertex; i++) {			
            graphString += "Vertice " + i + ":";
            if (this.graph.containsKey(i)) {
            	GraphListAdjacencyNode node = this.graph.get(i);
            	while (node != null) {
            		graphString += " -> " + node.getVertex() + "(" + node.getWeight() + ")";
            		node = node.getSuccessor();
            	}            	
            }
            graphString += "\n";   
		}
        
        return graphString;
	}

	@Override
	public void setEdge(int vertexOut, int vertexIn, int weight) {		
		GraphListAdjacencyNode newNode = new GraphListAdjacencyNode(vertexIn, weight);
        if (!this.graph.containsKey(vertexOut)) {
        	this.graph.put(vertexOut, newNode);
        } else {
            GraphListAdjacencyNode current = this.graph.get(vertexOut);
            while (current.getSuccessor() != null) {
                current = current.getSuccessor();
            }
            current.setSuccessor(newNode);
        }
	}
	
	@Override
	public boolean hasVertex(int vertex) {		
		return vertex >= 0 && vertex < this.numVertex;
	}
	
	@Override
	public boolean hasEdge(int vertexOut, int vertexIn) {
	    if (this.graph.containsKey(vertexOut)) {
	        GraphListAdjacencyNode current = this.graph.get(vertexOut);
	        while (current != null) {
	            if (current.getVertex() == vertexIn) {
	                return true;
	            }
	            current = current.getSuccessor();
	        }
	    }
	    return false;
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
	    for (int i = 0; i < this.numVertex; i++) {
	    	GraphListAdjacencyNode current = this.graph.get(i);
	    	while (current != null) {
	    		if(i == vertexOut && current.getVertex() != vertexIn) {
	    			result += "(" + vertexOut + " -> " + current.getVertex() + ", peso: " + current.getWeight() + ") ";	
	    		}	    		
	    		else if(i == vertexIn && current.getVertex() != vertexOut) {
	    			result += "(" + vertexIn + " -> " + current.getVertex() + ", peso: " + current.getWeight() + ") ";	
	    		}
	    		else if(i != vertexIn && i != vertexOut && (current.getVertex() == vertexOut || current.getVertex() == vertexIn)) {	    			
    				result += "(" + i + " -> " + current.getVertex() + ", peso: " + current.getWeight() + ") ";	    			
    			}	    		
	            
	            current = current.getSuccessor();
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
	    for (int i = 0; i < this.numVertex; i++) {
	    	GraphListAdjacencyNode current = this.graph.get(i);
	    	while (current != null) {
	    		if(i == vertex && current.getVertex() != vertex) {
	    			result += "(" + current.getVertex() +") ";	
	    		}
	    		else if(i != vertex && current.getVertex() == vertex) {	    			
	    			result += "(" + i +") ";	    			
    			}
	            
	            current = current.getSuccessor();
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
	    for (int i = 0; i < this.numVertex; i++) {
	    	GraphListAdjacencyNode current = this.graph.get(i);
	    	while (current != null) {
	    		if(i == vertex && current.getVertex() != vertex) {
	    			result += "(" + i + " -> " + current.getVertex() + ", peso: " + current.getWeight() + ") ";
	    		}
	    		else if(i != vertex && current.getVertex() == vertex) {	   
	    			result += "(" + i + " -> " + current.getVertex() + ", peso: " + current.getWeight() + ") ";
    			}
	            
	            current = current.getSuccessor();
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
		
		for (int i = 0; i < this.numVertex; i++) {
	    	GraphListAdjacencyNode current = this.graph.get(i);
	    	while (current != null) {
	    		if(i == vertex && current.getVertex() != vertex) {
	    			degree++;	
	    		}
	    		else if(i != vertex && current.getVertex() == vertex) {	    			
	    			degree++;	    			
    			}
	            
	            current = current.getSuccessor();
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
		if (this.graph.containsKey(vertexOut)) {
	        GraphListAdjacencyNode current = this.graph.get(vertexOut);
	        while (current != null) {
	            if (current.getVertex() == vertexIn) {
	                current.setWeight(weight);
	            }
	            current = current.getSuccessor();
	        }
	    }		
	}

	@Override
	public void replaceVertex(int vertex, int vertexB) {
		GraphListAdjacencyNode tempVertex = this.graph.get(vertex);
	    GraphListAdjacencyNode tempVertexB = this.graph.get(vertexB);
	    // Substitui indice dos vertices da lista
	    this.graph.put(vertex, tempVertexB);
	    this.graph.put(vertexB, tempVertex);
    	
	    // Substitui os vertices que podem estar em vertices diferentes dos informados.
	    for (int i = 0; i < this.numVertex; i++) {
	        if (i == vertex || i == vertexB) continue;
	        
	        GraphListAdjacencyNode current = this.graph.get(i);
	        while (current != null) {
	            if (current.getVertex() == vertex) {
	                current.setVertex(vertexB);
	            } else if (current.getVertex() == vertexB) {
	                current.setVertex(vertex);
	            }
	            current = current.getSuccessor();
	        }
	    }
		
	}

	@Override
	public String buscaEmLargura(int startVertex) {

	    // Array para armazenar os vértices visitados
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
	    queue.add(startVertex);
	    
	    // Marca o vértice inicial como visitado
	    visited[startVertex] = true;
	    
	    // Define o nível do vértice inicial como 0 (raiz da árvore de busca)
	    level[startVertex] = 0;

	    // Enquanto houver vértices na fila, executa
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
	                
	                // Adiciona o vizinho na fila
	                queue.add(neighbor);
	                
	                // Define o nível do vizinho (nível do vértice atual + 1)
	                level[neighbor] = level[vertex] + 1;
	                
	                // Define o predecessor do vizinho como o vértice atual
	                predecessor[neighbor] = vertex;
	            }
	        }
	    }

	    String result = "Vértice | Nível | Predecessor \n";	    
	    for (int i = 0; i < numVertex; i++) {
	        result += String.format("%7d | %5d | %11d%n", i, level[i], predecessor[i]);
	    }

	    return result;
	}

	private List<Integer> getNeighbors(int vertex) {
	    // Lista para armazenar os vizinhos do vértice
	    List<Integer> neighbors = new ArrayList<>();
	    
	    // Verifica se o vértice atual tem vizinhos
	    if (this.graph.containsKey(vertex)) {
	        // Obtém o primeiro vizinho do vertice
	        GraphListAdjacencyNode current = this.graph.get(vertex);
	        
	        while (current != null) {
	            // Adiciona o vizinho à lista
	            neighbors.add(current.getVertex());	            
	            current = current.getSuccessor();
	        }
	    }

	    return neighbors;
	}

}
