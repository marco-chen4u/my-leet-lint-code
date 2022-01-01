/***
* LintCode 1457. Search Subarray
Given an array arr and a nonnegative integer k, 
you need to find a continuous array from this array so that the sum of this array is k. 
Output the length of this array. 

If there are multiple such substrings, return the one with the minimum ending position; 
if there are multiple answers, return the one with the minimum starting position. 
If no such subarray is found, -1 is returned.

Note: 
The length of the array does not exceed 10^6, 
each number in the array is less than or equal to 10^6, 
and k does not exceed 10^6.


Example 1:
    Input：arr=[1,2,3,4,5] ，k=5
    Output：2
    Explanation:
        In this array, the earliest contiguous substring is [2,3].
        
Example 2:
    Input：arr=[3,5,7,10,2] ，k=12
    Output：2
    Explanation:
        In this array, the earliest consecutive concatenated substrings with a sum of 12 are [5,7].
***/
//version-1:prefix subarray sum
public class Solution {
    /**
     * @param arr: The array 
     * @param k: the sum 
     * @return: The length of the array
     */
    public int searchSubarray(int[] arr, int k) {
        int result = -1;
        
        if (arr == null || arr.length == 0) {
            return result;// default value
        }

        int size = arr.length;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        sum = 0;
        for (int i = 0; i < size; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k) && i >= map.get(sum - k)) {
                result = i - map.get(sum - k);
                break;
            }

            map.putIfAbsent(sum, i);
        }

        return result;
    }
}

// version-2: prefix subarray sum
public class Solution {
    /**
     * @param arr: The array 
     * @param k: the sum 
     * @return: The length of the array
     */
    public int searchSubarray(int[] arr, int k) {
        // intialize
        int result = -1;
        int sum = 0;

        // corner case
        if (arr == null || arr.length == 0) {
            return result;
        }

        // normal case
        int size = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = size - 1; i >= 0; i--) {
            sum += arr[i];
            map.put(sum, i);
        }

        sum = 0;
        for (int i = size - 1; i >= 0; i--) {
            
            if (map.containsKey(sum + k) && i >= map.get(sum + k)) {
                result = i - map.get(sum + k) + 1;
            }

            sum += arr[i];

            map.putIfAbsent(sum, i);
        }

        return result;
    }
}
