/***
* LintCode 1352. Compare Version Numbers
Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Example
    Example1
        Input: version1 = "1"，version2 = "01"
        Output: 0

    Example2
        Input: version1 = "4.9"，version2 = "4.5"
        Output: 1
***/
public class Solution {
    // field
    private final String SEPARATOR = "\\.";
	
    /**
     * @param version1: the first given number
     * @param version2: the second given number
     * @return: the result of comparing
     */
    public int compareVersion(String version1, String version2) {
        int result = 0;
        String[] levels1 = version1.split(SEPARATOR);
        String[] levels2 = version2.split(SEPARATOR);
        
        int size = Math.max(levels1.length, levels2.length);
        
        for (int i = 0; i < size; i++) {
            int value1 = (i < levels1.length) ? Integer.valueOf(levels1[i]) : 0;
            int value2 = (i < levels2.length) ? Integer.valueOf(levels2[i]) : 0;
            
            if (value1 != value2) {
                return value1 > value2 ? 1 : -1;
            }
        }
        
        return 0;
    }
}
