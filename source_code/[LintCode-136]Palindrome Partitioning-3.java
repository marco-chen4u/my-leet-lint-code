/***
* LintCode 136. Palindrome Partitioning
Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.

Example
	Given s = "aab", return:
		[
		  ["aa","b"],
		  ["a","a","b"]
		]
***/
// version 3: DFS-Combination
public class Solution {
    // helper methods
	private boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
	
    private void findPalindromPartitions(List<List<String>> results,
						List<String> partition, 
						String s, 
                        int startIndex) {
        if (startIndex == s.length()) {
            results.add(new ArrayList<String>(partition));
            return;
        }
        
        for (int i = startIndex; i < s.length(); i++) {
            String subString = s.substring(startIndex, i + 1);
            if (!isPalindrome(subString)) {
                continue;
            }
            partition.add(subString);
            findPalindromPartitions(results, partition, s, i + 1);
            partition.remove(partition.size() - 1);
        }
    }
	
    /**
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        List<List<String>> results = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return results;
        }
        
        List<String> partition = new ArrayList<String>();
        findPalindromPartitions(results, partiton, s, 0);
        
        return results;
    }
}