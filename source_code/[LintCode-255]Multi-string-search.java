/**
*LintCodet 255. Multi-string search
Given a source string sourceString and a target string array targetStrings, 
determine whether each string in the target string array is a substring of the source string
    len(sourceString) <= 1000
    sum(len(targetStrings[i])) <= 1000

Example
    Example 1:
        Input: sourceString = "abc" ，targetStrings = ["ab","cd"]
        Output: [true, false]
    Example 2:
        Input: sourceString = "lintcode" ，targetStrings = ["lint","code","codes"]
        Output: [true,true,false]
**/
public class Solution {
    /**
     * @param sourceString: a string
     * @param targetStrings: a string array
     * @return: Returns a bool array indicating whether each string in targetStrings is a substring of the sourceString
     */
    public boolean[] whetherStringsAreSubstrings(String sourceString, String[] targetStrings) {
        boolean[] result = new boolean[0];
        // check corner cases
        if (targetStrings == null || targetStrings.length == 0) {
            return result;
        }

        int size = targetStrings.length;
        result = new boolean[size];
        Arrays.fill(result, false);

        if (sourceString == null || sourceString.isEmpty()) {
            return result;
        }

        int n = sourceString.length();

        int index = 0;
        for (String targetString : targetStrings) {
            result[index++] = (sourceString.indexOf(targetString) != -1);
        }

        return result;
    }
}
