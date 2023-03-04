/***
* LintCode 399. Nuts & Bolts Problem
Given a set of n nuts of different sizes and n bolts of different sizes. 
There is a one-one mapping between nuts and bolts.
Comparison of a nut to another nut or a bolt to another bolt is not allowed. 
It means nut can only be compared with bolt and bolt can only be compared with nut to see which one is bigger/smaller. 
We will give you a compare function to compare nut with bolt.
Using the function we give you, you are supposed to sort nuts or bolts, so that they can map in order.

Example
    Given nuts = ['ab','bc','dd','gg'], bolts = ['AB','GG', 'DD', 'BC'].
    Your code should find the matching of bolts and nuts.
    According to the function, one of the possible return:
        nuts = ['ab','bc','dd','gg'], bolts = ['AB','BC','DD','GG'].
    If we give you another compare function, the possible return is the following:
        nuts = ['ab','bc','dd','gg'], bolts = ['BC','AA','DD','GG'].
    So you must use the compare function that we give to do the sorting.
    The order of the nuts or bolts does not matter. You just need to find the matching bolt for each nut.
***/

/**
 * public class NBCompare {
 *     public int cmp(String a, String b);
 * }
 * You can use compare.cmp(a, b) to compare nuts "a" and bolts "b",
 * if "a" is bigger than "b", it will return 1, else if they are equal,
 * it will return 0, else if "a" is smaller than "b", it will return -1.
 * When "a" is not a nut or "b" is not a bolt, it will return 2, which is not valid.
*/
public class Solution {
    /**
     * @param nuts: an array of integers
     * @param bolts: an array of integers
     * @param compare: a instance of Comparator
     * @return: nothing
     */
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        //check corner cases
        if (nuts == null || nuts.length == 0 || bolts == null || bolts.length == 0 || 
            nuts.length != bolts.length) {
            return;
        }
        
        int size = nuts.length;
        
        quickSort(nuts, bolts, 0, size - 1, compare);
    }
    
    // helper methods
    private void quickSort(String[] nuts, String[] bolts, int left, int right, 
                        NBComparator compare) {
        // check corner case
        if (left >= right) {
            return;
        }
        
        int mid = (left + right) / 2;
        String pivot = bolts[mid];
        int partitionIndex = partition(nuts, left, right, pivot, compare);
        partition(bolts, left, right, nuts[partitionIndex], compare);
        
        quickSort(nuts, bolts, left, partitionIndex - 1, compare);
        quickSort(nuts, bolts, partitionIndex + 1, right, compare);
    }
    
    private int partition(String[] values, int left, int right, 
                            String pivot, 
                            NBComparator compare) {
        int current = left;
        
        while (current <= right) {
            if (compare.cmp(values[current], pivot) == -1 || 
                compare.cmp(pivot, values[current]) == 1) {
                swap(values, current++, left++);
            }
            else if (compare.cmp(values[current], pivot) == 1 ||
                        compare.cmp(pivot, values[current]) == -1) {
                swap(values, current, right--);
            }
            else {
                current++;
            }
        }
        
        return left;
    }
    
    private void swap(String[] values, int i, int j) {
        String tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }
};
