/***
* LintCode 24. LFU Cache
LFU (Least Frequently Used) is a famous cache eviction algorithm.
For a cache with capacity k, if the cache is full and need to evict a key in it, 
the key with the lease frequently used will be kicked out.

Implement set and get method for LFU cache.

Example
	Input:
		LFUCache(3)
		set(2,2)
		set(1,1)
		get(2)
		get(1)
		get(2)
		set(3,3)
		set(4,4)
		get(3)
		get(2)
		get(1)
		get(4)
	Output:
		2
		1
		2
		-1
		2
		1
		4
***/
public class LFUCache {
    // inner class to the centent of the cache
    class CacheNode {
        int key;
        int value;
        int frequency;
        public CacheNode(int key, int value, int frequency) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
        }
    }
    
    // fields
    private int capacity;
    private int lowestFrequency;
    private int maxFrequency;
    private Set<CacheNode>[] frequencySets;
    private Map<Integer, CacheNode> cache;
    
    // private methods
    private void initializeFrequencySets() {
        for (int i = 0; i < frequencySets.length; i++) {
            frequencySets[i] = new LinkedHashSet<CacheNode>();
        }
    }
    
    private void updateFrequency(CacheNode node) {
        // check corner case
        if (node == null) {
            return;
        }
        
        if (node.frequency < this.maxFrequency) {
            upgradeFrequency(node);
        }
        else {
            // let the most recently accessed to get ahead of others
            Set<CacheNode> maxFrequenxySet = this.frequencySets[maxFrequency];
            maxFrequenxySet.remove(node);
            maxFrequenxySet.add(node);
        }
    }

    private void upgradeFrequency(CacheNode node) {
        int oldFrequency = node.frequency;
        int newFrequency = oldFrequency + 1;
        Set<CacheNode> oldFrequencySet = this.frequencySets[oldFrequency];
        Set<CacheNode> newFrequencySet = this.frequencySets[newFrequency];
        
        oldFrequencySet.remove(node);
        node.frequency = newFrequency;
        newFrequencySet.add(node);
        
        if (this.lowestFrequency == oldFrequency && oldFrequencySet.isEmpty()) {
            this.lowestFrequency = newFrequency;
        }
    }
    
    private void doEviction() {
        int i = 0;
        int target = 1;// just one
        
        while (i < target) {
            Set<CacheNode> lowestFrequecySet = this.frequencySets[lowestFrequency];
            Iterator<CacheNode> it = lowestFrequecySet.iterator();
            
            while(it.hasNext() && i++ < target) {
                CacheNode node = it.next();
                it.remove();
                
                this.cache.remove(node.key);
            }
            
            if (lowestFrequecySet.isEmpty()) {
                findNextLowestFrequency();
            }
        }
    }
    
    private void findNextLowestFrequency() {
        while (this.lowestFrequency < maxFrequency && this.frequencySets[lowestFrequency].isEmpty()) {
            this.lowestFrequency++;
        }
        
        // reset to default
        if (this.lowestFrequency > maxFrequency) {
            this.lowestFrequency = 0;
        }
    }
    
    // constructor
    /*
    * @param capacity: An integer
    */public LFUCache(int capacity) {
        this.capacity = capacity;
        this.lowestFrequency = 0;
        this.maxFrequency = capacity;
        this.frequencySets = new Set[capacity * 2];
        this.cache = new HashMap<Integer, CacheNode>(capacity);
        
        initializeFrequencySets();
    }

    // public methods
    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        CacheNode node = this.cache.get(key);
        
        if (node != null) {
            node = this.cache.get(key);
            node.value = value;
        }
        else {
            if (cache.size() == this.capacity) {
                doEviction();
            }
            
            this.lowestFrequency = 0;
            node = new CacheNode(key, value, lowestFrequency);
            cache.put(key, node);
        }
        
        updateFrequency(node);
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        CacheNode node = this.cache.get(key);
        if (node != null) {
            updateFrequency(node);
            return node.value;
        }
        else {
            return -1;
        }
    }
}