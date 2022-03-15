/***
* LintCode 156. Merge Intervals
Given a collection of intervals, merge all overlapping intervals.

Example 1:
    Input: [(1,3)]
    Output: [(1,3)]

Example 2:
    Input:  [(1,3),(2,6),(8,10),(15,18)]
    Output: [(1,6),(8,10),(15,18)]

Challenge
    O(n log n) time and O(1) extra space.
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
//version-1: iteration
public class Solution {
    /**
     * @param intervals: interval list.
     * @return: A new interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<Interval>();
        // check corner case
	if (intervals == null || intervals.isEmpty()) {
            return result;
	}

	Comparator<Interval> comparator = new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        };

        Collections.sort(intervals, comparator);

        Interval pre = null;
        Iterator<Interval> it = intervals.iterator();
        while (it.hasNext()) {
            Interval current = it.next();
            if (pre == null || pre.end < current.start) {
                pre = current;
            }
            else {
                pre.end = Math.max(pre.end, current.end);
                it.remove();
            }
        }

        result = intervals;

        return result;
    }
}

//version-2: iteration
public class Solution {
    /**
     * @param intervals: interval list.
     * @return: A new interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<Interval>();
        
        if (intervals == null || intervals.isEmpty()) {
            return result;
        }
        
        intervals.sort(Comparator.comparing(i ->i.start));
        
        Interval last = null;
        for (Interval current : intervals) {
            if (last == null || last.end < current.start) {
                result.add(current);
                last = current;
            }
            else {
                last.end = Math.max(last.end, current.end);
            }
        }
        
        return result;
    }
}

//version-3:iteration
public class Solution {
    /**
     * @param intervals: interval list.
     * @return: A new interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        if (intervals == null || intervals.isEmpty()) {
            return result;
        }

        Collections.sort(intervals, (a, b)-> a.start - b.start);

        Interval pre = null;
        for (Interval current : intervals) {
            if (pre == null) {
                pre = current;
                continue;
            }

            if (isOverlapping(pre, current)) {
                pre = merge(pre, current);
                continue;
            }

            result.add(pre);
            pre = current;
        }

        if (pre != null) {
            result.add(pre);
        }

        return result;
    }

    // helper methods
    private boolean isOverlapping(Interval pre, Interval current) {
        if (pre == null || current == null) {
            return false;
        }

        return pre.end >= current.start;
    }

    private Interval merge(Interval pre, Interval current) {
        if (pre == null) {
            return current;
        }

        if (current == null) {
            return pre;
        }

        int start = Math.min(pre.start, current.start);
        int end = Math.max(pre.end, current.end);

        Interval result = new Interval(start, end);
        return result;
    }
}
