/***
* Lintcode 892. Alien Dictionary
There is a new alien language which uses the latin alphabet. 
However, the order among letters are unknown to you. 
You receive a list of non-empty words from the dictionary, 
where words are sorted lexicographically by the rules of this new language. 
Derive the order of letters in this language.

Example
Example 1:
	Input：["wrt","wrf","er","ett","rftt"]
	Output："wertf"
	Explanation：
		from "wrt"and"wrf" ,we can get 't'<'f'
		from "wrt"and"er" ,we can get 'w'<'e'
		from "er"and"ett" ,we can get 'r'<'t'
		from "ett"and"rtff" ,we can get 'e'<'r'
		So return "wertf"

Example 2:
	Input：["z","x"]
	Output："zx"
	Explanation：
		from "z" and "x"，we can get 'z' < 'x'
		So return "zx"
Notice
	You may assume all letters are in lowercase.
	You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
	If the order is invalid, return an empty string.
	There may be multiple valid order of letters, return the smallest in normal lexicographical order
***/
public class Solution {
    
    // helper methods
    private Map<Character, Set<Character>> getGraph(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<Character, Set<Character>>();
        
        // build nodes
        for (String word : words) {
            char[] wordCharArray = word.toCharArray();
            for (int i = 0; i < wordCharArray.length; i++) {
                if (!graph.containsKey(wordCharArray[i])) {
                    graph.put(wordCharArray[i], new HashSet<Character>());
                }
            }
        }
        
        // build edges
        for (int i = 0; i < words.length - 1; i++) {
            int index = 0;
            while (index < words[i].length() && index < words[i + 1].length()) {
                if (words[i].charAt(index) != words[i + 1].charAt(index)) {
                    graph.get(words[i].charAt(index)).add(words[i + 1].charAt(index));
                    break;
                }
                
                index++;
            }
        }
        
        return graph;
    }
    
    private Map<Character, Integer> getIndegree(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> indegreeMap = new HashMap<Character, Integer>();
        
        for (Map.Entry<Character, Set<Character>> entry : graph.entrySet()) {
            Set<Character> neighbors = entry.getValue();
            char vertex = entry.getKey();
            
            // vertex
            if (!indegreeMap.containsKey(vertex)) {
                indegreeMap.put(vertex, 0);
            }
            
            // neighbor edges
            for (char neighbor : neighbors) {
                if (indegreeMap.containsKey(neighbor)) {
                    indegreeMap.put(neighbor, indegreeMap.get(neighbor) + 1);
                }
                else {
                    indegreeMap.put(neighbor, 1);
                }
            }
        }
        
        return indegreeMap;
    }
    
    /**
     * @param words: a list of words
     * @return: a string which is correct order
     */
    public String alienOrder(String[] words) {
        StringBuilder result = new StringBuilder();
        // check corner case
        if (words == null || words.length == 0) {
            return result.toString();
        }
        
        // build a graph
        Map<Character, Set<Character>> graph = getGraph(words);
        
        // get all in-degree records
        Map<Character, Integer> indegreeMap = getIndegree(graph);
        
        Queue<Character> queue = new PriorityQueue<Character>();
        // in-queue all 0-indegree node of the graph
        for (Map.Entry<Character, Integer> entry : indegreeMap.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }
        
        while (!queue.isEmpty()) {
            char currentCharacter = queue.poll();
            result.append(currentCharacter);
            
            // updete current node's neighbor indegree by - 1
            for (char neighbor : graph.get(currentCharacter)) {
                indegreeMap.put(neighbor, indegreeMap.get(neighbor) - 1);
                
                if (indegreeMap.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        if (result.length() != indegreeMap.size()) {
            return "";
        }
        
        return result.toString();
    }
}