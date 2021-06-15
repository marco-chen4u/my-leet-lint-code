/***
* LintCode 1137.Construct String from Binary Tree
You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.

The null node needs to be represented by empty parenthesis pair "()". 
And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.

Example 1:
    Input: Binary tree: [1,2,3,4]
           1
         /   \
        2     3
       /    
      4     

    Output: "1(2(4))(3)"
    Explanation: Originallay it needs to be "1(2(4)())(3()())", 
    but you need to omit all the unnecessary empty parenthesis pairs. 
    And it will be "1(2(4))(3)".

Example 2:
    Input: Binary tree: [1,2,3,null,4]
           1
         /   \
        2     3
         \  
          4 

    Output: "1(2()(4))(3)"
    Explanation: Almost the same as the first example, 
    except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.

Link: https://www.lintcode.com/problem/1137/
***/
// solution1: recursion
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    private final String leftBracket = "(";
    private final String rightBracket = ")";
    private final String emptyStr = "";

    /**
     * @param t: the root of tree
     * @return: return a string
     */
    public String tree2str(TreeNode t) {
        String result = emptyStr;
        // check conner case
        if (t == null) {
            return result;
        }

        result += t.val;
        String left = tree2str(t.left);
        String right = tree2str(t.right);

        if (isEmpty(left) && isEmpty(right)) {
            return result;
        }

        result += enBracket(left);
        if (!isEmpty(right)) {
            result += enBracket(right);
        }

        return result;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private String enBracket(String str) {
        return leftBracket + str + rightBracket;
    }
}
