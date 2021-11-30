/***
* LintCode 1630. Interesting String
There is now a string s consisting of only numbers and lowercase letters. 
If the string s is interesting, then s must be split into several substrings, 
each substring satisfies the beginning of the number, and the number represents the number of characters after it. 

For example, s = "4g12y6hunter", we can divide it into "4g12y" and "6hunter", so the string is interesting.
If s is an interesting string, output "yes", otherwise output "no"

Example 1:
    s = "124gray6hunter",return "yes"
    Input:
        124gray6hunter
    Output:
        yes
    Explanation:
        we can divide it into "12", "4gray", "6hunter".

Example 2:
    s = "31ba2a" ,return "no"
    Input:
        31ba2a
    Output:
        no
***/
//version-1: DFS
public class Solution {
  
    // constants
    private final String YES = "yes";
    private final String NO = "no";
  
    private String result;
  
    /**
     * @param s: the string s
     * @return: check if the string is interesting
     */
    public String check(String s) {
        // initialize
        result = NO;
      
        // check corner cases
        if (s == null || s.length() <= 1) {
            return result;
        }
        
        // regular case
        dfs(s, 0);
    
        return result;
    }

    // helper method
    private void dfs(String str, int startIndex) {
        // check corner case
        if (startIndex == str.length()) {
            result = YES;
            return;
        }
    
        // regular case
        int num = 0;
        for (int i = startIndex; i < str.length(); i++) {
            char ch = str.charAt(i);
        
            // check corner cases
            if (!Character.isDigit(ch)) {
                return;
            }
        
            num = num * 10 + ch - '0';
            
            int nextPos = i + num + 1;
        
            if (nextPos > str.length()) {
                return;
            }
        
            // regular case
            dfs(str, nextPos);
        }
    }
}

//version-2: BFS
public class Solution {
    // constants
    private final String YES = "yes";
    private final String NO = "no";
    
    /**
     * @param s: the string s
     * @return: check if the string is interesting
     */
    public String check(String s) {
        // check corner cases
        if (s == null || s.length() <= 1) {
            return NO;
        }
        
        // regular case
        Queue<Integer> queue = new LinkedList<>();// store the position of the starting character where statisfied interesting string
        queue.offer(0);// initialize
        
        while (!queue.isEmpty()) {
            int currentPos = queue.poll();
            int size = s.length();            
            
            int num = 0;
            
            // getting the next potential position of the new intersting string token
            for (char ch = s.charAt(currentPos); currentPos < size && Character.isDigit(ch); currentPos += 1, ch = s.charAt(currentPos)) {
                num = num * 10 + ch - '0';
                
                int nextPos = currentPos + num + 1;
                
                // check corner case
                if (nextPos == size) {
                    return YES;
                }
                
                if (nextPos > size || !Character.isDigit(s.charAt(nextPos))) {
                    continue;
                }
                
                queue.offer(nextPos);
            }
        }
        
        return NO;
    }
}
