/**
* LeetCode 1305 All Elements in 2 Binary Search Trees
Given two binary search trees root1 and root2, 
return a list containing all the integers from both trees sorted in ascending order.

Example 1:
               2                      1
             /   \                  /   \ 
            1     4                0     3
    Input: root1 = [2,1,4], root2 = [1,0,3]
    Output: [0,1,1,2,3,4]

Example 2:
               1                      8
                 \                  /     
                  8                1      
    Input: root1 = [1,null,8], root2 = [8,1]
    Output: [1,1,8,8]
 

Constraints:
    The number of nodes in each tree is in the range [0, 5000].
    -10^5 <= Node.val <= 10^5
    
Link: https://leetcode.com/problems/all-elements-in-two-binary-search-trees/

Related topics: DFS, Binary Search Tree, Sorting
**/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> result = new ArrayList<>();
        // if (root1 == null || root2 == null) {
        //     return result;
        // }
        
        int[] nums1 = traverse(root1);
        
        int[] nums2 = traverse(root2);
        
        int[] values =  merge(nums1, nums2);
        
        for (int value : values) {
            result.add(value);
        }
        
        return result;
    }
    
    // helper methods
    private int[] traverse(TreeNode node) {
        List<Integer> values = new ArrayList<>();
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = node;
        
        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            current = stack.pop();
            values.add(current.val);
            
            current = current.right;
        }
        
        int size = values.size();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = values.get(i);
        }
        
        return nums;
    }
    
    private int[] merge(int[] nums1, int[] nums2) {
        
        int i = 0;
        int j = 0;
        int size1 = nums1.length;
        int size2 = nums2.length;
        int size = size1 + size2;
        
        int[] result = new int[size];        
        
        int index = 0;
        while (i < size1 && j < size2) {
            if (nums1[i] <= nums2[j]) {
                result[index++] = nums1[i++];
            }
            else {
                result[index++] = nums2[j++];
            }
        }
        
        while (i < size1) {
            result[index++] = nums1[i++];
        }
        
        while (j < size2) {
            result[index++] = nums2[j++];
        }
        
        return result;
    }
}
