/***
* LintCode 390. Find Peak Element II
There is an integer matrix which has the following features:
	The numbers in adjacent positions are different.
	The matrix has n rows and m columns.
	For all i < m, A[0][i] < A[1][i] && A[n - 2][i] > A[n - 1][i].
	For all j < n, A[j][0] < A[j][1] && A[j][m - 2] > A[j][m - 1].	
We define a position P is a peek if:
	A[j][i] > A[j+1][i] && A[j][i] > A[j-1][i] && A[j][i] > A[j][i+1] && A[j][i] > A[j][i-1]
Find a peak element in this matrix. Return the index of the peak.
Example
	Given a matrix:
		[[1 ,2 ,3 ,6 ,5],
		  [16,41,23,22,6],
		  [15,17,24,21,7],
		  [14,18,19,20,10],
		  [13,14,11,10,9]]
		or [[1,2,3,4,5],[16,41,23,22,6],[15,17,24,21,7],[14,18,19,20,8],[13,12,11,10,9]]
***/ 
//version-1
public class Solution {	
    /*
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] A) {
        List<Integer> result = new ArrayList<Integer>();		
		// check corner case 
		if (A == null || A.length == 0 || A[0] == null || A[0].length == 0) {
			return result;
		}
		
		int start = 1;
		int end = A.length - 2;
		
		while (start <= end) {
			int mid = start + (end - start) / 2;
			int col = findPeakColumn(A[mid]);
			
			if (A[mid][col] < A[mid - 1][col]) {
				end = mid - 1;
			}
			else if (A[mid][col] < A[mid + 1][col]) {
				start = mid + 1;
			}
			else {
				result.add(mid);//row
				result.add(col);//col
				break;
			}
		}		
		return result;
    }

	// helper method
	private int findPeakColumn(int[]nums) {
		int result = 0;//default by 1st column of the array
		
		int start = 0;
		int end = nums.length;//***note this limited***
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			
			if (nums[mid] < nums[mid - 1]) {
				end = mid - 1;
			}
			else if (nums[mid] < nums[mid + 1]) {
				start = mid + 1;
			}
			else {
				end = mid;
			}
		}
		
		if (nums[start] > nums[end]) {
			result = start;
		}
		else {
			result = end;
		}
		
		return result;
	}
}

/*** LintCode 390. Find Peak Element II ***/
//version-2
public class Solution {	
    /*
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] A) {
        List<Integer> result = new ArrayList<Integer>();		
		// check corner case 
		if (A == null || A.length == 0 || A[0] == null || A[0].length == 0) {
			return result;
		}
		
		int start = 1;
		int end = A.length - 2;
		
		while (start <= end) {
			int mid = start + (end - start) / 2;
			int col = findPeakColumn(A[mid]);
			
			if (A[mid][col] < A[mid - 1][col]) {
				end = mid - 1;
			}
			else if (A[mid][col] < A[mid + 1][col]) {
				start = mid + 1;
			}
			else {
				result.add(mid);//row
				result.add(col);//col
				break;
			}
		}		
		return result;
    }

	// helper method
	private int findPeakColumn(int[]nums) {
		int result = 0; // default by 1st column of the array		
		int max = nums[0]; // default peak value by the 1st column of the array
		
		/*
		* this block of logic is just for the worse case, the sorted array consist of too many duplicates except of only one target value,
		*   for instance the input data as below:
		*		[[1,2,1,2,1,2,1],[2,17,13,6,5,17,2],[1,15,8,10,8,15,1],[2,14,12,11,12,14,2],[1,2,1,2,1,2,1]]
		*/
		for (int i = 0; i < nums.length; i++) {
			if (max < nums[i]) {
				max = nums[i];
				result = i;
			}
		}
		
		return result;
	}
}

//version-3
/***
* LintCode 390. Find Peak Element II
There is an integer matrix which has the following features:
	The numbers in adjacent positions are different.
	The matrix has n rows and m columns.
	For all i < m, A[0][i] < A[1][i] && A[n - 2][i] > A[n - 1][i].
	For all j < n, A[j][0] < A[j][1] && A[j][m - 2] > A[j][m - 1].	
We define a position P is a peek if:
	A[j][i] > A[j+1][i] && A[j][i] > A[j-1][i] && A[j][i] > A[j][i+1] && A[j][i] > A[j][i-1]
Find a peak element in this matrix. Return the index of the peak.
Example
	Given a matrix:
		[[1 ,2 ,3 ,6 ,5],
		  [16,41,23,22,6],
		  [15,17,24,21,7],
		  [14,18,19,20,10],
		  [13,14,11,10,9]]
		or 	[[1,2,3,4,5],[16,41,23,22,6],[15,17,24,21,7],[14,18,19,20,8],[13,12,11,10,9]]
			[[1,3,2],[4,6,5],[7,9,8],[13,15,14],[10,12,11]]
		    [[1,5,3],[4,10,9],[2,8,7]]
			[[1,2,1,2,1,2,1],[2,17,13,6,5,17,2],[1,15,8,10,8,15,1],[2,14,12,11,12,14,2],[1,2,1,2,1,2,1]]			[[161,163,21,58,12,931,18,75,65,122,535,172],[376,639,152,284,298,1559,89,214,127,123,683,216],[78,165,1046,704,1163,785,1426,1388,139,109,1562,506],[73,508,1222,87,297,16,405,102,33,115,156,113],[196,826,1952,1928,45,59,197,71,1035,31,179,103],[151,169,11,355,1641,74,38,108,88,946,1982,825],[25,417,1192,1214,1772,117,973,1302,616,185,180,48],[173,883,188,245,1446,668,153,685,1716,762,486,361],[55,105,93,112,1145,193,118,289,167,159,168,41]]
***/ 
public class Solution {	
    /*
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] A) {
        List<Integer> result = new ArrayList<Integer>();		
		// check corner case 
		if (A == null || A.length == 0 || A[0] == null || A[0].length == 0) {
			return result;
		}
		
		int start = 1;
		int end = A.length - 2;
		
		while (start <= end) {
			int mid = start + (end - start) / 2;
			int col = findPeakColumn(A[mid]);
			
			if (A[mid][col] < A[mid - 1][col]) {
				end = mid - 1;
			}
			else if (A[mid][col] < A[mid + 1][col]) {
				start = mid + 1;
			}
			else {
				result.add(mid);//row
				result.add(col);//col
				break;
			}
		}		
		return result;
    }

	// helper method
	private int findPeakColumn(int[]nums) {
		int result = 0;//default by 1st column of the array
		
		int start = 1;
		int end = nums.length;//***note this limited***
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			
			if (nums[mid] < nums[mid - 1]) {
				end = mid - 1;
			}
			else if (nums[mid] < nums[mid + 1]) {
				start = mid + 1;
			}
			else {
				end = mid;
			}
		}
		
		if (nums[start] > nums[end]) {
			result = start;
		}
		else {
			result = end;
		}
		
		return result;
	}
}