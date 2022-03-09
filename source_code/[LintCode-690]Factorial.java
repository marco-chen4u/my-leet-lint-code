/**
* LintCode 690. Factorial
Given a number n, return the factorial of the number as a string.

Link: https://www.lintcode.com/problem/690

Example 1
    Input: 0
    Output: "1"

Example 2
    Input: 20
    Output: "2432902008176640000"
    
related problem:
    LintCode 771. Double Factorial https://www.lintcode.com/problem/771
    LintCode 729. Last Digit By Factorial Divide https://www.lintcode.com/problem/729
    LintCode 728. Three Distinct Factors https://www.lintcode.com/problem/728
    LintCode 655. Add Strings https://www.lintcode.com/problem/655
    LintCode 656. Multiply Strings https://www.lintcode.com/problem/656
**/
// version-1: iteration
public class Solution {
    /**
     * @param n: an integer
     * @return:  the factorial of n
     */
    public String factorial(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(1);

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < result.size(); j++) {
                result.set(j, result.get(j) * i);
            }

            for (int j = 0; j < result.size() - 1; j++) {
                result.set(j + 1, result.get(j + 1) + result.get(j) / 10);
                result.set(j, result.get(j) % 10);
            }

            while (result.get(result.size() - 1) > 9) {
                result.add(result.get(result.size() - 1) / 10);
                result.set(result.size() - 2, result.get(result.size() -2) % 10);
            }
        }

        StringBuilder sb = new StringBuilder();
        Collections.reverse(result);
        for (int value : result){
            sb.append(value);
        }

        return sb.toString();
    }
}

//version-2: iteration
public class Solution {
    /**
     * @param n: an integer
     * @return:  the factorial of n
     */
    public String factorial(int n) {
        List<Integer> values = new ArrayList<>();
        
        values.add(1);

        for (int value = 2; value <= n; value++) {
            for (int i = 0; i < values.size(); i++) {
                values.set(i, values.get(i) * value);
            }

            // moving all new digits forward to the last, to make the rest except the last one, which of digits is [0..9]
            for (int i = 0; i < values.size() - 1; i++) {
                int current = values.get(i);
                int newDigit = current / 10;
                int remain = current % 10;
                values.set(i + 1, values.get(i + 1) + newDigit);
                values.set(i, remain);
            }

            // process the last value of value list to make more reasonable
            while (values.get(values.size() - 1) > 9) {
                int lastPos = values.size() -1;
                int newDigit = values.get(lastPos) / 10;
                int remain = values.get(lastPos) % 10;
                values.add(newDigit);
                int preLastPos = lastPos;
                values.set(preLastPos, remain);
            }
        }

        Collections.reverse(values);
        StringBuilder sb = new StringBuilder();
        for (int value : values) {
            sb.append(value);
        }

        return sb.toString();
    }
}
