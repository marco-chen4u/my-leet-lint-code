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