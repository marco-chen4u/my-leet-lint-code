/***
* LintCode 384. Longest Substring Without Repeating Characters
Given a string, find the length of the longest substring without repeating characters.

Example 1:
    Input: "abcabcbb"
    Output: 3
    Explanation: The longest substring is "abc".

Example 2:
    Input: "bbbbb"
    Output: 1
    Explanation: The longest substring is "b".

Challenge
    time complexity O(n)
***/
// version-1
public class Solution {
    /**
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        // check corner case
        if (s == null || s.length() == 0) {
            return result;
        }

        int i = 0, j = 0;
        Set<Character> set = new HashSet<Character>();
        int size = s.length();

        for (; i < size; i++) {
            while (j < size && !set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;

                result = Math.max(result, j - i);
            }

            if (j < size && set.contains(s.charAt(j))) {
                set.remove(s.charAt(i));
            }
        }

        return result;
    }
}

//version-2
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        
        if(s == null || s.length() == 0){
            return maxLength;
        }
        
        Set<Character> set = new HashSet<Character>();
        
        //two pointers
        int i = 0;//slower pointers
        int j = 0;//faster pointers
                       
        while(j < s.length()){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j));
                j ++;
                
                maxLength = Math.max(maxLength, j - i);
            }
            else{
                set.remove(s.charAt(i));
                i ++;
            }
        }
        
        return maxLength;
    }
}
