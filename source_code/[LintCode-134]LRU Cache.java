/***
* LintCode 134. LRU Cache
Design and implement a data structure for Least Recently Used (LRU) cache. 
It should support the following operations: get and set.
    -get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
    -set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, 
        it should invalidate the least recently used item before inserting a new item. 
	Finally, you need to return the data from each get

Example1
    Input:
        LRUCache(2)
        set(2, 1)
        set(1, 1)
        get(2)
        set(4, 1)
        get(1)
        get(2)
    Output: [1,-1,1]
    Explanation：
        cache cap is 2，set(2,1)，set(1, 1)，get(2) and return 1，set(4,1) and delete (1,1)，
	because （1,1）is the least use，get(1) and return -1，get(2) and return 1.
	
Example 2:
    Input：
        LRUCache(1)
        set(2, 1)
        get(2)
        set(3, 2)
        get(2)
        get(3)
    Output：[1,-1,2]
    Explanation：
        cache cap is 1，set(2,1)，get(2) and return 1，set(3,2) and delete (2,1)，get(2) and return -1，get(3) and return 2.
***/

public class LRUCache {
    // inner class
    class CacheNode {
        // fields
        int key;
        int value;
        CacheNode pre;
        CacheNode next;
        // constructor
        public CacheNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    // fields
    private int capacity;
    private Map<Integer, CacheNode> map;
    private CacheNode header;
    private CacheNode tail;
    
    // helper methods
    private void moveToLast(CacheNode node) {
        if (node == null) {
            return;
        }

        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }

        node.next = tail;
        node.pre = tail.pre;
        tail.pre.next = node;
        tail.pre = node;		
    }

    private void doUpdateCaching(CacheNode node) {
        moveToLast(node);
    }

    private void doEviction() {
        CacheNode node = header.next;

        if (node.equals(tail)) {
            return;
        }

        header.next = node.next;
        node.next.pre = header;		
        map.remove(node.key);
    }

    /*
    * @param capacity: An integer
    */public LRUCache(int capacity) {
        // do intialization if necessary
        this.capacity = capacity;
        map = new HashMap<Integer, CacheNode>();        
        header = new CacheNode(Integer.MIN_VALUE, Integer.MIN_VALUE);
        tail = new CacheNode(Integer.MAX_VALUE, Integer.MAX_VALUE);
        header.next = tail;
        tail.pre = header;
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        int result = -1;// default;

        if (map.containsKey(key)) {
            CacheNode node = map.get(key);
            result = node.value;            
            doUpdateCaching(node);
        }

        return result;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        if (map.containsKey(key)) {
            CacheNode node = map.get(key);
            node.value = value;

            doUpdateCaching(node);
            return;
        }
        
        if (map.size() >= capacity) {
            doEviction();
        }

        CacheNode node = new CacheNode(key, value);
        map.put(key, node);

        doUpdateCaching(node);
    }
}
