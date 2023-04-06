/**
* LintCode 954. Insert Delete GetRandom O(1) - Duplicates allowed
Design a data structure that supports all following operations in average O(1) time.

Duplicate elements are allowed.
    -insert(val): Inserts an item val to the collection.
    -remove(val): Removes an item val from the collection if present.
    -getRandom: Returns a random element from current collection of elements. 
               The probability of each element being returned is linearly related to the number of same value the collection contains.

Example 1:
    Input:
    insert(1)
    insert(1)
    insert(2)
    getRandom()
    remove(1)

    // Init an empty collection.
    RandomizedCollection collection = new RandomizedCollection();

    // Inserts 1 to the collection. Returns true as the collection did not contain 1.
    collection.insert(1);

    // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
    collection.insert(1);

    // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
    collection.insert(2);

    // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
    collection.getRandom();

    // Removes 1 from the collection, returns true. Collection now contains [1,2].
    collection.remove(1);

    // getRandom should return 1 and 2 both equally likely.
    collection.getRandom();
    
**/
//version-1: Linked + HashMap<val, Set-of-val-position/idex-in-linkedlist> to support for all O(1) operaiton
class RandomizedCollection {
    // fields
    private List<Integer> valueList;
    private Map<Integer, Set<Integer>> map;
    private Random random;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        valueList = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        // write your code here
        boolean result = map.containsKey(val);

        valueList.add(val);
        int size = valueList.size();
        int lastPos = size - 1;

        map.putIfAbsent(val, new HashSet<Integer>());

        map.get(val).add(lastPos);

        return result;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        // write your code here
        if (!map.containsKey(val)) {
            return false;
        }

        Set<Integer> set = map.get(val);
        Iterator<Integer> iterator = map.get(val).iterator();
        int currentPos = iterator.next();
        int lastPos = valueList.size() - 1;
        int lastVal = valueList.get(lastPos);

        // swap the curretn value and the last pos value
        valueList.set(currentPos, lastVal);
        valueList.set(lastPos, val);
        valueList.remove(lastPos);

        Set<Integer> lastValPosSet = map.get(lastVal);
        lastValPosSet.add(currentPos);
        lastValPosSet.remove(lastPos);

        set.remove(currentPos);
        if (set.isEmpty()) {
            map.remove(val);
        }

        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        // write your code here
        int size = valueList.size();
        //System.out.println("size = " + size);
        int randomPos = random.nextInt(size);
        return valueList.get(randomPos);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
