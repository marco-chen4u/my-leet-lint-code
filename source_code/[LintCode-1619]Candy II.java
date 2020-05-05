/***
* LintCode 1619. Candy II
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:
	-Each child must have at least one candy.
	-Children with a higher rating get more candies than their neighbors.
	-Children with the same rating and are located next to each other get the same candies.
What is the minimum candies you must give?

Example
	Example 1:
		Input: [4,7,8,1,6,6,2]
		Output: 12
		Explanation: 1 + 2 + 3 + 1 + 2 + 2 + 1 = 12
	Example 2:
		Input: [10,2,3,3,7,10]
		Output: 14
		Explanation: 2 + 1 + 2 + 2 + 3 + 4 = 14
	Example 3:
		Input: [10,10,10,10,2,1]
		Output: 15
***/
public class Solution {
    /**
     * @param ratings: rating value of each child
     * @return: Return the minimum candies you must give.
     */
    public int candyII(int[] ratings) {
        int result = 0;
        // check corner case
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        
        int size = ratings.length;
        if (size == 1) {
            return 1;
        }
        
        int[] candy = new int[size];
        
        Arrays.fill(candy, 1);
        
        // we have to make evaluation by both directions[moving forward and moving backward]
        // forward
        for (int i = 1; i < size; i++) {
            if (ratings[i] == ratings[i - 1]) {
                candy[i] = candy[i - 1];
                continue;
            }
            
            if (ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }
        }
        
        // backward
        for (int i = size - 2; i >= 0; i--) {
            if (ratings[i] == ratings[i + 1]) {
                candy[i] = candy[i + 1];
                continue;
            }
            
            if (ratings[i] > ratings[i + 1]) {
                candy[i] = Math.max(candy[i], candy[i + 1] + 1);
            }
        }
        
        // calculating values by sum up to the result
        for (int value : candy) {
            result += value;
        }
        
        return result;
    }
}