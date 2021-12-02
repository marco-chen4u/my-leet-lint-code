/***
* LintCode 935. Cartesian Product
We use a two-dimensional array setList[][] to represent a collection array, 
and each element in setList[i] is an integer and is not the same. 
Find the cartesian product of setList[0],setList[1],...,setList[setList.length - 1].

In general, the Cartesian product of the collection A and the set B is A×B = {(x,y)|x∈A∧y∈B}。
    *1 <= setList.length <= 5
    *1 <= setList[i].length <= 5

Example 1
    Input:  
        setList = [[1,2,3],[4],[5,6]]
    Output: [[1,4,5],[1,4,6],[2,4,5],[2,4,6],[3,4,5],[3,4,6]]
    Explanation:
        The cartesian product of [1,2,3], [4] and [5,6] is [[1,4,5],[1,4,6],[2,4,5],[2,4,6],[3,4,5],[3,4,6]].

Example 2:
    Input:  
        setList = [[1,2,3],[4]]
    Output: [[1,4],[2,4],[3,4]]
    Explanation:
        The cartesian product of [1,2,3] and [4] is [[1,4],[2,4],[3,4]].
***/

//version-1: DFS
public class Solution {
    private int n; // row size
    /**
     * @param setList: The input set list
     * @return: the cartesian product of the set list
     */
    public List<List<Integer>> getSet(int[][] setList) {
        List<List<Integer>> result = new ArrayList<>();
        // check corner cases
        if (setList == null || setList.length == 0) {
            return result;
        }
        // initialize
        n = setList.length;

        // normal case
        List<Integer> list = new ArrayList();
        dfs(setList, 0, list, result);

        return result;
    }

    // helper method
    private void dfs(int[][] setList, int currentRow, List<Integer> list, List<List<Integer>> result) {
        // check corner case
        if (currentRow == n) {
            result.add(new ArrayList<Integer>(list));

            return;
        }

        // normal case
        int m = setList[currentRow].length;// column size
        for (int i = 0; i < m; i++) {
            list.add(setList[currentRow][i]);
            dfs(setList, currentRow + 1, list, result);
            list.remove(list.size() - 1);
        }
    }
}
