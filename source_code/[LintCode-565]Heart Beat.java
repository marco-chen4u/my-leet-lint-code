/***
* LintCode 565. Heart Beat
In the Master-Slave architecture, slave server will ping master in every k seconds to tell master server he is alive. If a master server didn't receive any ping request from a slave server in 2 * k seconds, the master will trigger an alarm (for example send an email) to administrator.

Let's mock the master server, you need to implement the following three methods:
	1.initialize(slaves_ip_list, k). 
		salves_ip_list is a list of slaves' ip addresses. k is define above.
	2.ping(timestamp, slave_ip). 
		This method will be called every time master received a ping request from one of the slave server. 
		timestamp is the current timestamp in seconds. 
		slave_ip is the ip address of the slave server who pinged master.
	3.getDiedSlaves(timestamp). 
		This method will be called periodically (it's not guaranteed how long between two calls). 
		timestamp is the current timestamp in seconds, and you need to return a list of slaves' ip addresses that died. 
		Return an empty list if no died slaves found.

You can assume that when the master started, the timestamp is 0, and every method will be called with an global increasing timestamp.
Example
	Input: 
		initialize(["10.173.0.2", "10.173.0.3"], 10)
		ping(1, "10.173.0.2")
		getDiedSlaves(20)
		getDiedSlaves(21)
		ping(22, "10.173.0.2")
		ping(23, "10.173.0.3")
		getDiedSlaves(24)
		getDiedSlaves(42)
	Output: 
		["10.173.0.3"]
		["10.173.0.2", "10.173.0.3"]
		[]
		["10.173.0.2"]
	Explanation:
		initialize(["10.173.0.2", "10.173.0.3"], 10)
		ping(1, "10.173.0.2")
		getDiedSlaves(20)
		>> ["10.173.0.3"]
		getDiedSlaves(21)
		>> ["10.173.0.2", "10.173.0.3"]
		ping(22, "10.173.0.2")
		ping(23, "10.173.0.3")
		getDiedSlaves(24)
		>> []
		getDiedSlaves(42)
		>> ["10.173.0.2"]
***/
public class HeartBeat {
	// fields 
	private int K;
	private Map<String, Integer> slaveIPMap;//<ipAddress, expiredTime> key value pair
	
    public HeartBeat() {
        // do intialization if necessary
		this.slaveIPMap = new HashMap<String, Integer>();
    }

    /*
     * @param slaves_ip_list: a list of slaves'ip addresses
     * @param k: An integer
     * @return: nothing
     */
    public void initialize(List<String> slaves_ip_list, int k) {
        K = k;
		int expiredTime = 0 + K * 2;
		for (String slaveIP : slaves_ip_list) {
			slaveIPMap.put(slaveIP, expiredTime);
		}
    }

    /*
     * @param timestamp: current timestamp in seconds
     * @param slave_ip: the ip address of the slave server
     * @return: nothing
     */
    public void ping(int timestamp, String slave_ip) {
        if (!slaveIPMap.containsKey(slave_ip)) {
			return;
		}
		
		int expiredTime = timestamp + K * 2;
		slaveIPMap.put(slave_ip, expiredTime);
    }

    /*
     * @param timestamp: current timestamp in seconds
     * @return: a list of slaves'ip addresses that died
     */
    public List<String> getDiedSlaves(int timestamp) {
		List<String> result = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : slaveIPMap.entrySet()) {
			String slaveIP = entry.getKey();
			int expiredTime = entry.getValue();
			
			if (expiredTime <= timestamp) {// aleready expired
				result.add(slaveIP);
			}
		}
		
		return result;
    }
}