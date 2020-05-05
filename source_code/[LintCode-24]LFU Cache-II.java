/***
* LintCode 24. LFU Cache
LFU (Least Frequently Used) is a famous cache eviction algorithm.
For a cache with capacity k, 
if the cache is full and need to evict a key in it, the key with the lease frequently used will be kicked out.
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
	// inner class
	class CacheNode {
		// fields
		int key;
		int value;
		int frequency;		
		// constructor
		public CacheNode(int key, int value, int freq) {
			this.key = key;
			this.value = value;
			this.frequency = freq;
		}
	}
	
	// fields
	private final int DEFAULT_VALUE = 1;
	private Map<Integer, CacheNode> map;
	private Map<Integer, List<CacheNode>> freqMap;
	private int minFreq;
	private int capacity;
	
	// helper methods
	private void upgradeFrequency(CacheNode node) {
		// check corner case
		if (node == null) {
			return;
		}
		
		int freq = node.frequency;
		List<CacheNode> list = freqMap.get(freq);
		list.remove(node);// clear it up
		
		freq++;
		node.frequency = freq;//upgrade frequency
		if (!freqMap.containsKey(freq)) {
			freqMap.put(freq, new ArrayList<CacheNode>());
		}
		freqMap.get(freq).add(node);
	}
	
	private void doEviction() {
		List<CacheNode> list = freqMap.get(minFreq);
		while (list == null || list.isEmpty()) {
			minFreq++;
			list = freqMap.get(minFreq);
			
			if (minFreq >= freqMap.size()) {
				break;
			}
		}		
		if (list == null || list.isEmpty()) {
			return;
		}
		
		CacheNode node = list.get(0);
		map.remove(node.key);
		list.remove(0);
	}
	
	/*
	* @param capacity: An integer
	*/public LFUCache(int capacity) {
		// do intialization if necessary
		this.capacity = capacity;
		this.map = new HashMap<Integer, CacheNode>();
		this.freqMap = new HashMap<Integer, List<CacheNode>>();
		this.minFreq = DEFAULT_VALUE;
	}

	/*
	 * @param key: An integer
	 * @param value: An integer
	 * @return: nothing
	 */
	public void set(int key, int value) {
		// write your code here
		if (map.containsKey(key)) {
			CacheNode node = map.get(key);
			node.value = value;
			upgradeFrequency(node);			
			
			return;
		}
		
		if (map.size() >= capacity) {
			doEviction();
		}
		
		CacheNode newNode = new CacheNode(key, value, DEFAULT_VALUE);
		map.put(key, newNode);
		this.minFreq = DEFAULT_VALUE; // reset
		if (!freqMap.containsKey(minFreq)) {
			freqMap.put(minFreq, new ArrayList<CacheNode>());
		}
		freqMap.get(minFreq).add(newNode);
	}

	/*
	 * @param key: An integer
	 * @return: An integer
	 */
	public int get(int key) {
		// write your code here
		int result = -1;
		if (map.containsKey(key)) {
			CacheNode node = map.get(key);
			result = node.value;
			
			upgradeFrequency(node);
		}
		
		return result;
	}
}