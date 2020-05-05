/***
* LintCode LowBit Function
lowBit() function is used in binary indexed tree, it is able to calculate 2 power of the count of last contiguous 0 of a number-x in binary format,
if the k is the count for last consecutive 0 of number x in binary inside the function-lowBit(x) , the result should 2^k
Example
Example 1
	Input: 4
	Output: 4
	Explanation:
		The input number-4, its binary value is : 100, so the last consecutive 0 is 2, and the result is 2^2 = 4

Example 2
Input: 5
Output: 1
Explanation: 
	The Input number-5, its binary value is: 101, last digit in binary is 1, 
	there's no 0s, so there's 0 count of last consecutive 0, and the result is 2^0 = 1
***/

// version-1: O(nlogn)
class Solution {
	public int lowBit(int x) {
		int count = 0;
		while (x) {
			if (x & 1) {
				break;
			}
			
			count++;
			x = x >> 1;//right shit by 1 bit
		}
		
		return 2^count;
	}
}

// version-2: 2's complement, O(1)
class Solution {
	public int lowBit(int x) {
		return x & -x;
	}
}

	eg.(version-2) 
	6 & - 6 = ?
	expalanation:
		6 : 0000 0110                  [original code: positive number]

		-6: 1111 1001 + 1(last digit)  [reversed code + 1 : negative number]
		 -> 1111 1010
		--------------
		 
		 6 0000 0110
		 &
		-6 1111 1010
		------------------
		   0000 0010
				  = 2^1 = 2
		so, 6 & -6 = 2
