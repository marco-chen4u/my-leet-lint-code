/***
* LintCode 121. Word Ladder II
Given two words (start and end), and a dictionary, 
find all shortest transformation sequence(s) from start to end, such that:
    1.Only one letter can be changed at a time
    2.Each intermediate word must exist in the dictionary

Example
    Given:
        start = "hit"
        end = "cog"
        dict = ["hot","dot","dog","lot","log"]
    Return:
        [
          ["hit","hot","dot","dog","cog"],
          ["hit","hot","lot","log","cog"]
    ]
Notice
    All words have the same length.
    All words contain only lowercase alphabetic characters.
***/
public class Solution {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: a list of lists of string
     */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // initialize
        List<List<String>> ladders = new ArrayList<List<String>>();// result
        Map<String, List<String>> map = new HashMap<String, List<String>>();// keep record of each node's neighboring nodes
        Map<String, Integer> distance = new HashMap<String, Integer>(); // keep record of each node's distance[start->current]

        // check corner case
        if (dict == null || dict.size() == 0) {
            return ladders;
        }

        dict.add(start);
        dict.add(end);

        // use BFS to traverse every node of the graph, keep records of each node's neighboring nodes and distance
        traverse(start, end, dict, map, distance);

        List<String> path = new ArrayList<String>();
        // use DFS to get all path of ladders from end back-tracking to start
        getLadderPaths(start, end, map, distance, path, ladders);

        return ladders;
    }

    // helper methods
    /*
    * use BFS to traverse every node of the graph, keep records of each node's neighboring nodes and distance
    */
    private void traverse(String start, String end, 
                            Set<String> dict, 
                            Map<String, List<String>> map, 
                            Map<String, Integer> distance) {
        // initialize
        Queue<String> queue = new LinkedList<String>();
        for (String keyWord : dict) {
            map.put(keyWord, new ArrayList<String>());
        }		
        distance.put(start, 0);
        queue.offer(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            for (String nextWord : getNextWords(current, dict)) {
                map.get(current).add(nextWord);
                if (!distance.containsKey(nextWord)) {
                    int newDistance = distance.get(current) + 1;
                    distance.put(nextWord, newDistance);
                    queue.offer(nextWord);
                }
            }
        }
    }
        
    private List<String> getNextWords(String word, Set<String> dict) {
        List<String> result = new ArrayList<String>();

        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == c) {
                    continue;
                }

                String newWord = replace(word, i, c);
                if (dict.contains(newWord)) {
                    result.add(newWord);
                }
            }
        }

        return result;
    }
    
    private String replace(String word, int index, char c) {
        char[] wordCharArray = word.toCharArray();
        wordCharArray[index] = c;
        return new String(wordCharArray);
    }

    /*
    * use DFS to get all path of ladders from start to end
    */
    private void getLadderPaths(String current,
                                String start,
                                Map<String, List<String>>map,
                                Map<String, Integer>distance,
                                List<String>path,
                                List<List<String>>ladders) {
        path.add(current);
        
        if (current.equals(start)) {
            ladders.add(new ArrayList<String>(path));//deep copy
        }
        else {
            for (String next : map.get(current)) {
                if (distance.containsKey(next) &&
                        distance.get(current) + 1 == distance.get(next))
                    getLadderPaths(next, end, map, distance, path, ladders);
            }
        }
        
        path.remove(path.size() - 1);
    }
}
