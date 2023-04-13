/***
* LeetCode 68. Text Justification

Given an array of strings words and a width maxWidth, 
format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. 
Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. 
If the number of spaces on a line does not divide evenly between words, 
the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left-justified, and no extra space is inserted between words.

Note
    A word is defined as a character sequence consisting of non-space characters only.
    Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
    The input array words contains at least one word.

Example 1
    Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
    Output:
    [
       "This    is    an",
       "example  of text",
       "justification.  "
    ]

Example 2
    Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
    Output:
    [
      "What   must   be",
      "acknowledgment  ",
      "shall be        "
    ]
    Explanation: Note that the last line is "shall be    " instead of "shall     be", 
    because the last line must be left-justified instead of fully-justified.
    Note that the second line is also left-justified because it contains only one word.
    
Example 3
    Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
    Output:
    [
      "Science  is  what we",
      "understand      well",
      "enough to explain to",
      "a  computer.  Art is",
      "everything  else  we",
      "do                  "
    ]
    
Constraints:
    1 <= words.length <= 300
    1 <= words[i].length <= 20
    words[i] consists of only English letters and symbols.
    1 <= maxWidth <= 100
    words[i].length <= maxWidth

LeeCode link: https://leetcode.com/problems/text-justification/
LintCode link: https://www.lintcode.com/problem/1361/
***/
//version-1: simulation
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int index = 0;
        int size = words.length;

        while (index < size) {
            int count = words[index].length();
            int lastIndex = index + 1;

            while (lastIndex < size) {
                if (words[lastIndex].length() + count + 1 > maxWidth) {
                    break;
                }

                count = words[lastIndex].length() + count + 1;
                lastIndex++;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(words[index]);

            int gapCount = lastIndex - index - 1;

            if (lastIndex == size || gapCount == 0) {
                
                for(int i = index + 1; i < lastIndex; i++) {
                    sb.append(" ");//separator for each word
                    sb.append(words[i]);
                }

                // if there's some gap for last part to put space
                int startPos = sb.length();
                for (int i = startPos; i < maxWidth; i++) {
                    sb.append(" ");//put the remaining as spaces
                }
            } 
            else {
                int spaceCountPerGap = (maxWidth - count) / gapCount;
                int offSet = (maxWidth - count) % gapCount;

                for (int i = index + 1; i < lastIndex; i++) {
                    for (int k = 0; k < spaceCountPerGap; k++) {
                        sb.append(" ");//spaces to inject for each gap
                    }
                    if (offSet > 0) {
                        sb.append(" ");//space injection, for most left parts of words
                        offSet--;
                    }

                    sb.append(" "); // separator for each word
                    sb.append(words[i]);
                }
            }
            
            result.add(new String(sb));

            index = lastIndex;

        }

        return result;
    }
}
