/***
* LintCode 796. Open the Lock
You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

The lock initially starts at '0000', a string representing the state of the 4 wheels.

You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

Example
	Example 1:
		Given deadends = ["0201","0101","0102","1212","2002"], target = "0202"
		Return 6
		Explanation:
			A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
			Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
			because the wheels of the lock become stuck after the display becomes the dead end "0102".

	Example 2:
		Given deadends = ["8888"], target = "0009"
		Return 1
		Explanation:
			We can turn the last wheel in reverse to move from "0000" -> "0009".

Notice
	1.The length of deadends will be in the range [1, 500].
	2.target will not be in the list deadends.
	3.Every string in deadends and the string target will be a string of 4 digits from the 10,000 possibilities '0000' to '9999'.
***/
public class Solution {
    private final int DEFAULT_VALUE = -1;
    private final int[] DIRECTIONS = new int[] {-1, 1};
    private int n; // row size;
    private int m; // column size;
    /**
     * @param deadends: the list of deadends
     * @param target: the value of the wheels that will unlock the lock
     * @return: the minimum total number of turns 
     */
    public int openLock(String[] deadends, String target) {
        int result = 0;
        String start = "0000";
        Set<String> deadEndSet = new HashSet<String>(Arrays.asList(deadends));
        
        // check corner cases
        if (isEmptyStr(target) || deadEndSet.contains(target) || deadEndSet.contains(start)) {
            return DEFAULT_VALUE;
        }
        
        // normal case
        Queue<String> queue = new LinkedList<String>();
        Set<String> visited = new HashSet<String>();
        
        // initialize
        queue.offer(start);
        visited.add(start);
        
        // start Breadth First Searching
        while (!queue.isEmpty()) {
            result ++;
            
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                
                for (String next : getNext(current)) {
                    if (visited.contains(next) || deadEndSet.contains(next)) {
                        continue;
                    }
                    
                    //System.out.println("next = " + next);
                    if (target.equals(next)) {
                        return result;
                    }
                    
                    queue.offer(next);
                    visited.add(next);
                }
            }
            
        }
        
        return DEFAULT_VALUE;
    }
    
    // helper methods
    private boolean isEmptyStr(String str) {
        return str == null || str.length() == 0;
    }
    
    /*get 8 next states from the current state*/
    private List<String> getNext(String current) {
        List<String> result = new ArrayList<String>();
        
        char[] charArray = current.toCharArray();
        int size = charArray.length;
        
        for (int i = 0; i < size; i++) {
            char[] newCharArray = Arrays.copyOf(charArray, size);
            int currentValue = newCharArray[i] - '0';
            
            // change it up by 1
            // change it down by 1
            for (int index = 0 ; index < DIRECTIONS.length; index++) {
                int newValue = currentValue + DIRECTIONS[index];
                newValue = (newValue + 10) % 10;
                
                newCharArray[i] = (char)(newValue + '0');
                String next = String.valueOf(newCharArray);
                //System.out.println("current = " + current + ", next = " + next);
                result.add(next);
            }
        }
        
        return result;
    }
}