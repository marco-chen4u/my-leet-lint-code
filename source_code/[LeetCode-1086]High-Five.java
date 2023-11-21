/***
* LeetCode 1086. High Five
Given a list of the scores of different students, items, where items[i] = [IDi, scorei] 
represents one score from a student with IDi, calculate each student's top five average.

Return the answer as an array of pairs result, 
where result[j] = [IDj, topFiveAveragej] represents the student with IDj and their top five average. 
Sort result by IDj in increasing order.

A student's top five average is calculated 
by taking the sum of their top five scores and dividing it by 5 using integer division.

Example 1
  Input: items = [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
  Output: [[1,87],[2,88]]
  Explanation: 
  The student with ID = 1 got scores 91, 92, 60, 65, 87, and 100. 
  Their top five average is (100 + 92 + 91 + 87 + 65) / 5 = 87.
  The student with ID = 2 got scores 93, 97, 77, 100, and 76. 
  Their top five average is (100 + 97 + 93 + 77 + 76) / 5 = 88.6, but with integer division their average converts to 88.

Example 2
  Input: items = [[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100]]
  Output: [[1,100],[7,100]]

Constraints:
  1 <= items.length <= 1000
  items[i].length == 2
  1 <= IDi <= 1000
  0 <= scorei <= 100
  For each IDi, there will be at least five scores.

* Link(LeetCode): https://leetcode.com/problems/high-five
* Link(LintCode): https://www.lintcode.com/problem/613/
***/

//version-1: inner class + list + sort(reverse order) + Map, time-complexity: O(nLogn)
class Solution {
    class Student {
        int id;
        private List<Integer> scoreList;
        private int count;
        
        public Student(int id) {
            this.id = id;
            scoreList = new ArrayList<>();
        }

        public void add(int score) {
            this.scoreList.add(score);
        }

        public int getTotalScore() {
            int size = scoreList.size();
            size = size > 5 ? 5 : size;
            count = size;

            // get top 5 socre to sum
            
            int total = 0;
            Collections.sort(scoreList, Collections.reverseOrder());
            for (int i = 0; i < size; i++) {
                total += scoreList.get(i);
            }

            return total;
        }

        public int getAverage() {
            return getTotalScore() / count;
        }

        public String toString() {
            return "Student: [id: " + id + ", average : " + getAverage() + ", count = " + count +"]";
        }
    }
    public int[][] highFive(int[][] items) {
        Map<Integer, Student> studentMap = new HashMap<>();
        for (int[] studentItem : items) {
            int id = studentItem[0];
            int score = studentItem[1];
            studentMap.putIfAbsent(id, new Student(id));
            Student student = studentMap.get(id);
            student.add(score);
        }

        Map<Integer, Integer> scoreMap = new HashMap<>();
        for (int key : studentMap.keySet()) {
            Student current = studentMap.get(key);
            System.out.println(current);// this line of code is only for testing
            scoreMap.put(key, current.getAverage());
        }

        int studentNum = studentMap.size();
        int[][] result = new int[studentNum][2];
        int index = 0;
        for (int id : scoreMap.keySet()) {
            result[index][0] = id;
            result[index][1] = scoreMap.get(id);
            index++;
        }

        return result;
    }
}
