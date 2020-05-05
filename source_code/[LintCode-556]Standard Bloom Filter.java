/***
* LintCode 556. Standard Bloom Filter
Implement a standard bloom filter. Support the following method:
	1.StandardBloomFilter(k) The constructor and you need to create k hash functions.
	2.add(string) Add a string into bloom filter.
	3.contains(string) Check a string whether exists in bloom filter.
Example
	Example1
		Input:
			StandardBloomFilter(3)
			add("lint")
			add("code")
			contains("lint")
			contains("world")
		Output: [true,false]

	Example2
		Input:
			StandardBloomFilter(10)
			add("hello")
			contains("hell")
			contains("helloa")
			contains("hello")
			contains("hell")
			contains("helloa")
			contains("hello")
		Output: [false,false,true,false,false,true]
***/

// helper class
class HashFunction {
    // fields
    private int capacity;
    private int seed;
    
    // constructor
    public HashFunction(int capacity, int seed) {
        this.capacity = capacity;
        this.seed = seed;
    }
    
    public int hash(String value) {
        int result = 0;
        
        for (char ch : value.toCharArray()) {
            result += result * seed + ch;
            result %= capacity;
        }
        
        return result;
    }
}

public class StandardBloomFilter {
    // fields
    private final int SIZE = 100000;
    
    private int k; // the number of hash function
    private List<HashFunction> hashFunctions;
    
    private BitSet bitSet; // bit-array where hash values store
    
    
    /*
    * @param k: An integer
    */public StandardBloomFilter(int k) {
        // do intialization if necessary
        this.k = k;
        this.hashFunctions = new ArrayList<HashFunction>();
        for (int i = 0; i < k; i++) {
            HashFunction hashFunction = new HashFunction(SIZE + i, 2 * i + 3);
            this.hashFunctions.add(hashFunction);
        }
        
        this.bitSet = new BitSet(SIZE + k);
    }

    /*
     * @param word: A string
     * @return: nothing
     */
    public void add(String word) {
        for (HashFunction hashFunction : hashFunctions) {
            int value = hashFunction.hash(word);
            this.bitSet.set(value);
        }
    }

    /*
     * @param word: A string
     * @return: True if contains word
     */
    public boolean contains(String word) {
        for (HashFunction hashFunction : hashFunctions) {
            int value = hashFunction.hash(word);
            
            if (!this.bitSet.get(value)) {
                return false;
            }
        }
        
        return true;
    }
}