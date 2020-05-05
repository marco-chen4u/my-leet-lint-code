/***
* LintCode 367. Expression Tree Build
The structure of Expression Tree is a binary tree to evaluate certain expressions.
All leaves of the Expression Tree have an number string value. 
All non-leaves of the Expression Tree have an operator string value.

Now, given an expression array, build the expression tree of this expression, return the root of this expression tree.

Clarification
See wiki:
	Expression Tree(url: https://en.wikipedia.org/wiki/Binary_expression_tree)
resource : 
	https://www.youtube.com/watch?v=_LxbhLNRZkI

Example 
	Example 1:
		Input: ["2","*","6","-","(","23","+","7",")","/","(","1","+","2",")"]
		Output: {[-],[*],[/],[2],[6],[+],[+],#,#,#,#,[23],[7],[1],[2]}
		Explanation:
			The expression tree will be like

								 [ - ]
							 /          \
						[ * ]              [ / ]
					  /     \           /         \
					[ 2 ]  [ 6 ]      [ + ]        [ + ]
									 /    \       /      \
								   [ 23 ][ 7 ] [ 1 ]   [ 2 ] .

			After building the tree, you just need to return root node `[-]`. 
	Example 2:
		Input: ["10","+","(","3","-","5",")","+","7","*","7","-","2"]
		Output: {[-],[+],[2],[+],[*],#,#,[10],[-],[7],[7],#,#,[3],[5]} 
		Explanation:
			The expression tree will be like
									   [ - ]
								   /          \
							   [ + ]          [ 2 ]
						   /          \      
					   [ + ]          [ * ]
						  /     \         /     \
				  [ 10 ]  [ - ]    [ 7 ]   [ 7 ]
						  /    \ 
							[3]    [5]
			After building the tree, you just need to return root node `[-]`.
***/
/**
 * Definition of ExpressionTreeNode:
 * public class ExpressionTreeNode {
 *     public String symbol;
 *     public ExpressionTreeNode left, right;
 *     public ExpressionTreeNode(String symbol) {
 *         this.symbol = symbol;
 *         this.left = this.right = null;
 *     }
 * }
 */

class TreeNode {
	// fields
	int val;
	ExpressionTreeNode eNode;
	
	// constructors
	public TreeNode(int val, ExpressionTreeNode node) {
		this.val = val;
		this.eNode = node;
	}
	
	public TreeNode(int val, String str) {
		this.val = val;
		this.eNode = new ExpressionTreeNode(str);
	}
}

public class Solution {
    /*
     * @param expression: A string array
     * @return: The root of expression tree
     */
    public ExpressionTreeNode build(String[] expression) {
        // check corner cases
		if (expression == null || expression.length == 0) {
			return null;
		}
		
		Stack<TreeNode> stack = new Stack<TreeNode>();//decreamental monotonous stack
		
		int base = 0;
		int val = 0;
		
		for (String current : expression) {
			if (isLeftBracket(current)) {
				base += 10;
				continue;
			}
			
			if (isRightBracket(current)) {
				base -= 10;
				continue;
			}
			
			val = getWeight(base, current);
			TreeNode node = new TreeNode(val, current);
			
			while (!stack.isEmpty && node.val <= stack.peek().val) {
				node.eNode.left = stack.pop().eNode;
			}
			
			if (!stack.isEmpty()) {
				stack.peek().eNode.right = node.eNode;
			}
			
			stack.push(node);
		}
		
		if (stack.isEmpty()) {
			return null;
		}
		
		TreeNode result = stack.pop();
		while (!stack.isEmpty()) {
			result = stack.pop();
		}
		
		return result.eNode;
    }
	
	// helper methods
	private boolean isLeftBracket(String str) {
		return "(".equals(str);
	}
	
	private boolean isRightBracket(String str) {
		return ")".equals(str);
	}
	
	private int getWeight(int base, String str) {
		int value = 0;
		switch (str) {
			case "*":
			case "/":
				value = base + 2;
				break;
			case "+":
			case "-":
				base = base + 1;
				break;	
			default:
				return Integer.MAX_VALUE;
		}
		
		return value;
	}
}