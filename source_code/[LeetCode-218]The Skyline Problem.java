/***
* LeetCode 218. The Skyline Prolbem
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. 
Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.

The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:

lefti is the x coordinate of the left edge of the ith building.
righti is the x coordinate of the right edge of the ith building.
heighti is the height of the ith building.
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. 
Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, 
which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. 
Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.

Note: There must be no consecutive horizontal lines of equal height in the output skyline. 
For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; 
the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]

Example 1:
    Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
    Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
    Explanation:
        Figure A shows the buildings of the input.
    Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.

Example 2:
    Input: buildings = [[0,2,3],[2,5,3]]
    Output: [[0,3],[5,0]]

Link: https://leetcode.com/problems/the-skyline-problem/
***/

//version-1: sweep-line + maxHeap
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // check corner cases
        if (buildings == null || buildings.length == 0 ||
            buildings[0] == null || buildings[0].length == 0) {
            return result;
        }
        
        int size = buildings.length;
        Element[] elements = new Element[size * 2];
        int index = 0;
        for (int[] building : buildings) {
            int start = building[0], end = building[1]; // position
            int height = building[2];
            int up = 1, down = 0;// state
            elements[index++] = new Element(start, height, up);
            elements[index++] = new Element(end, height, down);
        }
        
        Comparator<Element> comparator = new Comparator<Element>() {
            @Override
            public int compare(Element a, Element b) {
                if (a.position != b.position) {
                    return a.position - b.position;
                }
                
                if (a.state == b.state) {
                    return (a.state == 1) ? (b.height - a.height) : (a.height - b.height);
                }
                
                return (a.state == 1) ? -1 : 1;
            }
        };
        
        Arrays.sort(elements, comparator);
        
        Queue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        
        // sweep line + heap
        List<OutLinePoint> points = new ArrayList<OutLinePoint>();
        for (Element element : elements) {
            OutLinePoint outLinePoint = null;// initialize
            
            if (element.state == 1) {// start position, state = 1
                if (maxHeap.isEmpty() || element.height > maxHeap.peek()) {
                    outLinePoint = new OutLinePoint(element.position, element.height);
                    points.add(outLinePoint);
                }
                
                maxHeap.offer(element.height);
            }
            else {// end position, state = 0
                maxHeap.remove(element.height);
                
                if (maxHeap.isEmpty() || element.height > maxHeap.peek()) {
                    int height = maxHeap.isEmpty() ? 0 : maxHeap.peek();
                    
                    outLinePoint = new OutLinePoint(element.position, height);                    
                    points.add(outLinePoint);
                }
            }
        }
        
        // check corner case
        if (points.isEmpty()) {
            return result;
        }
        
        for (OutLinePoint outLinePoint : points) {
            List<Integer> item = new ArrayList<Integer>();
            
            item.add(outLinePoint.position);
            item.add(outLinePoint.height);
            
            result.add(item);
        }  
        
        return result;
    }
}


// helper class
class OutLinePoint {
    // fields
    int position;
    int height;
    // constructor
    public OutLinePoint(int position, int height) {
        this.position = position;
        this.height = height;
    }
}

class Element {
    // fields
    int position;
    int height;
    int state;// 1-up;0-down
    // constructor
    public Element(int position, int height, int state) {
        this.position = position;
        this.height = height;
        this.state = state;
    }
}
