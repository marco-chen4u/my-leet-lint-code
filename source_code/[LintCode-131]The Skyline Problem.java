/***
* LintCide 131. The Skyline Problem
Given N buildings in a x-axis，
each building is a rectangle and can be represented by a triple (start, end, height)，
where start is the start position on x-axis, end is the end position on x-axis and height is the height of the building. 

Buildings may overlap if you see them from far away，find the outline of them.
An outline can be represented by a triple, (start, end, height), 
where start is the start position on x-axis of the outline, 
end is the end position on x-axis, 
and height is the height of the outline.

Building Outline

Example 1
    Input:
        [
            [1, 3, 3],
            [2, 4, 4],
            [5, 6, 1]
        ]
    Output:
        [
            [1, 2, 3],
            [2, 4, 4],
            [5, 6, 1]
        ]
    Explanation:
        The buildings are look like this in the picture. The yellow part is buildings.

Example 2
    Input:
        [
            [1, 4, 3],
            [6, 9, 5]
        ]
    Output:
        [
            [1, 4, 3],
            [6, 9, 5]
        ]
Explanation:
    The buildings are look like this in the picture. The yellow part is buildings.

Notice
    Please merge the adjacent outlines if they have the same height and make sure different outlines cant overlap on x-axis.
***/
/***
* test case-1 : [[1,2,3],[1,2,4],[1,2,3],[1,2,4]]
* test case-2 : [[1,3,3],[2,4,4],[5,6,1]]

***/
// helper class
// version-1 Comparator functor
class OutLinePoint {
    // fields
    int position;
    int height;
    //constructor
    public OutLinePoint(int position, int height) {
        this.position = position;
        this.height = height;
    }
}

class Element {
    // fields
    int position;
    int height;
    int state;// 1 = start, 0 = end
    // constructor
    public Element(int position, int height, int state) {
        this.position = position;
        this.height = height;
        this.state = state;
    }
}

public class Solution {	
    // fields
    Comparator<Element> comparator = new Comparator<Element>() {
        @Override
        public int compare(Element a, Element b) {
            if (a.position != b.position) {
                return a.position - b.position;
            }

            if (a.state == b.state) {
                return (a.state == 1) ? (b.height - a.height) : (a.height - b.height);
            }

            return (a.state == 1) ? - 1 : 1;
        }
    };
	
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    public List<List<Integer>> buildingOutline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // check corner case
        if (buildings == null || buildings.length == 0 || 
            buildings[0] == null || buildings[0].length == 0) {
            return result;
        }

        int size = buildings.length;
        Element[] elements = new Element[size * 2];
        int index = 0;
        for (int[] building: buildings) {
            int start = building[0], end = building[1], height = building[2];
            elements[index++] = new Element(start, height, 1);
            elements[index++] = new Element(end, height, 0);
        }

        Arrays.sort(elements, comparator); // sort

        Queue<Integer> maxHeap = new PriorityQueue(Collections.reverseOrder());

        List<OutLinePoint> outLines = new ArrayList<OutLinePoint>();

        for (Element current : elements) {
            if (current.state == 1) {// start point
                if (maxHeap.isEmpty() ||current.height > maxHeap.peek()) {	
                    outLines.add(new OutLinePoint(current.position, current.height));
                }

                maxHeap.offer(current.height);
            }
            else {                   // end point
                maxHeap.remove(current.height);				

                if (maxHeap.isEmpty() || current.height > maxHeap.peek()) {
                    int height = maxHeap.isEmpty() ? 0 : maxHeap.peek();
                    outLines.add(new OutLinePoint(current.position, height));
                }	
            }
        }

        if (outLines.isEmpty()) {
            return result;
        }

        size = outLines.size();
        int start = outLines.get(0).position, end = 0;
        int height = outLines.get(0).height;
        for (int i = 1; i < size; i++) {			
            OutLinePoint current = outLines.get(i);

            end = current.position;

            if (height > 0) {
                List<Integer> triplet = new ArrayList<Integer>();

                triplet.add(start);
                triplet.add(end);
                triplet.add(height);

                result.add(triplet);
            }

            start = current.position;
            height = current.height;			
        }		

        return result;
    }
}

// version-2 comparable class
public class Solution {
    private final int UP = 1;
    private final int DOWN = 0;
    
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    public List<List<Integer>> buildingOutline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // check corner case
        if (buildings == null || buildings.length == 0 || 
            buildings[0] == null || buildings[0].length == 0) {
            return result;
        }
        int size = buildings.length;
        Element[] elements = new Element[size * 2];
        int index = 0;
        for (int[] building : buildings) {
            int start = building[0], end = building[1], height = building[2];
            elements[index++] = new Element(start, height, UP);
            elements[index++] = new Element(end, height, DOWN);
        }
        
        Arrays.sort(elements);
        
        Queue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        List<OutLinePoint> outLines = new ArrayList<OutLinePoint>();
        
        for (Element current : elements) {
            if (current.state == UP) {
                if (maxHeap.isEmpty() || current.height > maxHeap.peek()) {
                    outLines.add(new OutLinePoint(current.position, 
                                                    current.height));
                }
                
                maxHeap.offer(current.height);
            }
            else {
                maxHeap.remove(current.height);
                
                if (maxHeap.isEmpty() || current.height > maxHeap.peek()) {
                    int height = maxHeap.isEmpty() ? 0 : maxHeap.peek();
                    outLines.add(new OutLinePoint(current.position, height));
                }
            }
        }
        
        if (outLines.isEmpty()) {
            return result;
        }
        
        size = outLines.size();
        int start = outLines.get(0).position, end = 0;
        int height = outLines.get(0).height;
        for (int i = 1; i < size; i++) {
            OutLinePoint current = outLines.get(i);
            
            end = current.position;
            
            if (height > 0) {
                List<Integer> triplet = new ArrayList<Integer>();
                
                triplet.add(start);
                triplet.add(end);
                triplet.add(height);
                
                result.add(triplet);
            }
            
            start = current.position;
            height = current.height;
        }
        
        return result;
    }
}

// helper class
class OutLinePoint {
    // fields
    int position;
    int height;
    //constructor
    public OutLinePoint(int position, int height) {
        this.position = position;
        this.height = height;
    }
}

class Element implements Comparable<Element> {
    // fields
    int position;
    int height;
    int state;// 1 = start, 0 = end
    // constructor
    public Element(int position, int height, int state) {
        this.position = position;
        this.height = height;
        this.state = state;
    }

    @Override
    public int compareTo(Element other) {
        if (this.position != other.position) {
            return this.position - other.position;
        }
        
        if (this.state == other.state) {
            return (this.state == 1) ? other.height - this.height : this.height - other.height;
        }
        
        return (this.state == 1) ? -1 : 1;
    }
}
