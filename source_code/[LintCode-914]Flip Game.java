/***
* LintCode 914. Flip Game
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

Example
	Example1
		Input:  s = "++++"
		Output: 
			[
			  "--++",
			  "+--+",
			  "++--"
			]
		
	Example2
		Input: s = "---+++-+++-+"
		Output: 
			[
				"---+++-+---+",
				"---+++---+-+",
				"---+---+++-+",
				"-----+-+++-+"
			]
***/
/*
字符串indexOf（）的处理
*/
public class Solution {
    /**
     * @param s: the given string
     * @return: all the possible states of the string after one valid move
     */
    public List<String> generatePossibleNextMoves(String s) {
		List<String> result = new ArrayList<String>();
		
		if (s == null || s.length() == 0) {
			return result;
		}
		
		for (int i = -1; (i = s.indexOf("++", i + 1)) >= 0;) {
			String newStr = s.substring(0, i) + "--" + s.substring(i + 2);
			
			if (!result.contains(newStr)) {
				result.add(newStr);
			}
		}
		
		return result;
	}
}