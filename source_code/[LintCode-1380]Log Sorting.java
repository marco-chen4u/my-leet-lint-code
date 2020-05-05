/***
* LintCode 1380. Log Sorting
Given a list of string logs, in which each element representing a log. Each log can be separated into two parts by the first space. The first part is the ID of the log, while the second part is the content of the log. (The content may contain spaces as well.) The content is composed of only letters and spaces, or only numbers and spaces.

Now you need to sort logs by following rules:
	1.Logs whose content is letter should be ahead of logs whose content is number.
	2.Logs whose content is letter should be sorted by their content in lexicographic order. And when two logs have same content, sort them by ID in lexicographic order.
	3.Logs whose content is number should be in their input order.
Example
	Example 1:
		Input:  
			logs = [
				"zo4 4 7",
				"a100 Act zoo",
				"a1 9 2 3 1",
				"g9 act car"
			]
		Output: 
			[
				"a100 Act zoo",
				"g9 act car",
				"zo4 4 7",
				"a1 9 2 3 1"
			]
		Explanation: "Act zoo" < "act car", so the output is as above.

	Example 2:
		Input:  
			logs = [
				"zo4 4 7",
				"a100 Actzoo",
				"a100 Act zoo",
				"Tac Bctzoo",
				"Tab Bctzoo",
				"g9 act car"
			]
		Output: 
			[
				"a100 Act zoo",
				"a100 Actzoo",
				"Tab Bctzoo",
				"Tac Bctzoo",
				"g9 act car",
				"zo4 4 7"
			]
		Explanation:
			Because "Bctzoo" == "Bctzoo", the comparison "Tab" and "Tac" have "Tab" < Tac ", so" Tab Bctzoo "< Tac Bctzoo".
			Because ' '<'z', so "A100 Act zoo" < A100 Actzoo".
	
Notice
	1.The total number of logs will not exceed 5000
	2.The length of each log will not exceed 100
	3.Each log's ID is unique.
***/
/*
* 先把numeric 的log content，全部扫一遍，先添加到result[]中，那怎么保持这些numeric content原有的顺序，倒着来处理就好了。
* 其它的string log content，定义好comparator，然后把这些逐个（不管啥顺序）都放到这个特制的heap即可。
*/
public class Solution {
	// fields
	private static final String SEPARATOR = " ";// space
	private Comparator<String> comparator = new Comparator<String>() {
		@Override
		public int compare(String logA, String logB) {
			String idOfLogA = getLogId(logA);
			String contentOfLogA = getLogContent(logA);
			
			String idOfLogB = getLogId(logB);
			String contentOfLogB = getLogContent(logB);
			
			if (!contentOfLogA.equals(contentOfLogB)) {
				return contentOfLogA.compareTo(contentOfLogB);
			}
			
			return idOfLogA.compareTo(idOfLogB);
		}
	};
	
    /**
     * @param logs: the logs
     * @return: the log after sorting
     */
    public String[] logSort(String[] logs) {
		String[] result = null;
        // check corner cases
		if (logs == null || logs.length == 0) {
			return result;
		}
		
		int size = logs.length;
		result = new String[size];
		Queue<String> heap = new PriorityQueue<String>(size, comparator);
		
		int index = size - 1; // last position of the log array index
		for (int i = size - 1; i >= 0; i--) {
			String currentLog = logs[i];
			if (isNumbericContent(currentLog)) {
				result[index--] = currentLog;
				continue;
			}
			
			heap.offer(currentLog);
		}
		
		index = 0;
		while (!heap.isEmpty()) {
			result[index++] = heap.poll();
		}
		
		return result;
    }
	
	// helper methods
	private static String getLogId(String log) {
		return log.substring(0, log.indexOf(SEPARATOR));
	}
	
	private static String getLogContent(String log) {
		return log.substring(log.indexOf(SEPARATOR) + 1);
	}
	
	private boolean isNumbericContent(String log) {
		String logContent = getLogContent(log);
		if (isEmptyStr(logContent)) {
			return false;
		}
		
		String[] strs = logContent.split(SEPARATOR);
		
		for (String str : strs) {
			if (!Character.isDigit(str.charAt(0))) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean isEmptyStr(String str) {
		return str == null || str.length() == 0;
	}
}