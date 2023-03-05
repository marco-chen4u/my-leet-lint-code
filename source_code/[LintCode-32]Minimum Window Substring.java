/***
* LintCode 32. Minimum Window Substring
Given two strings source and target. Return the minimum substring of source which contains each char of target.
Example
    Example 1:
        Input: source = "abc", target = "ac"
        Output: "abc"

    Example 2:
        Input: source = "adobecodebanc", target = "abc"
        Output: "banc"
        Explanation: "banc" is the minimum substring of source string which contains each char of target "abc".

    Example 3:
        Input: source = "abc", target = "aa"
        Output: ""
        Explanation: No substring contains two 'a'.
Challenge
    O(n) time
Notice
    1.If there is no answer, return "".
    2.You are guaranteed that the answer is unique.
    3.target may contain duplicate char, while the answer need to contain at least the same number of that char.
***/
//version-1 (O(n + 255)
public class Solution {
    private final int MAX_SIZE = 256;
	
    /**Main Method**/
    public String minWindow(String source , String target) {
        String result = "";
        int min = Integer.MAX_VALUE;
        // check corner cases
        if (source == null || source.length() == 0) {
            return result;
        }		
        if (target == null || target.length() == 0) {
            return source;
        }
		
        int[] targetHash = initializeHash(target);
        int[] sourceHash = new int[MAX_SIZE];
        int size = source.length();

        int i = 0, j = 0;//two pointers
        for (i = 0; i < size; i++) {
            while (j < size && !isIncluded(sourceHash, targetHash)) {
                sourceHash[source.charAt(j)] ++;				
                j++;
            }

            if (isIncluded(sourceHash, targetHash)) {
                int currentWindowSize = j - i;
                if (currentWindowSize < min) {
                    min = currentWindowSize;
                    result = source.substring(i, j);
                }
            }

            sourceHash[source.charAt(i)] --;
        }

        return result;
    }
	
    // helper methods
    private int[] initializeHash(String value) {
        int[] hash = new int[MAX_SIZE];
        for (char ch : value.toCharArray()) {
            hash[ch]++;
        }

        return hash;
    }
	
    private boolean isIncluded(int[] sourceHash, int[] targetHash) {
        for (int i = 0; i < MAX_SIZE; i++) {
            if (sourceHash[i] < targetHash[i]) {
                return false;
            }
        }

        return true;
    }
}

//version-2 (O(n))
public class Solution {
    private final int MAX_SIZE = 255;
    /**
     * @param source : A string
     * @param target: A string
     * @return: A string denote the minimum window, return "" if there is no such a string
     */
    public String minWindow(String source , String target) {
        // check corner case
        if (source == null || source.length() == 0 || 
                target == null || target.length() == 0) {
            return source;
        }
        
        int n = source.length();
        int[] targetHash = new int[MAX_SIZE];
        int targetUniqCharCount = 0;
        for (int i = 0; i < target.length(); i++) {
            char charIndex = target.charAt(i);
            targetHash[charIndex]++;
            
            if (targetHash[charIndex] == 1) {
                targetUniqCharCount++;
            }
        }
        
        int currentWindowUniqCharCount = 0;
        
        int i, j = 0; //two pointers
        int left = -1, right = -1;
        int[] sourceHash = new int[MAX_SIZE];
        
        for (i = 0; i < n; i++) {
            while (j < n && currentWindowUniqCharCount < targetUniqCharCount) {
                char currentChar = source.charAt(j);
                
                sourceHash[currentChar]++;
                if (sourceHash[currentChar] == targetHash[currentChar]) {
                    currentWindowUniqCharCount++;
                }
                
                
                j++;
            }// while j
            
            if (currentWindowUniqCharCount == targetUniqCharCount) {
                if (left == -1 || j - i < right - left) {
                    left = i;
                    right = j;// (j = j + 1)
                }
            }
            
            // remove from window
            int preChar = source.charAt(i);
            sourceHash[preChar]--;
            if (sourceHash[preChar] == targetHash[preChar] - 1) {
                currentWindowUniqCharCount--;
            }
        }// for i
        
        if (left == -1) {
            return "";
        }
        else {
            return source.substring(left, right);
        }
    }
}
