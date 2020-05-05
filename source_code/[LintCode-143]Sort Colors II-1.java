/***
* LintCode 143. Sort Colors II (3 pointers)
Given an array of n objects with k different colors (numbered from 1 to k), 
sort them so that objects of the same color are adjacent, with the colors in the order 1, 2, ... k.

Example
	Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4].

Challenge
	A rather straight forward solution is a two-pass algorithm using counting sort. 
	That will cost O(k) extra memory. Can you do it without using extra memory?

Notice
	You are not suppose to use the library's sort function for this problem.
	k <= n
***/
// version-1: rainbow sort(= merge sort + quick sort), O(nlogk), the best algorithm based on comparing
public class Solution {
    
    // helper methods
    private void swap(int[] colors, int i, int j) {
        int temp = colors[i];
        colors[i] = colors[j];
        colors[j] = temp;
    }
    
    private void rainbowSort(int[] colors, int start, int end, int colorFrom, int colorTo) {
        // check corner cases
        if (start == end || colorFrom == colorTo) {
            return;
        }
        
        int left= start;
        int right = end;
        int colorPivot = colorFrom + (colorTo - colorFrom) / 2;
        
        while (left <= right) {
            while (left <= right && colors[left] <= colorPivot) {
                left++;
            }
            
            while (left <= right && colors[right] > colorPivot) {
                right--;
            }
            
            if (left <= right) {
                swap(colors, left, right);
                
                left++;
                right--;
            }
        }
        
        rainbowSort(colors, start, right, colorFrom, colorPivot);
        rainbowSort(colors, left, end, colorPivot + 1, colorTo);
    }    
    
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        // check corner case
        if (colors == null || colors.length <= 1 || k <= 0) {
            return;
        }
        
        rainbowSort(colors, 0, colors.length - 1, 1, k);
    }
}