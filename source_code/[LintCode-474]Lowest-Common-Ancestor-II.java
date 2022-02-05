/***
* LintCode 474. Lowest Common Ancestor II
Given the root and two nodes in a Binary Tree. 
Find the lowest common ancestor(LCA) of the two nodes.

The nearest common ancestor of two nodes refers to the nearest common node among all the parent nodes of two nodes (including the two nodes).

In addition to the left and right son pointers, each node also contains a father pointer, parent, pointing to its own father.

Example 1:
    Input：{4,3,7,#,#,5,6},3,5
    Output：4
    Explanation：
              4
             / \
            3   7
               / \
              5   6
        LCA(3, 5) = 4
        
Example 2:
    Input：{4,3,7,#,#,5,6},5,6
    Output：7
    Explanation：
              4
             / \
            3   7
               / \
              5   6
        LCA(5, 6) = 7
        
 Related:
 LintCode 578. Lowest Common Ancestor III
 LintCode 88. Lowest Common Ancestor of a Binary Tree 
***/
//version-1:iteration
/**
 * Definition of ParentTreeNode:
 * 
 * class ParentTreeNode {
 *     public ParentTreeNode parent, left, right;
 * }
 */
public class Solution {
    /*
     * @param root: The root of the tree
     * @param A: node in the tree
     * @param B: node in the tree
     * @return: The lowest common ancestor of A and B
     */
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        // check corner cases
        if (root == null || A == null || B == null) {
            return null;
        }
        
        if (root == A || root == B) {
            return root;
        }
        
        List<ParentTreeNode> ancestorsOfA = new ArrayList<ParentTreeNode>();
        while (A != null) {
            ancestorsOfA.add(A);
            A = A.parent;
        }
        
        List<ParentTreeNode> ancestorsOfB = new ArrayList<ParentTreeNode>();
        while (B != null) {
            ancestorsOfB.add(B);
            B = B.parent;
        }
        
        int indexA = ancestorsOfA.size() - 1;
        int indexB = ancestorsOfB.size() - 1;
        ParentTreeNode result = null;
        
        while (indexA >= 0 && indexB >= 0) {
            if (ancestorsOfA.get(indexA) != ancestorsOfB.get(indexB)) {
                break;
            }
            
            result = ancestorsOfA.get(indexA);
            
            indexA--;
            indexB--;
        }
        
        return result;
    }
}



