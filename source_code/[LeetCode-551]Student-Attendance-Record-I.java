/***
* LeetCode 551. Student Attendance Record I

You are given a string s representing an attendance record for a student where each character signifies whether the student wasabsent, late, or present on that day. 
The record only contains the following three characters:

'A': Absent.
'L': Late.
'P': Present.
The student is eligible for an attendance award if they meet both of the following criteria:

The student was absent ('A') for strictly fewer than 2 days total.
The student was never late ('L') for 3 or more consecutive days.
Return true if the student is eligible for an attendance award, or false otherwise.

 

Example 1:
Input: s = "PPALLP"
Output: true
Explanation: The student has fewer than 2 absences and was never late 3 or more consecutive days.

Example 2:
Input: s = "PPALLL"
Output: false
Explanation: The student was late 3 consecutive days in the last 3 days, so is not eligible for the award.


Constraints:
    1 <= s.length <= 1000
    s[i] is either 'A', 'L', or 'P'.

***/
class Solution {
    public boolean checkRecord(String s) {

        // checker corner cases

        // regular case
        char[] chars = s.toCharArray();
        int size = chars.length;

        int countA = 0;

        int countL = 0;
        int maxL = 0;
        for (int i = 0; i < size; i++) {
            char ch = chars[i];

            if (ch == 'A') { 
                countA += 1;
                countL = 0;// reset the consecutive value to start

                continue;
            }

            if (ch == 'L') {
                countL += 1;// accumulate the consecutive L count
                maxL = Math.max(maxL, countL);
                continue;
            }

            countL = 0;
        }
        
        return maxL < 3 && countA < 2;
    }
}
