/***
* LintCode 453. Flatten Binary Tree to Linked List
Flatten a binary tree to a fake "linked list" in pre-order traversal.
Here we use the right pointer in TreeNode as the next pointer in ListNode.

Example 1:
    Input:
        {1,2,5,3,4,#,6}
             1
            / \
           2   5
          / \   \
         3   4   6
    Output:
        {1,#,2,#,3,#,4,#,5,#,6}
               1
                \
                 2
                  \
                   3
                    \
                     4
                      \
                       5
                        \
                         6
			 
Example 2:
    Input:
        {1}
        1
    Output:
        {1}
        1

Challenge
    Do it in-place without any extra memory.

Notice
    Don't forget to mark the left child of each node to null. 
    Or you will get Time Limit Exceeded or Memory Limit Exceeded.

URL
    https://www.lintcode.com/problem/flatten-binary-tree-to-linked-list/
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
 
//version-1: devide and conquar
/**
*（0)就是把左右子树都摊平，然后再做相关的链接，本题的操作： 树（的前序遍历）链表= 根结点+左子树链表+右子树链表
*（1)首先要理解这个方法flattenAndReturnLastNode(TreeNode node)的返回时这个树被flatten的最右边的节点；
*（2）用最简单的数据（比如3个节点，即父节点带左右两个叶子节点）带进去，进行调试运行并打印验证你的想法。
* 
* 小结：（不管是前、中、后序遍历的二叉树做flatten处理的scenarios，可以总结如下）
*    正如非recursion的处理一样，其本质按照某个遍历（前序，中序，后序）顺序的就是要存好这个树，在把结果一路向（右）串联起来。
*    这个顺序进行flattenTree()处理【即从这一顺序遍历所有的节点中，无脑地pre.right = current&pre.left = null即可】。
*    在递归处理的中，先做分治存好node的处理好left分支和处理好right分支（每个flatternTree()处理好的分支，就是已经变成一个linkedList，并存好这个分支的最后一个tail node）。
*    然后根据具体的遍历顺序做相应的connection处理，
*    比如：（1）前序：leftTail.right = node.right(注：node.right这是原来node的right分支节点)。
*         （2）中序：把经过flattenTree()处理的node节点left分支的最后一个节点,做（leftTail.right = node）链接处理。
*         （3）后序：把经过flattenTree()处理的node节点left分支的最后一个节点，做(leftTai.right = node.right[node.right是原来node的right节点]并且rightTail.right = node)链接处理。
**/
public class Solution {
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        // check corner case
        if (root == null) {
            return;
        }
	
        flattenHelper(root);
    }

    // helper methods
    /**
    * 注意： （1）为什么要返回最后一个点？
    *           答：主要是为了方便于（根结点，被摊平的左子树， 被摊平的右子树之间）拼接操作。
    *       （2）为什么此helper方法，不返回被摊平的起始点和最后一个点，偏偏只返回最后一个点？
    *。         答：因为起始点，通过原来树的根节点的两个左右孩子节点获得，所以无需再提供。
    **/
    private TreeNode flattenTree(TreeNode node) {
        if (node == null) {
            return null;
        }
	
	if (node != null && node.left == null && node.right == null) {
            return node;
	}
	
        TreeNode leftTail = flattenTree(node.left);
        TreeNode rightTail = flattenTree(node.right);

        // connect leftTail to node.right
        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }

        return (rightTail != null) ? rightTail : leftTail;
    }
}

//recursion-2: recursion
public class Solution {
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        // check corner cases
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            return;
        }

        if (root.left == null) {
            flatten(root.right);
            return;
        }

        // normal case
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;

        // divide
        flatten(leftNode);
        flatten(rightNode);

        // conquer
        root.right = leftNode;
        root.left = null;//remove its left branch, since left branch has been flattened and shift to right side

         if (rightNode == null) {
            return;
        }

        // check the flattened left branch nodes, find the last node to link the right branch headed node
        TreeNode current = leftNode;

        while (current.right != null) {
            current = current.right;
        }

        // let the last node to connected right branch headed node
        current.right = rightNode;
    }
}

//version-3 none-recursion
/**
* DFS或者分递归前序遍历这棵树，然后把结果一路向右串联起来
* 小结： 遇到二叉树的问题，就想想整棵树在该问题上的结果和左右子树在该问题结果之间有什么联系
**/
public class Solution {
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        // check corner case
        if (root == null || 
            root.left == null && root.right == null) {
            return;
        }
	
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
	
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
	
            if (current.right != null) {
                stack.push(current.right);
            }
	
            if (current.left != null) {
                stack.push(current.left);
            }
	
            current.left = null;
            current.right = (!stack.isEmpty()) ? stack.peek() : null;
        }
    }	
}
