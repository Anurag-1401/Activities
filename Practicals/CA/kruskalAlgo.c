#include<stdio.h>

#define MAX 10

struct Edge{
    int src,dst,wt;
};

struct Graph{
    int V,E;
    struct Edge edges[MAX];
};

int parent[MAX];

int find(int i){
    while(parent[i] != i) i = parent[i];
    return i;
}

void unionSet(int u,int v){
    parent[u] = v;
}

void sortEdges(struct Edge edges[],int E){
    for(int i=0;i<E-1;i++){
        for(int j=0;j<E-i-1;j++){
            if(edges[j].wt > edges[j+1].wt){
                struct Edge temp = edges[j];
                edges[j] = edges[j + 1];
                edges[j + 1] = temp;
            }
        }
    }
}

void kruskal(struct Graph g){
    int minCost = 0;

    for(int i=0;i<g.V;i++){
        parent[i] = i;
    }

    sortEdges(g.edges,g.E);

    printf("\nEdges in min spanning tree\n");

    for(int i=0;i<g.E;i++){
        int u = find(g.edges[i].src);
        int v = find(g.edges[i].dst);
    
        if(u!=v){
            printf("%d --- %d wt = %d\n",
                g.edges[i].src,
                g.edges[i].dst,
                g.edges[i].wt
            );

            minCost+=g.edges[i].wt;

            unionSet(u,v);
        }
    }

    printf("\nMinimum Cost: %d",minCost);

}

int main(){
    struct Graph g;

    printf("Enter number of vertices: ");
    scanf("%d", &g.V);

    printf("Enter number of edges: ");
    scanf("%d", &g.E);

    printf("\nEnter edges (source destination weight):\n");

    for (int i = 0; i < g.E; i++) {
        scanf("%d %d %d",
              &g.edges[i].src,
              &g.edges[i].dst,
              &g.edges[i].wt);
    }

    kruskal(g);

    return 0;
}