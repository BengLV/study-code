package 算法.栈;

import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

/**
 * @author lupeng
 */
public class StackClass {

    /**
     * 20. 有效的括号
     * https://leetcode-cn.com/problems/valid-parentheses/
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * <p>
     * 示例 1：
     * 输入：s = "()"
     * 输出：true
     * <p>
     * 示例2：
     * 输入：s = "()[]{}"
     * 输出：true
     * <p>
     * 示例3：
     * 输入：s = "(]"
     * 输出：false
     * <p>
     * 示例4：
     * 输入：s = "([)]"
     * 输出：false
     * <p>
     * 示例5：
     * 输入：s = "{[]}"
     * 输出：true
     */
    public static boolean isValid(String s) {
        //法一:
        /*int len = s.length() / 2;
        for (int i = 0; i < len; i++) {
            s = s.replace("[]", "").replace("{}", "").replace("()", "");
        }*/
        //return s.equals("")  ? true : false;
        //法二:
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if (stack.isEmpty() || c != stack.pop()) return false;
        }
        return stack.isEmpty();

    }

    /**
     * 1047. 删除字符串中的所有相邻重复项
     * https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/
     *
     * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
     *
     * 在 S 上反复执行重复项删除操作，直到无法继续删除。
     *
     * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
     *
     * 示例：
     *
     * 输入："abbaca"
     * 输出："ca"
     * 解释：例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。
     * 之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"
     *
     */
    public static String removeDuplicates(String s) {
        char[] sChar = s.toCharArray();
        Stack<Character> stack = new Stack();
        for (int i = 0; i < sChar.length; i++) {
            if (!stack.isEmpty()) {
                boolean equal = Objects.equals(stack.peek(), sChar[i]);
                if (equal) {
                    stack.pop();
                } else {
                    stack.push(sChar[i]);
                }
            } else {
                stack.push(sChar[i]);
            }
        }
        String result = Arrays.toString(stack.toArray());
        result = result.replace(", ", "").replace("[", "").replace("]", "");
        return result;
    }

    /**
     * 150. 逆波兰表达式求值
     * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
     *
     * 逆波兰表达式：
     * 逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。
     *
     * 平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
     * 该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。
     * 逆波兰表达式主要有以下两个优点：
     *
     * 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
     * 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
     *
     * 根据 逆波兰表示法，求表达式的值。
     * 有效的算符包括+、-、*、/。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     * 说明：
     * 整数除法只保留整数部分。
     * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
     *
     * 示例1：
     * 输入：tokens = ["2","1","+","3","*"]
     * 输出：9
     * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
     *
     * 示例2：
     * 输入：tokens = ["4","13","5","/","+"]
     * 输出：6
     * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
     *
     * 示例3：
     * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
     * 输出：22
     * 解释：
     * 该算式转化为常见的中缀算术表达式为：
     *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     * = ((10 * (6 / (12 * -11))) + 17) + 5
     * = ((10 * (6 / -132)) + 17) + 5
     * = ((10 * 0) + 17) + 5
     * = (0 + 17) + 5
     * = 17 + 5
     * = 22
     *
     */
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i];
            if (isNumber(s)) {
                stack.push(Integer.parseInt(s));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (s) {
                    case "+" :
                        stack.push(num1 + num2);
                        break;
                    case "-" :
                        stack.push(num1 - num2);
                        break;
                    case "*" :
                        stack.push(num1 * num2);
                        break;
                    case "/" :
                        stack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }
        return stack.pop();
    }

    public static boolean isNumber(String s) {
        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isValid("{[]}"));
        System.out.println(removeDuplicates("abbaca"));
    }

}
