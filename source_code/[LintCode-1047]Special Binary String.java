/***
* LintCode 1047. Special Binary String
Special binary strings are binary strings with the following two properties:
    -The number of 0's is equal to the number of 1's.
    -Every prefix of the binary string has at least as many 1's as 0's.
    Given a special string S, a move consists of choosing two consecutive, non-empty, special substrings of S, and swapping them. 
    (Two strings are consecutive if the last character of the first string is exactly one index before the first character of the second string.)

At the end of any number of moves, what is the lexicographically largest resulting string possible?

The strings "10" [occuring at S[1]] and "1100" [at S[3]] are swapped.
This is the lexicographically largest string possible after some number of swaps.

Example
    Example 1:
        Input: S = "11011000"
        Output: "11100100"
	Explanation:
        The strings "10" [occuring at S[1]] and "1100" [at S[3]] are swapped.
        This is the lexicographically largest string possible after some number of swaps.
    Example 2:
        Input: S = "10101010"
	Output: "10101010"
	
Notice
    S has length at most 50.
    S is guaranteed to be a special binary string as defined above.
***/
//version-1
public class Solution {
    /**
     * @param S: a string
     * @return: return a string
     */
    public String makeLargestSpecial(String S) {
        String result = S;
        // check corner case
        if (S == null || S.length() == 0) {
            return result;
        }
		
        // initialize
        int size = S.length();
		
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j <= size; j++) {
                String current = S.substring(i, j);
				
                if (isSpecialBinaryString(current)) {
                    for (int k = j + 1; k <= size; k++) {
                        String next = S.substring(j, k);
                        if (isSpecialBinaryString(next)) {
                            String newString = swap(S, i, j - 1, j, k - 1);
							
                            if (newString.compareTo(result) > 0) {
                                result = newString;
                            }
                        }
                    }// for k
                }				
            }// for j
        }// for i
		
        if (result.equals(S)) {
            return result;
        }
        else {
            return makeLargestSpecial(result);
        }
    }
	
    // heler methods
    private boolean isSpecialBinaryString(String str) {
	int count = 0;
	for (char ch : str.toCharArray()) {
	    if (ch == '1') {
	        count++;
	    }
	    else {
	        count--;
	    }

            if (count < 0) {
                return false;
	    }
	}

	return (count == 0);
    }
	
    private String swap(String str, int i, int j, int k, int l) {
	StringBuilder sb = new StringBuilder();
        for (int index = 0; index < i; index++) {
	    sb.append(str.charAt(index));
	}

        for (int index = k; index <= l; index++) {
	    sb.append(str.charAt(index));
	}

        for (int index = i; index <= j; index++) {
	    sb.append(str.charAt(index));
	}

	for (int index = l + 1; index < str.length(); index++) {
	    sb.append(str.charAt(index));
	}

	return sb.toString();
    }
}

//version-2
public class Solution {
    /**
     * @param S: a string
     * @return: return a string
     */
    public String makeLargestSpecial(String S) {
        String result = S;
        // check corner case
        if (S == null || S.length() == 0) {
            return result;
        }
        
        int size = S.length();
        int pre = 0;
        int count = 0;
        List<String> components = new ArrayList<String>();
        
        for (int i = 0; i < size; i++) {
            count += (S.charAt(i) == '1') ? 1 : -1;
            
            //Special Binary String check
            if (count == 0) {
                components.add("1" + makeLargestSpecial(S.substring(pre + 1, i)) + "0");
                pre = i + 1;
            }
        }
        
        Collections.sort(components, Collections.reverseOrder());
        
        result = String.join("", components);//merge together
        
        return result;
    }
}
