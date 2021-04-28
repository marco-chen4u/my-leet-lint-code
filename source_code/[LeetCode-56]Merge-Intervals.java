/***
* LeetCode 56. Merge Intervals
Given a collection of intervals, merge all overlapping intervals.

Example 1:
    Input: [[1,3],[2,6],[8,10],[15,18]]
    Output: [[1,6],[8,10],[15,18]]
    Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2: 
    Input: [[1,4],[4,5]]
    Output: [[1,5]]
    Explanation: Intervals [1,4] and [4,5] are considered overlapping.

Note:
    input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
***/
class Solution {
    private final int[] DEFAULT = new int[0];//null
    
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparing(value->value[0]));
        
        int[] last = DEFAULT;
        List<int[]> list = new ArrayList<>();
        for (int[] current : intervals) {
            if (last == DEFAULT || last[1] < current[0]) {
                list.add(current);
                last = current;
            }
            else {
                last[1] = Math.max(last[1], current[1]);
            }
        }
        
        int[][] result = list.toArray(new int[list.size()][2]);
        
        return result;
    }
}
