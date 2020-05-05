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
//version 2: DFS-Combination
public class Solution {
    
    // helper methods
    private boolean isPalindrom(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    private void findPalindromPartitions(List<List<String>> result,
                                            List<String> partition, 
                                            int startIndex, 
                                            String s) {
        if (startIndex == s.length()) {
            result.add(new ArrayList<String>(partition));
            return;
        }
        
        for (int i = startIndex; i < s.length(); i++) {
            String subString = s.substring(startIndex, i + 1);
            if (!isPalindrom(subString)) {
                continue;
            }
            
            partition.add(subString);
            findPalindromPartitions(result, partition, i + 1, s);
            partition.remove(partition.size() - 1);
        }
        
    }
    
    /*
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        
        // check corner case
        if (s == null || s.length() == 0) {
            return result;
        }
        
        List<String> partition = new ArrayList<String>();
        findPalindromPartitions(result, partition, 0, s);
        
        return result;
    }
}
