#include <stdio.h>

#define MAX 20
#define INF 999

int main() {

    int n;

    printf("Enter number of vertices: ");
    scanf("%d", &n);

    int cost[n][n];
    int visited[MAX] = {0};
    int mincost = 0;

    printf("Enter cost matrix:\n");

    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            scanf("%d", &cost[i][j]);

            if(cost[i][j] == 0)
                cost[i][j] = INF;
        }
    }

    visited[0] = 1;

    printf("\nEdges in Minimum Spanning Tree:\n");

    for(int k = 0; k < n-1; k++){

        int min = INF;
        int a = -1, b = -1;

        for(int i = 0; i < n; i++){
            if(visited[i]){
                for(int j = 0; j < n; j++){
                    if(!visited[j] && cost[i][j] < min){
                        min = cost[i][j];
                        a = i;
                        b = j;
                    }
                }
            }
        }

        if(a != -1 && b != -1){
            printf("%d -- %d  weight = %d\n", a, b, min);
            mincost += min;
            visited[b] = 1;
        }
    }

    printf("\nMinimum Cost = %d\n", mincost);

    return 0;
}