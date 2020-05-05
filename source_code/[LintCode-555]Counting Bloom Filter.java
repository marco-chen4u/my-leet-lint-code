/***
* LintCode 555. Counting Bloom Filter
Implement a counting bloom filter. Support the following method:
	1.add(string). Add a string into bloom filter.
	2.contains(string). Check a string whether exists in bloom filter.
	3.remove(string). Remove a string from bloom filter.
Example
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
public class CountingBloomFilter {
    /*
    * @param k: An integer
    */public CountingBloomFilter(int k) {
        // do intialization if necessary
    }

    /*
     * @param word: A string
     * @return: nothing
     */
    public void add(String word) {
        // write your code here
    }

    /*
     * @param word: A string
     * @return: nothing
     */
    public void remove(String word) {
        // write your code here
    }

    /*
     * @param word: A string
     * @return: True if contains word
     */
    public boolean contains(String word) {
        // write your code here
    }
}