/***
* LintCode 58. 4Sum
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?

Find all unique quadruplets in the array which gives the sum of target.

Note:
    Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
    The solution set must not contain duplicate quadruplets.

Example 1:
    Input:
        numbers = [2,7,11,15]
        target = 3
    Output:
        []

    Explanation:
        2 + 7 + 11 + 15 != 3. There is no quadruple satisfying the condition.

Example 2:
    Input:
        numbers = [1,0,-1,0,-2,2]
        target = 0
    Output:
        [[-1, 0, 0, 1],[-2, -1, 1, 2],[-2, 0, 0, 2]]
    Explanation:
        There are three different quadruples whose sum of four numbers is 0.
***/
//version-1: two pointer + HashSet to filter diplicates
ublic class Solution {
    /**
     * @param nums: Give an array
     * @param target: An integer
     * @return: Find all unique quadruplets in the array which gives the sum of zero
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // check corner case
        if (nums == null || nums.length < 3) {
            return result;
        }

        // normal case
        int size = nums.length;
        Arrays.sort(nums);

        Set<List<Integer>> values = new HashSet<>();

        for (int i = 0; i < size - 3; i++) {
            for (int j = i + 1; j < size - 2; j++) {
                int left = j + 1;
                int right = size - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        List<Integer> squadlet = new ArrayList();
                        squadlet.add(nums[i]);
                        squadlet.add(nums[j]);
                        squadlet.add(nums[left]);
                        squadlet.add(nums[right]);
                        values.add(squadlet);
                        
                        left++;
                        while(left < right && nums[left] == nums[left- 1]) {
                            left++;
                        }
                        
                        right--;
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                        continue;
                    }

                    if (sum < target) {
                        left++;
                    }
                    else {
                        right--;
                    }
                }
            }
        }

        result.addAll(values);

        return result;
    }
}

//version-2: two pointers
public class Solution {
    /**
     * @param numbers: Give an array
     * @param target: An integer
     * @return: Find all unique quadruplets in the array which gives the sum of zero
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // check corner case
        if (nums == null || nums.length < 4) {
            return result;
        }

        // sort
        Arrays.sort(nums);

        // make the 4sum as a 3sum problem to solve
        for (int i = 0; i < nums.length - 3; i++) {
            // skip the duplicate with the first same numbers
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // make the 3sum as a 2sum problem to solve
            for (int j = i + 1; j < nums.length - 2; j++) {
                // skip the duplicate
                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int left =j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int twoSum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (twoSum == target) {
                        List<Integer> quadruplet = new ArrayList<Integer>();
                        quadruplet.add(nums[i]);
                        quadruplet.add(nums[j]);
                        quadruplet.add(nums[left]);
                        quadruplet.add(nums[right]);
                        result.add(quadruplet);
                        
                        left++;
                        right--;
                        
                        //skip the duplicate
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    }
                    else if (twoSum > target) {
                        right--;
                    }
                    else {
                        left++;
                    }
                }
            }
        }
        return result;
    }
}



