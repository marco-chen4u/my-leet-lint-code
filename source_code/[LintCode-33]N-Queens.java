/***
 * LintCode 33. N-Queens
The n-queens puzzle is the problem of placing n queens on an n×n chessboard 
such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, 
where 'Q' and '.' both indicate a queen and an empty space respectively.

Example
    Example 1:
        Input:1
        Output:
           [["Q"]]
    
    
    Example 2:
        Input:4
        Output:
            [
              // Solution 1
              [".Q..",
               "...Q",
               "Q...",
               "..Q."
              ],
              // Solution 2
              ["..Q.",
               "Q...",
               "...Q",
               ".Q.."
              ]
            ]

Challenge
    Can you do it without recursion?
***/
/*
* x = 行， y = 列， 那么新的x' , y'
* 避免同列攻击(y = y')，斜线攻击(x + y = x' + y')和反斜线攻击(x-y = x' - y'),这些情况都要避免， 才能符合条件。
* 本题，用到深度优先搜素算法，结合字符数组填充知识和相关的同列、path列表值中，当前列值与当前行数值的规则判断，即斜线和反斜线攻击的规避判断。
*/
public class Solution {
    // helper methods
    private List<String> drawChessBoard(List<Integer> rows) {
        int size = rows.size();
        List<String> chessBoard = new ArrayList<String>();
        
        for(int rowIndex = 0; rowIndex < size; rowIndex++) {
            StringBuilder sb = new StringBuilder();
            
            for (int colIndex = 0; colIndex < size; colIndex++) {
                sb.append(colIndex == rows.get(rowIndex) ? 'Q' : '.');
            }
            
            chessBoard.add(sb.toString());
			
            /*char[] charArray = new char[size];
            Arrays.fill(charArray, '.');
            charArray[rows.get(rowIndex)] = 'Q';
            chessBoard.add(String.valueOf(charArray));*/
        }
        
        return chessBoard;
    }
    
    private boolean isValid(List<Integer> rows, int columnValue) {
        int rowSize = rows.size();
        for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
            // same column
            if (rows.get(rowIndex) == columnValue) {
                return false;
            }
            
            // slash attach (left-top -> right-bottom)
            if (rowIndex + rows.get(rowIndex) == rowSize + columnValue){
                return false;
            }
            
            // back-slash attach (left-bottom -> right-top)
            if (rowIndex - rows.get(rowIndex) == rowSize - columnValue){
                return false;
            }
        }
        
        return true;
    }
    
    private void search(List<List<String>> result, List<Integer> rows, int n) {
        if (rows.size() == n) {
            result.add(drawChessBoard(rows));
        }
        
        for (int i = 0; i < n; i++) {
            if (!isValid(rows, i)) {
                continue;
            }
            
            rows.add(i);
            search(result, rows, n);
            rows.remove(rows.size() - 1);
        }
    }
    
    /*
     * @param n: The number of queens
     * @return: All distinct solutions
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<List<String>>();
        
        // check corner case
        if (n <= 0) {
            return result;
        }
        
        search(result, new ArrayList<Integer>(), n);
        
        return result;
    }
}