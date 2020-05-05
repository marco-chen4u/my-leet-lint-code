/***
* LintCode 839. Merge Two Sorted Interval Lists
Merge two sorted (ascending) lists of interval and return it as a new sorted list. 
The new sorted list should be made by splicing together the intervals of the two lists and sorted in ascending order.

Example
	Example1
		Input: list1 = [(1,2),(3,4)] and list2 = [(2,3),(5,6)]
		Output: [(1,4),(5,6)]
		Explanation:
			(1,2),(2,3),(3,4) --> (1,4)
			(5,6) --> (5,6)

	Example2
		Input: list1 = [(1,2),(3,4)] and list2 = [(4,5),(6,7)]
		Output: [(1,2),(3,5),(6,7)]
		Explanation:
			(1,2) --> (1,2)
			(3,4),(4,5) --> (3,5)
			(6,7) --> (6,7)
Notice
	The intervals in the given list do not overlap.
	The intervals in different lists may overlap.
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
	
	// helper method
	private Interval merge(List<Interval> result, Interval last, Interval current) {
		if (last == null) {
			return current;
		}
		
		if (last.end < current.start) {
			result.add(last);			
			return current;
		}
		
		last.end = Math.max(last.end, current.end);
		
		return last;
	}
    /**
     * @param list1: one of the given list
     * @param list2: another list
     * @return: the new sorted list of interval
     */
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
		List<Interval> result = new ArrayList<Interval>();
        // check corner cases
		if ((list1 == null || list1.size() == 0) && 
				(list2 == null || list2.size() == 0))
		{
			return result;
		}
		
		if (list1 == null || list1.size() == 0) {
			return list2;
		}
		
		if (list2 == null || list2.size() == 0) {
			return list1;
		}
		
		// two pointers
		int i = 0;
		int j = 0;
		Interval current = null;
		Interval last = null;
		
		while (i < list1.size() && j < list2.size()) {
			if (list1.get(i).start < list2.get(j).start) {
				current = list1.get(i);
				i++;
			}
			else {
				current = list2.get(j);
				j++;
			}
			
			last = merge(result, last, current);
		}
		
		while (i < list1.size()) {
			last = merge(result, last, list1.get(i++));
		}
		
		while (j < list2.size()) {
			last = merge(result, last, list2.get(j++));
		}
		
		if (last != null) {
			result.add(last);
		}
		
		return result;
	}
}