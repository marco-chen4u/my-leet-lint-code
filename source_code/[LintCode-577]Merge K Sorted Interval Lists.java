/***
 * LintCode 577. Merge K Sorted Interval Lists
Merge K sorted interval lists into one sorted interval list. You need to merge overlapping intervals too.

Example
    Example1
        Input: [
          [(1,3),(4,7),(6,8)],
          [(1,2),(9,10)]
        ]
        Output: [(1,3),(4,8),(9,10)]
    Example2
        Input: [
          [(1,2),(5,6)],
          [(3,4),(7,8)]
        ]
        Output: [(1,2),(3,4),(5,6),(7,8)]
***/

/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */
public class Solution {
    // helper methods
    private Interval mergeTwoIntervals(List<Interval> result, Interval pre, Interval current) {
        if (pre == null) {
            return current;
        }
        
        if (pre.end < current.start) {
            result.add(pre);
            return current;
        }
        
        // merge
        pre.end = Math.max(pre.end, current.end);
        
        return pre;
    }    
    
    private List<Interval> mergeTwoIntervalList(List<Interval> left, List<Interval> right) {
        List<Interval> result = new ArrayList<Interval>();
        // check corner case
        if ((left == null || left.size() == 0) && 
                (right == null || right.size() == 0)) {
            return result;
        }
        
        if (left == null || left.size() == 0) {
            return right;
        }
        
        if (right == null || right.size() == 0) {
            return left;
        }
        
        // two pointers
        int i = 0, j = 0;
        Interval current = null, pre = null;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).start < right.get(j).start) {
                current = left.get(i);
                i++;
            }
            else {
                current = right.get(j);
                j++;
            }
            
            pre = mergeTwoIntervals(result, pre, current);
        }
        
        while (i < left.size()) {
            current = left.get(i);
            pre = mergeTwoIntervals(result, pre, current);
            i++;
        }
        
        while (j < right.size()) {
            current = right.get(j);
            pre = mergeTwoIntervals(result, pre, current);
            j++;
        }
        
        if (pre != null) {
            result.add(pre);
        }
        
        return result;
    }
    
    private List<Interval> mergeIntervalLists(List<List<Interval>> intervals, int start, int end) {
        if (start == end) {
            return intervals.get(start);
        }
        
        // divide
        int mid = start + (end - start) / 2;
        List<Interval> left = mergeIntervalLists(intervals, 0, mid);
        List<Interval> right = mergeIntervalLists(intervals, mid + 1, end);
        
		// conquer
        return mergeTwoIntervalList(left, right);
    }
    
    /**
     * @param intervals: the given k sorted interval lists
     * @return:  the new sorted interval list
     */
    public List<Interval> mergeKSortedIntervalLists(List<List<Interval>> intervals) {
        return mergeIntervalLists(intervals, 0, intervals.size() - 1);
    }
}