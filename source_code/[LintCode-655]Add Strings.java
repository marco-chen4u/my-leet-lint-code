/***
* LintCode 655. Add Strings
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.


Example 1:
    Input : num1 = "123", num2 = "45"
    Output : "168"

Notice
    The length of both num1 and num2 is < 5100.
    Both num1 and num2 contains only digits 0-9.
    Both num1 and num2 does not contain any leading zero.
    You must not use any built-in BigInteger library or convert the inputs to integer directly.
***/

/*
* (1）先把求和的2个字符串num1（“123”），num2（“45”），进行取反变成“321”，“54”，然后变成char[]类型数组，以方便计算合并
* (2)然后利用二路归并算法，把从头到尾进行求和归并，如果有进位值则保存在carry中，方便下一轮归并的求和计算。
* (3)完成二路归并求和操作后，查看进位值carry是否 > 0,则说明存在进位值，得把进位值进行添加到数组末端。
* （4）把二路归并和处理完进位值添加操作后，把整个结果字符串进行取反操作，并范围String 类型结果
*/
//version-1: reverse characters + merge to arrays
public class Solution {
    /**
     * @param num1: a non-negative integers
     * @param num2: a non-negative integers
     * @return: return sum of num1 and num2
     */
    public String addStrings(String num1, String num2) {
        char[] numArray1 = reverse(num1).toCharArray();
        char[] numArray2 = reverse(num2).toCharArray();
        
        int size1 = numArray1.length;
        int size2 = numArray2.length;
        int size = Math.max(size1, size2);
        char[] digits = new char[size];
        
        int i = 0;
        int j = 0;
        
        int carry = 0;
        int index = 0;
        while (i < size1 && j < size2) {
            int value1 = numArray1[i] - '0';
            int value2 = numArray2[j] - '0';
            int sum = value1 + value2 + carry;
            digits[index++] = (char)((sum % 10) + '0');
            carry = sum / 10;
            
            i++;
            j++;
        }
        
        while (i < size1) {
            int value = numArray1[i] - '0';
            int sum = value + carry;
            digits[index++] = (char)((sum % 10) + '0');
            carry = sum / 10;
            
            i++;
        }
        
        while (j < size2) {
            int value = numArray2[j] - '0';
            int sum = value + carry;
            digits[index++] = (char)((sum % 10) + '0');
            carry = sum / 10;
            
            j++;
        }
        
        String result = String.valueOf(digits);
        result = (carry > 0) ? result + carry : result;
        
        //System.out.println(result);
        result = reverse(result);
        
        return result;
    }
    
    private String reverse(String str) {
        char[] charArray = str.toCharArray();
        int size = charArray.length;
        int left = 0; 
        int right = size - 1;
        
        while (left < right) {
            char tmp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = tmp;
            
            left++;
            right--;
        }
        
        return String.valueOf(charArray);
    }
}

//version: reverse + merge
public class Solution {
    /**
     * @param num1: a non-negative integers
     * @param num2: a non-negative integers
     * @return: return sum of num1 and num2
     */
    public String addStrings(String num1, String num2) {
        char[] numArray1 = reverse(num1).toCharArray();
        char[] numArray2 = reverse(num2).toCharArray();
        
        int size1 = numArray1.length;
        int size2 = numArray2.length;
        int size = Math.max(size1, size2);
        char[] digits = new char[size];
        
        int i = 0;
        int j = 0;
        
        int carry = 0;
        int index = 0;
        while (i < size1 || j < size2) {
            int value1 = i < size1 ? numArray1[i] - '0' : 0;
            int value2 = j < size2 ? numArray2[j] - '0' : 0;
            int sum = value1 + value2 + carry;
            digits[index++] = (char)((sum % 10) + '0');
            carry = sum / 10;
            
            i++;
            j++;
        }        
        
        String result = String.valueOf(digits);
        result = (carry > 0) ? result + carry : result;
        
        //System.out.println(result);
        result = reverse(result);
        
        return result;
    }
    
    private String reverse(String str) {
        char[] charArray = str.toCharArray();
        int size = charArray.length;
        int left = 0; 
        int right = size - 1;
        
        while (left < right) {
            char tmp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = tmp;
            
            left++;
            right--;
        }
        
        return String.valueOf(charArray);
    }
}
