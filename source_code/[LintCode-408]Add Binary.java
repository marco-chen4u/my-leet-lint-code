/***
* LintCode 408. Add Binary
Given two binary strings, return their sum (also a binary string).

Example 1:
    Input:
        a = "0", b = "0"
    Output:
        "0"

Example 2:
    Input:
        a = "11", b = "1"
    Output:
        "100"
***/
//version-1
public class Solution {
    /**
     * @param a: a number
     * @param b: a number
     * @return: the result
     */
    public String addBinary(String a, String b) {
        int[] nums1 = getValues(a);
        int[] nums2 = getValues(b);
        
        //System.out.println("nums1 = " + Arrays.toString(nums1));
        //System.out.println("nums2 = " + Arrays.toString(nums2));
        
        int size = Math.max(nums1.length, nums2.length);
        int[] result = new int[size];
        
        int carry = 0;
        for (int i = 0; i < size; i++) {
            int value1 = (i < nums1.length) ? nums1[i] : 0;
            int value2 = (i < nums2.length) ? nums2[i] : 0;
            
            //System.out.println("value1 = " + value1 + ", value2 = " + value2);
            
            int sum = value1 + value2 + carry;
            result[size - i - 1] = sum % 2;
            carry = sum / 2;
        }
        
        //System.out.println("carry = " + carry);
        
        StringBuilder sb = new StringBuilder();
        sb.append((carry == 1) ? "1" : "");
        for (int value : result) {
            sb.append(value);
        }
        
        return sb.toString();
    }
    
    // helper method
    private int[] getValues(String str) {
        int size = str.length();
        int[] result = new int[size];
        
        for (int i = 0; i < size; i++) {
            //System.out.println("size = " + size + ", i = " + i);
            result[i] = Integer.valueOf(str.charAt(size - i - 1) - '0');
        }
        
        return result;
    }
}

//version-2
public class Solution {
    /**
     * @param a a number
     * @param b a number
     * @return the result
     */
    public String addBinary(String a, String b) {
        // Write your code here
        String ans = "";

        int carry = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = carry;
            sum += (i >= 0) ? a.charAt(i) - '0' : 0;
            sum += (j >= 0) ? b.charAt(j) - '0' : 0;
            ans = (sum % 2) + ans;
            carry = sum / 2;
        }
        if (carry != 0) {
            ans = carry + ans;
        }
        return ans;
    }
}

//version-3:
public class Solution {
    /**
     * @param a: a number
     * @param b: a number
     * @return: the result
     */
    public String addBinary(String a, String b) {
        // corner case
        if (isEmpty(a)) {
            return b;
        }

        if (isEmpty(b)) {
            return a;
        }

        char[] chars1 = reverse(a.toCharArray());
        char[] chars2 = reverse(b.toCharArray());
        int size1 = chars1.length;
        int size2 = chars2.length;

        int size = Math.max(size1, size2);
        char[] chars = new char[size];
        int index = 0;
        int i = 0;
        int j = 0;

        int carry = 0;
        while (i < size1 || j < size2) {
            int value1 = i < size1 ? chars1[i] - '0' : 0;
            int value2 = j < size2 ? chars2[j] - '0' : 0;

            int sum = value1 + value2 + carry;
            chars[index++] = (char)(sum % 2 + '0');

            carry = sum / 2;

            i++;
            j++;
        }

        String result = String.valueOf(reverse(chars));

        if (carry != 0) {
            result = "1" + result;
        }

        return result;

    }

    // helper methods
    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private char[] reverse(char[] chars) {
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;

            left++;
            right--;
        }

        return chars;
    }
}
