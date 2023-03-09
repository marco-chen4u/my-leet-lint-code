/***
* LintCode 624. Remove Substrings
Given a string s and a set of n substrings. 
You are supposed to remove every instance of those n substrings from s 
so that s is of the minimum length and output this minimum length.

Example 1:
    Input:
        "ccdaabcdbb"
        ["ab","cd"]
    Output:
        2
    Explanation: 
        ccdaabcdbb -> ccdacdbb -> cabb -> cb (length = 2)

Example 2:
    Input:
        "abcabd"
        ["ab","abcd"]
    Output:
        0
    Explanation: 
        abcabd -> abcd -> "" (length = 0)
***/
public class Solution {

    public int minLength(String s, Set<String> dict) {
        int result = Integer.MAX_VALUE;
        // check corner case
        if (s == null || s.length() == 0 || dict == null || dict.size() == 0) {
            return 0;
        }

        result = s.length();
        Queue<String> queue = new LinkedList<String>();
        Set<String> visitedBook = new HashSet<String>();

        queue.offer(s);
        visitedBook.add(s);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            for (String keyWord : dict) {
                int foundIndex = current.indexOf(keyWord);

                while (foundIndex != -1) {
                    String newStr = current.substring(0, foundIndex) + 
                        current.substring(foundIndex + keyWord.length(), current.length());

                    if (!visitedBook.contains(newStr)) {
                        queue.offer(newStr);
	                visitedBook.add(newStr);

                        result = Math.min(result, newStr.length());
                    }
	
                    int fromIndex = foundIndex + 1;
                    foundIndex = current.indexOf(keyWord, fromIndex);
                }
            }
        }

        return result;
    }
}
