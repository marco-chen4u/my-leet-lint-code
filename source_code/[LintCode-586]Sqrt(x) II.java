/***
* LintCode 586. Sqrt(x) II
Implement double sqrt(double x) and x >= 0.
Compute and return the square root of x.

Example
	Example 1:
		Input: n = 2 
		Output: 1.41421356
	Example 2:
		Input: n = 3
		Output: 1.73205081
Notice
	You do not care about the accuracy of the result, we will help you to output results.
***/
public class Solution {
    /**
     * @param x: a double
     * @return: the square root of x
     */
    public double sqrt(double x) {
		double start = 0;
		double end = Math.max(x, 1.0);
		double EPS = 1e-12;//define the closest difference unit
		
		while (start + EPS < end) {
			double mid = start + (end - start) / 2;
			
			if (getPower(mid) > x) {// getting the 1st position that is >= target(x)
				end = mid;
			}
			else {
				start = mid;
			}
		}
		
		if (getPower(start) >= x) {
			return start;
		}
		else {
			return end;
		}
		
	}
	
	private double getPower(double value) {
		return value * value;
	}
}