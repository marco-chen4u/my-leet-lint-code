/***
* LintCode 683. Word Break III
Give a dictionary of words and a sentence with all whitespace removed, 
return the number of sentences you can form by inserting whitespaces to the sentence so that each word can be found in the dictionary.

Example 1
    Input:
        "CatMat"
        ["Cat", "Mat", "Ca", "tM", "at", "C", "Dog", "og", "Do"]
    Output: 3
    Explanation:
        we can form 3 sentences, as follows:
        "CatMat" = "Cat" + "Mat"
        "CatMat" = "Ca" + "tM" + "at"
        "CatMat" = "C" + "at" + "Mat"

Example 2
    Input:
        "a"
        []
    Output: 
        0

Link: https://www.lintcode.com/problem/683/

***/
//version-1: memorized DFS
public class Solution {
    /**
     * @param s: A string
     * @param dict: A set of word
     * @return: the number of possible sentences.
     */
    public int wordBreak3(String s, Set<String> dict) {
        // corner cases
        if(s == null || s.length() == 0 || 
            dict == null || dict.size() == 0){
            return 0;
        }

        Set<String> lowerDict = new HashSet<>();
        int maxLength = initialize(lowerDict, dict);

        return dfs(s.toLowerCase(), 0, maxLength, lowerDict, new HashMap<Integer,Integer>());
    }

    // helper methods
    public int dfs(String s, 
                    int startIndex, 
                    int maxLength, 
                    Set<String>lowerDict, 
                    HashMap<Integer, Integer> visisted){
        // corner cases
        if(visisted.containsKey(startIndex)) {
            return visisted.get(startIndex);
        }

        if(startIndex == s.length()) {
            return 1;
        }
        
        int result = 0;
        for(int end = startIndex + 1; end <= s.length(); end++){

            if(end - startIndex > maxLength) break;

            String word = s.substring(startIndex, end);

            if(!lowerDict.contains(word)){
                continue;
            }

            result += dfs(s, end, maxLength, lowerDict, visisted);
        }

        visisted.put(startIndex, result);

        return result;
    }


    public int initialize(Set <String> lowerDict, Set <String> dict){
        int maxLength = 0;
        
        for(String word : dict){
            lowerDict.add(word.toLowerCase());
            maxLength = Math.max(maxLength,word.length());
        }

        return maxLength;
    }
}
