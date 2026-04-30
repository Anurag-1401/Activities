#include <stdio.h>
#include <time.h>

double linearSearch(int row, int col, int arr[row][col], int num, int repetitions) {
    int found;
    clock_t start = clock();

    for(int r = 0; r < repetitions; r++) {
        found = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(arr[i][j] == num) { found = 1; break; }
            }
            if(found) break;
        }
    }

    clock_t end = clock();
    double total_ns = (double)(end - start) * 1e9 / CLOCKS_PER_SEC;
    return total_ns / repetitions;
}

double binarySearch(int row, int col, int arr[row][col], int num, int repetitions) {
    int l, r, found;
    clock_t start = clock();

    for(int rcount = 0; rcount < repetitions; rcount++) {
        l = 0;
        r = row*col - 1;
        found = 0;
        while(l <= r) {
            int mid = l + (r - l)/2;
            int val = arr[mid/col][mid%col];
            if(val == num) { found = 1; break; }
            else if(val < num) l = mid + 1;
            else r = mid - 1;
        }
    }

    clock_t end = clock();
    double total_ns = (double)(end - start) * 1e9 / CLOCKS_PER_SEC;
    return total_ns / repetitions;
}

int main() {
    int arr[3][3] = { {1,2,3}, {4,5,6}, {7,8,9} };
    int row = sizeof(arr)/sizeof(arr[0]);
    int col = sizeof(arr[0])/sizeof(arr[0][0]);
    int repetitions = 1000000;

    double t_linear = linearSearch(row, col, arr, 7, repetitions);
    double t_binary = binarySearch(row, col, arr, 7, repetitions);

    printf("Average linear search time: %.2f ns\n", t_linear);
    printf("Average binary search time: %.2f ns\n", t_binary);
}
