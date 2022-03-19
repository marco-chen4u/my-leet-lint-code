/**
* LeetCode 705. Design HashSet
Design a HashSet without using any built-in hash table libraries.

Implement MyHashSet class:
    -void add(key) Inserts the value key into the HashSet.
    -bool contains(key) Returns whether the value key exists in the HashSet or not.
    -void remove(key) Removes the value key in the HashSet. 
     If key does not exist in the HashSet, do nothing.

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
**/
//version-1:
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
