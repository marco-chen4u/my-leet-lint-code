/***
*LeetCode 71. Simplify Path
Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.

In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level.

Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.

Example 1:
    Input: "/home/"
    Output: "/home"
    Explanation: Note that there is no trailing slash after the last directory name.

Example 2:
    Input: "/../"
    Output: "/"
    Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.

Example 3:
    Input: "/home//foo/"
    Output: "/home/foo"
    Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.

Example 4:
    Input: "/a/./b/../../c/"
    Output: "/c"

Example 5:
    Input: "/a/../../b/../c//.//"
    Output: "/c"

Example 6:
    Input: "/a//b////c/d//././/.."
    Output: "/a/b/c"

Link: https://leetcode.com/problems/simplify-path/
***/
//version-1
public class Solution {
    private final String SEPARATOR = "/";
    
    /**
     * @param path: the original path
     * @return: the simplified path
     */
    public String simplifyPath(String path) {
        // check corner case
        if (path == null || path.length() == 0) {
            return path;
        }
        
        String[] tokens = path.split(SEPARATOR);
        Stack<String> stack = new Stack<String>();
        
        for (String token : tokens) {
            switch(token) {
                case "":
                    break;

                case ".":
                    break;

                case "..":
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                    
                    break;

                default:
                    stack.push(token);
                    break;
            }// end of switch
        }// end for token
        
        if (stack.isEmpty()) {
            return SEPARATOR;
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
            sb.insert(0, SEPARATOR);
        }
        
        return sb.toString();
    }
}

//version-2
public class Solution {
    private final String SEPARATOR = "/";
    /**
     * @param path: the original path
     * @return: the simplified path
     */
    public String simplifyPath(String path) {
        // check corner case
        if (isEmpty(path)) {
            return path;
        }

        // regular case
        String[] tokens = path.split(SEPARATOR);
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if (isEmpty(token)) {
                continue;
            }

            if (".".equals(token)) {
                continue;
            }

            if ("..".equals(token)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            }

            stack.push(token);
        }

        // check corner case
        if (stack.isEmpty()) {
            return SEPARATOR;
        }

        // regular case
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            String fileName = SEPARATOR + stack.pop();
            sb.insert(0, fileName);
        }

        return sb.toString();
    }

    //helper emthods
    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
