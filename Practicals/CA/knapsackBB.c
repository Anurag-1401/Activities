#include <stdio.h>

#define MAX 20

struct Item {
    int weight, value;
    float ratio;
};

struct Node {
    int level, profit, weight;
    float bound;
};

float bound(struct Node u, int n, int W, struct Item items[]) {

    if(u.weight >= W)
        return 0;

    float profit_bound = u.profit;
    int j = u.level + 1;
    int totweight = u.weight;

    while(j < n && totweight + items[j].weight <= W){
        totweight += items[j].weight;
        profit_bound += items[j].value;
        j++;
    }

    if(j < n){
        profit_bound += (W - totweight) * items[j].ratio;
    }

    return profit_bound;
}

void sortItems(struct Item items[], int n){
    for(int i=0;i<n-1;i++){
        for(int j=0;j<n-i-1;j++){
            if(items[j].ratio < items[j+1].ratio){
                struct Item temp = items[j];
                items[j] = items[j+1];
                items[j+1] = temp;
            }
        }
    }
}

int knapsack(int n, int W, struct Item items[]) {

    struct Node u, v;
    int maxProfit = 0;

    struct Node Q[100];
    int front = 0, rear = 0;

    u.level = -1;
    u.profit = 0;
    u.weight = 0;
    u.bound = bound(u, n, W, items);

    Q[rear++] = u;

    while(front < rear){

        u = Q[front++];

        if(u.level == n-1)
            continue;

        v.level = u.level + 1;

        v.weight = u.weight + items[v.level].weight;
        v.profit = u.profit + items[v.level].value;

        if(v.weight <= W && v.profit > maxProfit)
            maxProfit = v.profit;

        v.bound = bound(v, n, W, items);

        if(v.bound > maxProfit)
            Q[rear++] = v;

        v.weight = u.weight;
        v.profit = u.profit;
        v.bound = bound(v, n, W, items);

        if(v.bound > maxProfit)
            Q[rear++] = v;
    }

    return maxProfit;
}

int main(){

    int n, W;

    printf("Enter number of items: ");
    scanf("%d", &n);

    struct Item items[MAX];

    printf("Enter weights:\n");
    for(int i=0;i<n;i++)
        scanf("%d", &items[i].weight);

    printf("Enter values:\n");
    for(int i=0;i<n;i++)
        scanf("%d", &items[i].value);

    printf("Enter capacity: ");
    scanf("%d", &W);

    for(int i=0;i<n;i++){
        items[i].ratio = (float)items[i].value / items[i].weight;
    }

    sortItems(items, n);

    int result = knapsack(n, W, items);

    printf("Maximum Profit = %d\n", result);

    return 0;
}