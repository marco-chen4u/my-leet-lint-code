/***
* LintCode 657. Insert Delete GetRandom O(1)
Design a data structure that supports all following operations in average O(1) time.
    insert(val): Inserts an item val to the set if not already present.
    remove(val): Removes an item val from the set if present.
    getRandom: Returns a random element from current set of elements. 
                Each element must have the same probability of being returned.
Example
    // Init an empty set.
    RandomizedSet randomSet = new RandomizedSet();

    // Inserts 1 to the set. Returns true as 1 was inserted successfully.
    randomSet.insert(1);

    // Returns false as 2 does not exist in the set.
    randomSet.remove(2);

    // Inserts 2 to the set, returns true. Set now contains [1,2].
    randomSet.insert(2);

    // getRandom should return either 1 or 2 randomly.
    randomSet.getRandom();

    // Removes 1 from the set, returns true. Set now contains [2].
    randomSet.remove(1);

    // 2 was already in the set, so return false.
    randomSet.insert(2);

    // Since 2 is the only number in the set, getRandom always return 2.
    randomSet.getRandom();
    
* lintCode link: https://www.lintcode.com/problem/657
* leetcode link: https://leetcode.com/problems/insert-delete-getrandom-o1/
***/
class RandomizedSet {

    // fields
    private List<Integer> valueList;
    private Map<Integer, Set<Integer>> map;
    private Random random;

    public RandomizedSet() {
        valueList = new LinkedList<>();
        map = new HashMap<>();
        random = new Random();
    }
    
    public boolean insert(int val) {
        boolean found = map.containsKey(val);
        if (found) {
            return false;
        }

        map.putIfAbsent(val, new HashSet<Integer>());
        
        valueList.add(val);
        int lastPos = valueList.size() - 1;
        map.get(val).add(lastPos);

        return true;
    }
    
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }

        Set<Integer> set = map.get(val);
        int currentPos = set.iterator().next();

        if (set.size() == 1) {
            map.remove(val);
        }
        else {
            set.remove(currentPos);
        }

        int lastPos = valueList.size() - 1;
        int lastVal = valueList.get(lastPos);
        Set<Integer> lastValSet = map.get(lastVal);
        if (lastPos != currentPos) {
            valueList.set(currentPos, lastVal);
            valueList.set(lastPos, val);

            lastValSet.remove(lastPos);
            lastValSet.add(currentPos);
        }

        valueList.remove(lastPos);

        return true;
    }
    
    public int getRandom() {
        int size = valueList.size();
        int result = valueList.get(random.nextInt(size));

        return result;
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param = obj.insert(val);
 * boolean param = obj.remove(val);
 * int param = obj.getRandom();
 */
