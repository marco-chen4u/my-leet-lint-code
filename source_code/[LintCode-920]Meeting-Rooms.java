/**
* LintCode 920. Meeting Rooms
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
determine if a person could attend all meetings.

*Note:
    (0,8),(8,10) is not conflict at 8

Example1
    Input: intervals = [(0,30),(5,10),(15,20)]
    Output: false
    Explanation: 
        (0,30), (5,10) and (0,30),(15,20) will conflict

Example2
    Input: intervals = [(5,8),(9,15)]
    Output: true
    Explanation: 
        Two times will not conflict
        
Link: https://www.lintcode.com/problem/920/

Related Problem:
    LintCode 919. Meeting Rooms II https://www.lintcode.com/problem/919
    LintCode 156. Merge Intervals https://www.lintcode.com/problem/156

Tags:
    Sort
**/

//version-1: iteration + sorting[Collecitons.sort() + comparator]
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
    private static final Comparator<Interval> comparator = new Comparator<Interval>(){
        @Override
        public int compare(Interval a, Interval b) {
            if (a.start != b.start) {
                return a.start - b.start;
            }
            
            return a.end - b.end;
        }
    };

    /**
     * @param intervals: an array of meeting time intervals
     * @return: if a person could attend all meetings
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
        // corner case
        if (intervals == null || intervals.isEmpty()) {
            return true;
        }

        Collections.sort(intervals, comparator);

        boolean result = true;
        Interval pre = null;
        for (Interval current : intervals) {
            if (pre == null) {
                pre = current;
                continue;
            }

            if (isOverLapping(pre, current)) {
                result = false;
                break;
            }

            pre = current;            
        }

        return result;
    }

    // helper method
    private boolean isOverLapping(Interval pre, Interval current) {
        return pre.end > current.start;
    }
}
