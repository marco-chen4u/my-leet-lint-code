/***
* LintCode 1373. Movies on Flight
You are on a flight and wanna watch two movies during this flight.
You are given List movieDurations which includes all the movie durations.
You are also given the duration of the flight which is d in minutes.
Now, you need to pick two movies and the total duration of the two movies is less than or equal to (d - 30min).

Find the pair of movies with the longest total duration and return their indexes. If multiple found, return the pair with the longest movie.

Example
Example:
Input: movieDurations = [90, 85, 75, 60, 120, 150, 125], d = 250
Output: [0, 6]
Explanation: movieDurations[0] + movieDurations[6] = 90 + 125 = 215 is the maximum number within 220 (250min - 30min)
***/
public class Solution {
    /**
     * @param nums: An integer numsay
     * @param k: An integer
     * @return: return the pair of movies index with the longest total duration
     */
    public int[] FlightDetails(int[] nums, int k) {
        int left = -1;
        int right = -1;
        int size = nums.length;
        int maxSum = Integer.MIN_VALUE;
        
        for (int i = 0; i <= size - 2; i++) {
            
            if (nums[i] + 30 >= k) {
                continue;
            }
            
            int sum = nums[i];
            
            for (int j = i + 1; j <= size - 1; j++) {
                sum += nums[j];
                
                if (sum > maxSum && sum + 30 <= k) {
                    maxSum = sum;
                    left = i;
                    right = j;
                }
                
                if (sum == maxSum && sum + 30 <= k) {
                    if (left != -1 && right != -1 &&
                        nums[i] > nums[left] && nums[i] > nums[right] ||
                        nums[j] > nums[left] && nums[j] > nums[right]) {
                        left = i;
                        right = j;
                    }
                }
                
                sum -= nums[j];
            }
        }
        
        return new int[] {left, right};
    }
}