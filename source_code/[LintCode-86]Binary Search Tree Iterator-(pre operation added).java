/***
* LintCode 86. Binary Search Tree Iterator-(pre operation added)
Design an iterator over a binary search tree with the following rules:
	-Elements are visited in ascending order (i.e. an in-order traversal)
	-next() and hasNext() queries run in O(1) time in average.
Example
    Example 1
        Input:  {10,1,11,#,6,#,12}
        Output:  [1, 6, 10, 11, 12]
        Explanation:
            The BST is look like this:
                  10
                  /\
                 1 11
                  \  \
                   6  12
            You can return the inorder traversal of a BST [1, 6, 10, 11, 12]

    Example 2
        Input: {2,1,3}
        Output: [1,2,3]
        Explanation:
            The BST is look like this:
                  2
                 / \
                1   3
            You can return the inorder traversal of a BST tree [1,2,3]
Challenge
    Extra memory usage O(h), h is the height of the tree.
    Super Star: Extra memory usage O(1)
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
 * Example of iterate a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * } 
 */
//version-1
public class BSTIterator {
    // fields
    private Stack<TreeNode> stack;
    private TreeNode current;
    private NavigableSet<TreeNode> treeSet;

    /*
    * @param root: The root of binary tree.
    */public BSTIterator(TreeNode root) {
        this.stack = new Stack<TreeNode>();
        this.treeSet = new TreeSet<TreeNode>((a,b)->a.val - b.val);
        this.current = root;

        TreeNode pre = null;
        while (current != null) {
            stack.push(current);
            treeSet.add(current);
            pre = current;
            current = current.left;
        }

        current = stack.peek();
    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        return (current != null) ? true : !stack.isEmpty();
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        TreeNode result = null;		

        result = current;	
        stack.pop();

        current = current.right;
        while (current != null) {
            stack.push(current);
            treeSet.add(current);
            current = current.left;
        }

        if (current == null) {
            current = stack.peek();
        }

        return result;
    }

    public TreeNode pre() {
        return (current == null) ? null : treeSet.lower(current)
    }
}

//version-2:
public class BSTIterator {
    // fields
    private Stack<TreeNode> stack;
    private Stack<TreeNode> backUpStack;
    private TreeNode current;

    /*
    * @param root: The root of binary tree.
    */public BSTIterator(TreeNode root) {
        this.stack = new Stack<TreeNode>();
        this.backUpStack = new Stack<TreeNode>();

        this.current = root;
    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        return (current != null) ? true : !stack.isEmpty();
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        TreeNode result = null;	
        
        while (current != null) {
            stack.push(current);
            current = current.left;
        }

        current = stack.pop();
        
        result = current;
        backUpStack.push(result);
        
        current = current.right;
        
        return result;
    }

    public TreeNode pre() {
        return (current == null) ? null : lowerTop();
    }
    
    // helper method for pre() method
    private TreeNode lowerTop() {
        if (backUpStack.isEmpty) {
            return null;
        }
        
        TreeNode top = backUpStack.pop();
        
        TreeNode result = backUpStack.isEmpty() ? null : backUpStack.peek();
        
        backUpStack.push(top);
        
        return result;
    }
}

//version-3
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
 * Example of iterate a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * } 
 */
public class BSTIterator {
    // fields
    private Stack<TreeNode> stack;
    private Stack<TreeNode> backUpStack;
    private TreeNode current;

    /*
    * @param root: The root of binary tree.
    */public BSTIterator(TreeNode root) {
        this.stack = new Stack<TreeNode>();
        this.backUpStack = new Stack<TreeNode>();

        this.current = root;
    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        return (current != null) ? true : !stack.isEmpty();
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        TreeNode result = null;	
        
        while (current != null) {
            stack.push(current);
            current = current.left;
        }

        current = stack.pop();
        
        result = current;
        backUpStack.push(result);
        
        current = current.right;
        
        return result;
    }

    public TreeNode pre() {
        return (current == null) ? null : backUpStack.peek();
    }
}
