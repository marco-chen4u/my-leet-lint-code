/***
* LintCode 578. Lowest Common Ancestor III
Given the root and two nodes in a Binary Tree. 
Find the lowest common ancestor(LCA) of the two nodes.

The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
Return null if LCA does not exist.

Example1
    Input: 
        {4, 3, 7, #, #, 5, 6}
        3 5
        5 6
        6 7 
        5 8
    Output: 
        4
        7
        7
        null
    Explanation:
          4
         / \
        3   7
           / \
          5   6

        LCA(3, 5) = 4
        LCA(5, 6) = 7
        LCA(6, 7) = 7
        LCA(5, 8) = null

Example2
    Input:
        {1}
        1 1
    Output: 
        1
Notice
    node A or node B may not exist in tree.
***/

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

/**
* 这道题的理解重点是A和B两个节点，不一定存在于Root的二叉树中。
* 这是本题与LintCode.88这道相对简单点的LCA题的区别，88号题，是A和B两节点，必定存在于Root的二叉树中。
* 所以本题不能用88号题的解法来处理，毕竟A和B这2个节点不一定都存在。
**/

// version-1: non-recursion
public class Solution {

    /*
     * @param root: The root of the binary tree.
     * @param A: A TreeNode
     * @param B: A TreeNode
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        TreeNode result = null; // default value
        // check corner case
        if (root == null) {
            return result;
        }

        Map<TreeNode, TreeNode> parentMap = new HashMap<TreeNode, TreeNode>();
        Set<TreeNode> ancestorSet = new HashSet<TreeNode>();

        // (1) get all the node into the parent map 
        preOrderTraverse(root, null, parentMap);

        // if one of [A,B] dose not belong to this binary tree
        if (!parentMap.containsKey(A) || !parentMap.containsKey(B)) {
            return result;
        }

        // (2)get A's all ancestors
        while (A != null) {
            ancestorSet.add(A);
            A = parentMap.get(A);// get A's direct parent
        }

        // (3) find the first common ancestor of A and B
        while (!ancestorSet.contains(B)) {
            ancestorSet.add(B);
            B = parentMap.get(B);// get B's direct parent
        }

        if (B != null) {
            result = B;
        }

        return result;
    }

    // helper method
    private void preOrderTraverse(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> map) {
        if (node == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(node);
        map.put(node, parent);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();

            if (current.right != null) {
                map.put(current.right, current);
                stack.push(current.right);
            }

            if (current.left != null) {
                map.put(current.left, current);
                stack.push(current.left);
            }
        }
    }
}

 // version-2: recursion
public class Solution {

    /*
     * @param root: The root of the binary tree.
     * @param A: A TreeNode
     * @param B: A TreeNode
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
		TreeNode result = null; // default value
        // check corner case
        if (root == null) {
            return result;
        }

        Map<TreeNode, TreeNode> parentMap = new HashMap<TreeNode, TreeNode>();
        Set<TreeNode> ancestorSet = new HashSet<TreeNode>();

        // (1) get all the node into the parent map 
        preOrderTraverse(root, null, parentMap);

        // if one of [A,B] dose not belong to this binary tree
        if (!parentMap.containsKey(A) || !parentMap.containsKey(B)) {
            return result;
        }

        // (2)get A's all ancestors
        while (A != null) {
            ancestorSet.add(A);
            A = parentMap.get(A);// get A's direct parent
        }

        // (3) find the first common ancestor of A and B
        while (!ancestorSet.contains(B)) {
            ancestorSet.add(B);
            B = parentMap.get(B);// get B's direct parent
        }

        if (B != null) {
            result = B;
        }

        return result;
    }

    // helper method
    private void preOrderTraverse(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> map) {
        if (node == null) {
            return;
        }

        map.put(node, parent);
        preOrderTraverse(node.left, node, map);
        preOrderTraverse(node.right, node, map);
    }
}

//version-3: real recursion
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
    // inner class
    class ResultType{
        // fields
        public boolean existA;
        public boolean existB;
        public TreeNode node;// common ancestor

        // constructor
        public ResultType(boolean existA, boolean existB, TreeNode node) {
            this.existA = existA;
            this.existB = existB;
            this.node = node;
        }
    }
    
    /*
     * @param root: The root of the binary tree.
     * @param A: A TreeNode
     * @param B: A TreeNode
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        // check corner cases
        if (root == null || A == null || B == null) {
            return null;
        }
        
        ResultType result = helper(root, A, B);
        //如果A和B节点都同时存在，才返回
        return result.existA && result.existB ? result.node : null;
    }

    // helper method
    private ResultType helper(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) {
            return new ResultType(false, false, null);
        }

        // 分别在左右子树查找LCA
        ResultType left = helper(root.left, a, b);
        ResultType right = helper(root.right, a, b);

        // 如果左边有A，或者右边有A，或者root本身就是A，那么root这棵树有A
        boolean existA = left.existA || right.existA || root == a;
        // 如果左边有B，或者右边有B，或者root本身就是B，那么root这棵树有B
        boolean existB = left.existB || right.existB || root == b;

        // 如果root为A或B，返回root（当前root有可能为LCA）
        if (root == a || root == b) {
            return new ResultType(existA, existB, root);
        }

        // 如果A，B分别存在于存在于两颗子树，root为LCA，返回root
        if (left.node != null && right.node != null) {
            return new ResultType(existA, existB, root);
        }

        // 左子树有一个点或者左子树有LCA
        if (left.node != null) {
            return new ResultType(existA, existB, left.node);
        }

        // 右子树有一个点或者右子树有LCA
        if (right.node != null) {
            return new ResultType(existA, existB, right.node);
        }

        // 左右子树都没有
        return new ResultType(existA, existB, null);
    }
}
