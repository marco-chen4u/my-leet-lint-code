/***
* LintCode 680. Split Word
Give a string, you can choose to split the string after one character or two adjacent characters, 
and make the string to be composed of only one character or two characters. Output all possible results.

Example1
    Input: "123"
    Output: [["1","2","3"],["12","3"],["1","23"]]

Example2
    Input: "12345"
    Output: 
        [
	 ["1","23","45"],
	 ["12","3","45"],
	 ["12","34","5"],
         ["1","2","3","45"],
	 ["1","2","34","5"],
	 ["1","23","4","5"],
         ["12","3","4","5"],
	 ["1","2","3","4","5"]
	]
***/
//version-1: DFS-cominbation based problem to solve
public class Solution {
    /**
     * @param s: a string to be split
     * @return: all possible split string array
     */
    public List<List<String>> splitString(String s) {
        // write your code here
        List<List<String>> result = new ArrayList<>();
        if (s == null) {
            return result;
        }

        List<String> combination = new ArrayList<>();
        if (s.isEmpty()) {
            result.add(combination);
            return result;
        }

        find(s, 0, combination, result);

        return result;
    }

    // helper method
    private void find(String str, int currentIndex, List<String> combination, List<List<String>> result) {
        if (currentIndex == str.length()) {
            result.add(new ArrayList<String>(combination));
            return;
        }

        // (1)concatenate 1 character scenario
        combination.add(String.valueOf(str.charAt(currentIndex)));
        find(str, currentIndex + 1, combination, result);
        combination.remove(combination.size() - 1);

        // (2)contatenate 2 character scenario
        if (currentIndex >= str.length() - 1) {
            return;
        }
        combination.add(str.substring(currentIndex, currentIndex + 2));
        find(str, currentIndex + 2, combination, result);
        combination.remove(combination.size() - 1);

    }
}
