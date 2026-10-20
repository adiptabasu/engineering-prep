
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 71: Simplify Path
 *
 * Problem:
 * https://leetcode.com/problems/simplify-path/
 *
 * Approach: Stack
 *
 * 1. Split the absolute path into directory components.
 * 2. Ignore empty components and ".".
 * 3. For "..", pop the most recent directory if one exists.
 * 4. Push ordinary directory names onto the stack.
 * 5. Pop the stack to construct the canonical path using StringBuilder.
 *
 * Time Complexity: O(n), where n is the length of the input path.
 * Each character is processed a constant number of times.
 *
 * Space Complexity: O(n) for the split components and stack.
 */
public class SimplifyPath {

    public String simplifyPath(String path) {
        // Split the absolute path into directory components.
        String[] dirs = path.split("/");

        Deque<String> stack = new ArrayDeque<>();

        for (String dir : dirs) {

            // Ignore empty components and the current directory.
            if (dir.isEmpty() || ".".equals(dir)) {
                continue;
            }

            // Move to the parent directory when possible.
            if ("..".equals(dir)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                // Store an ordinary directory name.
                stack.push(dir);
            }
        }

        // Pop directories in reverse order and prepend them
        // to reconstruct the canonical path.
        StringBuilder outputPath = new StringBuilder();

        while (!stack.isEmpty()) {
            outputPath.insert(0, "/" + stack.pop());
        }

        // An empty result represents the root directory.
        return outputPath.length() == 0 ? "/" : outputPath.toString();
    }

    public static void main(String[] args) {
        SimplifyPath solution = new SimplifyPath();

        System.out.println(
            solution.simplifyPath("/home/")
        ); // Expected: "/home"

        System.out.println(
            solution.simplifyPath("/a/./b/../../c/")
        ); // Expected: "/c"

        System.out.println(
            solution.simplifyPath("/x/../../y")
        ); // Expected: "/y"

        System.out.println(
            solution.simplifyPath("/../")
        ); // Expected: "/"

        System.out.println(
            solution.simplifyPath("/a//b////c/d//././/..")
        ); // Expected: "/a/b/c"
    }
}

