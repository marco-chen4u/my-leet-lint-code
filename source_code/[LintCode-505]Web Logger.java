/***
* LintCode 505. Web Logger
Implement a web logger, which provide two methods:
	1.hit(timestamp), record a hit at given timestamp.
	2.get_hit_count_in_last_5_minutes(timestamp), get hit count in last 5 minutes.
the two methods will be called with non-descending timestamp (in sec).

Example
	Example 1:
		Input:
			hit(1);
			hit(2);
			get_hit_count_in_last_5_minutes(3);
			hit(300);
			get_hit_count_in_last_5_minutes(300);
			get_hit_count_in_last_5_minutes(301);
		Output:
			2
			3
			2
	Example 2:
		Input:
			hit(1)
			hit(1)
			hit(1)
			hit(2)
			get_hit_count_in_last_5_minutes(3)
			hit(300)
			get_hit_count_in_last_5_minutes(300)
			get_hit_count_in_last_5_minutes(301)
			get_hit_count_in_last_5_minutes(302)
			get_hit_count_in_last_5_minutes(900)
			get_hit_count_in_last_5_minutes(900)
		Output:
			4
			5
			2
			1
			0
			0
Notice
	The unit of timestamp is seconds.

	The call to the function is in chronological order, that is to say, timestamp is in ascending order.
***/
public class WebLogger {
    // fields
    private final int FIVE_MINUTES = 300;// 300 secs = 5 minutes
    
    private LinkedList<Integer> timeStampList;
    
    // contructor
    public WebLogger() {
        // do intialization if necessary
        this.timeStampList = new LinkedList<Integer>();
    }

    /*
     * @param timestamp: An integer
     * @return: nothing
     */
    public void hit(int timestamp) {
        this.timeStampList.add(timestamp);
    }

    /*
     * @param timestamp: An integer
     * @return: An integer
     */
    public int get_hit_count_in_last_5_minutes(int timestamp) {
        while (!timeStampList.isEmpty() && 
                timeStampList.getFirst() + FIVE_MINUTES <= timestamp) {
            timeStampList.removeFirst();
        }
        
        return timeStampList.size();
    }
}