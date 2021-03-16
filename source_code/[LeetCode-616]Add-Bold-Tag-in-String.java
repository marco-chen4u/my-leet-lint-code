/***
* LeetCode 616. Add Bold Tag in String
Given a String s and a list of Strings dict, you need to add a closed pair of bold tage <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings olverlap, you need to wrap them together by only one pair of closed blod tag. Also if two substrings wrapped by bold tags are consecutive, you need to combine them.

Example 1:
    Input: 
        s = "abcxyz123"
        dict = ["abc","123"]
    Output:
        "<b>abc</b>xyz<b>123</b>"
 
Example 2:
    Input: 
        s = "aaabbcc"
        dict = ["aaa","aab","bc"]
    Output:
        "<b>aaabbc</b>c"

Constraints:
    - Then given dict won't contain duplicates, and its length won't exceed 100.
    - All the Strings in input have length in range [1, 1000].

Url: https://leetcode.com/problems/add-bold-tag-in-string/

Note: This question is the same as 758: https://leetcode.com/problems/bold-words-in-string/
***/
//solution-1: sliding window:
class Solution {
    public String addBoldTag(String s, String[] dict) {
        // check corner cases
        if (s == null || s.isEmpty()) {
            return s;
        }
        
        if (dict == null || dict.length == 0) {
            return s;
        }
        
        // regular case
        int size = s.length();
        boolean[] bolds = new boolean[size];
        Arrays.fill(bolds, false);
        
        for (int i = 0, j = 0; i < size; i++) {
            
            for (String word : dict) {
                if (!s.startsWith(word, i)) {
                    continue;
                }
                
                j = Math.max(j, i + word.length());
            }
            
            bolds[i] = (j > i);
        }
        
        char[] charArray = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < size; i++) {
            if (!bolds[i]) {
                sb.append(charArray[i]);
                continue;
            }
            
            j = i;
            while (j < size && bolds[j]) {
                j++;
            }
            sb.append("<b>").append(s.substring(i, j)).append("</b>");
            i = j - 1;
        }
        
        return sb.toString();
    }
}

//solution-2: 
public class Solution {
    /**
     * @param s: a string
     * @param dict: a list of strings
     * @return: return a string
     */
    public String addBoldTag(String s, String[] dict) {
        // check corner cases
        if (s == null || s.isEmpty()) {
            return s;
        }
        
        if (dict == null || dict.length == 0) {
            return s;
        }
        
        // regular case
        int size = s.length();
        boolean[] bolds = new boolean[size];
        Arrays.fill(bolds, false);

        for (int i = 0, j = 0; i < size; i++) {
            
            for (String word : dict) {
                if (!s.startsWith(word, i)) {
                    continue;
                }
                
                j = Math.max(j, i + word.length());
            }
            
            bolds[i] = (j > i);
        }
        
        char[] charArray = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < size; i++) {
            if (!bolds[i]) {
                sb.append(charArray[i]);
                continue;
            }
            
            j = i;
            while (j < size && bolds[j]) {
                j++;
            }
            
            sb.append("<b>").append(Arrays.copyOfRange(charArray, i, j)).append("</b>");
            
            i = j - 1;
        }
        
        return sb.toString();
    }
}

//solution-3
class Solution {
    
    // inner class
    private class Interval implements Comparable<Interval>{
        // fields
        public int start;
        public int end;
        
        // constructor
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        // method
        @Override
        public int compareTo(Interval other) {
            if (this.start != other.start) {
                return this.start - other.start;
            }
            
            return this.end - other.end;
        }
        
        /*public String toString() {
            return "start = " + start + ", " + "end = " + end;
        }*/
    }
    
    public String addBoldTag(String s, String[] dict) {
        List<Interval> intervals = new ArrayList<>();
        
        int size = s.length();
        
        for (String word: dict) {
            int index = s.indexOf(word, 0);
            while (index != -1) {
                intervals.add(new Interval(index, index + word.length()));
                index = s.indexOf(word, index + 1);
            }// end of while
        }// end of for

        /*System.out.println("after sorting");*/
        Collections.sort(intervals);
        /*for (Interval  interval : intervals) {
            System.out.println(interval);
        }*/
        
        List<Interval> mergedIntervals = mergeIntervals(intervals);
        /*System.out.println("after merged");
        for (Interval interval : mergedIntervals) {
            System.out.println(interval);
        }*/
        
        
        int pre = 0;
        StringBuilder sb = new StringBuilder();
        
        for (Interval interval : mergedIntervals) {
            sb.append(s.substring(pre, interval.start));
            sb.append("<b>").append(s.substring(interval.start, interval.end)).append("</b>");
            pre = interval.end;
        }
        
        sb.append((pre < size) ? s.substring(pre) : "");
        
        return sb.toString();
    }
    
    // helper method
    private List<Interval> mergeIntervals(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        if (intervals == null || intervals.isEmpty()) {
            return result;
        }
        
        Interval pre = null;
        for (Interval current : intervals) {
            if (pre == null) {
                pre = current;
                continue;
            }
            
            if (pre.end < current.start) {
                result.add(pre);
                
                pre = current;
                continue;
            }
            
            pre.end = Math.max(pre.end, current.end);
        }
        
        if (pre != null) {
            result.add(pre);
        }
        
        return result;
    }
}
