/***
* LintCode 555. Counting Bloom Filter
Implement a counting bloom filter. Support the following method:
    1.add(string). Add a string into bloom filter.
    2.contains(string). Check a string whether exists in bloom filter.
    3.remove(string). Remove a string from bloom filter.

Example1
    Input:
        CountingBloomFilter(3)
        add("lint")
        add("code")
        contains("lint") 
        remove("lint")
        contains("lint") 
    Output: 
        [true,false]

Example2
    Input:
        CountingBloomFilter(3)
        add("lint")
        add("lint")
        contains("lint")
        remove("lint")
        contains("lint")
    Output: 
        [true,true]
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

public class CountingBloomFilter {
	// fields
	private final int SIZE = 100000;
	
	private int k; // the number of Hash Function
	private List<HashFunction> hashFunctions; // list of Hash Functions
	
	private int[] bits;// bit-array where stores the hash values;
	
    /*
    * @param k: An integer
    */public CountingBloomFilter(int k) {
        // do intialization if necessary
		this.k = k;
		this.hashFunctions = new ArrayList<HashFunction>();
		for (int i = 0; i < k; i++) {
			HashFunction hashFunction = new HashFunction(SIZE + i, 2 * i + 3);
			this.hashFunctions.add(hashFunction);
		}
		
		this.bits = new int[SIZE + k];
    }

    /*
     * @param word: A string
     * @return: nothing
     */
    public void add(String word) {
        for (HashFunction hashFunction : hashFunctions) {
			int index = hashFunction.hash(word);
			bits[index] += 1;
		}
    }

    /*
     * @param word: A string
     * @return: nothing
     */
    public void remove(String word) {
        for (HashFunction hashFunction : hashFunctions) {
			int index = hashFunction.hash(word);
			bits[index] -= 1;
		}
    }

    /*
     * @param word: A string
     * @return: True if contains word
     */
    public boolean contains(String word) {
        for (HashFunction hashFunction : hashFunctions) {
			int index = hashFunction.hash(word);
			if (bits[index] <= 0) {
				return false;
			}
		}

		return true;
    }
}
