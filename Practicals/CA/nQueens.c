#include <stdio.h>

#define MAX 20

int board[MAX][MAX];
int N;

void printSolution() {
    printf("\nSolution:\n");
    for(int i = 0; i < N; i++){
        for(int j = 0; j < N; j++){
            printf("%d ", board[i][j]);
        }
        printf("\n");
    }
}

int isSafe(int row, int col) {

    for(int i = 0; i < row; i++){
        if(board[i][col])
            return 0;
    }

    for(int i=row-1, j=col-1; i>=0 && j>=0; i--, j--){
        if(board[i][j])
            return 0;
    }

    for(int i=row-1, j=col+1; i>=0 && j<N; i--, j++){
        if(board[i][j])
            return 0;
    }

    return 1;
}

int solveNQueen(int row) {

    if(row == N){
        printSolution();
        return 1;
    }

    for(int col = 0; col < N; col++){

        if(isSafe(row, col)){

            board[row][col] = 1;

            if(solveNQueen(row + 1))
                return 1;

            board[row][col] = 0;
        }
    }

    return 0;
}

int main(){

    printf("Enter value of N: ");
    scanf("%d", &N);

    if(N < 1 || N > MAX){
        printf("Invalid input! N should be between 1 and %d\n", MAX);
        return 0;
    }

    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            board[i][j] = 0;
        }
    }

    if(!solveNQueen(0)){
        printf("No solution exists for N = %d\n", N);
    }

    return 0;
} 