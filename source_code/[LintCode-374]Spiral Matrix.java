/***
* LintCode 374. Spiral Matrix
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example
	Example 1:
		Input:	[[ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ]]
		Output: [1,2,3,6,9,8,7,4,5]
	Example 2
		Input:	[[ 6,4,1 ], [ 7,8,9 ]]
		Output: [6,4,1,9,8,7]

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
     * @param matrix: a matrix of m x n elements
     * @return: an integer list
     */
    public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<Integer>();
        // check corner case
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return result;
		}
		
		int m = matrix.length; // row size
		int n = matrix[0].length; // column size
		
		int top = 0;
		int bottom = m - 1;
		int left = 0;
		int right = n - 1;
		
		// as long as to take a spiral round 
		while (top < bottom && left < right) {
			// top left -> top right(except the right most one for the next operation)
			for (int i = left; i < right; i++) {
				result.add(matrix[top][i]);
			}
			
			// top right -> bottom right(except the right bottom one for the next operation)
			for (int i = top; i < bottom; i++) {
				result.add(matrix[i][right]);
			}
			
			// bottom right -> bottom left(except the left most one for the next operation)
			for (int i = right; i > left; i--) {
				result.add(matrix[bottom][i]);
			}
			
			// bottom left -> top left (except the top most one case that one has been process on the first operation)
			for (int i = bottom; i > top; i--) {
				result.add(matrix[i][left]);
			}
			
			// continue for the next inner spiral round
			top++;
			bottom--;
			left++;
			right--;
		}
		
		// last stop for corner case to get the left-out[(a)only one row of elements, (b)only one colun of elements, (c)only one element]
		if (top == bottom) {// case (a) or (c)
			for (int i = left; i <= right; i++) {
				result.add(matrix[top][i]);
			}
		}
		else if (left == right) {// case (b) or (c)
			for (int i = top; i <= bottom; i++) {
				result.add(matrix[i][left]);
			}
		}
		
		return result;
    }
}