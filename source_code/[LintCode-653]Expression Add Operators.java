/***
* LintCode 653. Expression Add Operators
Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Example
	Example 1:
		Input:
			"123"
			6
		Output: 
			["1*2*3","1+2+3"]

	Example 2:
		Input:
			"232"
			8
		Output: 
			["2*3+2", "2+3*2"]

	Example 3:
		Input:
			"105"
			5
		Output:
			["1*0+5","10-5"]

	Example 4:
		Input:
			"00"
			0
		Output:
			["0+0", "0-0", "0*0"]

	Example 5:
		Input:
			"3456237490",
			9191 
		Output: 
			[]
***/
/*
* "12345"
  
  "12 + 3*4"-operator-dfs(5)
  preValue[12 + 3*4]
  lastValue[3*4]
  preStr = "12 + 3*4"
  operator[+, -, *]
  
  case operator   
				+:"12 + 3*4" + dfs(5)
					preValue = 12 + 3*4 = 24
					lastValue = 3 * 4 = 12
					preStr = "12 + 3*4"
					currentValue = Integer.valueOf("5") = 5
					
					lastValue = currentValue;
					preValue = preValue + currentValue--> 29-->end
					
				-:"12 + 3*4" - dfs(5)
					preValue = 12 + 3*4 = 24
					lastValue = 3 * 4 = 12
					preStr = "12 + 3*4"
					currentValue = Integer.valueOf("-5") = -5
					
					lastValue = currentValue; -5
					preValue = preValue + currentValue[-5]--> preValue - math.abs(currentValue) --> 19 -->end
					
				*:"12 + 3*4" * dfs(5)
					preValue = 12 + 3*4 = 24
					lastValue = 3 * 4 = 12
					preStr = "12 + 3*4"				
					
					currentValue = Integer.valueOf("5") = 5
					preValue = preValue - lastValue + lastValue * currentValue = 24 - 12 + 12 * 5 = 72--> "12 + 3* 4 * 5 = 72"
*/
public class Solution {
	// field
	private List<String> result;
	private int SIZE;
	
    /**
     * @param num: a string contains only digits 0-9
     * @param target: An integer
     * @return: return all possibilities
     */
    public List<String> addOperators(String num, int target) {
		result = new ArrayList<String>();
        // check corner cases
		if (num == null || num.length() == 0) {
			return result;
		}
		
		if (!isZeroHead(num) && Long.valueOf(num) == (long)target) {
		    result.add(num);
		}
		
		SIZE = num.length();
		
		dfs(num, (long)target, 0, "", 0, 0);
		
		return result;
    }
	
	// helper method
	private void dfs(String num, long target, int startIndex, String preStr, long preValue, long lastValue) {
		// check corner case
		if (startIndex == SIZE) {
			if (preValue == target && isContainingOperator(preStr)) {
				result.add(preStr);
			}
			
			return;
		}
		
		for (int i = startIndex + 1; i <= SIZE; i++) {
			String currentStr = num.substring(startIndex, i);
			if (isZeroHead(currentStr)) {
			    continue;
			}
			
			long currentValue = Long.valueOf(currentStr);
			
			if (startIndex == 0) {
				dfs(num, target, i, currentStr, currentValue, currentValue);				
				continue;
			}
			
			dfs(num, target, i, preStr + "+" + currentStr, preValue + currentValue, currentValue);
			dfs(num, target, i, preStr + "-" + currentStr, preValue - currentValue, -currentValue);
			dfs(num, target, i, preStr + "*" + currentStr, preValue - lastValue + lastValue * currentValue, lastValue * currentValue);
		}
	}
	
	private boolean isZeroHead(String num) {
	    return num.length() > 1 && num.charAt(0) == '0';
	}
	
	private boolean isContainingOperator(String str) {
	    return (str.length() > SIZE) && (str.indexOf("+") > 0 || str.indexOf("-") > 0 || str.indexOf("*") > 0);
	}
}