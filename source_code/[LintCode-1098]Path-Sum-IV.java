/**
* LintCode 1098. Path Sum IV
If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.

For each integer in this list:
    1.The hundreds digit represents the depth D of this node, 1 <= D <= 4.
    2.The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. 
The position is the same as that in a full binary tree.
    3.The units digit represents the value V of this node, 0 <= V <= 9.

Given a list of ascending three-digits integers representing a binary tree with the depth smaller than 5, you need to return the sum of all paths from the root towards the leaves.

Example-1
    Input: [113, 215, 221]
    Output: 12
    Explanation: 
        The tree that the list represents is:
            3
           / \
          5   1

        The path sum is (3 + 5) + (3 + 1) = 12.

Example-2:
    Input: [113, 221]
    Output: 4
    Explanation: 
        The tree that the list represents is: 
            3
             \
              1

        The path sum is (3 + 1) = 4.
**/

//verison-1: reversion, devide and conquer
public class Solution {
    private final int MAX_TREE_HEIGHT = 5;
    /**
     * @param nums: the list
     * @return: the sum of all paths from the root towards the leaves
     */
    public int pathSumIV(int[] nums) {
        // check corner cases
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // normal case
        int[] tree = buildTree(nums);
        //System.out.println("tree[] = " + Arrays.toString(tree));
        
        return sumTree(tree, 0)[0];
    }

    // helper methods
    /**
    * buildup a one-demension binary tree based on the array of 3-digit-integers
    * example: 123 
    *          1-2-3
    *          tree-height-level:  1 => 0
    *          tree-node-position: 2 => 2nd postion, since it's 0-based, so it's positon should be 1
    *          tree-node-value:    3 => tree node value is 3
    * in one-demension binary tree, a tree node left-child position is node-position * 2 + 1
    *                               a tree node right-child position is node-position * 2 + 2
    **/
    private int[] buildTree(int[] nums) {
        int[] tree = new int[((int)Math.pow(2, MAX_TREE_HEIGHT))];
        Arrays.fill(tree, -1);

        for (int num : nums) {
            int value = num % 10;
            int pos = (num / 10) % 10;
            int tree_height_level = num / 100;

            tree[((int)Math.pow(2, tree_height_level - 1)) - 1 + pos - 1] = value;
        }

        return tree;
    }

    /**
    * get path Sum value of tree
    * @param int[] tree, one demension stored tree node values
    * @param int pos, the position index of a tree node in this one demenstion binary tree structur
    *
    * return int[] {path-sum-of-current-tree-node, leaf-count-of-current-tree-node}
    **/
    private int[] sumTree(int[] tree, int pos) {
        // check corner case
        if (tree[pos] == -1) {
            return new int[]{0, 0};
        }

        // normal case
        if (tree[pos * 2 + 1] == -1 && tree[pos * 2 + 2] == -1) {
            return new int[]{tree[pos], 1};
        }

        int[] left = sumTree(tree, pos * 2 + 1);
        int[] right = sumTree(tree, pos * 2 + 2);

        int leafCount = left[1] + right[1];

        int currentVal = tree[pos];
        int sum = left[0] + right[0] + currentVal * leafCount;

        return new int[] {sum, leafCount};

    }
}
