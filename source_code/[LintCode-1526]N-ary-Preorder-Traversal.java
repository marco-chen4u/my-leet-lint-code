/***
* LintCode 1526. N-ary Tree Preorder Traversal
Given an n-ary tree, return the preorder traversal of its nodes' values.

Example
    Input : {1,3,2,4#2#3,5,6#4#5#6}
    Output: [1,3,5,6,2,4]
    Explaination:
                  1
               /  |  \
              3   2   4
            /  \
           5    6
           
           the preorder traversal:
                                   1->3->5->6
                                      ->2
                                      ->4
                                   the result of the order: 1->3->5->2->4
***/
/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) {
 *         label = x;
 *         neighbors = new ArrayList<UndirectedGraphNode>();
 *     }
 * }
 */
//version-1:Tree Preorder Traversal-recursion
public class Solution {
    /**
     * @param root: the tree
     * @return: pre order of the tree
     */
    public List<Integer> preorder(UndirectedGraphNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<UndirectedGraphNode> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            UndirectedGraphNode current = stack.pop();
            result.add(current.label);

            int size = current.neighbors.size();
            for (int i = size - 1; i >= 0; i--) {
                UndirectedGraphNode next = current.neighbors.get(i);
                stack.push(next);
            }
        }

        return result;
    }
}

//version-2:Tree Preorder traversal-non-recursion
public class Solution {
    /**
     * @param root: the tree
     * @return: pre order of the tree
     */
    public List<Integer> preorder(UndirectedGraphNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<UndirectedGraphNode> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            UndirectedGraphNode current = stack.pop();
            result.add(current.label);

            int size = current.neighbors.size();
            for (int i = size - 1; i >= 0; i--) {
                UndirectedGraphNode next = current.neighbors.get(i);
                stack.push(next);
            }
        }

        return result;
    }
}
