/***
* LeetCode 837. New 21 Game
Alice plays the following game, loosely based on the card game "21".

Alice starts with 0 points, and draws numbers while she has less than K points.  During each draw, she gains an integer number of points randomly from the range [1, W], where W is an integer.  Each draw is independent and the outcomes have equal probabilities.

Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N or less points?

Example 1:
	Input: N = 10, K = 1, W = 10
	Output: 1.00000
	Explanation:  Alice gets a single card, then stops.

Example 2:
	Input: N = 6, K = 1, W = 10
	Output: 0.60000
	Explanation:  Alice gets a single card, then stops.
	In 6 out of W = 10 possibilities, she is at or below N = 6 points.

Example 3:
	Input: N = 21, K = 17, W = 10
	Output: 0.73278
	
Note:
	1. 0 <= K <= N <= 10000
	2. 1 <= W <= 10000
	3. Answers will be accepted as correct if they are within 10^-5 of the correct answer.
	4. The judging time limit has been reduced for this question.
***/
//version-1: recursion， Runtime Error(Time Limit Exceeded)
class Solution {
    public double new21Game(int N, int K, int W) {
        return helper(0, N, K, W);
    }
	
	// helper method
	private double helper(int startingPoints, int N, int K, int W) {
		
		// check corner case
		if (startingPoints >= K) {// time to terminate
			return (startingPoints <= N) ? 1 : 0;// probability (100% or 0%)
		}
		
		double sum = 0;
		for (int i = 1; i <= W; i++) {
			sum += 1.0/W * helper(startingPoints + i, N, K, W);
		}
		
		return sum;
	}
}

//version-2: DP
/*
* x x x [i - w, i - w + 1, i - 1] i 中，i达到条件(>= K 且 <= N)时的概率值
* 其中，要达到i这个阶段，那么必须先达到[i - w, i - w + 1, i - 1]中的一个，然后才有达到i的可能。而[i - w, i - w + 1, i - 1]是等概率事件，
* 所以，要达到i这个阶段的是sum[(i - w)P1, (i - w + 1)P2,..., (i - 1)Pw-1]的概率之和，其中这里[i - w, i - w + 1, i - 1]区间的每一个P的概率值都是=1.0/W。
* 
*/
class Solution {
    public double new21Game(int N, int K, int W) {
		// state
		double[] dp = new double[N + 1];
		
		// initialize
		Arrays.fill(dp, 0);
		dp[0] = 1;
		
		// function
		double sum = 0;
		for (int i = 1; i <= N; i++) {
			
			/*for (int j = i - W; j <= i - 1; j++) {
				if (j < 0) {// check corner case, j < 0, that means it's within a W-size window scope
					continue;
				}
				
				if (j >= K) {
					continue;
				}
				
				dp[i] += dp[j] * 1.0/W;
			}*/
			
			//sliding window process
			sum += (i - 1 < K) ? dp[i - 1] : 0;
			sum -= (i - W - 1 >= 0) ? dp[i - W - 1] : 0;
			
			dp[i] = sum * 1.0/W;
		}
		
		double result = 0;
		for (int i = K; i <= N; i++) {
			result += dp[i];
		}
		
		return result;
	}
}