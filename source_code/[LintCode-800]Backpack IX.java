/***
* LintCode 800. Backpack IX
You have a total of n thousand yuan, hoping to apply for a university abroad. 
The application is required to pay a certain fee. 
Give the cost of each university application and the probability of getting the University's offer, and the number of university is m. 
If the economy allows, you can apply for multiple universities. 

Find the highest probability of receiving at least one offer.

Example
    Example 1:
        Input:  
            n = 10
            prices = [4,4,5]
            probability = [0.1,0.2,0.3]
        Output:  0.440	
        Explanation：
            select the second and the third school. 

    Example 2:
        Input: 
            n = 10
            prices = [4,5,6]
            probability = [0.1,0.2,0.3]
        Output:  0.370
        Explanation:
            select the first and the third school.
Notice
    0<=n<=10000,0<=m<=10000
***/
public class Solution {
    /**
     * @param n: Your money
     * @param prices: Cost of each university application
     * @param probability: Probability of getting the University's offer
     * @return: the  highest probability
     */
    public double backpackIX(int n, int[] prices, double[] probability) {
        int m = prices.length;

        double[][] dp = new double[m + 1][n + 1];

        // intialize
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;// impossible possibility
        }

        for (int j = 0; j <= m; j++) {
            dp[0][j] = 1; // impossible possibility, hard to get the offer
        }

        for (int i = 1; i <= n; i++) {
            for (int j = price[i - 1]; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j - prices[i - 1] >= 0) {
                    double currentPossibility  = 1 - probability[i - 1];
					
                    dp[i][j] = Math.min(dp[i][j], 
                                        dp[i - 1][j - prices[i - 1]] * currentPossibility);
                }
            }
        }

        return 1 - dp[m][n];
    }
}

public class Solution {
    /**
     * @param n: Your money
     * @param prices: Cost of each university application
     * @param probability: Probability of getting the University's offer
     * @return: the  highest probability
     */
    public double backpackIX(int n, int[] prices, double[] probability) {
        int m = prices.length;

        double[] dp = new double[n + 1];

        Arrays.fill(dp, 1.0);

        for (int i = 0; i < m; i++) {
            probability[i] = 1 - probability[i];
        }

        for (int i = 0; i < m; i++) {
            for (int j = n; j >=prices[i]; j--) {
                dp[j] = Math.min(dp[j], dp[j - prices[i]] * probability[i]);
            }
        }

        return 1 - dp[n];
    }
}
