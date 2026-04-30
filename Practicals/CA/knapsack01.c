#include <stdio.h>

int max(int a, int b){
    return (a > b) ? a : b;
}

int main(){

    int n, W;

    printf("Enter number of items: ");
    scanf("%d", &n);

    int weight[n], value[n];

    printf("Enter weights:\n");
    for(int i=0;i<n;i++)
        scanf("%d", &weight[i]);

    printf("Enter values:\n");
    for(int i=0;i<n;i++)
        scanf("%d", &value[i]);

    printf("Enter capacity: ");
    scanf("%d", &W);

    int dp[W+1];

    for(int i=0;i<=W;i++)
        dp[i] = 0;

    for(int i=0;i<n;i++){
        for(int w = W; w >= weight[i]; w--){
            dp[w] = max(dp[w],
                        value[i] + dp[w - weight[i]]);
        }
    }

    printf("Maximum value = %d\n", dp[W]);

    return 0;
}