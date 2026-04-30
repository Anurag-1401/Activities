#include<stdio.h>

struct res {
    int c;
    int swapCount;
};

struct res bubbleSort(int arr[],int size){
    struct res r;
    r.c  = 0; 
    int swapped = 0;
    r.swapCount = 0;
    
    for(int i=0;i<size-1;i++){
        for(int j=0;j<size-1-i;j++){
            if(arr[j]>arr[j+1]){
                r.c++;
                r.swapCount++;
                int t = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = t;
                swapped = 1;
            }
        }
        if(swapped == 0) break;
    }
    return r;
}

struct res selectionSort(int arr[],int size){
    struct res r;
    r.c  = 0;    
    r.swapCount = 0;

    for(int i=0;i<size-1;i++){
        int minIndex = i;
        for(int j=i+1;j<size;j++){
            if(arr[j]<arr[minIndex]){
                r.c++;
                minIndex = j;
            }
        }
        if(minIndex != i){
            r.swapCount++;
            int t = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = t;
        }
    }
    return r;
}

int main(){
    int barr1[] = {1,2,3,4,5,7,6};
    int barr2[] = {1,2,3,4,5};
    int barr3[] = {5,4,3,2,1,0};

    int bsize1 = sizeof(barr1)/sizeof(barr1[0]);
    int bsize2 = sizeof(barr2)/sizeof(barr2[0]);
    int bsize3 = sizeof(barr3)/sizeof(barr3[0]);
        
    struct res bc1 = bubbleSort(barr1,bsize1);
    struct res bc2 = bubbleSort(barr2,bsize2);
    struct res bc3 = bubbleSort(barr3,bsize3);

    int sarr1[] = {1,2,3,4,5,7,6}; 
    int sarr2[] = {1,2,3,4,5};
    int sarr3[] = {5,4,3,2,1,0,-1};

    int ssize1 = sizeof(sarr1)/sizeof(sarr1[0]);
    int ssize2 = sizeof(sarr2)/sizeof(sarr2[0]);
    int ssize3 = sizeof(sarr3)/sizeof(sarr3[0]);

    struct res sc1 = selectionSort(sarr1,ssize1);
    struct res sc2 = selectionSort(sarr2,ssize2);
    struct res sc3 = selectionSort(sarr3,ssize3);

    printf("bc1: %d,%d,bc2: %d,%d,bc3: %d,%d",bc1.c,bc1.swapCount,bc2.c,bc2.swapCount,bc3.c,bc3.swapCount);
    printf("\nsc1: %d,%d,sc2: %d,%d,sc3: %d,%d",sc1.c,sc1.swapCount,sc2.c,sc2.swapCount,sc3.c,sc3.swapCount);
}