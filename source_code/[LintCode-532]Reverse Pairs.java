/***
* LintCode 532. Reverse Pairs
Reverse pair is a pair of numbers (A[i], A[j]) such that A[i] > A[j] and i < j. 
Given an array, return the number of reverse pairs in the array.

Example
Example1
	Input:  A = [2, 4, 1, 3, 5]
	Output: 3
	Explanation:
		(2, 1), (4, 1), (4, 3) are reverse pairs
Example2
	Input:  A = [1, 2, 3, 4]
	Output: 0
	Explanation:
		No reverse pair
***/
public class Solution {
    class Node {
        int val, count;
        Node left, right;

        Node(int val) {
            this.val = val; // 节点值
            this.count = 1; // 大于等于该节点的元素个数
            this.left = this.right = null;
        }
    }

    /*
     * @param A: an array
     * @return: total of reverse pairs
     */
    public long reversePairs(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        Node root = null;
        int result = 0;
        // 遍历数组，查询每个数所能组成的逆序对个数，然后相加起来
        for (int i : A) {
            result += search(root, i + 1);
            root = insert(root, i);
        }

        return result;
    }

    private int search(Node root, int target) {
        // 如果 root 为空，直接返回 0 即可
        if (root == null) {
            return 0;
        // 如果 root.val 与 所要查找的值 相等，则直接返回 root.count 即可
        } else if (root.val == target) {
            return root.count;
        // 如果 root.val 小于 所要查找的值，说明目前还无法组成逆序对，进一步对 root 的右子树进行查找
        } else if (root.val < target) {
            return search(root.right, target);
        // 如果 root.val 大于 所要查找的值，说明可以组成逆序对，并且进一步对 root 的左子树进行查找
        } else {
            return root.count + search(root.left, target);
        }
    }

    private Node insert(Node root, int val) {
        // 如果 root 为空，直接建立新的节点并返回
        if (root == null) {
            return new Node(val);
        // 如果 root.val == val，则 root.count 需要加1
        } else if (root.val == val) {
            root.count++;
        // 如果 root.val < val,则说明新插入的值比 root 要大
        // 所以更新 root.count 使其 加1，并将其插入到 root 的右子树中
        } else if (root.val < val) {
            root.count++;
            root.right = insert(root.right, val);
        // 如果 root.val > val，这说明新插入的值比 root 小，
        // root.count 无需更新，将新节点插入到 root 的左子树中即可。
        } else {
            root.left = insert(root.left, val);
        }
        return root;
    }
}