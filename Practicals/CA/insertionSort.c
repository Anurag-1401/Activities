#include<stdio.h>

int insertionSort(int arr[],int size){
    int  count = 0;

    for(int i=1;i<size;i++){
        int key = arr[i];
        int j = i-1;

        while(j>=0 && arr[j]>key){
            arr[j+1] = arr[j];
            j--;
            count++;
        }
        arr[j+1] = key;
    }
    return count;
}

int main(){

    int arr1[] = {5,4,3,1,2};
    int arr2[] = {1,2,3,4,5};
    int arr3[] = {5,4,3,2,1};

    int size1 = sizeof(arr1)/sizeof(arr1[0]);
    int size2 = sizeof(arr2)/sizeof(arr2[0]);
    int size3 = sizeof(arr3)/sizeof(arr3[0]);

    int count1 = insertionSort(arr1,size1);
    int count2 = insertionSort(arr2,size2);
    int count3 = insertionSort(arr3,size3);

    printf("Count of 1st: %d\nCount of 2nd: %d\nCount of 3rd: %d\n",count1,count2,count3);
}