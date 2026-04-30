#include<stdio.h>

void fibo(int n,int fib[]){
    fib[0] = 0;
    if (n > 1) {
        fib[1] = 1;
    }

    for (int i = 2; i < n; i++) {
        fib[i] = fib[i-1] + fib[i-2];
    }
}

int main(){
    int n;
    printf("Enter the number of terms: ");
    scanf("%d", &n);

    int fib[n];

    fibo(n,fib);

    printf("Fibonacci series: ");
    for (int i = 0; i < n; i++) {
        printf("%d ", fib[i]);
    }
    printf("\n");

    return 0;
}