import java.util.Comparator;



/**

* Your implementation of various iterative sorting algorithms.

*/

public class Sorting {



/**

* Implement bubble sort.

*

* It should be:

* in-place

* stable

* adaptive

*

* Have a worst case running time of: O(n^2)

* And a best case running time of: O(n)

*

* NOTE: You should implement bubble sort with the last swap optimization.

*

* You may assume that the passed in array and comparator

* are both valid and will never be null.

*

* @param <T> Data type to sort.

* @param arr The array that must be sorted after the method runs.

* @param comparator The Comparator used to compare the data in arr.

*/




public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
if (arr.length<= 1){ //handles edge case where array length is one or zero
return;
}

int lastSwappedIndex = arr.length -1;
T tempValue;

boolean swapped;
do{
int currentLastPassSwap = 0; //makes sure we dont recheck the sorted portion of the array
swapped = false; //handles termination rule where no swaps took place so you can terminate early
for (int current = 0; current < lastSwappedIndex; current++) {

if (comparator.compare(arr[current], arr[current+1]) > 0){
tempValue = arr[current+1];
arr[current+1] = arr[current];
arr[current] = tempValue;
swapped = true; //if true, does not terminate early
currentLastPassSwap = current;

}

}

lastSwappedIndex = currentLastPassSwap;

}while(swapped && lastSwappedIndex > 0); 



}

/**

* Implement selection sort.

*

* It should be:

* in-place

* unstable

* not adaptive

*

* Have a worst case running time of: O(n^2)

* And a best case running time of: O(n^2)

*

* You may assume that the passed in array and comparator

* are both valid and will never be null.

*

* @param <T> Data type to sort.

* @param arr The array that must be sorted after the method runs.

* @param comparator The Comparator used to compare the data in arr.

*/

public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {


if (arr.length<= 1){ //handles edge case where array length is one or zero

return;

}



int unsortedSearch = arr.length-1;

while (unsortedSearch >= 1){ //goes from a max to a min value

//updates max index as we progess through the array left to right

int maxIdx=0;

for(int i=1; i<=unsortedSearch;i++){

if (comparator.compare(arr[i], arr[maxIdx]) > 0){

maxIdx= i; //reassigns i to maxIdx if i value is bigger

}

}

T tempValue = arr[unsortedSearch];

arr[unsortedSearch] = arr[maxIdx];

arr[maxIdx] = tempValue;



unsortedSearch--;

}



// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

}



/**

* Implement insertion sort.

*

* It should be:

* in-place

* stable

* adaptive

*

* Have a worst case running time of: O(n^2)

* And a best case running time of: O(n)

*

* You may assume that the passed in array and comparator

* are both valid and will never be null.

*

* @param <T> Data type to sort.

* @param arr The array that must be sorted after the method runs.

* @param comparator The Comparator used to compare the data in arr.

*/

public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {

if (arr.length<= 1){ //handles edge case where array length is one or zero



return;

}

for (int i = 1; i < arr.length; i++) { //i traverses the unsorted part while i is less than the length of the array (while we still have elements to sort )


T currentElement = arr[i]; //associates i with the actual array



int j = i - 1; // this is the sorted part



while (j >= 0 && comparator.compare(arr[j], currentElement) > 0) { // J is the sorted part. it is saying while there is still a sorted part left (j is bigger htan zero) and the sorted is bigger than the element to add, do this loop

arr[j + 1] = arr[j]; //3 sorted and 2 unsorted

j--; // Move to the next element on the left in the sorted part

}

arr[j + 1] = currentElement;

}





}

}

