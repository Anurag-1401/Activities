#include<stdio.h>

struct res {
    int max;
    int min;
    int compareCount;
};


struct res findMaxMin(int arr[],int size){
    struct res r;
    r.max = arr[0],r.min = arr[0],r.compareCount = 0;

    for(int i=1;i<size;i++){
        r.compareCount++;
        if(arr[i]>r.max) {
            r.max=arr[i];
        }
        else {
            r.compareCount++;
            if(arr[i]<r.min) {
            r.min = arr[i];
            }
        }    
    }

    return r;
}

int main(){
    int arr[] = {1,4,9,7,3,2,5,0,6,8};
    int arr1[] = {0,1,2,3,4,5,6,7,8,9};
    int arr2[] = {9,8,7,6,5,4,3,2,1,0};

    struct res r = findMaxMin(arr,10);
    struct res r1 = findMaxMin(arr1,10);
    struct res r2 = findMaxMin(arr2,10);

    printf("max: %d\n min: %d\n Number of Comparisons(avg): %d\n",r.max,r.min,r.compareCount);
    printf("max: %d\n min: %d\n Number of Comparisons(best): %d\n",r1.max,r1.min,r1.compareCount);
    printf("max: %d\n min: %d\n Number of Comparisons(worst): %d",r2.max,r2.min,r2.compareCount);
}