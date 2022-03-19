/**
* LeetCode 706. Design HashMap
Design a HashMap without using any built-in hash table libraries.

Implement the MyHashMap class:
    -MyHashMap() initializes the object with an empty map.
    -void put(int key, int value) inserts a (key, value) pair into the HashMap. 
          If the key already exists in the map, update the corresponding value.
    -int get(int key) returns the value to which the specified key is mapped, 
          or -1 if this map contains no mapping for the key.
    -void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
    
Example 1:
    Input
        ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
        [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
    Output
        [null, null, null, 1, -1, null, 1, null, -1]
    Explanation
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1); // The map is now [[1,1]]
        myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
        myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
        myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
        myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
        myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
        myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
        myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
        
Constraints:
    0 <= key, value <= 10^6
    At most 10^4 calls will be made to put, get, and remove.
    
Link: https://leetcode.com/problems/design-hashmap/

Tages: Array, Hash Table, Hash Function, Design, 
**/

class MyHashMap {
    // constants
    private static final int DEFAULT_VALUE = -1;
    private static final int SEED = 33;
    private static final int DEFAULT_KEY_SAPCE_SIZE = 2075;
    
    // fields
    private int keySpace;
    private int size;
    private Bucket[] hashTable;

    // inner classes
    class Pair {
        // fields
        int key;
        int value;
        
        // constructor
        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    class Bucket{
        // fields
        private List<Pair> bucketList;
        
        // constructor
        public Bucket() {
            this.bucketList = new LinkedList<>();
        }
        
        // method
        public Integer get(Integer key) {
            Pair pair = search(key);
            return pair == null ? DEFAULT_VALUE : pair.value;
        }
        
        public void update(Integer key, Integer value) {
            Pair pair = search(key);
            if (pair != null) {
                pair.value = value;
                return;
            }
            
            bucketList.add(new Pair(key, value));
        }
        
        public void remove(Integer key) {
            Pair value = search(key);
            
            if (value != null) {
                bucketList.remove(value);
            }
        }
        
        // helper method
        private Pair search(Integer key) {
            Pair result = null;
            
            for (Pair pair : bucketList) {
                if (key != pair.key) {
                    continue;
                }
                
                result = pair;
                break;
            }
            
            return result;
        }
    }
    
    // constructor
    public MyHashMap() {
        this.keySpace = DEFAULT_KEY_SAPCE_SIZE;
        this.size = DEFAULT_KEY_SAPCE_SIZE;
        
        this.hashTable = new Bucket[size];
        for (int i = 0; i < size; i++) {
            this.hashTable[i] = new Bucket();
        }
        
    }
    
    // methods
    public void put(int key, int value) {
        int hashKey = hash(key);
        this.hashTable[hashKey].update(key, value);
    }
    
    public int get(int key) {
        int hashKey = hash(key);
        return this.hashTable[hashKey].get(key);
    }
    
    public void remove(int key) {
        int hashKey = hash(key);
        this.hashTable[hashKey].remove(key);
    }
    
    // helper methods
    private int hash(int key) {
        return key * SEED % keySpace;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
