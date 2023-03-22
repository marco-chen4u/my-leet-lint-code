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
//version-1: DFS-combination
public class Solution {
    /*
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        // write your code here
        List<List<String>> result = new ArrayList<>();
        if (s == null) {
            return result;
        }

        List<String> combination = new ArrayList<String>();
        if (s.isEmpty()) {
            result.add(combination);
            return result;
        }

        find(s, 0, combination, result);

        return result;
    }

    // helper method
    private void find(String str, int currentIndex, List<String> combination, List<List<String>> result) {
        if (currentIndex == str.length()) {
            result.add(new ArrayList<String>(combination));
            return;
        }

        for (int i = currentIndex + 1; i <= str.length(); i++) {
            String currentStr = str.substring(currentIndex, i);
            if (!isPalindome(currentStr)) {
                continue;
            }

            combination.add(currentStr);
            find(str, i, combination, result);
            combination.remove(combination.size() - 1);
        }
    }

    private boolean isPalindome(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }

        if (str != null && str.length()== 1) {
            return true;
        }

        // tow pointers
        int i = 0;
        int j = str.length() - 1;

        while (i < j) {
            
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }
}

// version-2: DP
public class Solution {
    List<List<String>> results;
    boolean[][] isPalindrome;
    
    /**
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        results = new ArrayList<List<>>();
        
        // check corner case
        if (s == null || s.length() == 0) {
            return results;
        }
        
        getIsPalindrome(s);
        
        find(s, 0, new ArrayList<Integer>());
        
        return results;
    }
    
    // helper method
    private void getIsPalindrome(String s) {
        // initialize
        int n = s.length();
        isPalindrome = new boolean[n][n];
        
        // state
        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < n - 1; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }
        
        // function
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                isPalindrome[i][j] = isPalindrome[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
            }
        }
    }

    private void find(String s,
                        int startIndex,
                        List<Integer> partition) {
        if (startIndex == s.length()) {
            addResult(s, partition);
            return;
        }
        
        for (int i = startIndex; i < s.length(); i++) {
            if (!isPalindrome[startIndex][i]) {
                continue;
            }
            partition.add(i);
            find(s, i + 1, partition);
            partition.remove(partition.size() - 1);
        }
    }
    
    private void addResult(String s, List<Integer> partition) {
        List<String> result = new ArrayList<>();
        int startIndex = 0;
        for (int i = 0; i < partition.size(); i++) {
            result.add(s.substring(startIndex, partition.get(i) + 1));
            startIndex = partition.get(i) + 1;
        }
        results.add(result);
    }
}
