/***
* LintCode 680. Split Word
Give a string, you can choose to split the string after one character or two adjacent characters, 
and make the string to be composed of only one character or two characters. Output all possible results.

Example
	Example1
		Input: "123"
		Output: [["1","2","3"],["12","3"],["1","23"]]

	Example2
		Input: "12345"
		Output: [["1","23","45"],["12","3","45"],["12","34","5"],
			["1","2","3","45"],["1","2","34","5"],["1","23","4","5"],
			["12","3","4","5"],["1","2","3","4","5"]]
***/
public class Solution {
	
	// helper method
	private void splitStringHelper(List<List<String>> result, List<String> combination, String s, int index) {
		if (index == s.length()) {
			result.add(new ArrayList<String>(combination));
			return;
		}
		
		// (1) one-character combination way
		combination.add(String.valueOf(s.charAt(index)))
		splitStringHelper(result, combination, s, index + 1);
		combination.remove(combination.size() - 1);
		
		// (2) two-character combination way
		if (index < s.length() - 1) {
			combination.add(s.substring(index, index + 2)); //*notice* substring(index,index+2)=s[index,index+1]
			splitStringHelper(result, combination, s, index + 2);
			combination.remove(combination.size() - 1);
		}
	}
	
    /*
     * @param : a string to be split
     * @return: all possible split string array
     */
    public List<List<String>> splitString(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
		List<String> combination = new ArrayList<String>();
		// check corner case
		if (s == null) {
			return result;
		}
		
		
		if (s.length() == 0) {
			result.add(combination);
			return result;
		}
		
		splitStringHelper(result, combination, s, 0);
		
		return result;
    }
}