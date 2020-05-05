/***
* LintCode 526. Load Balancer
Implement a load balancer for web servers. It provide the following functionality:
	-Add a new server to the cluster => add(server_id).
	-Remove a bad server from the cluster => remove(server_id).
	-Pick a server in the cluster randomly with equal probability => pick().
At beginning, the cluster is empty. When pick() is called you need to randomly return a server_id in the cluster.

Example
	Example 1:
		Input:
			add(1)
			add(2)
			add(3)
			pick()
			pick()
			pick()
			pick()
			remove(1)
			pick()
			pick()
			pick()
		Output:
			1
			2
			1
			3
			2
			3
			3
		Explanation: The return value of pick() is random, it can be either 2 3 3 1 3 2 2 or other.
***/
/*
* 要在o(1)的时间内插入删除，只能hash。那hash可以getRandom吗？ 不太好做
* 什么数据结构比较好getRandom？ 数组/List
* 考虑hash与数组结合起来用，hash插入一个，数组也插入一个。那么问题 来了，数组删除元素怎么办？ 与最后插入的一个元素交换
* 那怎么o(1)时间在数组中找到要删除元素（要交换）的位置？ 用hash将元素的位置记下来

* 算法： 
	• 插入： – 数组末尾加入这个元素 – Hash这个元素存下数组中的下标
	• 删除： – 通过hash找到这个元素在数组中的位置 – 数数组中这个元素和数组的末尾元素交换，交换后删除 – Hash中删除这个元素，更新数组原末尾元素现在在数组中的位置
	• Pick： – 数组中random一个返回
* 考点： • 两种数据结构的综合应用
*/
public class LoadBalancer {
	// fields
	int size;
	List<Integer> cluster;
	Map<Integer, Integer> idToPosMap;
	Random random;
	
	// constructor
    public LoadBalancer() {
        // do intialization if necessary
		this.size = 0;
		this.cluster = new ArrayList<Integer>();
		this.idToPosMap = new HashMap<Integer, Integer>();
		this.random = new Random();
    }

	// helper method
	private void swap(int i, int j) {
		int server_idI = cluster.get(i);
		int server_idJ = cluster.get(j);
		cluster.set(i, server_idJ);
		cluster.set(j, server_idI);
		
		idToPosMap.put(server_idI, j);
		idToPosMap.put(server_idJ, i);		
	}

    /*
     * @param server_id: add a new server to the cluster
     * @return: nothing
     */
    public void add(int server_id) {
        if (idToPosMap.containsKey(server_id)) {
			return;
		}
		
		int pos = size;
		cluster.add(server_id);
		idToPosMap.put(server_id, pos);
		
		size++;
    }

    /*
     * @param server_id: server_id remove a bad server from the cluster
     * @return: nothing
     */
    public void remove(int server_id) {
        if (!idToPosMap.containsKey(server_id)) {
			return;
		}
		
		int currentPos = idToPosMap.get(server_id);
		int lastPos = cluster.size()  - 1;
		swap(currentPos, lastPos);
		
		idToPosMap.remove(server_id);
		cluster.remove(lastPos);
		
		this.size--;
    }

    /*
     * @return: pick a server in the cluster randomly with equal probability
     */
    public int pick() {
        int index = random.nextInt(size);
		
		return cluster.get(index);
    }
}