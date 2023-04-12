/***
* LeetCode 1229. Meeting Scheduler

Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, 
return the earliest time slot that works for both of them and is of duration duration.

If there is no common time slot that satisfies the requirements, return an empty array.

The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.

It is guaranteed that no two availability slots of the same person intersect with each other. 
That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.

Example 1
    Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
    Output: [60,68]
    
Example 2
    Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
    Output: []
    
Constraints
    1 <= slots1.length, slots2.length <= 104
    slots1[i].length, slots2[i].length == 2
    slots1[i][0] < slots1[i][1]
    slots2[i][0] < slots2[i][1]
    0 <= slots1[i][j], slots2[i][j] <= 109
    1 <= duration <= 106

LeetCode link: https://leetcode.com/problems/meeting-scheduler/
***/
//version-1: sorting with comparator + two pointers
class Solution {
    private Comparator<int[]> comparator = new Comparator<int[]>(){
        @Override
        public int compare(int[] a, int[] b) {
            return a[0] - b[0];
        }
    };

    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        // check corner case
        if (slots1 == null || slots1.length == 0 || slots2 == null || slots2.length == 0) {
            return Collections.EMPTY_LIST;
        }

        // normal case
        Arrays.sort(slots1, comparator);
        Arrays.sort(slots2, comparator);

        int size1 = slots1.length;
        int size2 = slots2.length;

        // two points
        int i = 0; 
        int j = 0;
        while (i < size1 && j < size2) {
            int[] slot1 = slots1[i];
            int[] slot2 = slots2[j];
            int start = Math.max(slot1[0], slot2[0]);
            int end = Math.min(slot1[1], slot2[1]);
            //System.out.println("start = " + start + ", end = " + end);
            if (end - start >= duration) {                
                return Arrays.asList(start, start + duration);
            }

            if (slot1[1] <= slot2[1]) {
                i++;
            }else {
                j++;
            }
        }

        return Collections.EMPTY_LIST;
    }
}
