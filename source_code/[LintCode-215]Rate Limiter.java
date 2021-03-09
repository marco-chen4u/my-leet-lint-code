/***
* LintCode 215. Rate Limiter
Implement a rate limiter, provide one method: is_ratelimited(timestamp, event, rate, increment).
    -timestamp: The current timestamp, which is an integer and in second unit.
    -event: The string to distinct different event. for example, "login" or "signup".
    -rate: The rate of the limit. 1/s (1 time per second), 2/m (2 times per minute), 10/h (10 times per hour), 100/d (100 times per day). The format is [integer]/[s/m/h/d].
    -increment: Whether we should increase the counter. (or take this call as a hit of the given event)
The method should return true or false to indicate the event is limited or not.
Example
    Example 1
        Input: is_ratelimited(1, "login", "3/m", true)
        Output: false
    Example 2
        Input: is_ratelimited(11, "login", "3/m", true)
        Output: false
    Example 3
        Input: is_ratelimited(21, "login", "3/m", true)
        Output: false
    Example 4
        Input: is_ratelimited(30, "login", "3/m", true)
        Outuput: true
    Example 5
        Input: is_ratelimited(65, "login", "3/m", true)
        Output: false
    Example 6
        Input: is_ratelimited(300, "login", "3/m", true)
        Output: false
Challenge
    How many different algorithms you can come up with?
	
url: https://www.lintcode.com/problem/rate-limiter/description
***/
public class Solution {
    // fields
    Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
	
    /*
     * @param timestamp: the current timestamp
     * @param event: the string to distinct different event
     * @param rate: the format is [integer]/[s/m/h/d]
     * @param increment: whether we should increase the counter
     * @return: true or false to indicate the event is limited or not
     */
    public boolean isRatelimited(int timestamp, String event, String rate, boolean increment) {
        int index = rate.indexOf("/");
	int limit = Integer.valueOf(rate.substring(0, index));
	String type = rate.substring(index + 1, rate.length());

	int duration = 1;//second
	switch (type) {
		case "m"://minute
			duration *= 60;
			break;
		case "h": // hour
			duration *= 60 * 60;
			break;
		case "d": // day
			duration += 60 * 60 * 24;
			break;
	}

	int startTimeStamp = timestamp - duration + 1;//the duration before the current timestamp

	map.putIfAbsent(event, new ArrayList<Integer>());



	int size = map.get(event).size();
	int count = getCountAfter(map.get(event), startTimeStamp);
	boolean isRatelimited = count >= limit;

	if (increment && !isRatelimited) {
		map.get(event).add(timestamp);//we should increase the counter
	}

	return isRatelimited;
    }
	
    // helper method
    private int getCountAfter(List<Integer> eventsList, int startTimeStamp) {
    int count = 0;
    if (eventsList == null || eventsList.isEmpty()) {
        return count;
    }

    int size = eventsList.size();
    int lastPos = size - 1;
    if (eventsList.get(lastPos) < startTimeStamp) {
	return count;
    }

    int start = 0;
    int end = lastPos;
    int index = start;

    // binary search
    while (start + 1 < end) {
        int mid = start + (end - start) / 2;

        if (eventsList.get(mid) < startTimeStamp) {
            start = mid;
        }
        else {
            end = mid;
        }			
    }

    if (eventsList.get(start) >= startTimeStamp) {
        index = start;
    }
    else {
        index = end;
    }

    count = lastPos - index + 1;

    return count;
}
}
