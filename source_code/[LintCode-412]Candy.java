/***
* LintCode 412. Candy
There are N children standing in a line. Each child is assigned a rating value. Given array ratings representing all rating value of these children.

You are giving candies to these children subjected to the following requirements:
	-Each child must have at least one candy.
	-Children with a higher rating get more candies than their neighbors.

What is the minimum candies you must give?

Example
	Example 1:
		Input: [1, 2]
		Output: 3
		Explanation: 
			Give the first child 1 candy and give the second child 2 candies.

	Example 2:
		Input: [1, 1, 1]
		Output: 3
		Explanation: 
			Give every child 1 candy.

	Example 3:
		Input: [1, 2, 2]
		Output: 4
		Explanation: 
			Give the first child 1 candy.
			Give the second child 2 candies.
			Give the third child 1 candy.

	Example 4:
		Input: [1,0,2]
		Output: 5
		Explanation: 
			You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
***/
/*
* （1）初始化数组values，每个item值初始化为最小值1
* (2)从条件2【Children with a higher rating get more candies than their neighbors】可知，我们不许从前后2个方向进行扫描，
* (3)当前位置values[i]值通过规则（后面比前面ranting值大，则在前面value[i -1]值的基础上加1进行更新， 否则保持原来的状态值values[i]）
* (4)完成前后2个方向的状态值更新
* (5)把数组values所有元素状态值，进行累加到结果值当中，并返回结果。
example:

	ratings   [1, 2, 3, 4, 2, 1]
	values    [1, 1, 1, 1, 1, 1]
	forard  ->[1, 2, 3, 4, 1, 1]
	backward<-[1, 2, 3, 4, 2, 1]	
	result =  sum(values) = ( 1 + 2 + 3 + 4 + 2 + 1) = 13

    ratings   [1, 2, 87, 87, 87, 2, 1]
	values    [1, 1, 1,   1,  1, 1, 1]
	forard  ->[1, 2, 3,   1,  1, 1, 1]
	backward<-[1, 2, 3,   1,  3, 2, 1]
	result =  sum(values) = ( 1 + 2 + 3 + 1 + 3 + 2 + 1) = 13
*/
public class Solution {
    /**
     * @param ratings: Children's ratings
     * @return: the minimum candies you must give
     */
    public int candy(int[] ratings) {
        int result = 0;
        // check corner case
        if (ratings == null || ratings.length == 0) {
            return result;
        }
        
        int size = ratings.length;
        
        int[] values = new int[size];
        Arrays.fill(values, 1);
        
        if (size == 1) {
            return 1;
        }
        
        // we have to make evaluation by both directions[moving forward and moving backward]
        // forward
        for (int i = 1; i < size; i++) {
            if (ratings[i] > ratings[i - 1]) {
                values[i] = values[i - 1] + 1;
            }
        }
        
        // backward
        for (int i = size - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                //values[i] = (values[i] <= values[i + 1]) ? values[i + 1] + 1 : values[i];
				values[i] = Math.max(values[i], values[i + 1] + 1);
            }
        }
        
        // calculating values by sum up to the result
        for (int value : values) {
            result += value;
        }
        
        return result;
    }
}