/***
* LintCode 1538. Card Game II
You are playing a card game with your friends, there are n cards in total. 
Each card costs cost[i] and inflicts damage[i] damage to the opponent. 
You have a total of totalMoney dollars and need to inflict at least totalDamage damage to win. 
And Each card can only be used once. 

Determine if you can win the game.

Example
	Example1
		Input:
			cost = [1,2,3,4,5]
			damage = [1,2,3,4,5]
			totalMoney = 10
			totalDamage = 10
		Output: true
		Explanation: We can use the [1,4,5] to cause 10 damage, which costs 10.

	Example2
		Input:
			cost = [1,2]
			damage = [3,4]
			totalMoney = 10
			totalDamage = 10
		Output: false
		Explanation: We can only cause 7 damage at most.
***/
/*
* 题意： 你跟你的朋友在玩一个卡牌游戏，总共有 n 张牌。每张牌的成本为 cost[i] 并且可以对对手造成 damage[i] 的伤害。你总共有 totalMoney 元并且需要造成至少 totalDamage 的伤害才能获胜。每张牌只能使用一次，判断你是否可以取得胜利。 
*
* 本题其实是一个[01]背包类型问题，用totalMoney的花费得到的最大的收益，判断收益是否达到totalDamage即可
*/
public class Solution {
    /**
     * @param cost: costs of all cards
     * @param damage: damage of all cards
     * @param totalMoney: total of money
     * @param totalDamage: the damage you need to inflict
     * @return: Determine if you can win the game
     */
    public boolean cardGame(int[] cost, int[] damage, int totalMoney, int totalDamage) {
        int n = cost.length;
        
        int[][] dp = new int[n + 1][totalMoney + 1];
        
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        
        for (int j = 0; j <= totalMoney; j++) {
            dp[0][j] = 0;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= totalMoney; j++) {
                dp[i][j] = dp[i - 1][j];//不取当前的牌
                
                if (j - cost[i - 1] >= 0) {//取当前的牌
                    dp[i][j] = Math.max(dp[i][j],
                                    dp[i - 1][j - cost[i - 1]] + damage[i - 1]);
                }
            }
        }
        
        return dp[n][totalMoney] >= totalDamage;
    }
}