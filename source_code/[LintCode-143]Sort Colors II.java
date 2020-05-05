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
//version 2: O(nk)
public class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        // check corner case
        if (colors == null || colors.length <= 1) {
            return;
        }
        
        int count = 0;
        int left = 0;
        int right = colors.length - 1;
        
        while (count < k) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            
            // get the min and max values(color) from the remaining
            for (int i = left; i <= right; i++) {
                min = Math.min(min, colors[i]);
                max = Math.max(max, colors[i]);
            }
            
            // get the two boudaries[left, right]
            //partition the colors in 3 categors by the 2(left and right) boundaries
            int current = left;
            while (current <= right) {
                if (colors[current] == min) {
                    swap(colors, left, current);
                    current++;
                    left++;
                }
                else if (colors[current] == max) {
                    swap(colors, current, right);
                    right--;
                }
                else {
                    current++;
                }
            }
            
            count += 2;
        }
    }
    
    private void swap(int[] colors, int i, int j) {
        int temp = colors[i];
        colors[i] = colors[j];
        colors[j] = temp;
    }
}