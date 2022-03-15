/**
* LintCode 548. Intersection of Two Arrays II
* Given two arrays, write a function to compute their intersection.
    -Each element in the result should appear as many times as it shows in both arrays.
    -The result can be in any order.
    
Example1
    Input: 
    nums1 = [1, 2, 2, 1], nums2 = [2, 2]
    Output: 
    [2, 2]

Example2
    Input: 
    nums1 = [1, 1, 2], nums2 = [1]
    Output: 
    [1]

Challenge
    -What if the given array is already sorted? How would you optimize your algorithm?
    -What if nums1's size is small compared to num2's size? Which algorithm is better?
    -What if elements of nums2 are stored on disk, 
    and the memory is limited such that you cannot load all elements into the memory at once?
    
Link: https://www.lintcode.com/problem/548/

**/
//version-1: HashMap
public class Solution {
    /**
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // check conrer cases
        if (nums1 == null || nums1.length == 0) {
            return nums1;
        }
        
        if (nums2 == null || nums2.length == 0) {
            return nums2;
        }
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums1) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            }
            else {
                map.put(num, 1);
            }
        }
        
        List<Integer> resultList = new ArrayList<Integer>();
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                resultList.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        
        int size = resultList.size();
        int index = 0;
        int[] result = new int[size];
        for (int num : resultList) {
            result[index++] = num;
        }
        
        return result;
    }
}
