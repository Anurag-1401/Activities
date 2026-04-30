#include <stdio.h>

int mergeSortCallCnt = 0,mergeCallCnt=0,mergeCmp = 0;

void merge(int arr[],int s,int m,int e){
    mergeCallCnt++;
    int i,j,k;
    int n1 = m - s + 1;
    int n2 = e - m;

    int L[n1], R[n2];

    for(i = 0; i < n1; i++) L[i] = arr[s + i];
    for(j=0;j<n2;j++) R[j] = arr[m + 1 + j];

    i = 0; j = 0; k = s;
    while(i < n1 && j < n2){
        if(L[i] <= R[j])arr[k] = L[i++];
        else arr[k] = R[j++];
        mergeCmp++;
        k++;
    }

    while(i < n1) arr[k++] = L[i++];

    while(j < n2) arr[k++] = R[j++];
}

void mergeSort(int arr[], int left, int right) {
    mergeSortCallCnt++;
    if (left < right) {
        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }
}

int main(){
    int arr[] = {38, 27, 43, 3, 1, 82, 10,2};
    int arr_size = sizeof(arr) / sizeof(arr[0]);

    mergeSort(arr, 0, arr_size - 1);

    printf("Sorted array: \n");
    for (int i = 0; i < arr_size; i++) printf("%d ", arr[i]);

    printf("\nCompare and calls values: \n");
    printf("MergeSort fun call: %d\n",mergeSortCallCnt);
    printf("Merge fun call: %d\n",mergeCallCnt);
    printf("Comparisons cnt: %d\n",mergeCmp);
    return 0;
}