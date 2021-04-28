/***
* LeetCode 920. Number of Music Playlists
Your music player contains N different songs and she wants to listen to L (not necessarily different) songs during your trip.  
You create a playlist so that:
    -Every song is played at least once
    -A song can only be played again only if K other songs have been played
Return the number of possible playlists.  As the answer can be very large, return it modulo 10^9 + 7.

Example
    Example 1:
        Input: N = 3, L = 3, K = 1
        Output: 6
        Explanation: There are 6 possible playlists. [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1].
    Example 2:
        Input: N = 2, L = 3, K = 0
        Output: 6
        Explanation: There are 6 possible playlists. [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 2, 1], [2, 1, 2], [1, 2, 2]
    Example 3:
        Input: N = 2, L = 3, K = 1
        Output: 2
        Explanation: There are 2 possible playlists. [1, 2, 1], [2, 1, 2]
Note:
    0 <= K < N <= L <= 100
***/
/***
*这道题说是一个音乐播放器有N首歌，有个她想听L首歌（可以有重复），但需要满足两个条件，一个是每首歌都必须至少播放1次，第二个是两首重复歌的中间至少要有K首其他的歌。提示了结果可能非常巨大，需要对一个超大数取余。对于这类结果超大的数，基本不用怀疑，基本都是用动态规划 Dynamic Programming 来做，这里主要参考了 大神 optimisea 的帖子。首先就是要确定 dp 的定义式，显然这里一维的 dp 数组是罩不住的，因为貌似有三个参数，N，L 和 K。但是否意味着需要个三维数组呢，其实也不用，并不关心所有的K值，但是对于N和L是必须要关注的，这里用一个二维 dp 数组，其中 dp[i][j] 表示总共放了i首歌，其中j首是不同的。下面来考虑状态转移方程，在加入一首歌的时候，此时有两种情况：
    -当加入的是一首新歌，则表示之前的 i-1 首歌中有 j-1 首不同的歌曲，其所有的组合情况都可以加上这首新歌，那么当前其实有 N-(j-1) 首新歌可以选。
    -当加入的是一首重复的歌，则表示之前的 i-1 首歌中已经有了 j 首不同的歌，那么若没有K的限制，则当前有 j 首重复的歌可以选。但是现在有了K的限制，意思是两首重复歌中间必须要有K首其他的歌，则当前只有 j-K 首可以选。而当 j<K 时，其实这种情况是为0的。
综上所述可以得到状态转移方程：
                dp[i-1][j-1]*(N-(j-1)) + dp[i-1][j]*(j-k)    (j > K)
               /
    dp[i][j] = 
               \
                dp[i-1][j-1]*(N-(j-1))   (j <= K)

***/
class Solution {
    //field
    private int MOD = (int)(1e9 + 7);
    public int numMusicPlaylists(int N, int L, int K) {
        //0 <= K < N <= L <= 100
        // state
        long[][] dp = new long[L + 1][N + 1];
        
        // initialize
        dp[0][0] = 1;
        
        // function
        for (int i = 1; i <= L; i++) {
            for (int j = 1; j <= N; j++) {
                // j <= k
                dp[i][j] = (dp[i - 1][j - 1] * (N - (j - 1))) % MOD;
                
                // j > k
                if (j > K) {
                    dp[i][j] = (dp[i][j] + (dp[i - 1][j] * (j - K)) % MOD) % MOD;
                }
            }
        }
        
        return (int)dp[L][N];
    }
}
