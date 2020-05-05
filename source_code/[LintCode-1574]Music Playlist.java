/***
* LintCode 1574. Music Playlist
Ming likes to listen to music on his mobile phone while traveling by train. He has a n song on his mobile phone. He can listen to the p song on the whole train, so he wants to generate a playlist to produce a p song. The rules for this playlist are:
	-(1) Each song must be played at least once
	-(2) In the middle of two songs, there are at least m other songs.
Xiao Ming wants to know how many different playlists can be produced. Since the result is large, the output result is the remainder of the 1000000007.

Example
	Example 1:
		Input：2,0,3
		Output：6
		Explanation：
		There are 2 songs in total, which are recorded as A and B. 
		No other songs are needed between the two songs.
		There are 6 programs in AAB, ABA, BAA, BBA, BAB and ABB.

	Example 2:
		Input：1,1,3
		Output：0
		Explanation：There is only one AAA scheme, but there are at least one other song in the middle of the same song, so there is no scheme that meets the requirements.

Notice
	1≤n≤100
	1≤m≤n
	1≤p≤100
***/
/***
假设dp[i][j]表示对于播放表的前i首歌，去除重复的歌曲后还剩下j首歌的方案数。（即使用j首歌来生成播放表的前i首歌）
那么需要return的答案便为：dp[p][n] (因为每首歌必须至少出现一次，故这p首歌去除重复后一定有n首歌)
对于dp[i][j]的求解，需要分类讨论：
	1.播放表的第i首歌跟前面的(i-1)首都不一样，则对于dp[i][j]，我们可以先使用(j-1)首歌排好播放表的前(i-1)首歌，然后从剩下的(n - (j-1))首歌
	里面再任意取一首歌，放在第i个位置，即：
	dp[i][j] += dp[i-1][j-1] * (n - (j - 1))

	2.播放表的第i首歌跟前面的(i-1)首存在重复的，则对于dp[i][j]，我们可以先使用j首歌排好前面的(i-1)首歌，然后因为保证任意两首相同的歌之间至
	少有m首不同的歌，故对于dp[i-1][j]里面的方案，最后的m首歌一定不一样，故我们只需要选一首跟最后面的m首歌不一样的歌，放在第i个位置即可，即：
	dp[i][j] += dp[i-1][j] * (j - m)

此题得解，时间复杂度:O(np)
注意一开始的初始化：dp[0][0] = 1
***/
public class Solution {
    // field
    private final int MOD = (int)(1e9 + 7);//(1e9+7)
    
    /**
     * @param n: the number of on his mobile phone
     * @param m: in the middle of two songs, there are at least m other songs
     * @param p: the number of songs he can listen to
     * @return: the number of playlists
     */
    public int getAns(int n, int m, int p) {
        
        // 1 <= m <= p <= n <= 100
        // state
        long[][] dp = new long[p + 1][n + 1];
        
        // initialize
        dp[0][0] = 1;
        
        for (int i = 1; i <= p; i++) {
            for (int j = 1; j <= n; j++) {
                // j <= m
                dp[i][j] = (dp[i - 1][j - 1] * (n - (j - 1))) % MOD;
                
                // j > m
                if (j > m)
                dp[i][j] = (dp[i][j] + dp[i - 1][j] * (j - m)  % MOD) % MOD;
            }
        }
        
        return (int)dp[p][n];
    }
}