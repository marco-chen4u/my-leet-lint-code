/***
* LintCode 913. Flip Game II
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

Example
    Example1
        Input:  s = "++++"
        Output: true
        Explanation:
            The starting player can guarantee a win by flipping the middle "++" to become "+--+".

    Example2
        Input: s = "+++++"
        Output: false 
        Explanation:
            The starting player can not win 
            "+++--" --> "+----"
            "++--+" --> "----+"

Challenge
	Derive your algorithm's runtime complexity.
***/
//version-1: Memorization Search
public class Solution {
    /**
     * @param s: the given string
     * @return: if the starting player can guarantee a win
     */
    public boolean canWin(String s) {
        // check corner case
        if (s == null || s.length() == 0) {
            return false;
        }
        
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        
        return helper(s, map, s.length());
    }
    
    // helper method
    private boolean helper(String str, Map<String, Boolean> map, int n) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        
        for (int i = 0; i < n - 1; i++) {
            if (str.charAt(i) == '+' && str.charAt(i + 1) == '+') {
                String newStr = str.substring(0, i) + "--" + str.substring(i + 2);
                
                boolean canComponentWin = helper(newStr, map, n);
                
                if (!canComponentWin) {
                    map.put(str, true);
                    return true;
                }
            }
        }
        
        map.put(str, false);
        return false;
    }
}
