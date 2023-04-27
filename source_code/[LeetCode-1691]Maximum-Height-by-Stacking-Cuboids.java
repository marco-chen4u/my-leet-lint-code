/***
* LeetCode 1691. Maximum Height by Stacking Cuboids 
Given n cuboids where the dimensions of the ith cuboid is cuboids[i] = [widthi, lengthi, heighti] (0-indexed). 
Choose a subset of cuboids and place them on each other.

You can place cuboid i on cuboid j if widthi <= widthj and lengthi <= lengthj and heighti <= heightj. 
You can rearrange any cuboid's dimensions by rotating it to put it on another cuboid.

Return the maximum height of the stacked cuboids.

Example 1
    image link: https://assets.leetcode.com/uploads/2019/10/21/image.jpg
        Input: cuboids = [[50,45,20],[95,37,53],[45,23,12]]
    Output: 190
    Explanation:
        Cuboid 1 is placed on the bottom with the 53x37 side facing down with height 95.
        Cuboid 0 is placed next with the 45x20 side facing down with height 50.
        Cuboid 2 is placed next with the 23x12 side facing down with height 45.
        The total height is 95 + 50 + 45 = 190.
        
Example 2
    Input: cuboids = [[38,25,45],[76,35,3]]
    Output: 76
    Explanation:
        You can't place any of the cuboids on the other.
        We choose cuboid 1 and rotate it so that the 35x3 side is facing down and its height is 76.

Example 3
    Input: cuboids = [[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]]
    Output: 102
    Explanation:
        After rearranging the cuboids, you can see that all cuboids have the same dimension.
        You can place the 11x7 side down on all cuboids so their heights are 17.
        The maximum height of stacked cuboids is 6 * 17 = 102.
        
Constraints:
    n == cuboids.length
    1 <= n <= 100
    1 <= widthi, lengthi, heighti <= 100

LeetCode link: https://leetcode.com/problems/maximum-height-by-stacking-cuboids/
***/
//version-1: dp, time complexity: O(n^2), space complexity: O(n)
class Solution {
    public int maxHeight(int[][] cuboids) {
        // rearrange any cuboid's dimensions by rotating it to put it on another cuboid.
        for (int[] cuboid : cuboids) {
            Arrays.sort(cuboid); // width <= length <= height  [width, length, height]
            //System.out.println(Arrays.toString(cuboid));
        }

        Arrays.sort(cuboids, (a, b) -> {/* make it stacked if we select i-th cuboid as the bottom one */
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }

            if (a[1] != b[1]) {
                return a[1] - b[1];
            }

            return a[2] - b[2];
        });

        // state
        int n = cuboids.length;
        int[] dp = new int[n];//maximum total heights could be stacked if we select i-th cuboid as the bottom one

        // initialize
        for (int i = 0; i < n; i++) {
            dp[i] = cuboids[i][2]; // width: cuboid[0], length : cuboid[1], height : cuboid[2]
                                   // aka height[i]
        }

        //System.out.println(Arrays.toString(dp));

        // calculation
        int result = 0;
        for (int i = 0; i < n; i++) {
            int[] current = cuboids[i];
            for (int j = 0; j < i; j++) {
                int[] pre = cuboids[j];
                if (pre[0] <= current[0] && pre[1] <= current[1] && pre[2] <= current[2]) {
                    dp[i] = Math.max(dp[i], dp[j] + current[2]);// dp[i] = max(dp[i],dp[j] + height[i])
                }
            }
            //System.out.println("dp[" + i + "]" + dp[i]);
            
            result = Math.max(result, dp[i]);
        }

        return result;
    }
}
