/**
* LintCode 1116 · Exclusive Time of Functions
Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU, 
find the exclusive time of these functions.

Each function has a unique id, start from 0 to n-1. 
A function may be called recursively or by another function.

A log is a string has this format : function_id:start_or_end:timestamp. 
For example, 0:start:0 means function 0 starts from the very beginning of time 0. 
0:end:0 means function 0 ends to the very end of time 0.

Exclusive time of a function is defined as the time spent within this function, 
the time spent by calling other functions should not be considered as this function's exclusive time. 
You should return the exclusive time of each function sorted by their function id.

Note:
    -Input logs will be sorted by timestamp, NOT log id.
    -Your output should be sorted by function id, 
     which means the 0th element of your output corresponds to the exclusive time of function 0.
    -Two functions won't start or end at the same time.
    -Functions could be called recursively, and will always end.
    -1 <= n <= 100

Example 1:
    Input:
        2
        0:start:0
        1:start:2
        1:end:5
        0:end:6
    Output:
        3 4
    Explanation:
        Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1. 
        Now function 0 calls function 1, function 1 starts at time 2, executes 4 units of time and end at time 5.
        Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time. 
        So function 0 totally execute 2 + 1 = 3 units of time, and function 1 totally execute 4 units of time.

Example 2:
    Input:
        3
        0:start:0
        1:start:2
        2:start:3
        2:end:4
        1:end:5
        0:end:6
        1:start:7
        1:end:10
    Output:
        3 6 2
    Explanation:
        Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1. 
        Now function 0 calls function 1, function 1 starts at time 2, executes 1 units of time.
        Function 1 calls function 2, function 2 starts at time 3, executes 2 units of time.
        Function 1 is running again at time 5, and also end at the time 5, thus executes 1 unit of time. 
        Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time. 
        Function 1 starts at time 7, and also end at the time 10, thus executes 4 unit of time. 
        So function 0 totally execute 2+1 = 3, units of time, and function 1 totally execute 1+1+4 = 6 units of time.
        function 2 totally execute 2 units of time.
        
tag: stack
**/
public class Solution {

    // constants
    private static final String OPERATION_START = "start";
    private static final String OPERATION_END = "end";

    // inner class
    class ThreadFunction {
        // fields
        int id;
        int startTimeStamp;
        int timeByOthers;

        // constructor
        public ThreadFunction(int id, int startTimeStamp) {
            this.id = id;
            this.startTimeStamp = startTimeStamp;
        }
    }

    /**
     * @param n: a integer
     * @param logs: a list of integers
     * @return: return a list of integers
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] results = new int[n];

        if (logs == null || logs.isEmpty()) {
            return results;
        }

        Stack<ThreadFunction> stack = new Stack<>();

        for (String log : logs) {
            String[] attributes = log.split(":");
            int id = Integer.valueOf(attributes[0]);
            String operation = attributes[1];
            int timeStamp = Integer.valueOf(attributes[2]);
   
            if (OPERATION_START.equals(operation)) {
                ThreadFunction threadFunction = new ThreadFunction(id, timeStamp);
                stack.push(threadFunction);
                continue;
            }

            if (OPERATION_END.equals(operation)) {
                ThreadFunction currentFunction = stack.pop();
                int currentId = currentFunction.id;
                int lastTime = currentFunction.startTimeStamp;
                int timeByOthers = currentFunction.timeByOthers;
                if (currentId != id) {
                    continue;
                }

                int totalTime = timeStamp - lastTime + 1;
                int costTime = totalTime - timeByOthers;
                results[id] += costTime;

                if (!stack.isEmpty()) {
                    stack.peek().timeByOthers += totalTime;
                }
            }
        }

        return results;
    }
}
