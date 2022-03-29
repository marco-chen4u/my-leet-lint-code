/***
* LintCode 1378 · Minimum String Array Coverage
Given a string collection tag_list, an array of strings all_tags, 
find the smallest all_tags sub-array contains all the string in the tag_list, 
output the length of this sub-array. 

If there is no return -1.

Note:
    1 <= |tag_list|,|all_tags| <=10000
    All string length <= 50
    String is not repeated in tag_list
    
Example1
    Input:  tag_list = ["made","in","china"], all_tags = ["made", "a","b","c","in", "china","made","b","c","d"]
    Output: 3
    Explanation:
    ["in", "china","made"] contains all the strings in tag_list,6 - 4 + 1 = 3.

Example2
    Input:  tag_list = ["a"], all_tags = ["b"]
    Output: -1
    Explanation:
    Does not exist.
**/

/**
*two points, same direction(slow + fast) two pointers, sliding window
*using hash set to keep all tags(keys) of tag_list as a dictionary
*using the hash map to maintain the current window(key-element, count) infomation 
   -map.size() should be the same as dictionary size when all key elements holding, 
   -map would not contain any key other than dictionary where it dose not exist.
*/
//version-1: two pointers(sliding window), time-complexity: O(n), space-complexity: O(n)
public class Solution {
    /**
     * @param tagList: The tag list.
     * @param allTags: All the tags.
     * @return: Return the answer
     */
    public int getMinimumStringArray(String[] tagList, String[] allTags) {
        // corner cases
        if (tagList == null || tagList.length == 0 || allTags == null || allTags.length == 0) {
            return -1;
        }

        Set<String> set = new HashSet<>();
        for (String tag : tagList) {
            set.add(tag);
        }

        int i;
        int j = 0;
        int size = allTags.length;
        int minCoverage = -1;

        Map<String, Integer> map = new HashMap<>();
        for (i = 0; i < size; i++) {
            while (j < size && !isSameSize(map, set)) {
                String key = allTags[j];

                if (set.contains(key)) {
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
                
                j++;
            }

            if (j <= size && isSameSize(map, set)) {

                if (minCoverage == -1) {
                    minCoverage = size;
                }
                
                int currentSize = j - i;//getSize(map);
                minCoverage = Math.min(minCoverage, currentSize);
                
                String key = allTags[i];
                if (!set.contains(key)) {
                    continue;
                }
                map.put(key, map.get(key) - 1);
                if (map.get(key) == 0) {
                    map.remove(key);
                }
            }
        }

        return minCoverage;

    }

    // helper method
    private boolean isSameSize(Map<String, Integer> map, Set<String> set) {
        return map.size() == set.size();
    }

    // private int getSize(Map<String, Integer> map) {
    //     return map.values().stream().reduce(0, Integer::sum);
    // }
}
