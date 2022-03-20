/**
* LintCode 919 · Meeting Rooms II
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
find the minimum number of conference rooms required.)

* Notice:
    (0,8),(8,10) is not conflict at 8
    
Example1
    Input: intervals = [(0,30),(5,10),(15,20)]
    Output: 2
    Explanation:
        We need two meeting rooms
        room1: (0,30)
        room2: (5,10),(15,20)
        
Example2
    Input: intervals = [(2,7)]
    Output: 1
    Explanation: 
        Only need one meeting room

Link: https://www.lintcode.com/problem/919

Related Problems:
    LintCode 920. Meeting Rooms https://www.lintcode.com/problem/920
    LintCode 156. Merge Intervals https://www.lintcode.com/problem/156

Tags:
    Sort, Sweep Line
**/
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
//version-1: sort + sweep line
class Meeting {
    // fields
    int time;
    int state; // open-1; close-0.
    // constructor
    public Meeting(int time, int state) {
        this.time = time;
        this.state = state;
    }
}

public class Solution {
    //fields
    private final int OPEN = 1;
    private final int CLOSE = 0;
    
    /**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    public int minMeetingRooms(List<Interval> intervals) {
        int result = 0;
        // check corner case
        if (intervals == null || intervals.isEmpty()) {
            return result;
        }
        
        int size = intervals.size();
        Meeting[] meetings = new Meeting[size * 2];
        int index = 0;
        for (Interval interval : intervals) {
            meetings[index++] = new Meeting(interval.start, OPEN);
            meetings[index++] = new Meeting(interval.end, CLOSE);
        }
        
        Comparator<Meeting> comparator = new Comparator<Meeting>() {
            @Override
            public int compare(Meeting a, Meeting b) {
                if (a.time == b.time) {
                    return a.state - b.state;
                }
                
                return a.time - b.time;
            }
        };
        
        Arrays.sort(meetings, comparator);
        
        int count = 0;
        for (Meeting current : meetings) {
            if (current.state == OPEN) {
                count++;
            }
            else {
                count--;
            }
            
            result = Math.max(result, count);
        }
        
        return result;
    }
}
