/***
* LintCode 391. Number of Airplanes in the Sky
Given an list interval, which are taking off and landing time of the flight. How many airplanes are there at most at the same time in the sky?

Example
	Example 1:
		Input: [(1, 10), (2, 3), (5, 8), (4, 7)]
		Output: 3
		Explanation:
			The first airplane takes off at 1 and lands at 10.
			The second ariplane takes off at 2 and lands at 3.
			The third ariplane takes off at 5 and lands at 8.
			The forth ariplane takes off at 4 and lands at 7.
			During 5 to 6, there are three airplanes in the sky.
	Example 2:
		Input: [(1, 2), (2, 3), (3, 4)]
		Output: 1
		Explanation: Landing happen before taking off.
Notice
	If landing and taking off of different planes happen at the same time, we consider landing should happen at first.
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

class Element {
	// fields
	int time;
	int state; // 1 = taking off, 0 = landed down
	
	// constructor
	public Element(int time, int state) {
		this.time = time;
		this.state = state;
	}
}

public class Solution {
 
    
    /**
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        // check corner case
        if (airplanes == null || airplanes.isEmpty()) {
            return 0;
        }
        
        int size = airplanes.size();
        Element[] elements = new Element[size * 2];
        int index = 0;
        for (Interval interval : airplanes) {
            elements[index++] = new Element(interval.start, 1);
            elements[index++] = new Element(interval.end, 0);
        }
        
        Comparator<Element> comparator = new Comparator<Element>() {
            @Override
            public int compare(Element a, Element b) {
                if (a.time == b.time) {
                    return a.state - b.state;
                }
                
                return a.time - b.time;
            }
        };
        
        Arrays.sort(elements, comparator);
        
        int result = Integer.MIN_VALUE;
        int count = 0;
        for (Element element : elements) {
            if (element.state == 1) {
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