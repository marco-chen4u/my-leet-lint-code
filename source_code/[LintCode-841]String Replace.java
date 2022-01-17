/***
* LintCode 841. String Replace
Given two identical-sized string array A, B and a string S. 
All substrings A appearing in S are replaced by B.(Notice: From left to right, it must be replaced if it can be replaced. 
If there are multiple alternatives, replace longer priorities. After the replacement of the characters can't be replaced again.)

Example 1
    Input:
        A = ["ab","aba"]
        B = ["cc","ccc"]
        S = "ababa"
    Output: "cccba"
    Explanation: In accordance with the rules, the substring that can be replaced is "ab" or "aba". Since "aba" is longer, we replace "aba" with "ccc". 

Example 2
    Input:
        A = ["ab","aba"]
        B = ["cc","ccc"]
        S = "aaaaa"
    Output: "aaaaa"
    Explanation: S does not contain strings in A, so no replacement is done.
	
Example 3
    Input:
        A = ["cd","dab","ab"]
        B = ["cc","aaa","dd"]
        S = "cdab"
    Output: "ccdd"
    Explanation: From left to right, you can find the "cd" can be replaced at first, so after the replacement becomes "ccab", then you can find "ab" can be replaced, so the string after the replacement is "ccdd".
Notice
    -The size of each string array does not exceed 100, the total string length does not exceed 50000.
    -The lengths of A [i] and B [i] are equal.
    -The length of S does not exceed 50000.
    -All characters are lowercase letters.
    -We guarantee that the A array does not have the same string
***/

public class Solution {
    // fields
    private final int DEFAULT = -1;
    private final int SEED = 31;// smaller prime
    private final int MOD = Integer.MAX_VALUE - 1000000000;// bigger prime
    
    /**
     * @param a: The A array
     * @param b: The B array
     * @param s: The S string
     * @return: The answer
     */
    public String stringReplace(String[] a, String[] b, String s) {
        // check coner case
        if (isEmpty(a) || isEmpty(b) || isEmptyStr(s)) {
            return s;
        }
        
        List<Integer> hashValuesOfA = getHashValues(a);
        List<Integer> hashValuesOfS = getHashValuesOfStr(s);
        
        int sizeOfA = a.length;
        int sizeOfS = s.length();
        
        int maxLength = getMaxLength(a);
        List<Integer> base = getHashValuesOfBase(maxLength);
        
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sizeOfS; i++) {
            int maxSize = DEFAULT;
            int index = DEFAULT;
            
            for(int j = 0; j < sizeOfA; j++) {
                int currentLength = a[j].length();
                int left = i + 1;
                int right = i + currentLength;
                
                if(right > sizeOfS) {
                   continue;
                }
                
                long sHashValueOfRight = (long)hashValuesOfS.get(right);
                long sHashValueOfLeft = (long)hashValuesOfS.get(left - 1) * (long)(base.get(right - left + 1)) % (long)MOD;
				
                int sHashValue = (int)((sHashValueOfRight - sHashValueOfLeft + (long)MOD) % (long)MOD);
                                    
                int aHashValue = (hashValuesOfA.get(j) - base.get(currentLength) + MOD) % MOD;
                
                if(sHashValue == aHashValue && currentLength > maxSize) {
                   maxSize = currentLength;
                   index = j;
                }
            }// for j
            
            if(maxSize != DEFAULT) {
               sb.replace(i, i + maxSize, b[index]);
               i += maxSize - 1;
            }
            
        }// for i
        
        
        return sb.toString();
    }
    
    // helper methods
    private boolean isEmpty(String[] strs) {
        return (strs == null || strs.length == 0);
    }
    
    private boolean isEmptyStr(String str) {
        return (str == null || str.length() == 0);
    }
    
    private int getMaxLength(String[] strs) {
        int value = 0;
        
        for (String str : strs) {
            value = Math.max(value, str.length());
        }
        
        return value;
    }
    
    private List<Integer> getHashValuesOfStr(String str) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner case 
        if (isEmptyStr(str)) {
            return result;
        }
        
        long value = 1;
        result.add((int) value);
        for (char ch : str.toCharArray()) {
            value = (value * (long)SEED + (long)(ch - 'a')) % (long)MOD;
            result.add((int) value);
        }
        
        return result;
    }
    
    private int getHashValue(String str) {
        long value = 1;
        int size = str.length();
        
        for (int i = 0; i < size; i++) {
            value = (value * (long)SEED + (long)(str.charAt(i) - 'a')) % (long) MOD;
        }
        
        return (int)value;
    }
    
    private List<Integer> getHashValues(String[] strs) {
        List<Integer> result = new ArrayList<Integer>();
        
        for (String str : strs) {
            int value = getHashValue(str);
            result.add(value);
        }
        
        return result;
    }
    
    private List<Integer> getHashValuesOfBase(int n) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner case
        if (n <= 0) {
            return result;
        }
        
        long value = 1;
        result.add((int) value);
        
        for (int i = 0; i < n; i++) {
            value = (value * (long)SEED) % (long)MOD;
            result.add((int) value);
        }
        
        return result;
    }
}

