/***
* LintCode 541. Zigzag Iterator II
Follow up Zigzag Iterator: What if you are given k 1d vectors? 
How well can your code be extended to such cases? The "Zigzag" order is not clearly defined
 and is ambiguous for k > 2 cases. 
 If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic".

Example
	Example1
		Input: k = 3
			vecs = [
				[1,2,3],
				[4,5,6,7],
				[8,9],
			]
		Output: [1,4,8,2,5,9,3,6,7]

	Example2
		Input: k = 3
			vecs = [
				[1,1,1]
				[2,2,2]
				[3,3,3]
			]
		Output: [1,2,3,1,2,3,1,2,3]
***/
public class ZigzagIterator2 {
	// fields
	private List<Iterator<Integer>> its;
	private int turns;
	
	// methods
    /*
    * @param vecs: a list of 1d vectors
    */public ZigzagIterator2(List<List<Integer>> vecs) {
        // do intialization if necessary
		its = new ArrayList<Iterator<Integer>>();
		for (List<Integer> list : vecs) {
			if (!list.isEmpty()) {
				its.add(list.iterator());
			}
		}
		
		turns = 0;
    }

    /*
     * @return: An integer
     */
    public int next() {
        int value = (Integer)(its.get(turns).next());
		
		if (its.get(turns).hasNext()) {
			turns = (turns + 1) % its.size();
		}
		else {
			its.remove(turns);
			
			if (!its.isEmpty()) {
				turns = turns % its.size();
			}
		}
		
		return value;
    }

    /*
     * @return: True if has next
     */
    public boolean hasNext() {
        return (!its.isEmpty());
    }
}

/**
 * Your ZigzagIterator2 object will be instantiated and called as such:
 * ZigzagIterator2 solution = new ZigzagIterator2(vecs);
 * while (solution.hasNext()) result.add(solution.next());
 * Output result
 */