/***
* LintCode 381. Spiral Matrix II
Given an integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.
(The spiral rotates clockwise from the outside to the inside, referring to examples)

Example
	Example 1:
		input: 2
		output:
			[
			  [1, 2],
			  [4, 3]
			]

	Example 2:
		input: 3
		output:
			[
			  [ 1, 2, 3 ],
			  [ 8, 9, 4 ],
			  [ 7, 6, 5 ]
			]
***/
/*
* (1)spiral旋转，是从矩阵的左顶点开始顺时针，由外到内选择。
* (2)top left-> top right -> bottom right -> bottom left -> up left
     如果i是代表其运动变量，那么其运动轨迹如下：
		上[top][i]     运动范围 i的取值范围[left, right)  注意不包括riht这一列，它是在下一个运动范围中
		右[i][right]                    [top, bottom)
		下[bottom][i]                   [right, left)
		左[i][left]                     [bottom, top)
	 每一次完整的spiral旋转运动，都是包含上述3个部分构成，直至第(3)情况的出现，进行corner case处理。
* (3) 最后没法旋转之后，直到这个圈绕完后，只剩下3中情况： (a)只剩下被包围的最中间“必定是从左到右”的一行； （b）只剩下被包围的最中间“必定是从上到下”一列； （c）只剩下被包围的最中间一个点。
*/
public class Solution {
    /**
     * @param n: An integer
     * @return: a square matrix
     */
    public int[][] generateMatrix(int n) {
		int[][] matrix = new int[n][n];
		
		// check corner case
		if (n <= 0) {
		    return matrix;
		}
		
		int top = 0;
		int bottom = n - 1;
		int left = 0;
		int right = n -1;
		
		int value = 1;
        
		// as long as to take a spiral round 
		while (top < bottom && left < right) {
			// top left -> top right(except the right most one for the next operation)
			for (int i = left; i < right; i++) {
				matrix[top][i] = value++;
			}
			
			// top right -> bottom right(except the right bottom one for the next operation)
			for (int i = top; i < bottom; i++) {
				matrix[i][right] = value++;
			}
			
			// bottom right -> bottom left(except the left most one for the next operation)
			for (int i = right; i > left; i--) {
				matrix[bottom][i] = value++;
			}
			
			// bottom left -> top left (except the top most one case that one has been process on the first operation)
			for (int i = bottom; i > top; i--) {
				matrix[i][left] = value++;
			}
			
			// continue for the next inner spiral round
			top++;
			bottom--;
			left++;
			right--;
		}
		
		// last stop for corner case to get the left-out[(a)only one row of elements, (b)only one colun of elements, (c)only one element]
		/*if (top == bottom) {// case (a) or (c)
			for (int i = left; i <= right; i++) {
				matrix[top][i] = value++;
			}
		}
		else if (left == right) {// case (b) or (c)
			for (int i = top; i <= bottom; i++) {
				matrix[i][left] = value++;
			}
		}*/
		if (n % 2 == 1) {
			matrix[n / 2][n / 2] = value;
		}
		
		
		return matrix;
    }
}