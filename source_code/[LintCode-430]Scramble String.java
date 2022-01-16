/***
* LintCode 430. Scramble String
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":
        great
       /    \
      gr    eat
     / \    /  \
    g   r  e   at
               / \
              a   t
To scramble the string, we may choose any non-leaf node and swap its two children.
For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
        rgeat
       /    \
      rg    eat
     / \    /  \
    r   g  e   at
               / \
              a   t
We say that "rgeat" is a scrambled string of "great".
Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
        rgtae
       /    \
      rg    tae
     / \    /  \
    r   g  ta  e
           / \
          t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.

Example 1:
    Input: s1 = "great", s2 = "rgeat"
    Output: true
    Explanation: As described above.

Example 2:
    Input: s1 = "a", s2 = "b"
    Output: false

Challenge
    O(n3) time

Notice
    You can start scrambling from any binary tree legally built from s1, but you can not rebuild another binary tree while you are scrambling to get s2.
***/

//version-1: memorized search(DFS)
public class Solution {
    // fields
    private Map<String, Boolean> hash = new HashMap<String, Boolean>();
    private final String SEPERATOR = "//";

    // methods
    /**
     * @param s1: A string
     * @param s2: Another string
     * @return: whether s2 is a scrambled string of s1
     */
    public boolean isScramble(String s1, String s2) {
        // check corner cases
        if (s1 == null && s2 == null) {
            return true;
        }

        if (s1 == null || s2 == null) {
            return false;
        }
        
        // memorization search
        String key = s1 + SEPERATOR + s2;
        if (hash.containsKey(key)) {
            return hash.get(key);
        }

        if (s1.equals(s2)) {
            return true;
        }
        
        // get the length
        int n = s1.length();
        int m = s2.length();
        
        if (n != m) {
            return false;
        }
        
        // check if the 2 strigns contains the same characters
        if (!isCheckCharacterContent(s1, s2)) {
            return false;
        }
		
        // normail case
        for (int i = 1; i < n; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && 
                    isScramble(s1.substring(i, n), s2.substring(i, n)) ||
                    isScramble(s1.substring(0, i), s2.substring(n - i, n)) && 
                    isScramble(s1.substring(i, n), s2.substring(0, n - i))) {
				hash.put(key, true);
                return true;
            }
        }
        
		hash.put(key, false);
        return false;
    }	
	
    // helepr method 
    /**
     * check if the 2 strigns contains the same characters
     */
    private boolean isCheckCharacterContent(String s, String t) {
        int[] count = new int[255];
 
        int n = s.length();
        int m = t.length();
        // check corner case
        if (n != m) {
            return false;
        }	

        // normal case
        for(int i = 0; i < n; i++) {
            count[s.charAt(i) - 'A']++;
        }

        for (int i = 0; i < n; i++) {
            count[t.charAt(i) - 'A']--;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return false;
            }
        }

        return true;
    }
}

//version-2: DP
/*
*假设所进行分析处理的2个字符串分别为S和T，
    -如果T的长度和S的长度不一样，那么T肯定不能由S变换而来。
    -如果T是S变换而来的。并且我们知道S最上层二分即S=S1+S2,那么一定存在如下2种情况的一种：
        -T也有两部分T=T1+T2， T1是S1变换而来的，T2是S2变换而来的。
        -T也由两部分T=T1+T2， T1是S2变换而来的，T2是S1变换而来的。
        -S1,S2, T1,T2，是子问题的分析处理。
    因此，状态： f[i][j][k][h],表示T[k..h]是由S[i..j]变换而来的。这个4维数组是可以降维处理的，因为存在这样的情况 (h-k) = (j - i).

    确定状态：
        这里所有字符串都是S和T的子串，且长度两两相同
        所以每个字符串都可以（起始位置，长度）表示
        例如：
            S1长度是5，在S的起始位置从7开始。
            T1长度是5，在T的起始位置从0开始。
            这样，就可以用f[7][0][5]= true/false, 表示S1能否通过变换成为T1

        状态： 设f[i][j][k]表示S1能否通过变换成为T1
                -S1为S从字符i开始的长度为k的子串（子串也即区间）
                -T1为T从字符j开始的长度为k的子串（子串也即区间）
        转移方程：要解决2个问题
                （1）如何枚举，在哪里把S和T，劈成2份；
                （2）如要不要交换左右儿子
                因此：
                    （a）不交换左右儿子
                        f[i][j][k] = OR{f[i][j][w] AND f[i+w][j+w][k - 2]},其中长度w的取值范围1<=w<=k-1
                    （b）交换左右儿子
                        f[i][j][k] = OR{f[i][j + k - w][w] AND f[i + w][j][k - w]}
        初始化： 
                如果S[i] = T[j], 那么f[i][j][1] = true, 否则f[i][j][1] = false
        计算顺序：
                设f[i][j][k]	表示S1能否通过变换成为T1
                    -S1为S从字符i开始的长度为k的子串（子串也即区间）
                    -T1为T从字符j开始的长度为k的子串（子串也即区间）
                按照k从小到大的计算顺序进行计算：
                    -f[i][j][1], i属于[0..n], j属于[0..n]
                    -f[i][j][2], i属于[0..n-1], j属于[0..n-1]
                    -f[i][j][3], i属于[0..n-2], j属于[0..n-2]
                    ...
                    -f[i][j][n]
                答案：f[0][0][n]
        时间复杂度： O(n^4), 空间复杂度： O(n^3), 不能用滚动数组
*/
public class Solution {
    /**
     * @param S: A string
     * @param T: Another string
     * @return: whether T is a scrambled string of S
     */
    public boolean isScramble(String S, String T) {
        int n = S.length();
        int m = T.length();

        // check corner cases
        if (n != m) {
            return false;
        }

        boolean[][][] dp = new boolean[n][n][n + 1]; // the last demension '[n + 1]' is the length for string process
        int i, j , w, len;

        // len = 1
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                dp[i][j][1] = S.charAt(i) == T.charAt(j);
            }
        }

        for (len = 2; len <= n; len ++) {
            for (i = 0; i <= n - len; i++) {
                for (j = 0; j <= n - len; j++) {
                    // S break into S1 and S2
                    for (w = 1; w < len; w++) {

                        // no swap
                        // S1 --> T1, S2 --> T2
                        if (dp[i][j][w] && dp[i + w][j + w][len - w]) {
                            dp[i][j][len] = true;
                            break;
                        }

                        // swap
                        // S1 --> T2, S2 --> T1
                        if (dp[i][j + len - w][w] && dp[i + w][j][len - w]) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }// for w
                }// for j
            }// for i
        }// for len

        return dp[0][0][n];
    }
}