/***
首先要知道滚动哈希是怎么样的思路，可以看九章微课堂或者维基百科了解一下。其实就是一个对字符串的hash函数，例如对字符串"abc"，它的hash值计算为(('a')*seed+'b')*seed+'c'，而对于字符串"abcd"，它的hash值为((('a')*seed+'b')*seed+'c')*seed+'d'(这里的seed也可以叫作base，但为了和代码中保持一致我使用了seed)。可以发现对于每一个字符串，它的hash值都能由它的上一个前缀递推过来，这也是它称之为滚动的原因。

现在将"abcd"的哈希值展开，得到：'a'*seed^3+'b'*seed^2+'c'*seed^1+'d'*seed^0。然后看"ab"的哈希值展开，为'a'*seed^1+'b'*seed^0。假设这时候我要求"cd"的哈希值，我会发现"cd"的哈希值为'c'*seed^1+'d'*seed^0，不就是"abcd"的哈希值表达式中次数较低的两项吗？因此我只要将最高次数的两项减掉就可以了，然后我又发现最高次数的两项不就是"ab"的哈希值表达式乘以seed^2吗，因此我们得到hash("cd")=hash("abcd")-hash("ab")*seed^2，类似于34=1234-12*100。
现在将它推广，就可以使用字符串前缀的hash值在常数时间内计算出任意的子串的hash值：考虑字符串s在[l,r]中的子串（为了和代码的下标统一起来，我假定这个下标从1开始），我可以用s[l,r]表示这个子串，那么就有hash(s[l,r]) = hash(s[1,r])-hash(s[1,l-1])*seed^len(s[l,r]) = hash(s[1,r])-hash(s[1,l-1])*seed^(r-l+1)。

在理解了这些的基础上，这个代码就很好理解了。首先预处理出a中字符串的hash值放到aHash中，然后预处理出s的所有前缀的hash值放到sHash中，然后将seed从0开始的所有幂次seed^0，seed^1，seed^2...放到base中。因为上面说的hash函数滚动的特性，hash(s[1,n])能够从hash(s[1,n-1])中递推过来，因此这一步花费的时间正比于s和a中所有字符串的长度总和。要注意的是代码中的sHash和aHash和上面说的有些不同。可能是为了方便迭代计算，它规定了sHash[0]=1，因此之后的每一项都会多一个1*seed^...的项，但两个前缀相减之后这一项会消去，因此不会影响上面那个表达式的计算。然后有了这些之后，我知道对于s的任意一个子串s[l,r]，我都能在O(1)时间内计算出它的哈希值了，因为有hash(s[l,r]) = hash(s[1,r])-hash(s[1,l-1])*seed^(r-l+1)，而hash(s[1,r])和hash(s[1,l-1])和seed^(r-l+1)这些都已经被我们预处理完了。

下面的思路就很简单了，对于s的每一个位置，判断是否有a中的字符串可以匹配，如果有的话取最长的那个进行替换，并跳过这些被替换的字符；如果没有则将当前位置向前移动一个字符。最后返回被替换的字符串就好了。

代码中有一个地方可能不是很好懂，就是计算sHashValue的那个表达式：

int sHashValue = (int)(((long)sHash.get(r) - (long)(base.get(r - l + 1)) * (long)sHash.get(l - 1) % (long)mod + (long)mod) % (long)mod);
mod的作用是让所有的运算结果的范围都在一定范围以内，因为如果不加mod，hash值的大小可能会因为字符串很长变得很大很大，所以要使用一个mod将其映射到一定范围之内，mod后的结果对于加、乘都是不变的，也就是说a % mod + b % mod = (a+b) % mod，并且((a % mod) * (b % mod)) % mod = (a*b) % mod。对于减法其实也是不变的，但减法之后可能会变成负数，因此只要出现减法，需要对结果加一个mod再对mod取模，也就是：((a % mod) - (b % mod) + mod) % mod = (a-b) % mod。但对于整数除法就不满足((a % mod) / (b % mod)) % mod = (a/b) % mod了，因为取模之后很可能没法整除了，不过有一个叫逆元的东西也能让它满足，这里用不到就不说了。

无尽的(long)和mod确实让人看得很头疼，把它分几步简化一下就好懂了：

int sHashValue = sHash.get(r) - base.get(r - l + 1) * sHash.get(l - 1); // 这是最原始的表达式，也就是对应上面的计算思路的表达式
int sHashValue = (sHash.get(r) - (base.get(r - l + 1) * sHash.get(l - 1)) % mod + mod) % mod; // 这是加了取模运算之后的表达式，注意到这里的减法需要多加一个mod，让结果保证为正数
如果仅仅这样写，就算是取模之后的运算结果最大值也有可能是mod-1，那么两个这样的数做加法和乘法很可能导致结果超出int范围，会导致溢出，因此需要在一个更广阔的空间内做乘法，使得结果不会溢出，这里就用long强制转换运算数的类型，使得表达式运算的中间结果用long来保存，最后再将% mod的必定在int范围内的运算结果转换成int，这样就不会溢出了。于是就得到了最上面的那个看起来很复杂的表达式。

然后这个解法一定能过吗？不一定，因为哈希函数会冲突，存在一定的概率将不同的字符串hash得到同一个整数，这时只比较这两个字符串的哈希值（相同），就会把这两个不同的字符串判断为是同一个字符串。不过只要mod和seed取得好（一般来说要互质），就可以保证这种情况出现的概率接近于0。举个取的不好的例子：seed=50, mod=1000000000这时就会过不了。

表达式，也就是对应子串哈希值计算思路的表达式
int sHashValue = sHash.get(r) - base.get(r - l + 1) * sHash.get(l - 1);
// 这是加了取模运算之后的表达式，注意到这里的减法需要多加一个mod，让结果保证为正数
int sHashValue = (sHash.get(r) - (base.get(r - l + 1) * sHash.get(l - 1)) % mod + mod) % mod;
// 这是加了类型转换，将表达式中间结果用long计算，最终结果再重新转换成int类型，保证了中间结果不会溢出（如果用int保存中间结果，也就是默认的做法，就很可能导致溢出）
int sHashValue = (int)(((long)sHash.get(r) - (long)(base.get(r - l + 1)) * (long)sHash.get(l - 1) % (long)mod + (long)mod) % (long)mod);
***/
