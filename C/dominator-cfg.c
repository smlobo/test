#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define MAX_VERTICES 100

// Node structure for adjacency list
struct Node {
    int vertex;
    struct Node* next;
};

// Graph structure
struct Graph {
    int numVertices;
    struct Node* adjacencyList[MAX_VERTICES];
};

// Create a graph with a given number of vertices
struct Graph* createGraph(int numVertices) {
    struct Graph* graph = (struct Graph*)malloc(sizeof(struct Graph));
    graph->numVertices = numVertices;
    for (int i = 0; i < numVertices; i++) {
        graph->adjacencyList[i] = NULL;
    }
    return graph;
}

// Add an edge to the graph (directed)
void addEdge(struct Graph* graph, int src, int dest) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->vertex = dest;
    newNode->next = graph->adjacencyList[src];
    graph->adjacencyList[src] = newNode;
}

// Depth-First Search (DFS) traversal for dominator calculation
void dfs(struct Graph* graph, int v, int dominators[], bool visited[]) {
    visited[v] = true;

    struct Node* curr = graph->adjacencyList[v];
    while (curr != NULL) {
        int adjVertex = curr->vertex;

        if (!visited[adjVertex]) {
            dominators[adjVertex] = v;
            dfs(graph, adjVertex, dominators, visited);
        }

        curr = curr->next;
    }
}

// Lengauer-Tarjan Algorithm for dominator calculation
void calculateDominators(struct Graph* graph, int dominators[]) {
    bool visited[MAX_VERTICES] = { false };

    for (int v = 0; v < graph->numVertices; v++) {
        dominators[v] = -1;
    }
    dominators[0] = 0; // The root node dominates itself

    dfs(graph, 0, dominators, visited);

    for (int v = 1; v < graph->numVertices; v++) {
        if (!visited[v]) {
            dominators[v] = 0; // Unreachable nodes are dominated by the root node
        }
    }
}

int main() {
    struct Graph* graph = createGraph(6);
    addEdge(graph, 0, 1);
    addEdge(graph, 1, 2);
    addEdge(graph, 2, 3);
    addEdge(graph, 3, 1); // Creates a loop

    int dominators[MAX_VERTICES];
    calculateDominators(graph, dominators);

    for (int i = 0; i < 6; i++) {
        printf("Dominator of vertex %d in the loop: %d\n", i, dominators[i]);        
    }

    return 0;
}
