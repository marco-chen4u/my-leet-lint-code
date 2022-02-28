/***
* LintCode 627. Longest Palindrome
Given a string which consists of lowercase or uppercase letters, 
find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.


Example 1:
    Input : s = "abccccdd"
    Output : 7
    Explanation :
        One longest palindrome that can be built is "dccaccd", whose length is `7`.

Notice
    Assume the length of given string will not exceed 1010.
***/

//version-1
public class Solution {
    /**
     * @param s: a string which consists of lowercase or uppercase letters
     * @return: the length of the longest palindromes that can be built
     */
    public int longestPalindrome(String s) {
        int result = 0;
        if (s == null || s.length() == 0) {
            return result;
        }

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (char ch : s.toCharArray()) {
            if (!map.containsKey(ch)) {
                map.put(ch, 1);
            }
            else {
                map.remove(ch);
            }
        }

        result = s.length() - map.size();//we got the number of characters that even int count

        result += (!map.isEmpty()) ? 1 : 0;

        return result;
    }
}

//version-2: better to understand
public class Solution {
    /**
     * @param s: a string which consists of lowercase or uppercase letters
     * @return: the length of the longest palindromes that can be built
     */
    public int longestPalindrome(String s) {
        int result = 0;
        if (s == null || s.length() == 0) {
            return result;
        }

        Map<Character, Integer> map = new HashMap<Character, Integer>();

        for (char ch : s.toCharArray()) {
            map.putIfAbsent(ch, 0);
            map.put(ch, map.get(ch) + 1);
        }

        boolean hasOdd = false;
        for (int value : map.values()) {
            if (value % 2 == 0) {
                result += value;
            }
            else {
                result += (value - 1);
                hasOdd = true;
            }
        }

        result += (hasOdd) ? 1 : 0;

        return result;
    }
}
	
//version-3: best solution
public class Solution {
    /**
     * @param s: a string which consists of lowercase or uppercase letters
     * @return: the length of the longest palindromes that can be built
     */
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int size = 'z' - 'A' + 1;
        int[] counter = new int[size];
        
        for (char ch : s.toCharArray()) {
            int index = ch - 'A';
            counter[index]++;
        }
        
        boolean hasOdd = false;
        int result = 0;
        for (int i = 0; i < size; i++) {
            if (counter[i] == 0) {
                continue;
            }
            
            if (counter[i] % 2 == 0) {
                result += counter[i];
            }
            else {
                result += counter[i] - 1;
                hasOdd = true;
            }
        }
        
        result += (hasOdd) ? 1 : 0;
        
        return result;
    }
}
