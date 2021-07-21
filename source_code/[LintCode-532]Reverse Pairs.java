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
//version-1: brute-force, O(n^2)
public class Solution {
    /**
     * @param A: an array
     * @return: total of reverse pairs
     */
    public long reversePairs(int[] nums) {
        int result = 0;
        // check corner case
        if (nums == null || nums.length <= 1) {
            return result;
        }

        int size = nums.length;
        int lastPos = size - 1;

        for (int i = 0; i < lastPos; i++) {
            for (int j = i + 1; j <= lastPos; j++) {
                result += (nums[i] > nums[j]) ? 1 : 0;
            }
        }

        return result;
    }
}


//version-2: mergeSort + 2 pointers, O(nlogn)
public class Solution {

    /**
     * @param A: an array
     * @return: total of reverse pairs
     */
    public long reversePairs(int[] nums) {
        int result = 0;
        // check corner case
        if (nums == null || nums.length <= 1) {
            return result;
        }

        int start = 0;
        int end = nums.length - 1;

        return mergeSort(nums, 0, end);
    }

    // helper method
    private int mergeSort(int[] nums, int start, int end) {
        int result = 0;
        // check corner case
        if (start >= end) {
            return result;
        }

        // normal case
        int mid = start + (end - start) / 2;
        result += mergeSort(nums, start, mid);
        result += mergeSort(nums, mid + 1, end);

        result +=merge(nums, start, mid, end);

        return result;
    }

    private int merge(int[] nums, int start, int mid, int end) {
        int count = 0;
        int size = end - start + 1;
        int[] result = new int[size];

        int leftStart = start;
        int leftEnd = mid;
        int rightStart = mid + 1;
        int rightEnd = end;

        int i = leftStart;
        int j = rightStart;

        int index = 0;

        while (i <= leftEnd && j <= rightEnd) {
            if (nums[i] <= nums[j]) {
                result[index++] = nums[i++]; 
            }
            else {
                count+= mid - i + 1;
                result[index++] = nums[j++];
            }
        }

        while (i <= leftEnd) {
            result[index++] = nums[i++];
        }

        while (j <= rightEnd) {
            result[index++] = nums[j++];
        }

        index = 0;
        for (int k = start; k <= end; k++) {
            nums[k] = result[index++];
        }

        return count;
    }
}

//version-3:binary search tree, O(nlogn), space : O(n)
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
