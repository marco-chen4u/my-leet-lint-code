/***
* LeetCode 710. Random Pick with Blacklist
Given a blacklist B containing unique integers from [0, N), write a function to return a uniform random integer from [0, N) which is NOT in B.
Optimize it such that it minimizes the call to system’s Math.random().

Note:
    (1)1 <= N <= 1000000000
    (2)0 <= B.length < min(100000, N)
    (3)[0, N) does NOT include N. See interval notation.

Example 1:
    Input: 
        ["Solution","pick","pick","pick"]
        [[1,[]],[],[],[]]
    Output: [null,0,0,0]
Example 2:
    Input: 
        ["Solution","pick","pick","pick"]
        [[2,[]],[],[],[]]
    Output: [null,1,1,1]
Example 3:
    Input: 
        ["Solution","pick","pick","pick"]
        [[3,[1]],[],[],[]]
    Output: [null,0,0,2]
Example 4:
    Input: 
        ["Solution","pick","pick","pick"]
        [[4,[2]],[],[],[]]
    Output: [null,1,3,1]

Explanation of Input Syntax:
    The input is two lists: the subroutines called and their arguments. 
    Solution's constructor has two arguments, N and the blacklist B. pick has no arguments. 
    Arguments are always wrapped with a list, even if there aren't any.
	
***/

//version-1: Rum Time Exceeded(when it test for huge data intput, it will get this error)
class Solution {
	
    // fields
    Set<Integer> blackListSet;
    int limit;
    Random random;

    public Solution(int N, int[] blacklist) {
        this.limit = N;
        
        blackListSet = new HashSet<Integer>();
        for (int value : blacklist) {
            blackListSet.add(value);
        }
        
        random = new Random();
    }
    
    public int pick() {
        int value = random.nextInt(limit);
		
		while (blackListSet.contains(value)) {
			value = random.nextInt(limit);
		}
		
		return value;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(N, blacklist);
 * int param_1 = obj.pick();
 */
 
/*
 rnadom % n
 
 在[0,n)的数中，真正合法数又m个。
 所以最保守的数值范围random % m 即[0, m)
 例如 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
     其中blacklist[3,8,9]
	 所以其有效范围数中为0,1,2,4,5,6，7 这7个数，其中数字7已经超过有效个数值（6），而这个处于lblacklist中的3，却在有效数值个数（6）范围之内，所以，可以对3<->7，做一个有效映射
 
 
*/ 
 class Solution {

    public Solution(int N, int[] blacklist) {
        
    }
    
    public int pick() {
        
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(N, blacklist);
 * int param_1 = obj.pick();
 */
