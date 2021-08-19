/***
* LintCode 806. Buy Fruits
* Xiao Ming is going to help companies buy fruit.
Give a codeList, which is loaded with the fruit he bought.
Give a shoppingCart, which is loaded with target fruit.
We need to check if the order in the codeList matches the order in the shoppingCart.

Note that only the sum of the items in all linked lists in the codeList add up to
less than or equal to the sum of items in the shoppingcart may return 1.

In addition, the item in codeList may be "anything", which can match with any fruit.

Note:
    The number of fruits in codeList and the number of fruits in shppingCart are both less than 2000.

Example 1:
    Input:  codeList = [["apple", "apple"],["orange", "banana", "orange"]],
            shoppingCart = ["orange", "apple", "apple", "orange", "banana", "orange"]
    Output:  1
    Explanation: Because the order in the codeList matches the fruit in the shoppingCart except for the first orange.

Example 2:
    Input:  codeList = [["orange", "banana", "orange"],["apple", "apple"]],
            shoppingCart = ["orange", "apple", "apple", "orange", "banana", "orange"]
    Output:  0
    Explanation: Because the order in the codeList doesn't match the shoppingCart.

Example 3:
    Input:  codeList = [["apple", "apple"],["orange", "anything", "orange"]],
            shoppingCart = ["orange", "apple", "apple", "orange", "mango", "orange"]
    Output:  1
    Explanation: anything matches mango, so codeList can match the fruit of shoppingCart.

***/
//two pointers, time-complexity: O(m*n*l)[m*n = codeList size; l = shopping-cart size]
public class Solution {
    /**
     * @param codeList: The codeList
     * @param shoppingCart: The shoppingCart
     * @return: The answer
     */
    public int buyFruits(List<List<String>> codeList, List<String> shoppingCart) {
        int result = 0;
        // check corner cases
        if (codeList == null || codeList.isEmpty() || shoppingCart == null || shoppingCart.isEmpty()) {
            return result;
        }

        List<String> codes = new ArrayList<>();
        for (List<String> codeListItem : codeList) {
            codes.addAll(codeListItem);
        }

        int codeSize = codes.size();
        int cartSize = shoppingCart.size();

        // check corner case
        if (cartSize < codeSize) {
            return result;
        }

        // normal case
        for (int i = 0; i < cartSize - codeSize + 1; i++) {
            int j = 0;

            while (j < codeSize &&
                    (codes.get(j).equals(shoppingCart.get(i + j)) ||
                    "anything".equals(codes.get(j)))) {
                j++;
            }

            if (j == codeSize) {
                result = 1;
                break;
            }
        }

        return result;
    }
}
