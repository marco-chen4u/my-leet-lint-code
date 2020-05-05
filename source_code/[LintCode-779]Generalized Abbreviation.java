/***
* LintCode 779. Generalized Abbreviation
Write a function to generate the generalized abbreviations of a word.(order does not matter)

Example
	Example 1:
		Input: 
			word = "word", 
		Output: 
			["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

	Example 2:
		Input:
			word = "today"
		Output:
			["1o1a1", "1o1ay", "1o2y", "1o3", "1od1y", "1od2", "1oda1", "1oday", "2d1y", "2d2", "2da1", "2day", "3a1", "3ay", "4y", "5", "t1d1y", "t1d2", "t1da1", "t1day", "t2a1", "t2ay", "t3y", "t4", "to1a1", "to1ay", "to2y", "to3", "tod1y", "tod2", "toda1", "today"]
***/
//version-1: DFS
/*
* 通过DFS对所有缩写的可能进行搜索，从字符串的第一个字符开始，依次搜索该字位符进行和不进行缩写操作后可能的情况，直到搜索至最后一位。
*/
public class Solution {
    /**
     * @param word: the given word
     * @return: the generalized abbreviations of a word
     */
    public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<String>();
        
        // check corner case
        if (isEmptyStr(word)) {
            return result;
        }
        
        int startIndex = 0;
        String currentStr = "";
        int count = 0;
        dfs(result, word, startIndex, currentStr, count);
        
        return result;
    }
    
    // helper method
    private void dfs(List<String> result, String word, int startIndex, String currentStr, int count) {
        // check corner case
        if (startIndex == word.length()) {
            if (count > 0) {
                currentStr += count;
            }
            
            result.add(currentStr);
            
            return;
        }
        
        dfs(result, word, startIndex + 1, currentStr, count + 1);// take current postion's char as for a number
        
        dfs(result, word, startIndex + 1, currentStr + ((count > 0) ? count : "") + word.charAt(startIndex), 0); // concate current postion's char into the currentStr, and go to next recursion
    }
    
    private boolean isEmptyStr(String str) {
        return str == null || str.length() == 0;
    }
}

//version-2: bit operation
public class Solution {
    /**
     * @param word: the given word
     * @return: the generalized abbreviations of a word
     */
    public List<String> generateAbbreviations(String word) {
		List<String> result = new ArrayList<String>();
		// check corner case
		if (word == null || word.length() == 0) {
			return result;
		}
		
		int wordSize = word.length();
		double limit = Math.pow(2, wordSize);
		int mask = 0;
		
		while (mask < limit) {
			
			String currentStr = "";
			int count = 0;
			int submask = 1;
			
			for (int i = 0; i < wordSize; i++) {
				if ((mask & submask) == 0) {
					if (count > 0) {
						currentStr += count;
						count = 0;//reset the value
					}
					
					currentStr += word.charAt(i);
				}
				else {
					count++;
				}
				
				submask <<= 1;
			}
			
			if (count > 0) {
				currentStr += count;
			}
			
			result.add(currentStr);
			
			mask++;
		}
		
		return result;
	}
}