#include<stdio.h>


int bubbleSort(int arr[],int size){
    int c  = 0; 
    int swapped = 0;
    
    for(int i=0;i<size-1;i++){
        for(int j=0;j<size-1-i;j++){
            if(arr[j]>arr[j+1]){
                c++;
                int t = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = t;
                swapped = 1;
            }
        }
        if(swapped == 0) break;
    }
    return c;
}

int main(){
    int arr1[] = {1,2,3,4,5,7,6};
    int arr2[] = {1,2,3,4,5};
    int arr3[] = {5,4,3,2,1};

    int size1 = sizeof(arr1)/sizeof(arr1[0]);
    int size2 = sizeof(arr2)/sizeof(arr2[0]);
    int size3 = sizeof(arr3)/sizeof(arr3[0]);

    int c1 = bubbleSort(arr1,size1);
    int c2 = bubbleSort(arr2,size2);
    int c3 = bubbleSort(arr3,size3);

    printf("c1: %d,c2: %d,c3: %d",c1,c2,c3);
}