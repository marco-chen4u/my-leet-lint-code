/**
* LeetCode 705. Design HashSet
Design a HashSet without using any built-in hash table libraries.

Implement MyHashSet class:
    -void add(key)      Inserts the value key into the HashSet.
    -bool contains(key) Returns whether the value key exists in the HashSet or not.
    -void remove(key)   Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.

Example 1:
    Input
        ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
        [[], [1], [2], [1], [3], [2], [2], [2], [2]]
    Output
        [null, null, null, true, false, null, true, null, false]
    Explanation
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);      // set = [1]
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(1); // return True
        myHashSet.contains(3); // return False, (not found)
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(2); // return True
        myHashSet.remove(2);   // set = [1]
        myHashSet.contains(2); // return False, (already removed)

Constraints:
    0 <= key <= 10^6
    At most 10^4 calls will be made to add, remove, and contains.
    
Link: https://leetcode.com/problems/design-hashset/
**/
//version-1: linked list implementation with bucket, time complexity: O(n)
class MyHashSet {
    
    // constants
    private static final int DEFAULT_VALUE = 765;
    private static final int SEED = 33;
    
    // fields
    private Bucket[] bucketArray;
    private int keyRange;
    
    // inner class
    class Bucket {
        // fields
        private List<Integer> container;
        
        // constructor
        public Bucket() {
            this.container = new LinkedList<Integer>();
        }
        
        // method
        public void insert(Integer key) {
            if (!isExist(key)) {
                this.container.add(key);
            }
        }
        
        public void delete(Integer key) {
            if (isExist(key)) {
                this.container.remove(key);    
            }
        }
        
        public boolean isExist(Integer key) {
            return this.container.contains(key);
        }
    }
    
    // constructor
    public MyHashSet() {
        int size = DEFAULT_VALUE;
        this.keyRange = size;
        this.bucketArray = new Bucket[size];
        for (int i = 0; i < size; i++) {
            bucketArray[i] = new Bucket();
        }
    }
    
    // public methods    
    public void add(int key) {
        int index = hash(key);
        bucketArray[index].insert(key);
    }
    
    public void remove(int key) {
        int index = hash(key);
        bucketArray[index].delete(key);
    }
    
    public boolean contains(int key) {
        int index = hash(key);
        return bucketArray[index].isExist(key);
    }
    
    // helper methods
    private int hash(int key) {
        return (key * SEED) % keyRange;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */


//version-2: binary search tree implementation for the bucket, time complexity: O(logn)
class MyHashSet {
    
    // constants
    private static final int DEFAULT_VALUE = 765;
    private static final int SEED = 33;
    
    // fields
    private Bucket[] bucketArray;
    private int keyRange;
    
    // inner class
    class Bucket {
        // fields
        private BSTree tree;
        
        // constructor
        public Bucket() {
            this.tree = new BSTree();
        }
        
        // method
        public void insert(Integer key) {
            this.tree.root = tree.insert(key);
        }
        
        public void delete(Integer key) {
            this.tree.root = tree.remove(key);
        }
        
        public boolean isExist(Integer key) {
            TreeNode node = tree.search(key);
            return (node != null);
        }
    }
    
    class BSTree {
        // fields
        TreeNode root = null;
        
        // // constructor
        // public BSTree() {
        //     root = null;
        // }
        
        // methods
        public TreeNode insert(int value) {
            return insert(root,value);
        }
        
        public TreeNode insert(TreeNode node, int val) {
            if (node == null) {
                return new TreeNode(val);
            }
            
            if (val < node.val) {
                node.left = insert(node.left, val);
            }
            
            if (val > node.val) {
                node.right = insert(node.right, val);
            }
            
            return node;
        }
        
       
        public TreeNode remove(int value) {
            return remove(root, value);
        }
        
        public TreeNode remove(TreeNode node, int target) {
            // check corner case
            if (node == null) {
                return node;
            }
            
            if (target < node.val) {
                node.left = remove(node.left, target);
                return node;
            }
            
            if (target > node.val) {
                node.right = remove(node.right, target);
                return node;
            }
            
            // target = node.val
            if (isLeaf(node)) {
                return null;
            }
            
            if (node.left == null) {
                return node.right;
            }
            
            if (node.right == null) {
                return node.left;
            }
            
            TreeNode rightChild = node.right;
            TreeNode current = rightChild;
            while (current.left != null) {
                current = current.left;
            }
            
            node.val = current.val;
            remove(rightChild, current.val);
            
            return node;
        }
        
        private boolean isLeaf(TreeNode node) {
            return node != null && node.left == null && node.right == null;
        }
        
        private TreeNode next(TreeNode node) {
            if (node == null) {
                return node;
            }
            
            node = node.right;
            
            while (node.left != null) {
                node = node.left;
            }
            
            return node;
        }
        
        private TreeNode previous(TreeNode node) {
            if (node == null) {
                return node;
            }
            
            node = node.left;
            while (node.right != null) {
                node = node.right;
            }
            
            return node;
        }
        
        public TreeNode search(int target) {
            return search(root, target);
        }
        
        public TreeNode search(TreeNode node, int target) {
            String nodeValue = node == null ? "[]" : node.toString();
            
            System.out.println("node = " + nodeValue + ", target =" + target);
            
            if (node == null || node.val == target) {
                return node;
            }
            
            return (target < node.val) ? search(node.left, target) :search(node.right, target);            
        }
    }
    
    class TreeNode {
        // fields
        int val;
        TreeNode left;
        TreeNode right;
        
        // constructor
        public TreeNode(int value) {
            this.val = value;
        }
        
        public String toString() {
            return "[value = " + val + "]";
        }
    }
    
    // constructor
    public MyHashSet() {
        int size = DEFAULT_VALUE;
        this.keyRange = size;
        this.bucketArray = new Bucket[size];
        for (int i = 0; i < size; i++) {
            bucketArray[i] = new Bucket();
        }
    }
    
    // public methods    
    public void add(int key) {
        int index = hash(key);
        bucketArray[index].insert(key);
    }
    
    public void remove(int key) {
        int index = hash(key);
        bucketArray[index].delete(key);
    }
    
    public boolean contains(int key) {
        int index = hash(key);
        return bucketArray[index].isExist(key);
    }
    
    // helper methods
    private int hash(int key) {
        return (key * SEED) % keyRange;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
