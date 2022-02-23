/***
* LintCode.1858  Set of boxes
We have a lot of boxes to transport. To save space, we put the boxes together. 
Give you the length and width of each box. What is the maximum number of layers?

note:
    -The input may not be length in front, wide in back.You can see Example 2.
    -You can rotate the box, but make sure that the four sides are parallel to the four sides of the original box, that is, do not place it diagonally.
    -This number of boxes is between [1,50000].
    -The thickness of the box is not negligible, so it must be strictly smaller.
    
Example 1:
    Input: 
        boxes = [[5,4],[6,4],[6,7],[2,3]]
    Output: 
        3
    Explanation:
        The maximum number of boxes is 3 ([2,3] => [5,4] => [6,7]).

Example 2:
    Input: 
        boxes = [[1,5],[6,2]]
    Output: 
        2
    Explanation:
        The maximum number of boxes is 2 ([1,5] => [2,6]).
***/
//version-1:
public class Solution {

    // consants
    private static final int MAX_VALUE = Integer.MAX_VALUE;

    private Comparator<int[]> comparator = new Comparator<int[]>() {
        @Override
        public int compare(int[] a, int[] b) {
            if (a[0] != b[0]) {
                return a[0] - b[0];// ascending order
            }

            return b[1] - a[1];//descending order to accomendate the nesting boxes
        }
    };

    /**
     * @param boxes: An array
     * @return: the number of boxes
     */
    public int maxBoxes(int[][] boxes) {

        if (boxes == null || boxes.length == 0 || 
            boxes[0] == null || boxes[0].length == 0) {
            return 0;
        }

        //rotate it when possible
        for(int[] box : boxes){
            if(box[0] < box[1]){
                int tmp = box[1];
                box[1] = box[0];
                box[0] = tmp;
            }
        }
        
        int n = boxes.length;

        Arrays.sort(boxes, comparator);

        int[] minLast = new int[n];
        Arrays.fill(minLast, MAX_VALUE);
        
        for (int[] box : boxes) {
            
            int wideth = box[0];
            int height = box[1];

            int index = binarySearchFirstGreaterIndex(minLast, height);
            minLast[index] = height;
        }

        int result = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (minLast[i] == MAX_VALUE) {
                continue;
            }

            result = i + 1;
            break;
        }

        return result;
    }

    // helper method
    private int binarySearchFirstGreaterIndex(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }

        if (nums[start] >= target) {
            return start;
        }

        return end;
    }
}

//version-2
public class Solution {

    // consants
    private static final int MAX_VALUE = Integer.MAX_VALUE;

    private Comparator<int[]> comparator = new Comparator<int[]>() {
        @Override
        public int compare(int[] a, int[] b) {
            if (a[0] != b[0]) {
                return a[0] - b[0];// ascending order
            }

            return b[1] - a[1];//descending order to accomendate the nesting boxes
        }
    };

    /**
     * @param boxes: An array
     * @return: the number of boxes
     */
    public int maxBoxes(int[][] boxes) {

        if (boxes == null || boxes.length == 0 || 
            boxes[0] == null || boxes[0].length == 0) {
            return 0;
        }

        for(int[] box : boxes){
            if(box[0] < box[1]){
                int tmp = box[1];
                box[1] = box[0];
                box[0] = tmp;
            }
        }
        
        int n = boxes.length;

        Arrays.sort(boxes, comparator);

        int[] heights = new int[n];
        int index = 0;
        for (int[] box : boxes) {
            heights[index++] = box[1];
        }
        
        return getLIS(heights);
    }

    // helper method
    private int getLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] increasingSubsequence = new int[n];
        
        int index = 0;
        increasingSubsequence[index++] = nums[0];
        for (int i = 0; i < n; i++) {
            if (nums[i] > increasingSubsequence[index -1]) {
                increasingSubsequence[index++] = nums[i];
            }
            else {
                int position = findPositionToReplace(increasingSubsequence, 0, index - 1, nums[i]);
                increasingSubsequence[position] = nums[i];
            }
        }

        return index;
    }

    private int findPositionToReplace(int[] nums, int start, int end, int target) {
       
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }

        if (nums[start] >= target) {
            return start;
        }

        return end;
    }
}
