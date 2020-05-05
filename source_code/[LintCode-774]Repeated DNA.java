/***
* LintCode 774. Repeated DNA
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T.
For example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example
	Example 1:
		Input:
		"AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
		Output:
		["AAAAACCCCC","CCCCCAAAAA"]

	Example 2:
		Input:
		"GAGAGAGAGAGA"
		Output:
		["GAGAGAGAGA"]
***/
public class Solution {
    /**
     * @param s: a string represent DNA sequences
     * @return: all the 10-letter-long sequences 
     */
    public List<String> findRepeatedDna(String s) {
        List<String> result = new ArrayList<String>();
        
        // check corner case
        if (s == null || s.length() <= 10) {
            return result;
        }
        
        int size = s.length();
        Map<String, Integer> map = new HashMap<String, Integer>();
        
        for (int i = 9; i < size; i++) {
            String dnaSeq = s.substring(i - 9, i + 1);
            
            map.put(dnaSeq, map.getOrDefault(dnaSeq, 0) + 1);
        }
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                result.add(entry.getKey());
            }
        }
        
        return result;
    }
}