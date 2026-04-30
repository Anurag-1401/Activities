#include<stdio.h>

void swap(int* a,int* b){
    int t = *a;
    *a = *b;
    *b = t;
}

int heapifyCall = 0;

void heapify(int arr[],int i,int n){
    heapifyCall++;

    int parent = i;
    int left = 2*i + 1;
    int right = 2*i + 2;

    if(left < n && arr[left] > arr[parent]) parent = left;
    if(right < n && arr[right] > arr[parent]) parent = right;

    if(parent != i){
        swap(&arr[parent],&arr[i]);
        heapify(arr,parent,n);
    }
}

void heapSort(int arr[],int n){
    for(int i =n/2-1;i>=0;i--){
        heapify(arr,i,n);
    }

    for(int i=n-1;i>0;i--){
        swap(&arr[0],&arr[i]);

        heapify(arr,0,i);
    }
}

int main(){
    int n = 0;
    printf("Enter no of ele to sort: ");
    scanf("%d",&n);

    int arr[n];

    for(int i=0;i<n;i++){
        printf("Enter ele: ");
        scanf("%d",&arr[i]);
    }

    heapSort(arr,n);

    printf("Sorted Array\n");
    for(int i=0;i<n;i++){
        printf("%d ",arr[i]);
    }

    printf("\nheapify call count %d",heapifyCall);
}
