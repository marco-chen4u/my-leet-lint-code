/***
* LintCode 13. Implement strStr()
For a given source string and a target string, you should output the first index(from 0) of target string in source string.

If target does not exist in source, just return -1.

Example
	Example 1:
		Input: source = "source" ，target = "target"
		Output: -1	
		Explanation: If the source does not contain the target content, return - 1.
	Example 2:
		Input:source = "abcdabcdefg" ，target = "bcd"
		Output: 1	
		Explanation: If the source contains the target content, return the location where the target first appeared in the source.
Challenge
	O(n^2) is acceptable. Can you implement an O(n) algorithm? (hint: KMP)

Clarification
Do I need to implement KMP Algorithm in a real interview?
	-Not necessary. When you meet this problem in a real interview, the interviewer may just want to test your basic implementation -ability. But make sure you confirm with the interviewer first.
***/
//version-1: time complexity(O(n^2))
public class Solution {
	// fields
	private final int DEFAULT_VALUE = -1;
	
    /**
     * @param source: 
     * @param target: 
     * @return: return the index
     */
    public int strStr(String source, String target) {
        // check corner cases
        if (target == null || source == null) {
            return DEFAULT_VALUE;
        }
        
        if (target.length() == 0) {
            return 0;
        }
        
        if (source.length() == 0) {
            return DEFAULT_VALUE;
        }        

        int sizeOfSource = source.length();
        int sizeOfTarget = target.length();
		
        if (sizeOfSource < sizeOfTarget) {
            return DEFAULT_VALUE;
        }
        
        for (int i = 0; i <= sizeOfSource - sizeOfTarget; i++) {
            int j = 0;;
            while (j < sizeOfTarget) {
                if (source.charAt(i + j) != target.charAt(j)) {
                    break;
                }
				
				j++
            }
            
            if (j == sizeOfTarget) {
                return i;
            }
        }
        
        return -1;
    }
}

//version-2： time complexity(O(n)), space complexity(O(m));
public class Solution {
	// fields
	private final int DEFAULT_VALUE = -1;
	
    /**
     * @param source: 
     * @param target: 
     * @return: return the index
     */
    public int strStr(String source, String target) {
        // check corner cases
        // check corner cases
        if (target == null || source == null) {
            return DEFAULT_VALUE;
        }
        
        if (target.length() == 0) {
            return 0;
        }
        
        if (source.length() == 0) {
            return DEFAULT_VALUE;
        } 
        
        if (source.length() < target.length()) {
            return DEFAULT_VALUE;
        }
        
        int n = source.length();
        int m = target.length();
        
         Map<String, Boolean> prefixMap = getPrefixMap(target); 
        
        for (int i = 0; i < n - m + 1; i++) {
            String key = source.substring(i, i + 1);
            String value = source.substring(i, i + m);
            if (prefixMap.containsKey(key) && Boolean.TRUE.equals(prefixMap.get(value))) {
                return i;
            }
        }
        
        return -1;
    }
    
    // helper method
    private Map<String, Boolean> getPrefixMap(String str) {
        Map<String, Boolean> prefixMap = new HashMap<String, Boolean>(str.length());
        for (int i = 0; i < str.length() - 1; i++) {
            prefixMap.put(str.substring(0, i + 1), false);
        }
        prefixMap.put(str, true);
        return prefixMap;
    }
}

//version-3: Hash+ KMP
public class Solution {
	// field
	private final int DEFAULT_VALUE = -1;
	private final int SEED = 31;//smaller prime
	private final int MOD = Integer.MAX_VALUE - 1000000000;//bigger prime
	
	
    /**
     * @param source: 
     * @param target: 
     * @return: return the index
     */
    public int strStr(String source, String target) {
        // check corner cases
		if (source == null || target == null) {
			return DEFAULT_VALUE;
		}
		
		if (target.length() == 0) {
			return 0;
		}
		
		if (source.length() == 0) {
			return DEFAULT_VALUE;
		}
		
		int sizeOfSource = source.length();
		int sizeOfTarget = target.length();
		
		if (sizeOfSource < sizeOfTarget) {
			return DEFAULT_VALUE;
		}
		
		// normal case
		int hashValueOfTarget = 0;
		for (char ch : target.toCharArray()) {
			hashValueOfTarget = getHashValueOfChar(hashValueOfTarget, ch);
		}
		
		int base = 1;
		for (int i = 0; i < sizeOfTarget - 1; i++) {
			base = base * SEED % MOD;
		}
		
		int value = 0;
		for (int i = 0; i < sizeOfSource; i++) {
			if (i >= sizeOfTarget) {
				value = (value - (source.charAt(i - sizeOfTarget) - 'a') * base) % MOD;
			}
			
			value = getHashValueOfChar(value, source.charAt(i));
			
			if (i >= sizeOfTarget - 1 && value == hashValueOfTarget) {
				String currentWord = source.substring(i - sizeOfTarget + 1, i + 1);
				if (!target.equals(currentWord)) {
					continue;
				}
				
				return i - sizeOfTarget + 1;
			}// if
		}// for
		
		return DEFAULT_VALUE;		
    }
	
	// helper method
	private int getHashValueOfChar(int value, char ch) {
		value = (value * SEED + ch - 'a') % MOD;//note: SEED and MOD both are prime is good to make hash
		
		value += (value < 0) ? MOD : 0;
		
		return value;
	}
}