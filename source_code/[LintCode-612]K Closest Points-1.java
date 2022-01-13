/***
* LintCode 612. K Closest Points
Given some points and an origin point in two-dimensional space, 
find k points which are nearest to the origin.
Return these points sorted by distance, if they are same in distance, 
sorted by the x-axis, and if they are same in the x-axis, sorted by y-axis.


Example 1:
    Input: points = [[4,6],[4,7],[4,4],[2,5],[1,1]], origin = [0, 0], k = 3 
    Output: [[1,1],[2,5],[4,4]]
		
Example 2:
    Input: points = [[0,0],[0,9]], origin = [3, 1], k = 1
    Output: [[0,0]]
***/
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

public class Solution {

    // fields
    private Point ORIGINAL_POINT = null;
    
    /**
     * @param points: a list of points
     * @param origin: a point
     * @param k: An integer
     * @return: the k closest points
     */
    public Point[] kClosest(Point[] points, Point origin, int k) {

	Point[] default_value = new Point[0]; //default value

        // check corner cases
        if (points == null || points.length == 0) {
            return points;
        }
        
        if (k < 1) {
            return default_value;
        }
        
        ORIGINAL_POINT = origin;
        Comparator<Point> comparator = new Comparator<Point>() {
            
            @Override
            public int compare(Point a, Point b) {
                int aDistance = getDistance(a, ORIGINAL_POINT);
                int bDistance = getDistance(b, ORIGINAL_POINT);
                
                int diff = aDistance - bDistance;
                if (diff == 0) {
                    diff = a.x - b.x;
                }
                if (diff == 0) {
                    diff = a.y - b.y;
                }
                
                return diff;
            }
        };
        
        Arrays.sort(points, comparator);
        
        int size = (k >= points.length) ? points.length : k;
        Point[] result = new Point[size];
        for (int i = 0; i < k; i++) {
            result[i] = points[i]; 
        }
        
        return result;
    }
	
    // helper method
    private int getDistance(Point a, Point b) {
        int result = 0;

        int xDistance = Math.abs(a.x - b.x);
        int yDistiance = Math.abs(a.y - b.y);

        result = xDistance*xDistance + yDistiance*yDistiance;

        return result;
    }
}
