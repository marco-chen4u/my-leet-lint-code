/***
* LintCode 594. strStr II
mplement strStr function in O(n + m) time.

strStr return the first index of the target string in a source string. 
The length of the target string is m and the length of the source string is n.
If target does not exist in source, just return -1.


Example 1:
    Input：source = "abcdef"， target = "bcd"
    Output：1
    Explanation：
        The position of the first occurrence of a string is 1.

Example 2:
    Input：source = "abcde"， target = "e"
    Output：4
    Explanation：
        The position of the first occurrence of a string is 4.
***/
//version-1: partial past testcases(not perfect)
public class Solution {
	// fields
	private final int DEFAULT_VALUE = -1;
    /*
     * @param source: A source string
     * @param target: A target string
     * @return: An integer as index
     */
    public int strStr2(String source, String target) {
        // check corner case
        if (target == null || source == null || source.length() == 0) {
            return DEFAULT_VALUE;
        }
        
        if (target.length() == 0) {
            return 0;
        }
        
        int sizeOfSource = source.length();
        int sizeOfTarget = target.length();
        
        if (sizeOfSource < sizeOfTarget) {
            return DEFAULT_VALUE;
        }
        
        Map<String, Boolean> prefixMap = getPrefixMap(target);
        
        for (int i = 0; i < sizeOfSource - sizeOfTarget + 1; i++) {
            String key = new String(new char[] {source.charAt(i)});
            int start = i;
            int end = (i + sizeOfTarget <= sizeOfSource) ? (i + sizeOfTarget) : sizeOfSource;
            String word = source.substring(start, end);
            if (prefixMap.containsKey(key) && Boolean.TRUE.equals(prefixMap.get(word))) {
                return i;
            }
        }
        
        return DEFAULT_VALUE;
    }
    
    // helper method
    private Map<String, Boolean> getPrefixMap(String target) {
        Map<String, Boolean> prefixMap = new HashMap<String, Boolean>();
        int size = target.length();
        for (int i = 1; i < size - 1; i++) {
            prefixMap.put(target.substring(0, i), false);
        }
        
        prefixMap.put(target, true);
        
        return prefixMap;
    }
    
    private boolean isEmpty(String str) {
        return (str.length() == 0);
    }
    
    private boolean isNull(String str) {
        return (str == null);
    }
}

//version-2: Hash/KMP algorithm
/***
采用字符串哈希，字符串哈希时需要将字符串映射为数字，hash_target = (hash_target * 33 + target.charAt(i) - 'a') % mod;
此处哈希函数，提供了字符串转化数字的关系式。
对于需要匹配的子串对应一个值，然后再遍历主串，当前位置为i，则用i的哈希值减去i-m部分的哈希值，求出中间m个长度的子串的哈希值，如果与要匹配串相同，
由于哈希本身不安全，需要截取出来m长度的子串再进行匹配，完全相同即可。
注意负数取模时，需要通过+mod，将负数变为正数。
kmp是线性的字符串匹配算法，同样可以实现。
***/
public class Solution {
    // fields
    private final int DEFAULT_VALUE = -1;
    private final int SEED = 31;
    private final int MOD = Integer.MAX_VALUE / SEED;
    
    /*
     * @param source: A source string
     * @param target: A target string
     * @return: An integer as index
     */
    public int strStr2(String source, String target) {
        // check corner case
        if (target == null || source == null) {
            return DEFAULT_VALUE;
        }
        
        if (target.length() == 0) {
            return 0;
        }
        
        if (source.length() == 0) {
            return DEFAULT_VALUE;
        }
        
        // initialize
        int sizeOfSource = source.length();
        int sizeOfTarget = target.length();
        //System.out.println("sizeOfSource = " + sizeOfSource);
        //System.out.println("sizeOfTarget = " + sizeOfTarget);
        
        int hashValueOfTarget = 0;
        for (int i = 0; i < sizeOfTarget; i++) {
            hashValueOfTarget = getHashVAlueOfChar(hashValueOfTarget, target.charAt(i));
        }
        
        //System.out.println("hashValueOfTarget = " + hashValueOfTarget);
        
        int base = 1;
        for (int i = 0; i < sizeOfTarget - 1; i++) {
            base = base * SEED % MOD;
        }
        
        //System.out.println("base = " + base);
        
        int value = 0;
        for (int i = 0; i < sizeOfSource; i++) {
            //System.out.println("--i = " + i);
            if (i >= sizeOfTarget) {
                value = (value - (source.charAt(i - sizeOfTarget) - 'a') * base) % MOD;
            }
            
            value = getHashVAlueOfChar(value, source.charAt(i));
            
            //System.out.println("----value = " + value);
            //System.out.println("----hashValueOfTarget = " + hashValueOfTarget);
            if (i >= sizeOfTarget - 1 && value == hashValueOfTarget) {
                String currentWord = source.substring(i - sizeOfTarget + 1, i + 1);
                if (!target.equals(currentWord)) {
                    continue;
                }
                
                return i - sizeOfTarget + 1;
            }// if
            
        }// for i
        
        return DEFAULT_VALUE;
    }
    
    // helper method
    private int getHashVAlueOfChar(int value , char ch) {
        value = (value * SEED + ch - 'a') % MOD;
		value = (value < 0) ? value + MOD : value;
        return value;
    }
}
