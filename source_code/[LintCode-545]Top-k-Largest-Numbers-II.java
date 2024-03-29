/***
* LintCode 545. Top k Largest Numbers II
Implement a data structure, provide two interfaces:
    -1.add(number). Add a new number in the data structure.
    -2.topk(). Return the top k largest numbers in this data structure. 
      k is given when we create the data structure.
      
Example1
    Input: 
        s = new Solution(3);
        s.add(3)
        s.add(10)
        s.topk()
        s.add(1000)
        s.add(-99)
        s.topk()
        s.add(4)
        s.topk()
        s.add(100)
        s.topk()		
    Output: 
        [10, 3]
        [1000, 10, 3]
        [1000, 10, 4]
        [1000, 100, 10]
    Explanation:
        s = new Solution(3);
        >> create a new data structure, and k = 3.
        s.add(3)
        s.add(10)
        s.topk()
        >> return [10, 3]
        s.add(1000)
        s.add(-99)
        s.topk()
        >> return [1000, 10, 3]
        s.add(4)
        s.topk()
        >> return [1000, 10, 4]
        s.add(100)
        s.topk()
        >> return [1000, 100, 10]

Example2
    Input: 
        s = new Solution(1);
        s.add(3)
        s.add(10)
        s.topk()
        s.topk()
    Output: 
        [10]
        [10]
    Explanation:
        s = new Solution(1);
        >> create a new data structure, and k = 1.
        s.add(3)
        s.add(10)
        s.topk()
        >> return [10]
        s.topk()
        >> return [10]
***/
//version-1: minHeap(PriorityQueue, with default (natural order) comparator)
public class Solution {
    
    private Queue<Integer> minHeap;
    private int MAX_SIZE = Integer.MIN_VALUE;
    
    /*
    * @param k: An integer
    */public Solution(int k) {
        // do intialization if necessary
        minHeap = new PriorityQueue(k);
        MAX_SIZE = k;
    }

    /*
     * @param num: Number to be added
     * @return: nothing
     */
    public void add(int num) {
        minHeap.offer(num);
        if (minHeap.size() > MAX_SIZE) {
            minHeap.poll();
        }
    }

    /*
     * @return: Top k element
     */
    public List<Integer> topk() {
        List<Integer> result = new ArrayList<Integer>();
        
        for (int num : minHeap) {
            result.add(num);
        }
        
        Collections.sort(result, Collections.reverseOrder());
        
        return result;
    }
}
