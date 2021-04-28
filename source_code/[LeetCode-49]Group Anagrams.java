/**
* LeetCode 49. Group Anagrams
Given an array of strings, group anagrams together.

Example:
    Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
    Output:
        [
          ["ate","eat","tea"],
          ["nat","tan"],
          ["bat"]
        ]
Note:
    All inputs will be in lowercase.
    The order of your output does not matter.

**/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        
        // check corner case
        if (strs == null || strs.length == 0) {
            return result;
        }
        
        // normal case
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        
        for (String str : strs) {
            String anagramCode = getAnagramCode(str);
            map.putIfAbsent(anagramCode, new ArrayList<String>());
            map.get(anagramCode).add(str);
        }
        
        
        for (Map.Entry<String,List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        
        return result;
    }
    
    // helper methods
    private String getAnagramCode(String str) {
        char[] charArray = str.toCharArray();
        
        Arrays.sort(charArray);
        
        return String.valueOf(charArray);
    }
}

test-data:
	["eat","tea","tan","ate","nat","bat"]
