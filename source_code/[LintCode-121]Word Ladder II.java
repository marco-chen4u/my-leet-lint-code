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
//version-1:BFS(buiding graph) + DFS(find out all paths)
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
                                String destination,
                                Map<String, List<String>>map,
                                Map<String, Integer>distance,
                                List<String>path,
                                List<List<String>>ladders) {
        path.add(current);
        
        if (current.equals(destination)) {
            ladders.add(new ArrayList<String>(path));//deep copy
        }
        else {
            for (String next : map.get(current)) {
                if (distance.containsKey(next) &&
                        distance.get(current) + 1 == distance.get(next))
                    getLadderPaths(next, destination, map, distance, path, ladders);
            }
        }
        
        path.remove(path.size() - 1);
    }
}

//version-2: BFS(building out a graph&indegree map) + DFS(search out all paths)
public class Solution {
    
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: a list of lists of string
     */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> results = new ArrayList<List<String>>();
        // check corner case
        if (dict == null || dict.size() == 0) {
            return results;
        }
        
        // initialize
        Map<String, List<String>> edgeMap = new HashMap<String, List<String>>();
        Map<String, Integer> distanceMap = new HashMap<String, Integer>();
        
        dict.add(start);
        dict.add(end);
        
        // traverse by BFS to get all a graph
        traverse(start, dict, edgeMap, distanceMap);
        
        List<String> path = new ArrayList<String>();
        // use DFS to get all paths from end node back to start node
        findAllLadderPaths(start, end, path, edgeMap, distanceMap, results);
        
        return results;
    }

    // helper methods
    private boolean isOneCharDiff(String word, String keyWord) {
        char[] wordChars = word.toCharArray();
        char[] keyWordChars = keyWord.toCharArray();
        if (wordChars.length != keyWordChars.length) {
            return false;
        }

        int size = wordChars.length;
        int diffCount = 0;
        for (int i = 0; i < size; i++) {
            diffCount += (wordChars[i] == keyWordChars[i]) ? 0 : 1; 
        }

        return diffCount == 1;
    }
    
    private List<String> getNextWords(String word, Set<String> wordDict) {
        List<String> result = new ArrayList<String>();
        
        for (String keyWord : wordDict) {
            if (isOneCharDiff(keyWord, word)) {
                result.add(keyWord);
            }
        }
        
        return result;
    }
    
    private void traverse(String start, 
                            Set<String> wordDict,
                            Map<String, List<String>> edgeMap,
                            Map<String, Integer> distanceMap) {
        for (String keyWord : wordDict) {
            edgeMap.put(keyWord, new ArrayList<String>());
        }
        
        distanceMap.put(start, 0);
        
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            String current = queue.poll();
            
            List<String> nextWords = getNextWords(current, wordDict);
            for (String next : nextWords) {
                
                edgeMap.get(current).add(next);
                
                if (!distanceMap.containsKey(next)) {
                    int newDistance = distanceMap.get(current) + 1;
                    distanceMap.put(next, newDistance);
                    
                    queue.offer(next);
                }
            }
        }
    }
    
    private void findAllLadderPaths(String current, 
                                    String destination,
                                    List<String> path,
                                    Map<String, List<String>> edgeMap,
                                    Map<String, Integer> distanceMap,
                                    List<List<String>> results) {
        path.add(current);
        
        if (current.equals(destination)) {
            results.add(new ArrayList<String>(path));// deep copy
        }
        else {
            List<String> neighbors = edgeMap.get(current);
            for (String next : neighbors) {
                if (distanceMap.containsKey(next) &&
                    (distanceMap.get(current) + 1 == distanceMap.get(next))) {
                    findAllLadderPaths(next, destination, path, edgeMap, distanceMap, results);
                }
            }
        }
        
        path.remove(path.size() - 1);
    }

}
