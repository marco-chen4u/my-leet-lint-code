/***
* LintCode 1371. Linked List Components
We are given head, the head node of a linked list containing unique integer values.
We are also given the list G, a subset of the values in the linked list.
Return the number of connected components in G, where two values are connected if they appear consecutively in the linked list.

Example
	Example 1:
		Input: head = 0->1->2->3, G = [0, 1, 3]
		Output: 2
		Explanation: 
			0 and 1 are connected, so [0, 1] and [3] are the two connected components.
		
	Example 2:
		Input: head = 0->1->2->3->4, G = [0, 3, 1, 4]
		Output: 2
		Explanation: 
			0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.

Notice
	-If N is the length of the linked list given by head, 1 \leq N \leq 100001≤N≤10000.
	-The value of each node in the linked list will be in the range[0, N - 1].
	-1≤G.length≤10000.
	-G is a subset of all values in the linked list.
***/
/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
//version-1: union find
public class Solution {
    // fields
    private final int default_value = Integer.MIN_VALUE;
    
    /**
     * @param head: the head
     * @param G: an array
     * @return: the number of connected components in G
     */
    public int numComponents(ListNode head, int[] G) {
        // check corner case
        if (head == null || G == null || G.length == 0) {
            return 0;
        }
        
        int left = default_value;
        int right = default_value;
        UnionFind uf = new UnionFind(G);
        
        while (head != null) {
            left = right;
            right = head.val;
            
            if (uf.contains(left, right)) {
                uf.union(left, right);
            }
            
            head = head.next;
        }
        
        return uf.getComponentCount();
    }
}

// helper class
class UnionFind {
    // fields
    Map<Integer, Integer> fathers;
    int count;
    
    // contructor
    public UnionFind(int[] nums) {
        this.count = nums.length;
        fathers = new HashMap<Integer, Integer>();
        
        for (int num : nums) {
            fathers.put(num, num);
        }
    }
    
    private int find(int x) {
        int superParent = fathers.get(x);
        while (superParent != fathers.get(superParent)) {
            superParent = fathers.get(superParent);
        }
        
        int tmp = -1;
        int parent = x;
        while (parent != fathers.get(parent)) {
            tmp = fathers.get(parent);
            fathers.put(parent, superParent);
            parent = tmp;
        }
        
        return superParent;
    }
    
    public void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);
        
        if (parentX != parentY) {
            fathers.put(parentX, parentY);
            count -= (count > 0) ? 1 : 0;
        }
    }
    
    public boolean contains(int num1, int num2) {
        return fathers.containsKey(num1) && fathers.containsKey(num2);
    }
    
    public int getComponentCount() {
        return count;
    }
}

//version-2: HashSet
public class Solution {    
    /**
     * @param head: the head
     * @param G: an array
     * @return: the number of connected components in G
     */
    public int numComponents(ListNode head, int[] G) {
		
		int count = 0;
		Set<Integer> set = new HashSet<Integer>();
		for (int value : G) {
			set.add(value);
		}
		
		ListNode current = head;
		while (current != null) {
			if (set.contains(current.val) && (current.next == null || !set.contains(current.next.val))) {
				count++;
			}
			
			current = current.next;
		}
		
		return count;
	}
}