/***
* LintCode 120. Word Ladder
Given two words (start and end), and a dictionary, find the shortest transformation sequence from start to end, output the length of the sequence.

Transformation rule such that:
    1.Only one letter can be changed at a time
    2.Each intermediate word must exist in the dictionary. (Start and end words do not need to appear in the dictionary )

Note:
  Return 0 if there is no such transformation sequence.
  All words have the same length.
  All words contain only lowercase alphabetic characters.
  You may assume no duplicates in the dictionary.
  You may assume beginWord and endWord are non-empty and are not the same.
  len(dict) <= 5000, len(start) < 5len(dict)<=5000,len(start)<5

Example 1:
  Input:
    start = "a"
    end = "c"
    dict =["a","b","c"]
  Output:
    2
    
Example 2:
  Input:
    start ="hit"
    end = "cog"
    dict =["hot","dot","dog","lot","log"]
  Output:
    5
  Explanation:
    "hit"->"hot"->"dot"->"dog"->"cog"
***/
//version-1: BFS,time-complexity: O(|V| + |E|), space-complexity: O(|V|)
//note: |V| : number of vertics, |E| : number of edge 
//      in this scenario, the number of vertics is the size of word dict, the number of edge is the length of each keyWord, 
//      coz each one character diff of the keyWord, could be the next keyWord in the graph.
public class Solution {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: An integer
     */
    public int ladderLength(String start, String end, Set<String> dict) {
        int step = 0;
        // check corner cases
        if (start == null || start.isEmpty() || end == null || end.isEmpty() || 
            dict == null || dict.isEmpty()) {
            return step;
        }

        if (start.equals(end)) {
            return step;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        dict.add(end);

        queue.offer(start);
        visited.add(start);

        step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            step +=1;
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                for (String next : getNeighbours(current, dict)) {
                    if (visited.contains(next)) {
                        continue;
                    }

                    if (next.equals(end)) {
                        return step;
                    }

                    queue.offer(next);
                    visited.add(next);
                }
            }
        }

        return step;
    }

    // helper method
    private List<String> getNeighbours(String word, Set<String> dict) {
        List<String> result = new ArrayList<>();
        for (String keyWord : dict) {
            if (isOneCharDiff(word, keyWord)) {
                result.add(keyWord);
            }
        }
        return result;
    }

    private boolean isOneCharDiff(String word, String keyWord) {
        char[] wordCharArray = word.toCharArray();
        char[] keyWordCharArray = keyWord.toCharArray();

        if (wordCharArray.length != keyWordCharArray.length) {
            return false;
        }

        int size = wordCharArray.length;

        int diffCount = 0;
        for (int i = 0; i < size; i++) {
            diffCount += (wordCharArray[i] != keyWordCharArray[i]) ? 1 : 0;
        }

        return diffCount == 1;
    }
}

//version-2: BFS, more faster, time-complexity: O(|V| + |E|), space-complexity: O(|V|)
//note: |V| : number of vertics, |E| : number of edge 
public class Solution {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: An integer
     */
    public int ladderLength(String start, String end, Set<String> dict) {
        int step = 0;
        // check corner cases
        if (start == null || start.isEmpty() || end == null || end.isEmpty() || 
            dict == null || dict.isEmpty()) {
            return step;
        }

        if (start.equals(end)) {
            return step;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        dict.add(end);

        queue.offer(start);
        visited.add(start);

        step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            step +=1;
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                for (String next : getNeighbours(current, dict)) {
                    if (visited.contains(next)) {
                        continue;
                    }

                    if (next.equals(end)) {
                        return step;
                    }

                    queue.offer(next);
                    visited.add(next);
                }
            }
        }

        return step;
    }

    // helper method
    private List<String> getNeighbours(String word, Set<String> dict) {
        List<String> result = new ArrayList<>();
        
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);

                if (ch == currentChar) {
                    continue;
                }

                String nextWord = getReplaceWord(word, i, ch);

                if (dict.contains(nextWord)) {
                    result.add(nextWord);
                }
            }
        }

        return result;
    }

    private String getReplaceWord(String word, int index, char ch) {
        char[] chars = word.toCharArray();
        chars[index] = ch;
        return String.valueOf(chars);
    }
}
