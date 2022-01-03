/***
* LintCode 1691. Best Time to Buy and Sell Stock V
Given a stock n-day price, you can only trade at most once a day, 
you can choose to buy a stock or sell a stock or give up the trade today, 
output the maximum profit can be achieved.

Note: 1 <= n <= 10000

Example 1
    Given 'a = [1,2,10,9]', return '16'
    Input:
        [1,2,10,9]
    Output:
        16
    Explanation:
        you can buy in day 1,2 and sell in 3,4.
        profit:-1-2+10+9 = 16 

Example 2
    Given 'a = [9,5,9,10,5]', return '5'
    Input:
        [9,5,9,10,5]
    Output:
        5
    Explanation:
        you can buy in day 2 and sell in 4.
        profit:-5 + 10 = 5

***/
/*
* 题意：给出一个股票n天的价格，每天最多只能进行一次交易，可以选择买入一支股票或卖出一支股票或放弃交易，输出能够达到的最大利润值
* 解题思路：
从前往后遍历每天的股票价格，我们考虑每天能够获取的最大收益，考虑贪心获得最大收益，那么我们可以从左往右遍历，若当前价格大于之前遇到的最低价，则做交易。
同时把在heap里用卖出价代替买入价，即将当前价格压入队列
（假设当前价格为b,要被弹出的元素是a,后面有一个c元素，如果那时a还在，作为最低价，差值为c-a，而这里已经被b拿去做了差值，所以b得压入队列，因为c-b+b-a = c-a），
弹出之前的最低价,可以利用优先队列来使之前的价格有序
    -用优先队列存储当前遇到过的价格
    -每日的新价格 与历史最低价比较
    -若比最低价高，则弹出最低价，同时更新答案，即加上差值
    -压入当前价格

时间复杂度：O(nlogn)
空间复杂度：O(n)

*注：n为天数
*/

public class Solution {
    /**
     * @param nums: the array nums
     * @return: return the maximum profit
     */
    public int getAns(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        Queue<Integer> minHeap = new PriorityQueue<>();
        int result = 0;
        int size = nums.length;
        
        for (int num : nums) {
            if (!minHeap.isEmpty() && num > minHeap.peek()) {
                result += num - minHeap.poll();
                minHeap.offer(num);
            }

            minHeap.offer(num);
        }

        //System.out.println("minHeap = " + minHeap);

        // return
        return result;
    }
}
