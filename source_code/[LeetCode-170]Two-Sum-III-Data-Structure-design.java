/***
* LeetCode 170. Two Sum III - Data Structure Design
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

Example 1:
    add(1); add(3); add(5);
    find(4) -> true
    find(7) -> false

Example 2:
    add(3); add(1); add(2);
    find(3) -> true
    find(6) -> false

***/
//version-1:O(n), Map+TreeSet
class TwoSum {
    // field
    private Map<Integer, Integer> map;
    private Set<Integer> set;

    /** Initialize your data structure here. */
    public TwoSum() {
        map = new HashMap<>();
        set = new TreeSet<>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
        set.add(number);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (int num1 : set) {
            int num2 = value - num1;
            if (num2 == num1) {
                if (map.get(num2) > 1) {
                    return true;
                }
            }
            else {
                if (map.containsKey(num2)) {
                    return true;
                }
            }
        }
        
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */

//version-2: O(nlogn), Map + List + TwoPoints
class TwoSum {
    // field
    private Map<Integer, Integer> map;
    private List<Integer> numList;
    
    private int[] toArray(List<Integer> numList) {
        int[] result = new int[numList.size()];
        int index = 0;
        for (int value : numList) {
            result[index++] = value;
        }
        
        Arrays.sort(result);
        
        return result;
    }

    /** Initialize your data structure here. */
    public TwoSum() {
        map = new HashMap<>();
        numList = new ArrayList<>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
        numList.add(number);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        int[] nums = toArray(numList);
        int i = 0;
        int j = nums.length - 1;
        
        while (i < j) {
            int twoSum = nums[i] + nums[j];
            
            // check corner cases
            if (nums[i] * 2 == value && map.get(nums[i]) > 1) {
                return true;
            } 
            
            if (nums[j] * 2 == value && map.get(nums[j]) > 1) {
                return true;
            }
            
            // regualr case
            if (twoSum < value) {
                i++;
            }
            else if (twoSum > value) {
                j--;
            }
            else {// twoSum == value
                return true;
            }
        }
        
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
