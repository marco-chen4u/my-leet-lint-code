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
//version-1: BFS
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

//versio-2: BFS
public class Solution {
    /**
     * @param s: a string
     * @param dict: a set of n substrings
     * @return: the minimum length
     */
    public int minLength(String s, Set<String> dict) {
        
        int result = s.length();

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(s);
        visited.add(s);

        while(!queue.isEmpty()) {
            String current = queue.poll();
            result = Math.min(result, current.length());

            for (String next : getNext(current, dict)) {
                if (visited.contains(next)) {
                    continue;
                }

                queue.offer(next);
                visited.add(next);
            }
        }

        return result;
    }

    // helper method
    private List<String> getNext(String current, Set<String> dict) {
        List<String> result = new ArrayList<>();

        for (String word: dict) {
            int fromIndex = current.indexOf(word);

            while (fromIndex != -1) {
                String next = current.substring(0, fromIndex) + current.substring(fromIndex + word.length());

                result.add(next);

                int nextStart = fromIndex + word.length();
                fromIndex = current.indexOf(word, nextStart);
            }

        }

        return result;
    }
}
