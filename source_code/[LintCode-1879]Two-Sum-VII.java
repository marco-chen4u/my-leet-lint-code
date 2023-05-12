/***
* LintCode 1879 · Two Sum VII
Given an array of integers that is already sorted in ascending order of absolute value, 
find two numbers so that the sum of them equals a specific number.

The function twoSum should return indices of the two numbers such that they add up to the target, 
where index1 must be less than index2. Note: the subscript of the array starts with 0

You are not allowed to sort this array.

Note:
    It is guaranteed that all numbers in the nums is distinct.
    The length of nums is ≤ 100,000.
    The number in nums is ≤10^9.

Example 1
    Input: 
        [0,-1,2,-3,4]
        1
    Output: 
        [[1,2],[3,4]]
    Explanation: 
        nums[1] + nums[2] = -1 + 2 = 1, nums[3] + nums[4] = -3 + 4 = 1
        You can return [[3,4],[1,2]], the system will automatically help you sort it to [[1,2],[3,4]]. But [[2,1],[3,4]] is invaild.

Challenge
    O(n) time complexity and O(1) extra space

LintCode link: https://www.lintcode.com/problem/1879
***/
//version-1: two pointers, easy solution, with time complexity: O(nlogn), space complexity: O(n)
public class Solution {

    class Element implements Comparable<Element> {
        int val;
        int index;

        public Element(int val, int index) {
            this.val = val;
            this.index = index;
        }

        @Override
        public int compareTo(Element other) {
            return this.val - other.val;
        }
    }

    /**
     * @param nums: the input array
     * @param target: the target number
     * @return: return the target pair
     *          we will sort your return value in output
     */
    public List<List<Integer>> twoSumVII(int[] nums, int target) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length <= 1) {
            return result;
        }

        int size = nums.length;
        Element[] elements = new Element[size];
        
        for (int i = 0; i < size; i++) {
            elements[i] = new Element(nums[i], i);
        }

        Arrays.sort(elements);

        int left = 0;
        int right = size - 1;

        while (left < right) {
            int sum = elements[left].val + elements[right].val;
            if (sum == target) {

                List<Integer> indexes = new ArrayList<>();
                indexes.add(Math.min(elements[left].index, elements[right].index));
                indexes.add(Math.max(elements[left].index, elements[right].index));
                result.add(indexes);

                left++;
                continue;
            }

            if (sum < target) {
                left++;
                continue;
            }

            if (sum > target) {
                right--;
            }
        }

        return result;
    }
}

//version-2: two pointers and statisfy the challenge requirements with O(n) time complexity and O(1) space complexity
// to continue
