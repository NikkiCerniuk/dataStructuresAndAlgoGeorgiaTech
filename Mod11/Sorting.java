import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Your implementation of various divide & conquer sorting algorithms.
 */

public class Sorting {

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(n log n)
     * And a best case running time of: O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: You may need to create a helper method that merges two arrays
     * back into the original T[] array. If two data are equal when merging,
     * think about which subarray you should pull from first.
     *
     * You may assume that the passed in array and comparator are both valid
     * and will not be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array to be sorted.
     * @param comparator The Comparator used to compare the data in arr.
     */
   
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if(arr.length <= 1){
            return;
        }
        
//base case of merge sort 
        //copy left set of elements from arr into left array 
        int midIndex = arr.length/2;


            T[] left =  (T[]) new Object[midIndex]; 
            for(int i= 0; i<midIndex;i++){
                left[i] = arr[i]; //recursive call for left side 
            }

            //copy right set of element from arr into right array
            T[] right = (T[]) new Object[arr.length-midIndex]; 
            for (int j = midIndex; j<arr.length;j++){
                right[j - midIndex] = arr[j]; 
            }
            mergeSort(left, comparator);
            mergeSort(right, comparator); //recursive call for right side 
            merge(left, right, arr, comparator);
    
//merging the two subarrays when there are items to compare
           // i = index zero of left subarray
           // j = index zero of right subarray 

}

//merge sort helper method 
 private static <T> void merge(T[] left, T[] right, T[] arr, Comparator<T> comparator) {
int i = 0;  
int j = 0;
int k = 0;

while (i< left.length && j<right.length){ //while the left & right subarrays have not been reached
if (comparator.compare(left[i],right[j]) <=0){//if left is smaller than right. Non strict inequality ensures stability 
	arr[k] = left[i]; //we override the data in the larger array 
	i++;
}
else{
arr[k] = right[j]; //if left is not smaller than right then right needs to be put back into the larger array 
j++;
}
k++;
}   

//Emptying one of the subarrays once the end of the other subarray has been reached: 
        //executes if the right subarray has been emptied first: (no need for more comparisions)

while (i<left.length){
arr[k] = left[i];
i++;
k++;
}
      //executes if the left subarray has been emptied first: (no need for more comparisions)
while (j<right.length){
arr[k] = right[j];
j++;
k++;
}
/*
Base case of merge sort: 
Splitting part of the algorithm: 
Smallest array is of length one and is considered sorted. Return from method in this instance 
Mid index = array length / 2
Copy data from left half of the array into a new subarray 
Array [0 through mid index -1]
Copy the data from the right half of the data into a new subarray 
Array [mid index (array.length -1)] //this gets the extra data into the right sub array 
merge(left) //recursive 
merge(right) //recursive 
*/
 }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(kn)
     * And a best case running time of: O(kn)
     *
     * Feel free to make an initial O(n) passthrough of the array to
     * determine k, the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * You may use an ArrayList or LinkedList if you wish, but it should only
     * be used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with merge sort. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * You may assume that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     */
    public static void lsdRadixSort(int[] arr) {
          if(arr.length <= 1){
            return; //if the array is of length one, it is already sorted. just return 
        }
   //create buckets for -9. to 9 (inclusive) 
            LinkedList<Integer>[] buckets = (LinkedList<Integer>[]) new LinkedList[19]; 
        
        for (int i = 0; i<buckets.length;i++){
            buckets[i] = new LinkedList<Integer>(); //creates a new linked list off each bucket so that it can hold multiple numbers 
        }

//figuring out what number has the max number of digits and how many it has
        int highestMagnitudeValue = findHighestMagnitude(arr);
        int k = 0;
        if (highestMagnitudeValue == 0) { // Handle case where array only contains 0
            k = 1; // 0 has one digit
        } else {
            k = (int) (Math.log10(Math.abs((long)highestMagnitudeValue)) + 1); //finds how many digits the num with the most digits has
            }// adds all the numbers to their respective buckets 

            int currentDivisor = 1;
            for(int pass =0; pass<k;pass++){//repeat for all the sig digits available in the longest number
                for(int number : arr){//for each number in array we want to put them into their respective buckets

                    int digit = (number / currentDivisor) % 10; //this is how we get what digit we are on

                    buckets[digit+9].add(number); //adding that number to its particular bucket
                }

                //readd to the array use a while loop to skip over empty buckets 
                //remove the data from buckets in the order that they were placed into the buckets 

                int arrIndex = 0;
                for (LinkedList<Integer> bucket : buckets){
                    while (!bucket.isEmpty()){
                    arr[arrIndex] = bucket.removeFirst(); //deque from the front
                    arrIndex++;
                    }
                }

                currentDivisor *= 10; //increase the current divisor by 10 with each pass 
            }

    }

    //helper method for radix sort
        private static int findHighestMagnitude(int[] arr) {

        int highestMagnitudeValue = arr[0]; // initalizes the largest mag value to be at the first element in the array 
        // Use long for maxAbsolute to safely handle Math.abs(Integer.MIN_VALUE)
        long maxAbsolute = Math.abs((long) arr[0]);

        for (int i = 1; i < arr.length; i++) {
            long currentAbsolute = Math.abs((long) arr[i]); 
            if (currentAbsolute > maxAbsolute) {
                maxAbsolute = currentAbsolute;
                highestMagnitudeValue = arr[i];
            }
        }
        return highestMagnitudeValue;


        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    }
}
