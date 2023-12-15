/***
* LintCode 622. Frog Jump
A frog is crossing the river, the river is divided into units, 
and each unit may exist or not exist a stone. 
The frog can jump on the stone, but it must not jump into the water.

Given a list of stones' position(in units) in sorted ascending order, 
determind if the frog is able to cross the river by landing the last stone. 

Initially, the frog is on the first position and the first jump must be 1 unit.
If the frog's last jump was k unit, 
then its next jump must be either k -1, k, or k + 1 units. 

Note that frog can only jump in forward direction.

Example 1
    Given stones = [0,1,3,5,6,8,12,17]
    Input:
        [0,1,3,5,6,8,12,17]
    Output:
        true
    Explanation:
        There are a total of 8 stones.
        The first stone at the 0th unit, 
	second stone at the 1st unit,
        third stone at the 3rd unit, and so on...
	The last stone at the 17th unit.
        Return true. 
	The frog can jump to the last stone by 
        jumping 1 unit to the 2nd stone, 
	then 2 units to the 3rd stone, 
	then 2 units to the 4th stone, 
        then 3 units to the 6th stone, 
	4 units to the 7th stone, 
        and 5 units to the 8th stone.

Example 2
    Given stones = `[0,1,2,3,4,8,9,11]`
    Input:
        [0,1,2,3,4,8,9,11]
    Output:
        false
    Explanation:
        Return false. 
	There is no way to jump to the last stone as the gap between the 5th and 6th stone is too large.

Notice
    -The number of stones is ≥ 2 and is < 1100.
    -Each stone's position will be a non-negative integer < 2^31.
    -The first stone's position is always 0.

Link
    LintCode: https://www.lintcode.com/problem/622/
    LeetCode: https://leetcode.com/problems/frog-jump/
***/
// version-1: memorization search(DFS)
public class Solution {
    // field
    private boolean result = false;

    /**
     * @param stones: a list of stones' positions in sorted ascending order
     * @return: true if the frog is able to cross the river or false
     */
    public boolean canCross(int[] stones) {
        // check corner cases
        if (stones == null || stones.length == 0) {
            return result;// false
        }

        for (int i = 0; i < stones.length; i++) {
            if (i > 2 &&
                stones[i] > 2 * stones[i - 1]) {// if gap ,between 2 postions, is too far to jump 
                return result;// false, because it's unable to jump the bigger gap
            }
        }

        // normal case
        dfs(stones, 0, 1, 1);

        return result;
    }

    // helper method
    private void dfs(int[] stones, // units of postion in wartor to jump
                        int currentIndex,    // the index of postions, where the frog start to jump  
                        int start,    // the smallest jump step size ( k -1)
                        int end) {    // the biggest jump step size (k + 1)
        // check corner cases
        if (currentIndex == stones.length - 1) {
            result = true;
            return;
        }

        if (result) {
            return;
        }

        // normal case
        // next step to jump
        int currentPos = stones[currentIndex];// current position where the frog to jump
        for (int i = currentIndex + 1; i < stones.length; i++) {			
            int jumpStepSize = stones[i] - currentPos;

            if (jumpStepSize < start) {
                continue;
            }

            if (jumpStepSize > end) {
                break;
            }

            dfs(stones, i, jumpStepSize - 1, jumpStepSize + 1);
        }// for
    }
}

//version-2: DFS(/w HashSet to record each position last jump steps to reach)
class Solution {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) {
            return false;
        }

        int length = stones.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();// map<position, set[different, jump-setp]>

        for (int i = 0; i < length; i++) {
            int currentPos = stones[i];
            map.put(currentPos, new HashSet<Integer>());
        }

        map.get(stones[0]).add(0);

        for (int i = 0; i < length; i++) {
            int currentPos = stones[i];
            
            Set<Integer> lastSteps = map.containsKey(currentPos) ? map.get(currentPos) : null;
            if (lastSteps == null || lastSteps.isEmpty()) {
                continue;
            }

            for (int stepSize : lastSteps) {
                for (int nextStep = stepSize - 1; nextStep <= stepSize + 1; nextStep++) {
                    if (nextStep <= 0) {//no point of this jump step
                        continue;// skip it
                    }

                    // is it possible to jump into the water
                    int nextPos = currentPos + nextStep;
                    if (!map.containsKey(nextPos)) {
                        continue; // skip it
                    }

                    map.get(nextPos).add(nextStep);// keep current jump unit
                }// end nextStep
            }// end stepSize
        }// end ith jump location

        int lastPos = stones[length - 1];
        if (map.get(lastPos).isEmpty()) { // check if the last location is able to get reach
            return false;
        }

        return true;
    }
}
