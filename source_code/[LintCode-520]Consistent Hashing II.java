/***
* 	
In Consistent Hashing I we introduced a relatively simple consistency hashing algorithm. 
This simple version has two defects：
    -After adding a machine, the data comes from one of the machines. 
    The read load of this machine is too large, which will affect the normal service.
    -When adding to 3 machines, the load of each server is not balanced, it is 1:1:2
    In order to solve this problem, the concept of micro-shards was introduced, and a better algorithm is like this：
        1.From 0~359 to a range of 0 ~ n-1, the interval is connected end to end and connected into a circle.
        2.When joining a new machine, randomly choose to sprinkle k points in the circle, representing the k micro-shards of the machine.
        3.Each data also corresponds to a point on the circumference, which is calculated by a hash function.
        4.Which machine belongs to which data is to be managed is determined by 
	the machine to which the first micro-shard point that is clockwise touched on the circle is corresponding to the point 
	on the circumference of the data.
    n and k are typically 2^64 and 1000 in a real NoSQL database.

Implement these methods of introducing consistent hashing of micro-shard.
    1.create(int n, int k)
    2.addMachine(int machine_id) // add a new machine, return a list of shard ids.
    3.getMachineIdByHashCode(int hashcode) // return machine id

Example 1:
    Input:
        create(100, 3)
        addMachine(1)
        getMachineIdByHashCode(4)
        addMachine(2)
        getMachineIdByHashCode(61)
        getMachineIdByHashCode(91)
    Output:
        [77,83,86]
        1
        [15,35,93]
        1
        2

Example 2:
    Input:
        create(10, 5)
        addMachine(1)
        getMachineIdByHashCode(4)
        addMachine(2)
        getMachineIdByHashCode(0)
        getMachineIdByHashCode(1)
        getMachineIdByHashCode(2)
        getMachineIdByHashCode(3)
        getMachineIdByHashCode(4)
        getMachineIdByHashCode(5)

    Output:
        [2,3,5,6,7]
        1
        [0,1,4,8,9]
        2
        2
        1
        1
        2
        1
Notice
When n is 2^64, there is almost no repetition in the interval within this interval.
However, in order to test the correctness of your program, n may be small in the data, 
so you must ensure that the k random numbers you generate will not be duplicated.
LintCode does not judge the correctness of your returnMachine's return (because it is a random number), 
it will only judge the correctness of your getMachineIdByHashCode result based on the result of the addMachine you return.
***/
public class Solution {
    //fields
    int n;// count of segemnt data scope
    int k;// sprinkle k points in the circle（圆周中撒 k 个点的数量)	

    Set<Integer> idRegister;// register all the machine's occupied interval points in the circle
    Map<Integer, List<Integer>> machines; // machine and it's interval collection
    Random random;
	
    /*
     * @param n: a positive integer
     * @param k: a positive integer
     * @return: a Solution object
     */
    public static Solution create(int n, int k) {
        Solution solution = new Solution();
        solution.n = n;// scope
        solution.k = k;// how many points to scatter
        
        solution.idRegister = new TreeSet<Integer>();
        solution.machines = new HashMap<Integer, List<Integer>>();
        solution.random = new Random();
        
        return solution;
    }

    /*
     * @param machine_id: An integer
     * @return: a list of shard ids
     */
    public List<Integer> addMachine(int machine_id) {
        List<Integer> result = new ArrayList<Integer>();//return it's allocated interval values
        if (machines.containsKey(machine_id)) {
            return machines.get(machine_id);
        }
        
        for (int i = 0; i < k; i++) {
            int index = random.nextInt(n);
            while (idRegister.contains(index)) {
                index = random.nextInt(n);
            }
            
            idRegister.add(index);
            result.add(index);
        }
        
        Collections.sort(result);
        machines.put(machine_id, result);
        
        return result;
    }

    /*
     * @param hashcode: An integer
     * @return: A machine id
     */
    public int getMachineIdByHashCode(int hashcode) {
        /***
        * try to find the least value that is greater than hashcode value
        * (that means get its nearest machine id)
        **/
        
        int distance = n + 1;// the maximum value.(to get the minimu distance from hashCode)
        int machineId = 0;
        for (Map.Entry<Integer, List<Integer>> entry : machines.entrySet()) {
            int key = entry.getKey();// machine id
            List<Integer> list = entry.getValue();
            
            for (int value : list) {
                int diff = value - hashcode;
                if (diff < 0) {
                    diff += n;
                }
                
                if (diff < distance) {
                    distance = diff;
                    machineId = key;
                }
            }
            
        }
        
        return machineId;        
    }
}
