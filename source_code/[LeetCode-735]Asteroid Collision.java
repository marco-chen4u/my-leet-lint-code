/***
* LeetCode 735. Asteroid Collision
We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

Example 1:
    Input: 
        asteroids = [5, 10, -5]
    Output: [5, 10]
    Explanation: 
        The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.

Example 2:
    Input: 
        asteroids = [8, -8]
    Output: []
    Explanation: 
        The 8 and -8 collide exploding each other.

Example 3:
    Input: 
        asteroids = [10, 2, -5]
    Output: [10]
    Explanation: 
        The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.

Example 4:
    Input: 
        asteroids = [-2, -1, 1, 2]
    Output: [-2, -1, 1, 2]
    Explanation: 
        The -2 and -1 are moving left, while the 1 and 2 are moving right.
        Asteroids moving the same direction never meet, so no asteroids will meet each other.
		
URL：https://leetcode.com/problems/asteroid-collision/

Note:
    -The length of asteroids will be at most 10000.
    -Each asteroid will be a non-zero integer in the range [-1000, 1000].
***/
/*
* 主要考点： stack，模拟
*思路如下：
*(1)把所有的小行星，在数组中之间的相隔距离假设为等距离。
*（2）正数则表示该小行星静止不动（或向右运动）， 负数则表示该小行星向左运动。
*（3）当为正数，则正常入栈(stack)
*(4)当为负数式，
*	如果站（stack）为空，或者栈顶为相同的负数，则正常入栈。
*	如果栈顶为正数，这说明栈顶和当前的小行星（负数）为相向碰撞
*		如果栈顶(整数)的尺寸小于相向碰撞的小行星尺寸值，那么则弹栈，保持当前相向而行的小行星（负数）以便下一个循环做栈顶的值判断
*（5）把栈中所剩的元素，反向输出道相同栈size大小的数组作为结果返回
*/
//version-1: for loop & stack
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        int[] result = new int[0]; // defaul value
        
        // check corner case
        if (asteroids == null || asteroids.length == 0) {
            return result;
        }
        
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < asteroids.length; i++) {
            int value = asteroids[i];
            
            if (value > 0) {
                stack.push(value);
                continue;
            }
            
            if (value < 0) {
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(value);
                    continue;
                }
                
                // going to collide
                if (Math.abs(stack.peek()) <= Math.abs(value)) {
                    if (Math.abs(stack.peek()) < Math.abs(value)) {
                        i--;// stay alive of this current value
                    }
                    
                    stack.pop();
                }
            }
        }
        
        if (stack.isEmpty()) {
            return result;
        }
        
        int index = stack.size() - 1;
        result = new int[stack.size()];
        while (!stack.isEmpty()) {
            result[index--] = stack.pop();
        }
        
        return result;
    }
}

//version-2: while loop & stack
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
		int[] result = new int[0]; // default value
		
		// check corner case
		if (asteroids == null || asteroids.length == 0) {
			return result;
		}
		
		Stack<Integer> stack = new Stack<Integer>();
		
		for (int value : asteroids) {
			if (value > 0) {
				stack.push(value);
				continue;
			}
			
			if (value < 0) {
				if (stack.isEmpty() || stack.peek() < 0) {
					stack.push(value);
					continue;
				}
				
				boolean flag = true;
				while (!stack.isEmpty() && stack.peek() > 0) {
					if (stack.peek() == Math.abs(value)) {
						flag = false;
						stack.pop();
						break;
					}
					
					if (stack.peek() < Math.abs(value)) {
						stack.pop();
						continue;
					}
					
					if (stack.peek() > Math.abs(value)) {
						flag = false;
						break;
					}
				}
				
				if (flag) {
					stack.push(value);
				}
			}
		}
		
		if (stack.isEmpty()) {
			return result;
		}
		
		int size = stack.size();
		result = new int[size];
		int index = size - 1;
		while (!stack.isEmpty()) {
			result[index--] = stack.pop();
		}
		
		return result;
	}
}
