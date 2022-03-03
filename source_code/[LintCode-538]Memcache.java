/***
* LintCode 538. Memcache
Implement a memcache which support the following features:
    -get(curtTime, key). Get the key's value, return 2147483647 if key does not exist.
    -set(curtTime, key, value, ttl). Set the key-value pair in memcache with a time to live (ttl). 
        The key will be valid from curtTime to curtTime + ttl - 1 and it will be expired after ttl seconds. 
        if ttl is 0, the key lives forever until out of memory.
    -delete(curtTime, key). Delete the key.
    -incr(curtTime, key, delta). Increase the key's value by delta return the new value. 
        Return 2147483647 if key does not exist.
    -decr(curtTime, key, delta). Decrease the key's value by delta return the new value. 
        Return 2147483647 if key does not exist.
It's guaranteed that the input is given with increasingcurtTime.

Example1
    get(1, 0)
    >> 2147483647
    set(2, 1, 1, 2)
    get(3, 1)
    >> 1
    get(4, 1)
    >> 2147483647
    incr(5, 1, 1)
    >> 2147483647
    set(6, 1, 3, 0)
    incr(7, 1, 1)
    >> 4
    decr(8, 1, 1)
    >> 3
    get(9, 1)
    >> 3
    delete(10, 1)
    get(11, 1)
    >> 2147483647
    incr(12, 1, 1)
    >> 2147483647

Clarification
    Actually, a real memcache server will evict keys if memory is not sufficient, 
    and it also supports variety of value types like string and integer. 
    In our case, let's make it simple, 
    we can assume that we have enough memory and all of the values are integers.

    Search "LRU" & "LFU" on google to get more information about how memcache evict data.

Try the following problem to learn LRU cache:
    http://www.lintcode.com/problem/lru-cache
***/
/**
 * Definition of Column:
 * public class Column {
 *     public int key;
 *     public String value;
 *     public Column(int key, String value) {
 *         this.key = key;
 *         this.value = value;
 *    }
 * }
 */
public class Memcache {
    // inner class
    class CacheNode {
        // fields
        int expired;
        int value;

        // constructor
        public CacheNode(int expired, int value) {
            this.expired = expired;
            this.value = value;
        }
    }

    // fields
    private final int DEFAULT_VALUE = Integer.MAX_VALUE;
    private final int LIVE_FOR_EVER = -1;

    private Map<Integer, CacheNode> cacheMap;
	
    public Memcache() {
        // do intialization if necessary
        this.cacheMap = new HashMap<Integer, CacheNode>(); 
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @return: An integer
     */
    public int get(int curtTime, int key) {
        if (!cacheMap.containsKey(key)) {
            return DEFAULT_VALUE;
        }

        CacheNode currentNode = cacheMap.get(key);

        if (currentNode.expired >= curtTime || 
            currentNode.expired == LIVE_FOR_EVER) {
            return currentNode.value;
        }

        return DEFAULT_VALUE;
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @param value: An integer
     * @param ttl: An integer
     * @return: nothing
     */
    public void set(int curtTime, int key, int value, int ttl) {
        int expired = 0;
        if (ttl == 0) {
            expired = LIVE_FOR_EVER;
        }
        else {
            expired = curtTime + ttl - 1;
        }

        cacheMap.putIfAbsent(key, new CacheNode(LIVE_FOR_EVER, DEFAULT_VALUE));

        CacheNode currentNode = cacheMap.get(key);
        currentNode.expired = expired;
        currentNode.value = value;		
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @return: nothing
     */
    public void delete(int curtTime, int key) {
        if (!cacheMap.containsKey(key)) {
            return;
        }

        cacheMap.remove(key);
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @param delta: An integer
     * @return: An integer
     */
    public int incr(int curtTime, int key, int delta) {
        if (get(curtTime, key) == DEFAULT_VALUE) {
            return DEFAULT_VALUE;
        }

        cacheMap.get(key).value += delta;

        return cacheMap.get(key).value;
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @param delta: An integer
     * @return: An integer
     */
    public int decr(int curtTime, int key, int delta) {
        if (get(curtTime, key) == DEFAULT_VALUE) {
            return DEFAULT_VALUE;
        }

        cacheMap.get(key).value -= delta;

        return cacheMap.get(key).value;
    }
}
