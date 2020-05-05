/***
* LintCode 1335. Repeated DNA Sequences
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example
	Example1
		Input: "A"
		Output: []
	Example2
		Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
		Output: ["AAAAACCCCC", "CCCCCAAAAA"]
***/
/*
* 长字符串查找，最好通过压缩编码处理。
*/
public class Solution {
    // field
    private final int SEED = 4;
    
    /**
     * @param s: a string
     * @return: return List[str]
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<String>();
        
        // check corner case
        if (s == null || s.length() < 10) {
            return result;
        }
        
        Set<Integer> dnaRecordSet = new HashSet<Integer>();// HashSet saving more space compared to HashMap
        Set<String> dnaSeqSet = new HashSet<String>();
        
        for (int i = 9; i < s.length(); i++) {
            String currentDNASeq = s.substring(i - 9,  i + 1);
            int dnaSeqKey = getEncodeValue(currentDNASeq);
            
            
            if (!dnaRecordSet.contains(dnaSeqKey)) {
                dnaRecordSet.add(dnaSeqKey);
            }
            else {
                dnaSeqSet.add(currentDNASeq);
            }
        }
        
        result = new ArrayList<String>(dnaSeqSet);
        
        return result;
    }
    
    // helper method
    private int getEncodeValue(String dnaStr) {
        int value = 0;
        
        for(char ch : dnaStr.toCharArray()) {
            switch (ch) {
                case 'A' :
                    value = value * SEED + 1;
                    break;
                case 'C' :
                    value = value * SEED + 2;
                    break;
                case 'G' :
                    value = value * SEED + 3;
                    break;
                case 'T' :
                    value = value * SEED + 4;
                    break;
            }
        }
        
        return value;
    }
}