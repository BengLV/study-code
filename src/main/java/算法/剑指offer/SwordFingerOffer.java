package 算法.剑指offer;

import 算法.二叉树.TreeNode;
import 算法.链表.ListNode;

import java.util.*;

/**
 * @description: 剑指offer
 * @author: LuPeng
 * @create: 2022-06-27
 **/
public class SwordFingerOffer {


    /**
     * 剑指 Offer 03. 数组中重复的数字
     * https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
     * <p>
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     * 示例 1：
     * 输入：
     * [2, 3, 1, 0, 2, 5, 3]
     * 输出：2 或 3
     */
    // //方法1：排序，时间O(nlogn)，空间O(logn)，修改了原数据
    // public int findRepeatNumber(int[] nums) {
    //     Arrays.sort(nums);
    //     for(int i = 0 ; i < nums.length-1 ;i++){
    //         if(nums[i]==nums[i+1]) return nums[i];
    //     }
    //     return -1;
    // }

    //方法2：hash表，时间O(n)，空间O(n)，不修改原数据
    // public int findRepeatNumber(int[] nums) {
    //     HashSet<Integer> set = new HashSet<>();
    //     for(int num:nums){
    //         if(set.contains(num)) return num;
    //         set.add(num);
    //     }
    //     return -1;
    // }

    // //方法3：利用辅助数组，与方法2类似，时间O(n)，空间O(n)，不修改原数据
    // public int findRepeatNumber(int[] nums) {
    //     boolean[] isExist = new boolean[nums.length];
    //     for(int num : nums){
    //         if(isExist[num]) return num;
    //         isExist[num] = true;
    //     }
    //     return -1;
    // }

    // //方法4：利用索引与数字的关系，时间O(n)，空间O(1)，修改了原数据
    public int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        for (int i = 0; i < nums.length; i++) {
            //如果该数字没有不和他的索引相等
            while (nums[i] != i) {
                //重复返回
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                //不重复交换
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }
        return -1;
    }

    // //方法5：对0到n-1进行二分查找，时间O(nlogn)，空间O(1)，不修改原数据，用时间换空间
    //该方法需要数字一定有重复的才行，因此如果题目修改在长度为n，数字在1到n-1的情况下，此时数组中至少有一个数字是重复的，该方法可以通过。
    // public int findRepeatNumber(int[] nums) {
    //     //统计nums中元素位于0到m的数量，如果数量大于这个值，那么重复的元素肯定是位于0到m的
    //     int min = 0 ;
    //     int max = nums.length-1;
    //     while(min<max){
    //         int mid = (max+min)>>1;
    //         int count = countRange(nums,min,mid);
    //         if(count > mid-min+1) {
    //             max = mid;
    //         }else{
    //             min = mid+1;
    //         }
    //     }
    //     最后min=max
    //     return min;
    // }


    /**
     * 剑指 Offer 04. 二维数组中的查找
     * https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
     * <p>
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * <p>
     * 示例:
     * 现有矩阵 matrix 如下：
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * 给定 target=5，返回true。
     * 给定target=20，返回false。
     * <p>
     * 从右上角开始走，利用这个顺序关系可以在O(m+n)的复杂度下解决这个题：
     * <p>
     * 如果当前位置元素比target小，则往下走
     * 如果当前位置元素比target大，则往左走
     * 如果相等，返回true
     * 如果越界了还没找到，说明不存在，返回false
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int n = matrix[0].length;
        int m = matrix.length;
        int x = n - 1;
        int y = 0;
        while (x >= 0 && y < m) {
            if (target < matrix[y][x]) {
                x--;
            } else if (target > matrix[y][x]) {
                y++;
            } else {
                return true;
            }
        }
        return false;
    }


    /**
     * 剑指 Offer 05. 替换空格
     * https://leetcode.cn/problems/ti-huan-kong-ge-lcof/
     * <p>
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * <p>
     * 示例 1：
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     */
    public String replaceSpace(String s) {
        //return s.replaceAll(" ", "%20");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                builder.append("%20");
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }


    /**
     * 剑指 Offer 06. 从尾到头打印链表
     * https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
     * <p>
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     * <p>
     * 示例 1：
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     */
    public int[] reversePrint(ListNode head) {
        //先反转链表,并且获得数组长度
        ListNode temp = head;
        ListNode prev = null;
        int size = 0;
        while (temp != null) {
            ListNode next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
            size++;
        }
        temp = prev;
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = temp.data;
            temp = temp.next;
        }
        return res;
    }


    /**
     * 剑指 Offer 07. 重建二叉树
     * https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/
     * <p>
     * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * <p>
     * 示例 1:
     * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * Output: [3,9,20,null,null,15,7]
     */
    private HashMap<Integer, Integer> map = new HashMap<>();
    private int[] pre;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        pre = preorder;
        TreeNode node = buildTree(0, inorder.length - 1, 0, preorder.length);
        return node;
    }

    private TreeNode buildTree(int inorderS, int inorderE, int preorderS, int preorderE) {
        if (inorderS > inorderE || preorderS > preorderE) return null;
        int root = pre[preorderS];
        int rootIndex = map.get(root);
        TreeNode node = new TreeNode(root);
        node.left = buildTree(inorderS, rootIndex - 1, preorderS + 1, rootIndex - inorderS + preorderS);
        node.right = buildTree(rootIndex + 1, inorderE, rootIndex - inorderS + preorderS + 1, preorderE);
        return node;
    }

    /**
     * 剑指 Offer 09. 用两个栈实现队列
     * https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
     * <p>
     * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
     * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead操作返回 -1 )
     * <p>
     * 示例 1：
     * 输入：
     * ["CQueue","appendTail","deleteHead","deleteHead"]
     * [[],[3],[],[]]
     * 输出：[null,null,3,-1]
     */
    class CQueue {
        LinkedList<Integer> list1;
        LinkedList<Integer> list2;

        public CQueue() {
            list1 = new LinkedList<>();
            list2 = new LinkedList<>();
        }

        public void appendTail(int value) {
            list1.add(value);
        }

        public int deleteHead() {
            if (list2.isEmpty()) {
                if (list1.isEmpty()) {
                    return -1;
                }
                while (!list1.isEmpty()) {
                    list2.add(list1.pop());
                }
                return list2.pop();
            }
            return list2.pop();
        }
    }

    /**
     * 剑指 Offer 10- I. 斐波那契数列
     * https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/
     * <p>
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
     * F(0) = 0, F(1)= 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     * <p>
     * 示例 1：
     * 输入：n = 5
     * 输出：5
     * <p>
     * 类型:动态规划
     */
    public int fib(int n) {
        if (n < 2) return n;
        int res = 0;
        int a = 0;
        int b = 1;
        for (int i = 1; i < n; i++) {
            res = a + b;
            if (res > 1000000007) {
                res -= 1000000007;
            }
            a = b;
            b = res;
        }
        return res;
    }

    /**
     * 剑指 Offer 10- II. 青蛙跳台阶问题
     * https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/
     * <p>
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n级的台阶总共有多少种跳法。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     * <p>
     * 示例 1：
     * 输入：n = 7
     * 输出：21
     */
    public int numWays(int n) {
        if (n == 0) return 1;
        if (n < 2) return n;
        int a = 1, b = 2, res = 0;
        for (int i = 3; i <= n; i++) {
            res = a + b;
            if (res > 1000000007) {
                res -= 1000000007;
            }
            a = b;
            b = res;
        }
        return res;
    }


    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     * https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
     * <p>
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * <p>
     * 给你一个可能存在重复元素值的数组numbers，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。
     * 例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。
     * <p>
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     * <p>
     * 有序数组:二分法
     */
    public static int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            //二分法
            int mid = (left + right) / 2;
            //中间的值大于最右边的
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;//既然小于最右边的了,就说明不是mid下标的值
                //中间值小于最右边的
            } else if (numbers[mid] < numbers[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return numbers[left];
    }


    /**
     * 剑指 Offer 12. 矩阵中的路径
     * https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof/
     * <p>
     * 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     * <p>
     * 示例 1：
     * <p>
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
     * 输出：true
     * <p>
     * 解题思路: DFS + 回溯
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        char[] chars = word.toCharArray();
        //使用二维数组记录当前的位置是否已经被访问过,如果已经被访问过了,直接返回false,说明此路不通.
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, chars, visited, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] chars, boolean[][] visited, int i, int j, int start) {
        //路不通的场景.
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || chars[start] != board[i][j] || visited[i][j]) {
            return false;
        }
        //如果能访问到最后一个字符,说明路是通的
        if (start == chars.length - 1) {
            return true;
        }
        //遍历到i,j位置时, 表明访问过
        visited[i][j] = true;
        boolean ans = false;
        ans = dfs(board, chars, visited, i + 1, j, start + 1)
                || dfs(board, chars, visited, i - 1, j, start + 1)
                || dfs(board, chars, visited, i, j + 1, start + 1)
                || dfs(board, chars, visited, i, j - 1, start + 1);
        //回溯,如果某条路已经不通了,则回溯.
        visited[i][j] = false;
        return ans;
    }


    /**
     * 剑指 Offer 13. 机器人的运动范围
     * https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
     * <p>
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
     * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     * <p>
     * 示例 1：
     * 输入：m = 2, n = 3, k = 1
     * 输出：3
     */
    public int movingCount(int m, int n, int k) {
        //判断是否走过走过某个节点
        boolean[][] visited = new boolean[m][n];
        return dfs(m, n, k, 0, 0, visited);
    }

    private int dfs(int m, int n, int k, int i, int j, boolean[][] visited) {
        //终止条件,返回0
        if (i >= m || i < 0 || j >= n || j < 0 || (i / 10 + i % 10 + j / 10 + j % 10) > k || visited[i][j]) {
            return 0;
        }
        //标记节点走过
        visited[i][j] = true;
        return 1 + dfs(m, n, k, i - 1, j, visited) + dfs(m, n, k, i + 1, j, visited)
                + dfs(m, n, k, i, j - 1, visited) + dfs(m, n, k, i, j + 1, visited);
    }


    /**
     * 剑指 Offer 14- I. 剪绳子
     * https://leetcode.cn/problems/jian-sheng-zi-lcof/
     * <p>
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
     * 请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     * <p>
     * 示例 1:
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
     * <p>
     * 动态五部曲
     * 1.确定dp下标及其含义
     * 2.确定递推公式
     * 3.确定dp初始化
     * 4.确定遍历顺序
     * 5.打印dp
     */
    public int cuttingRope(int n) {
        //1. 确定dp数组以及下标的含义。 最大乘积。
        int[] dp = new int[n + 1];
        //3. dp数组初始化。
        dp[2] = 1;
        //4. 确定遍历顺序。
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                //2. 确定递推公式。
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), dp[i - j] * j));
            }
        }
        return dp[n];
    }


    /**
     * 剑指 Offer 14- II. 剪绳子 II
     * https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/
     * <p>
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。
     * 请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     * <p>
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     */
    public int cuttingRope2(int n) {
        //首先排除特殊情况
        if (n == 1) return 1;
        if (n == 2) return 1;
        if (n == 3) return 2;
        long res = 1;
        while (n > 4) {
            //包含3最多,结果最优
            res *= 3;
            //先对结果取模.
            res = res % 1000000007;
            n -= 3;
        }
        //可能剩余1,2,3. 乘以res即可.
        return (int) (res * n % 1000000007);
    }


    /**
     * 剑指 Offer 15. 二进制中1的个数
     * https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
     * <p>
     * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 11 (控制台输入 00000000000000000000000000001011)
     * 输出：3
     * 解释：输入的二进制串 00000000000000000000000000001011中，共有三位为 '1'。
     * <p>
     * 位运算
     */
    public static int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            /**
             * A = 0011 1100
             * B = 0000 1101
             * ＆如果相对应位都是1，则结果为1，否则为0
             * （A＆B），得到12，即0000 1100
             */
            //消去数字n转为二进制后最右边的 1
            n &= n - 1;
        }
        return res;
    }


    /**
     * 剑指 Offer 16. 数值的整数次方
     * https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
     * <p>
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
     * <p>
     * 示例 1：
     * 输入：x = 2.00000, n = 10
     * 输出：1024.00000
     */
    public double myPow(double x, int n) {
        /*if (n == 0) return 1;
        if (n == -1) return 1/x;
        if (n == 1) return x;
        double half = myPow(x, n/2);
        double mod = myPow(x, n%2);
        return half * half * mod;*/
        //防止n越界
        long b = n;
        if (b < 0) {
            //负数的话需要转换一下
            b = -b;
            x = 1 / x;
        }
        double res = 1.00;
        while (b > 0) {
            if ((b & 1) == 1) {
                //b &1 == 1 b为奇数  ==0 为偶数
                res *= x;
            }
            x *= x;
            b = b >> 1;//b除以2
        }
        return res;
    }

    /**
     * 剑指 Offer 17. 打印从1到最大的n位数
     * https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
     * <p>
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     * <p>
     * 示例 1:
     * 输入: n = 1
     * 输出: [1,2,3,4,5,6,7,8,9]
     */
    public static int[] printNumbers(int n) {
        //10的n次方
        int length = (int) Math.pow(10, n);
        int[] res = new int[length - 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = i + 1;
        }
        return res;
    }

    static List<String> res = new ArrayList<>();

    public static List<String> printNumbers1(int n) {
        for (int i = 1; i <= n; i++) {
            trackBack(i, new StringBuilder());
        }
        return res;
    }

    public static void trackBack(int n, StringBuilder builder) {
        if (n == 0) {
            res.add(builder.toString());
            return;
        }
        for (int i = 0; i <= 9; i++) {
            //去除前面的0
            if (i == 0 && builder.length() == 0) continue;
            builder.append(i);
            //按照位数回溯
            trackBack(n - 1, builder);
            builder.delete(builder.length() - 1, builder.length());
        }
    }


    /**
     * 剑指 Offer 18. 删除链表的节点
     * https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/
     * <p>
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     * 返回删除后的链表的头节点。
     * <p>
     * 示例 1:
     * <p>
     * 输入: head = [4,5,1,9], val = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为5的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     */
    public ListNode deleteNode(ListNode head, int val) {
        /*//递归
        if (head == null) return null;
        if (head.data == val) {
            return head.next;
        }
        head.next = deleteNode(head.next, val);
        return head;*/
        if (head == null) return null;
        ListNode temp = head;
        ListNode pre = null;
        if (temp.data == val) return temp.next;
        while (temp.next != null && temp.data != val) {
            //获取到需要删除节点的头节点.
            pre = temp;
            temp = temp.next;
        }
        if (temp.next != null || temp.data == val) {
            //修改节点的引用
            pre.next = pre.next.next;
        }
        return head;
    }


    /**
     * 剑指 Offer 19. 正则表达式匹配
     * https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof/
     * <p>
     * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
     * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
     */
    public boolean isMatch(String s, String p) {
        /*
        dp五部曲:(参考最高赞的思路)
        设原始串s的长度为m,模式串p的长度为n
        注意定义:'*'表示它前面的(一个)字符可以出现任意次（含0次）!注意是一个
        1.状态定义:设dp[i][j]为考虑s[0,i-1],p[0,j-1]时,是否能匹配上,匹配上就为true
        2.状态转移:
            2.1 p[j-1]为非'*'
                2.1.1 若p[j-1]==s[i-1](必定为'a'-'z'),继续看dp[i-1][j-1]
                2.1.2 若p[j-1]为'.',直接看dp[i-1][j-1]
            2.2 p[j-1]为'*'
                2.2.1 若p[j-2]==s[i-1](必定为'a'-'z'),则继续向前看dp[i-1][j]
                    因为对于p[0,j-1]来说,s[i-1]是冗余匹配可以由p[j-2]*补充
                2.2.2 p[j-2]为'.',则'.'匹配了s[i-1],可以继续往前看dp[i-1][j]
                    注意这里是".*"的情形,也就是"万能串",生成"......"可以匹配任何非空s
                2.2.3 此时p[j-2]为'a'-'z',且p[j-2]!=s[i-1],"p[j-2]*"直接废掉,看dp[i][j-2]
            其中2.1.1和2.1.2可以合并为一种情形;2.2.1和2.2.2可以合并为一种情形
        3.初始化:
            3.1 空的p
                3.1.1 可以匹配空的s,dp[0][0]=true
                3.1.2 不可以匹配非空的s,dp[i][0]=false,i∈[1,m-1]
            3.2 空的s
                3.2.1 可以匹配空的s,dp[0][0]=true
                3.2.2 可能可以匹配非空的p,要经过计算如"a*b*c*"
            3.3 非空的p与非空的s,要经过计算
        4.遍历顺序:显然是正序遍历
        5.返回形式:直接返回dp[m][n]就表示考虑s[0,m-1],j[0,n-1]是否能匹配上
        */
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // 空的p的情形
                if (j == 0) {
                    // dp[0][0]=true;其余为false
                    dp[i][j] = i == 0;
                } else {
                    // 非空的p都是要经过计算的
                    if (p.charAt(j - 1) != '*') {
                        // 1.p[j-1]!='*'的情形(2.1)
                        // 此时j>=1
                        if (i >= 1 && (s.charAt(i - 1) == p.charAt(j - 1) ||
                                p.charAt(j - 1) == '.')) {
                            // p与s都向前一位匹配
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    } else {
                        // 2.p[j-1]=='*'的情形(2.2)
                        // 看"x*"的情形(2.2.1与2.2.2)
                        if (i >= 1 && j >= 2 &&
                                (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')) {
                            // p不动s向前一位
                            dp[i][j] = dp[i - 1][j];
                        }
                        // 不看"x*"的情形(2.2.3)
                        // 此时p[j-2]为'a'-'z',且p[j-2]!=s[i-1],"p[j-2]*"直接废掉
                        if (j >= 2) {
                            // s不变p向前2位
                            dp[i][j] |= dp[i][j - 2]; //等号两边只要有一个是true，结果就是true。只有当两边都是 false 的时候，才是false
                            // 这里用|=的原因是:2.2中满足任意一种情形就可以判定dp[i][j]=true
                        }
                    }
                }
            }
        }
        return dp[m][n];
    }


    /**
     * 剑指 Offer 20. 表示数值的字符串
     * https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
     * <p>
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * <p>
     * 数值（按顺序）可以分成以下几个部分：
     * <p>
     * 若干空格
     * 一个小数或者整数
     * （可选）一个'e'或'E'，后面跟着一个整数
     * 若干空格
     * 小数（按顺序）可以分成以下几个部分：
     * <p>
     * （可选）一个符号字符（'+' 或 '-'）
     * 下述格式之一：
     * 至少一位数字，后面跟着一个点 '.'
     * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
     * 一个点 '.' ，后面跟着至少一位数字
     * 整数（按顺序）可以分成以下几个部分：
     * <p>
     * （可选）一个符号字符（'+' 或 '-'）
     * 至少一位数字
     * 部分数值列举如下：
     * <p>
     * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
     * 部分非数值列举如下：
     * <p>
     * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
     * <p>
     * 示例 1：
     * 输入：s = "0"
     * 输出：true
     * <p>
     * 示例 2：
     * 输入：s = "."
     * 输出：false
     * <p>
     * 示例 3：
     * 输入：s = "    .1  "
     * 输出：true
     */
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;
        s = s.trim();//去除空格
        boolean numFlag = false;//是否为数字,返回结果
        boolean dotFlag = false;//是否出现.
        boolean eFlag = false;//是否出现e
        for (int i = 0; i < s.length(); i++) {
            //是否为数字
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                numFlag = true;
                //是否为.,需要之前没出现过. 并且没出现e
            } else if (s.charAt(i) == '.' && !dotFlag && !eFlag) {
                dotFlag = true;
                //是否为e,需要之前没出现过e,并且出现过数字了
            } else if ((s.charAt(i) == 'e' || s.charAt(i) == 'E') && !eFlag && numFlag) {
                eFlag = true;
                numFlag = false;//防止出现123e这种情况,出现e就标记为false.
                //是否为+ -符号,只能出现在第一位或e后面
            } else if ((s.charAt(i) == '+' || s.charAt(i) == '-') && (i == 0 || s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E')) {
                //不做任何操作.
            } else {
                //其他情况都是非法的
                return false;
            }
        }
        return numFlag;
    }


    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     * https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
     * <p>
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
     * <p>
     * 示例：
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,2,4]
     * 注：[3,1,2,4] 也是正确的答案之一。
     */
    public static int[] exchange(int[] nums) {
        /*额外数组
        int[] res = new int[nums.length];
        int pre = 0;//记录偶数出现的次数
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 1) {//是否为奇数
                res[i - pre] = nums[i];
            } else {
                res[nums.length - pre - 1] = nums[i];
                pre++;
            }

        }
        return res;
        */
        /*首尾指针
        int right = nums.length - 1;
        int left = 0;
        while (left < right) {
            if ((nums[left] & 1) == 1) {//为奇数
                left++;
                continue;
            }
            if ((nums[right] & 1) == 0) {//为偶数
                right--;
                continue;
            }
            //交换
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
        return nums;*/
        int fast = 0;//快指针
        int low = 0;//慢指针
        while (fast < nums.length) {
            if ((nums[fast] & 1) == 1) {
                int temp = nums[low];
                nums[low] = nums[fast];
                nums[fast] = temp;
                low++;
            }
            fast++;
        }
        return nums;
    }

    public int[] trainingPlan(int[] actions) {
        if (actions == null || actions.length == 0) {
            return actions;
        }
        int left = 0, right = actions.length - 1;
        while (left < right) {
            if (actions[left] % 2 == 0) {
                //交换后面的数字
                while (right > left && actions[right] % 2 == 0) {
                    right--;
                }
                if (right > left) {
                    int temp = actions[left];
                    actions[left] = actions[right];
                    actions[right] = temp;
                }
            }
            left++;
        }
        return actions;

    }

    /**
     * 剑指 Offer 22. 链表中倒数第k个节点
     * https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
     * <p>
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
     * <p>
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 k = 2.
     * 返回链表 4->5.
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
       /* ListNode temp = head;
        int count = 0;
        while (temp != null) {
            //先遍历获取count
            count++;
            temp = temp.next;
        }
        temp = head;
        int j = 1;
        while (temp != null) {
            if (j == count - k + 1) {
                return temp;
            }
            temp = temp.next;
            j++;
        }
        return null;*/
        ListNode fast = head, low = head;//快慢指针
        for (int i = 0; i < k; i++) {//快指针先走k步试试
            fast = fast.next;
        }
        while (fast != null) {//快指针比慢指针多走k步.快指针走完时,慢指针还剩k步没有走.返回即可.
            fast = fast.next;
            low = low.next;
        }
        return low;
    }


    /**
     * 剑指 Offer 24. 反转链表
     * https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/submissions/
     * <p>
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     * <p>
     * 示例:
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */
    public ListNode reverseList(ListNode head) {
        ListNode temp = head;
        ListNode pre = null;
        while (temp != null) {
            ListNode node = temp.next;
            temp.next = pre;
            pre = temp;
            temp = node;
        }
        return pre;
    }



    public ListNode reverseList1(ListNode head) {
        return reverse(null, head);
    }

    private ListNode reverse(ListNode prev, ListNode cur) {
        if (cur == null) {
            return prev;
        }
        ListNode temp = cur.next;
        cur.next = prev;
        return reverse(cur, temp);
    }


    /**
     * 剑指 Offer 25. 合并两个排序的链表
     * https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
     * <p>
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
     * <p>
     * 示例1：
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        /**
         * ListNode res = new ListNode(-1);
         *         ListNode temp = res;
         *         while (l1 != null && l2 != null) {
         *             if (l1.val <= l2.val) {
         *                 temp.next = l1;
         *                 l1 = l1.next;
         *             } else {
         *                 temp.next = l2;
         *                 l2 = l2.next;
         *             }
         *             temp = temp.next;
         *         }
         *         temp.next = l1 != null ? l1 : l2;
         *         return res.next;
         */
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.data <= l2.data) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }


    /**
     * 剑指 Offer 26. 树的子结构
     * https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/
     * <p>
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     * <p>
     * 例如:
     * 给定的树 A:
     * 3
     * / \
     * 4  5
     * / \
     * 1  2
     * <p>
     * 给定的树 B：
     * <p>
     * 4
     * /
     * 1
     * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
     * <p>
     * 示例 1：
     * 输入：A = [1,2,3], B = [3,1]
     * 输出：false
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;
        return dfs(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    public boolean dfs(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null) return false;
        return A.val == B.val && dfs(A.left, B.left) && dfs(A.right, B.right);
    }

    public boolean isSubStructure1(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        if (A.val == B.val && isContain(A, B)) {
            return true;
        }
        return isSubStructure1(A.left, B) || isSubStructure1(A.right, B);
    }

    private boolean isContain(TreeNode node, TreeNode B) {
        if (node == null && B != null) {
            return false;
        }
        if (B == null) {
            return true;
        }
        return node.val == B.val && isContain(node.left, B.left) && isContain(node.right, B.right);
    }


    /**
     * 剑指 Offer 27. 二叉树的镜像
     * https://leetcode.cn/problems/er-cha-shu-de-jing-xiang-lcof/
     * <p>
     * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
     * <p>
     * 例如输入：
     * 4
     * /  \
     * 2   7
     * / \  / \
     * 1  3 6  9
     * <p>
     * 镜像输出：
     * 4
     * /  \
     * 7   2
     * / \  / \
     * 9  6 3 1
     * <p>
     * 示例 1：
     * 输入：root = [4,2,7,1,3,6,9]
     * 输出：[4,7,2,9,6,3,1]
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return root;
        TreeNode temp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(temp);
        return root;
    }


    /**
     * 剑指 Offer 28. 对称的二叉树
     * https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/
     * <p>
     * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     * 1
     * / \
     * 2  2
     * / \ / \
     * 3 4 4 3
     * <p>
     * 但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
     * <p>
     * 1
     * / \
     * 2  2
     * \  \
     * 3  3
     * <p>
     * <p>
     * 示例 1：
     * 输入：root = [1,2,2,3,4,4,3]
     * 输出：true
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        TreeNode node1 = root;
        TreeNode node2 = root;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(node1.left);
        deque.add(node2.right);
        while (!deque.isEmpty()) {
            node1 = deque.poll();
            node2 = deque.poll();
            if (node1 == null && node2 == null) continue;
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            deque.add(node1.left);
            deque.add(node2.right);
            deque.add(node1.right);
            deque.add(node2.left);
        }
        return true;
    }


    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true; // 空树是对称的
        }
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true; // 左右子树都为空，是对称的
        }
        if (left == null || right == null) {
            return false; // 左右子树其中一个为空，不对称
        }
        // 判断当前节点值是否相等，并递归判断左子树的右子树和右子树的左子树，以及左子树的左子树和右子树的右子树是否对称
        return (left.val == right.val) && isMirror(left.right, right.left) && isMirror(left.left, right.right);
    }

    /**
     * 做递归思考三步：
     * <p>
     * 递归的函数要干什么？
     * 函数的作用是判断传入的两个树是否镜像。
     * 输入：TreeNode left, TreeNode right
     * 输出：是：true，不是：false
     * <p>
     * 递归停止的条件是什么？
     * 左节点和右节点都为空 -> 倒底了都长得一样 ->true
     * 左节点为空的时候右节点不为空，或反之 -> 长得不一样-> false
     * 左右节点值不相等 -> 长得不一样 -> false
     * <p>
     * 从某层到下一层的关系是什么？
     * 要想两棵树镜像，那么一棵树左边的左边要和二棵树右边的右边镜像，一棵树左边的右边要和二棵树右边的左边镜像
     * 调用递归函数传入左左和右右
     * 调用递归函数传入左右和右左
     * 只有左左和右右镜像且左右和右左镜像的时候，我们才能说这两棵树是镜像的
     * 调用递归函数，我们想知道它的左右孩子是否镜像，传入的值是root的左孩子和右孩子。这之前记得判个root==null。
     */
    public boolean isSymmetricDfs(TreeNode root) {
        if (root == null) return true;
        return isSymmetricDfs(root.left, root.right);
    }

    public boolean isSymmetricDfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return left.val == right.val && dfs(left.left, right.right) && dfs(left.right, right.left);
    }


    /**
     * 剑指 Offer 29. 顺时针打印矩阵
     * https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
     * <p>
     * 示例 1：
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null) return null;
        int row = matrix.length;
        if (row == 0) return new int[0];
        int col = matrix[0].length;
        int[] res = new int[row * col];
        int idx = 0;
        int left = 0, top = 0, right = col - 1, bottom = row - 1;//边界值, 左上右下
        while (true) {
            //从左往右走
            for (int i = left; i <= right; i++) {
                res[idx++] = matrix[top][i];
            }
            top++;
            if (top > bottom) break;
            //从上往下走
            for (int i = top; i <= bottom; i++) {
                res[idx++] = matrix[i][right];
            }
            right--;
            if (right < left) break;
            //从右往左走
            for (int i = right; i >= left; i--) {
                res[idx++] = matrix[bottom][i];
            }
            bottom--;
            if (bottom < top) break;
            //从下往上走
            for (int i = bottom; i >= top; i--) {
                res[idx++] = matrix[i][left];
            }
            left++;
            if (left > right) break;
        }
        return res;
    }


    public int[] spiralArray(int[][] array) {
        if (array == null) return null;
        int width = array.length;
        if (width == 0) return new int[0];
        int length = array[0].length;

        int left = 0;
        int rigth = length - 1;
        int top = 0;
        int bottom = width - 1;

        int cnt = 0;
        int size = length * width;
        int[] result = new int[size];
        try {
            while (cnt < size) {
                for (int i = left; i <= rigth; i++) {
                    result[cnt++] = array[top][i];
                }
                top++;

                for (int i = top; i <= bottom; i++) {
                    result[cnt++] = array[i][rigth];
                }
                rigth--;

                for (int i = rigth; i >= left; i--) {
                    result[cnt++] = array[bottom][i];
                }
                bottom--;

                for (int i = bottom; i >= top; i--) {
                    result[cnt++] = array[i][left];
                }
                left++;
            }
        } finally {
            return result;
        }
    }


    /**
     * 剑指 Offer 30. 包含min函数的栈
     * https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/
     * <p>
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
     * <p>
     * 示例:
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.min();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.min();   --> 返回 -2.
     */
    class MinStack {

        private Node node;

        public MinStack() {

        }

        //添加
        public void push(int x) {
            if (node == null) {
                node = new Node(x, x, null);
            } else {
                node = new Node(x, Math.min(node.min, x), node);
            }
        }

        //删除最后添加的那个元素
        public void pop() {
            node = node.next;
        }

        public int top() {
            return node.val;
        }

        public int min() {
            return node.min;
        }

        class Node {

            private int val;

            private int min;

            private Node next;

            public Node(int val, int min, Node next) {
                this.min = min;
                this.val = val;
                this.next = next;
            }
        }
    }

    /**
     * 剑指 Offer 31. 栈的压入、弹出序列
     * https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
     * <p>
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。
     * 例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
     * <p>
     * 示例 1：
     * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
     * 输出：true
     * 解释：我们可以按以下顺序执行：
     * push(1), push(2), push(3), push(4), pop() -> 4,
     * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
     * <p>
     * 示例 2：
     * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
     * 输出：false
     * 解释：1 不能在 2 之前弹出。
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int j = 0;
        for (int i : pushed) {
            deque.push(i);
            while (j < popped.length && !deque.isEmpty() && deque.peek() == popped[j]) {
                deque.pop();
                j++;
            }
        }
        return deque.isEmpty();
        //return deque.length == j;
    }


    /**
     * 剑指 Offer 32 - I. 从上到下打印二叉树
     * https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/
     * <p>
     * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
     * <p>
     * 例如:
     * 给定二叉树:[3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * <p>
     * 返回：
     * [3,9,20,15,7]
     */
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[0];
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            TreeNode poll = deque.poll();
            res.add(poll.val);
            if (poll.left != null) {
                deque.add(poll.left);
            }
            if (poll.right != null) {
                deque.add(poll.right);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }


    /**
     * 剑指 Offer 32 - II. 从上到下打印二叉树 II
     * https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/
     * <p>
     * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
     * <p>
     * 例如:
     * 给定二叉树:[3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其层次遍历结果：
     * <p>
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            //获取当前队列大小
            int size = deque.size();
            ArrayList<Integer> temp = new ArrayList<>();
            //弹出当前队列元素
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                temp.add(poll.val);
                if (poll.left != null) {
                    deque.add(poll.left);
                }
                if (poll.right != null) {
                    deque.add(poll.right);
                }
            }
            res.add(temp);
        }
        return res;
    }

    List<List<Integer>> res1 = new ArrayList<>();

    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        levelOrderDFSHelper(root, 0);
        return res1;
    }

    /**
     * @param k 当前遍历的层数
     */
    private void levelOrderDFSHelper(TreeNode root, int k) {
        if (root != null) {
            if (res1.size() <= k) {
                res1.add(new ArrayList<>());
            }
            res1.get(k).add(root.val);
            levelOrderDFSHelper(root.left, k + 1);
            levelOrderDFSHelper(root.right, k + 1);
        }
    }


    /**
     * 剑指 Offer 32 - III. 从上到下打印二叉树 III
     * https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
     * <p>
     * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
     * <p>
     * 例如:
     * 给定二叉树:[3,9,20,null,null,15,7],
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * <p>
     * 返回其层次遍历结果：
     * [
     * [3],
     * [20,9],
     * [15,7]
     * ]
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            LinkedList<Integer> temp = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                //利用双端队列
                if ((res.size() & 1) == 1) {//奇数
                    temp.addFirst(poll.val);
                } else {//偶数
                    temp.addLast(poll.val);
                }
                if (poll.left != null) {
                    deque.add(poll.left);
                }
                if (poll.right != null) {
                    deque.add(poll.right);
                }
            }
            res.add(temp);
        }
        return res;
    }

    List<List<Integer>> res2 = new ArrayList<>();

    public List<List<Integer>> levelOrder2DFS(TreeNode root) {
        levelOrder2DFSHelper(root, 0);
        return res2;
    }

    private void levelOrder2DFSHelper(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (level == res2.size()) {
            res2.add(new ArrayList<>());
        }
        if ((level & 1) == 1) {
            res2.get(level).add(0, node.val);
        } else {
            res2.get(level).add(node.val);
        }
        levelOrder2DFSHelper(node.left, level + 1);
        levelOrder2DFSHelper(node.right, level + 1);
    }


    /**
     * 剑指 Offer 33. 二叉搜索树的后序遍历序列
     * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
     * <p>
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回true，否则返回false。假设输入的数组的任意两个数字都互不相同。
     * <p>
     * 参考以下这颗二叉搜索树：
     * 5
     * / \
     * 2   6
     * / \
     * 1   3
     * <p>
     * 示例 1：
     * 输入: [1,6,3,2,5]
     * 输出: false
     * <p>
     * 示例 2：
     * 输入: [1,3,2,6,5]
     * 输出: true
     */
    public boolean verifyPostorder(int[] postorder) {
        Deque<Integer> deque = new LinkedList<>();
        int temp = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            //左子树元素必须要小于递增栈被peek访问的元素，否则就不是二叉搜索树
            if (postorder[i] > temp) return false;
            while (!deque.isEmpty() && postorder[i] < deque.peek()) {
                //当元素小于栈顶元素值时,说明当前为左子树
                temp = deque.poll();//记录左子树的根节点
            }
            //将当前元素入栈顶
            deque.push(postorder[i]);
        }
        return true;
    }


    public boolean verifyPostorder1(int[] postorder) {
        // 要点：二叉搜索树中根节点的值大于左子树中的任何一个节点的值，小于右子树中任何一个节点的值，子树也是
        return verifyDFS(0, postorder.length - 1, postorder);
    }

    private boolean verifyDFS(int left, int right, int[] postorder) {
        if (left >= right) return true;
        // 当前树的根节点的值
        int mid = postorder[right];
        int k = left;
        // 从当前区域找到第一个大于根节点的，说明后续区域数值都在右子树中
        while (k < right && postorder[k] < mid) {
            k++;
        }
        // 进行判断后续的区域是否所有的值都是大于当前的根节点，如果出现小于的值就直接返回false
        for (int i = k; i < right; i++) {
            if (postorder[i] < mid) return false;
        }
        // 当前树没问题就检查左右子树
        if (!verifyDFS(left, k - 1, postorder)) return false;
        if (!verifyDFS(k, right - 1, postorder)) return false;
        // 最终都没问题就返回true
        return true;
    }


    /**
     * 剑指 Offer 34. 二叉树中和为某一值的路径
     * https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
     * <p>
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     * <p>
     * 叶子节点 是指没有子节点的节点。
     */
    LinkedList<List<Integer>> res3 = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        pathSumDFS(root, target);
        return res3;
    }

    private void pathSumDFS(TreeNode node, int target) {
        if (node == null) return;
        path.add(node.val);
        target -= node.val;
        if (target == 0 && node.left == null && node.right == null) {
            //值得注意的是，记录路径时若直接执行 res.add(path) ，则是将 path 对象加入了 res ；后续 path 改变时， res 中的 path 对象也会随之改变。
            res3.add(new LinkedList<>(path));
        }
        pathSumDFS(node.left, target);
        pathSumDFS(node.right, target);
        path.removeLast();
    }


    /**
     * 剑指 Offer 35. 复杂链表的复制
     * https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/
     * <p>
     * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
     * <p>
     * 示例 1：
     * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
     * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
     */
    public Node copyRandomList(Node head) {
        if (head == null) return head;
        Map<Node, Node> map = new HashMap<>();
        for (Node cur = head; cur != null; cur = cur.next) {
            map.put(cur, new Node(cur.val));
        }
        for (Node cur = head; cur != null; cur = cur.next) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
        }
        return map.get(head);
    }

    public Node copyRandomList1(Node head) {
        if (head == null) return head;
        Node cur = head;
        while (cur != null) {
            Node copy = new Node(cur.val);
            copy.next = cur.next;
            cur.next = copy;
            cur = cur.next.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        Node res = head.next;
        Node copy = head.next;
        cur = head;
        while (cur != null) {
            cur.next = cur.next.next;
            cur = cur.next;
            if (copy.next != null) {
                copy.next = copy.next.next;
                copy = copy.next;
            }
        }
        return res;
    }


    /**
     * 剑指 Offer 36. 二叉搜索树与双向链表
     * https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
     * <p>
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
     * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
     */
    private NodeTree temp, head;

    public NodeTree treeToDoublyList(NodeTree root) {
        if (root == null) return root;
        treeToDoublyListDFS(root);
        head.left = temp;
        temp.right = head;
        return head;
    }

    private void treeToDoublyListDFS(NodeTree nodeTree) {
        if (nodeTree == null) return;
        treeToDoublyListDFS(nodeTree.left);
        if (temp == null) {
            head = nodeTree;
        } else {
            temp.right = nodeTree;
            nodeTree.left = temp;
        }
        temp = nodeTree;
        treeToDoublyListDFS(nodeTree.right);
    }


    /**
     * 剑指 Offer 38. 字符串的排列
     * https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/
     * <p>
     * 输入一个字符串，打印出该字符串中字符的所有排列。
     * <p>
     * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
     * <p>
     * 示例:
     * 输入：s = "abc"
     * 输出：["abc","acb","bac","bca","cab","cba"]
     */
    Set<String> StringSet = new HashSet<>();

    public String[] permutation(String s) {
        char[] arr = s.toCharArray();
        boolean[] visited = new boolean[arr.length];
        dfs(arr, "", visited);
        return StringSet.toArray(new String[0]);
    }

    private void dfs(char[] arr, String s, boolean[] visited) {
        if (s.length() == arr.length) {
            StringSet.add(s);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(arr, s + String.valueOf(arr[i]), visited);
            visited[i] = false;
        }
    }


    /**
     * 剑指 Offer 39. 数组中出现次数超过一半的数字
     * https://leetcode.cn/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/
     * <p>
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * <p>
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * <p>
     * 示例1:
     * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
     * 输出: 2
     */
    public int majorityElement(int[] nums) {
        /*//复杂度O(nlogn)
        Arrays.sort(nums);
        return nums[nums.length/2];*/

        //解法三：摩尔投票法
        //也可以理解成混战极限一换一，不同的两者一旦遇见就同归于尽，最后活下来的值都是相同的，即要求的结果
        //摩尔投票法找的其实不是众数，而是占一半以上的数。当数组没有超过一半的数，则可能返回非众数，比如[1, 1, 2, 2, 2, 3, 3]，最终返回3。
        //投票法简单来说就是不同则抵消，占半数以上的数字必然留到最后。
        //时间O(n)，空间O(1)
        int res = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                res = nums[i];
                count++;
            } else {
                count = nums[i] == res ? count + 1 : count - 1;
            }
        }
        return res;
    }


    /**
     * 剑指 Offer 40. 最小的k个数
     * https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/
     * <p>
     * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     * <p>
     * 示例 1：
     * 输入：arr = [3,2,1], k = 2
     * 输出：[1,2] 或者 [2,1]
     * <p>
     * 示例 2：
     * 输入：arr = [0,1,2,1], k = 1
     * 输出：[0]
     * <p>
     * topK问题
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k >= arr.length) return arr;
        quickSort(arr, 0, arr.length - 1);
        return Arrays.copyOf(arr, k);
    }

    /**
     * 通过判断舍去了不必要的递归（哨兵划分）。
     */
    private int[] quickSort(int[] arr, int k, int l, int r) {
        int i = l, j = r;
        while (i < j) {
            while (i < j && arr[j] >= arr[l]) j--;
            while (i < j && arr[i] <= arr[l]) i++;
            swap(arr, j, i);
        }
        swap(arr, i, l);
        //若 k < i，代表第 k + 1小的数字在 左子数组 中，则递归左子数组
        if (i > k) return quickSort(arr, k, l, i - 1);
        //若 k > i，代表第 k + 1小的数字在 右子数组 中，则递归右子数组
        if (i < k) return quickSort(arr, k, i + 1, r);
        //若 k = i，代表此时 arr[k] 即为第 k + 1 小的数字，则直接返回数组前 k 个数字即可；
        return Arrays.copyOf(arr, k);
    }

    /**
     * 快速排序
     * 平局时间复杂度为O(NlogN) ,最坏时间复杂度为n的平方.
     */
    private void quickSort(int[] arr, int l, int r) {
        if (r <= l) return;
        int i = l, j = r;
        while (i < j) {
            //以下标i为基数,大于i的放在最右边
            while (i < j && arr[j] >= arr[l]) j--;
            //小于i的放在最左边
            while (i < j && arr[i] <= arr[l]) i++;
            //交换i 跟 j 的位置
            swap(arr, j, i);
        }
        //再用基数把坑填满,这个时候得到的数组,左边的都小于基数,右边的都大于基数.
        swap(arr, i, l);
        quickSort(arr, l, i - 1);
        quickSort(arr, i + 1, r);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 剑指 Offer 41. 数据流中的中位数
     * https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/
     * <p>
     * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
     * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
     * <p>
     * 例如，
     * [2,3,4]的中位数是 3
     * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
     * <p>
     * 设计一个支持以下两种操作的数据结构：
     * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
     * double findMedian() - 返回目前所有元素的中位数。
     * <p>
     * 示例 1：
     * 输入：
     * ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
     * [[],[1],[2],[],[3],[]]
     * 输出：[null,null,null,1.50000,null,2.00000]
     * <p>
     * <p>
     * 平衡堆:大顶堆+小顶堆(参考k神)
     * 创建两个堆:其中堆A为小顶堆(存储较大的一半元素);堆B为大顶堆(存储较小的一半元素)
     * 规定存储元素个数:A>=B;设A中有m个元素,B中有n个元素,一共有N=m+n个元素
     * 当N为奇数时:m=(N+1)/2,m=(N-1)/2;当N为偶数时:m=n=N/2
     * findMedian():
     * 1.当m+n为偶数时,中位数=(A堆顶元素+B堆顶元素)/2.0
     * 2.当m+n为奇数时,中位数=A堆顶元素
     * addNum(int num):其目的是使得两个堆平衡(数目差0或1)
     * 1.当m=n时,num应该添加进A中,但num可能是较小的一半,因此先入堆B,再将B堆顶弹出入A
     * 2.当m=n+1时,num应该添加进B中,但num可能是较大的一半,因此先入堆A,再将A堆顶弹出入B
     * 时间复杂度:O(1)+O(logN)=O(logN);空间复杂度:O(N)
     */
    class MedianFinder {

        Queue<Integer> A, B;

        public MedianFinder() {
            A = new PriorityQueue<>();//最小堆(堆顶为最小的元素)，保存较大的一半，
            B = new PriorityQueue<>((x, y) -> (y - x));//最大堆(堆顶为较大的元素)，保存较小的一半，
        }

        public void addNum(int num) {
            if (A.size() != B.size()) {
                //当A的大小不等于B时，先往A中添加元素，再取出来添加至B中，这样能保证B中的元素一直是最小的一半
                A.add(num);
                B.add(A.poll());
            } else {
                B.add(num);
                A.add(B.poll());
            }
        }

        public double findMedian() {
            return A.size() != B.size() ? A.peek() : (A.peek() + B.peek()) / 2.0;//需要除以2.0,不能除以2
        }
    }


    /**
     * 剑指 Offer 42. 连续子数组的最大和
     * https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
     *
     * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     *
     * 要求时间复杂度为O(n)。
     *
     * 
     *
     * 示例1:
     * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释:连续子数组[4,-1,2,1] 的和最大，为6。
     *
     */
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }


    /**
     * 动规五部曲如下：
     *
     * 确定dp数组（dp table）以及下标的含义
     * dp[i]：包括下标i之前的最大连续子序列和为dp[i]。
     *
     * 确定递推公式
     * dp[i]只有两个方向可以推出来：
     *
     * dp[i - 1] + nums[i]，即：nums[i]加入当前连续子序列和
     * nums[i]，即：从头开始计算当前连续子序列和
     * 一定是取最大的，所以dp[i] = max(dp[i - 1] + nums[i], nums[i]);
     *
     * dp数组如何初始化
     * 从递推公式可以看出来dp[i]是依赖于dp[i - 1]的状态，dp[0]就是递推公式的基础。
     *
     * dp[0]应该是多少呢?
     *
     * 更具dp[i]的定义，很明显dp[0]因为为nums[0]即dp[0] = nums[0]。
     *
     * 确定遍历顺序
     * 递推公式中dp[i]依赖于dp[i - 1]的状态，需要从前向后遍历。
     *
     * 举例推导dp数组
     */
    public int maxSubArrayDP(int[] nums) {
        int dp = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp = Math.max(dp + nums[i], nums[i]);
            res = Math.max(dp, res);
        }
        return res;
    }


    /**
     * 剑指 Offer 44. 数字序列中某一位的数字
     * https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/
     *
     * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
     *
     * 请写一个函数，求任意第n位对应的数字。
     *
     * 示例 1：
     * 输入：n = 11
     * 输出：0
     *
     * 数字范围    数量  位数    占多少位
     *     1-9        9      1       9
     *     10-99      90     2       180
     *     100-999    900    3       2700
     *     1000-9999  9000   4       36000  ...
     *
     *     例如 2901 = 9 + 180 + 2700 + 12 即一定是4位数,第12位   n = 12;
     *     数据为 = 1000 + (12 - 1)/ 4  = 1000 + 2 = 1002
     *     定位1002中的位置 = (n - 1) %  4 = 3    s.charAt(3) = 2
     */
    public int findNthDigit(int n) {
        int digit = 1;//数字位数
        long start = 1;//数字范围起始值
        long count = 9;//各 digit下的数位数量 count
        //1. 确定所求数位的所在数字的位数,循环执行 nn 减去 一位数、两位数、... 的数位数量 count ，直至 n <= count 时跳出
        while (n > count) {
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        //2. 确定所求数位所在的数字
        long num = start + (n - 1) / digit;
        //3. 确定所求数位在 num 的哪一数位
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }


    /**
     * 剑指 Offer 45. 把数组排成最小的数
     * https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/
     *
     * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     *
     * 示例 1:
     * 输入: [3,30,34,5,9]
     * 输出: "3033459"
     */
    public String minNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int num : nums) {
            list.add(String.valueOf(num));
        }
        /**
         * 设数组 numsnums 中任意两数字的字符串为 xx 和 yy ，则规定 排序判断规则 为：
         *
         * 若拼接字符串 x + y > y + xx+y>y+x ，则 xx “大于” yy ；
         * 反之，若 x + y < y + xx+y<y+x ，则 xx “小于” yy ；
         *
         * xx “小于” yy 代表：排序完成后，数组中 xx 应在 yy 左边；“大于” 则反之。
         */
        list.sort((o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        return String.join("", list);
    }


    /**
     * 剑指 Offer 46. 把数字翻译成字符串
     * https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
     *
     * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。
     * 请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
     *
     * 示例 1:
     * 输入: 12258
     * 输出: 5
     * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
     *
     */
    public int translateNum(int num) {
        /*
        //递归
        if (num < 10) {
            return 1;
        } else if (num < 26) {
            return 2;
        } else if (num < 100) {
            return 1;
        }
        if (num % 100 > 25 || num % 100 < 10) {
            return translateNum(num / 10);
        } else {
            return translateNum(num / 10) + translateNum(num / 100);
        }*/
        String s = String.valueOf(num);
        int[] dp = new int[s.length() + 1];//翻译后方案的数量
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= s.length(); i++) {
            String temp = s.substring(i - 2, i);
            if (temp.compareTo("25") <= 0 && temp.compareTo("10") >= 0) {
                dp[i] = dp[i -1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[s.length()];
    }


    /**
     * 剑指 Offer 47. 礼物的最大价值
     * https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof/?favorite=xb9nqhhg
     *
     * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
     * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
     * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
     *
     * 
     * 示例 1:
     * 输入:
     * [
     *  [1,3,1],
     *  [1,5,1],
     *  [4,2,1]
     * ]
     *
     * 输出: 12
     * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
     * 
     */
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j ++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n -1];
        /*
        int m = grid.length, n = grid[0].length;
        //dp[i][j]表示从grid[0][0]到grid[i - 1][j - 1]时的最大价值
        int[][] dp = new int[m + 1][n + 1];//边界问题不容易出错
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
            //滚动数组,以空间换时间
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return dp[m][n];
        */
    }


    /**
     * 剑指 Offer 48. 最长不含重复字符的子字符串
     * https://leetcode.cn/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/
     *
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     *
     * 示例 1:
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        //数组记录字符出现的次数
        int[] arr = new int[128];
        //滑动窗口
        int left =0, right = 0;
        while (right < s.length()) {
            char ch = s.charAt(right);
            arr[ch]++;
            right++;
            //如果字符出现次数大于1,说明重复出现了
            while (arr[ch] > 1) {
                //获取左指针字符的值,并且次数减一, 直到减掉跟右指针一样的字符
                char leftChar = s.charAt(left);
                arr[leftChar]--;
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }


    /**
     * 剑指 Offer 49. 丑数
     * https://leetcode.cn/problems/chou-shu-lcof/?favorite=xb9nqhhg
     * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
     *
     * 示例:
     * 输入: n = 10
     * 输出: 12
     * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
     *
     */
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;////基础丑数为1
        int i = 0, j = 0, k = 0;////初始分别指向三个有序链表第一个元素,这三个有序链表是想象出来的，分别就是dp数组元素分别乘以2,3,5得到的
        for (int idx = 1; idx < n; idx++) {
            int temp = Math.min(dp[i] * 2, dp[j] * 3);
            temp = Math.min(temp, dp[k] * 5);
            // //三个链表可能有相同元素，所以只要是最小的，都要移动指针
            if (temp == dp[i] * 2) i++;
            if (temp == dp[j] * 3) j++;
            if (temp == dp[k] * 5) k++;
            dp[idx] = temp;
        }
        return dp[n - 1];
    }


    /**
     * 剑指 Offer 50. 第一个只出现一次的字符
     * https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
     *
     * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
     *
     * 示例 1:
     * 输入：s = "abaccdeff"
     * 输出：'b'
     */
    public char firstUniqChar(String s) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if ( arr[s.charAt(i) - 'a'] == 1) return s.charAt(i);
        }
        return ' ';
    }


    /**
     * 剑指 Offer 51. 数组中的逆序对
     * https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
     *
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
     *
     * 示例 1:
     * 输入: [7,5,6,4]
     * 输出: 5
     *
     * 解题思路:归并排序,在合并的时候，当左边的大于右边，就计算逆序数。
     * 计算公式； mid-left+1
     */
    int reversePairsRes;
    public int reversePairs(int[] nums) {
        merge(nums, 0, nums.length - 1);
        return reversePairsRes;
    }

    private void merge(int[] nums, int left, int right) {
        //当只有一个节点的时候，直接返回，退出递归
        if (left >= right) return;
        int mid = (right + left) / 2;//将数组分割成两部分
        merge(nums, left, mid);//对前一部分进行归并排序
        merge(nums, mid + 1, right);//对后一部分进行排序
        mergeSort(nums, left, mid, right);//合并两部分
    }

    private void mergeSort(int[] nums, int left, int mid, int right) {
        //存储处理过的数据的
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            //比较两个数组的元素，取较小的元素加入到，临时数组中
            if (nums[i] <= nums[j]) {
                temp[t++] = nums[i++];
            } else {
                //当左边数组的大与右边数组的元素时，就对当前元素以及后面的元素的个数进行统计，
                reversePairsRes += mid - i + 1;
                temp[t++] = nums[j++];
            }
        }
        //当左边的数组没有遍历完成后，直接将剩余元素加入到临时数组中
        while(i <= mid) {
            temp[t++] = nums[i++];
        }
        //当右边的数组没有遍历完成后，直接将剩余元素加入到临时数组中
        while(j <= right) {
            temp[t++] = nums[j++];
        }
        //将新数组中的元素，覆盖nums旧数组中的元素。
        //此时数组的元素已经是有序的
        for (int k = 0; k < temp.length; k++) {
            nums[left + k] = temp[k];
        }
    }


    /**
     * 剑指 Offer 52. 两个链表的第一个公共节点
     * https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
     *
     * 输入两个链表，找出它们的第一个公共节点。
     */
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }


    /**
     * 剑指 Offer 53 - I. 在排序数组中查找数字 I
     * https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
     *
     * 示例 1:
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: 2
     *
     * 示例2:
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: 0
     *
     * 解题思路:二分法
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int res = 0;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        while (left < nums.length && nums[left++] == target) {
            res++;
        }
        return res;
    }

    /**
     * 剑指 Offer 53 - II. 0～n-1中缺失的数字
     * https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/
     *
     * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
     * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
     *
     * 示例 1:
     * 输入: [0,1,3]
     * 输出: 2
     *
     */
    public int missingNumber(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left  < right) {
            int mid = (left + right) >> 1;
            //如果中间值等于mid,则说明在右边
            if (nums[mid] == mid) {
                left = mid + 1;
            } else {
                //否则在左边
                right = mid;
            }
        }
        //除去特殊情况
        if (nums[nums.length - 1] == nums.length - 1) {
            if (nums[nums.length - 1] == 0) return 1;
            return nums.length;
        }
        return left;
    }


    /**
     * 剑指 Offer 54. 二叉搜索树的第k大节点
     * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/?favorite=xb9nqhhg
     *
     * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
     *
     * 二叉搜索树: 有序,使用中序遍历.
     */
    int count = 0, ans = 0;
    public int kthLargest(TreeNode root, int k) {
        /*List<Integer> list = new LinkedList<>();
        inorderBfs(root, list);
        return list.get(list.size() - k);*/
        inorderHelper(root, k);
        return ans;
    }

    public void inorderBfs(TreeNode root, List<Integer> res) {
        //空间复杂度(O(n))
        if (root == null) return;
        inorderBfs(root.left, res);
        res.add(root.val);
        inorderBfs(root.right, res);
    }

    public void inorderHelper(TreeNode root, int k) {
        //空间复杂度(O(1))
        if (root.right != null) inorderHelper(root.right, k);
        if (++count == k) {//++count是先加后比较, count++=k是先比较后加
            ans = root.val;
            return;
        }
        if (root.left != null) inorderHelper(root.left, k);
    }

    /**
     * 剑指 Offer 55 - I. 二叉树的深度
     * https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/
     *
     * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3 。
     */
    public int maxDepth(TreeNode root) {
        //递归
        if(root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    private int maxDepthDFS(TreeNode root) {
        int res = 0;
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right!= null) queue.add(node.right);
            }

        }
        return res;
    }


    /**
     * 剑指 Offer 55 - II. 平衡二叉树
     * https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/?favorite=xb9nqhhg
     *
     * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
     *
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int leftHeight = isBalancedHelper(root.left);
        int rightHeight = isBalancedHelper(root.right);
        if (Math.abs(leftHeight - rightHeight) <= 1) {
            return isBalanced(root.left) && isBalanced(root.right);
        }
        return false;
    }

    private int isBalancedHelper(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(isBalancedHelper(root.left), isBalancedHelper(root.right));
    }


    /**
     * 剑指 Offer 56 - I. 数组中数字出现的次数
     * https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/?favorite=xb9nqhhg
     *
     * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
     * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
     *
     * 示例 2：
     * 输入：nums = [1,2,10,4,1,4,3,3]
     * 输出：[2,10] 或 [10,2]
     *
     * 首先要知道异或运算的几个性质
     *
     * 交换律
     * 结合律（即(a^b)^c == a^(b^c)）
     * 对于任何数x，都有x^x=0，x^0=x
     * 自反性 A ^ B ^ B = A ^ 0 = A ---> A ^ B = C 则 C ^ B = A
     *      对给定的数A，用同样的运算因子（B）作两次异或运算后仍得到A本身
     *
     *   0000 1111 //p=15
     * ^ 0000 1000 //q=8
     * ------------
     *   0000 0111
     */
    public int[] singleNumbers(int[] nums) {
        /**
         * 异或满足交换律，第一步异或，相同的数其实都抵消了，剩下两个不同的数。
         * 这两个数异或结果肯定有某一位为1，不然都是0的话就是相同数。
         * 找到这个位，不同的两个数一个在此位为0，另一个为1。
         * 按此位将所有数分成两组，分开后各自异或，相同的两个数异或肯定为0（而且分开的时候，两个数必为一组）。
         * 剩下的每组里就是我门要找的数。
         */
        int x = 0; // 用于记录 A B 的异或结果

        for (int val : nums) x ^= val;

        // x & (-x)本身的作用是得到最低位的1，(从由往左)
        int flag = x & (-x);
        // 而我们所需要的做到的是：利用这个1来进行分组，也就是做到将A和B区分开
        // 前面已经知道，x是我们需要的结果数A和B相异或的结果，也就是说，x的二进制串上的任何一个1，都能成为区分A和B的条件
        // 因此我们只需要得到x上的任意一个1，就可以做到将A和B区分开来


        int res = 0; // 用于记录A或B其中一者

        // 分组操作
        for (int val : nums) {
            // 根据二进制位上的那个“1”进行分组
            // 需要注意的是，分组的结果必然是相同的数在相同的组，且还有一个结果数
            // 因此每组的数再与res=0一路异或下去，最终会得到那个结果数A或B
            // 且由于异或运算具有自反性，因此只需得到其中一个数即可
            if ((flag & val) != 0) {
                res ^= val;
            }
        }
        // 利用先前的x进行异或运算得到另一个，即利用自反性
        return new int[] {res, x ^ res};
    }


    public int[] singleNumbers1(int[] nums) {
        //因为相同的数字异或为0，任何数字与0异或结果是其本身。
        //所以遍历异或整个数组最后得到的结果就是两个只出现一次的数字异或的结果：即 z = x ^ y
        int z = 0;
        for(int i : nums) z ^= i;
        //我们根据异或的性质可以知道：z中至少有一位是1，否则x与y就是相等的。
        //我们通过一个辅助变量m来保存z中哪一位为1.（可能有多个位都为1，我们找到最低位的1即可）。
        //举个例子：z = 10 ^ 2 = 1010 ^ 0010 = 1000,第四位为1.
        //我们将m初始化为1，如果（z & m）的结果等于0说明z的最低为是0
        //我们每次将m左移一位然后跟z做与操作，直到结果不为0.
        //此时m应该等于1000，同z一样，第四位为1.
        int m = 1;
        while((z & m) == 0) m <<= 1;
        //我们遍历数组，将每个数跟m进行与操作，结果为0的作为一组，结果不为0的作为一组
        //例如对于数组：[1,2,10,4,1,4,3,3]，我们把每个数字跟1000做与操作，可以分为下面两组：
        //nums1存放结果为0的: [1, 2, 4, 1, 4, 3, 3]
        //nums2存放结果不为0的: [10] (碰巧nums2中只有一个10，如果原数组中的数字再大一些就不会这样了)
        //此时我们发现问题已经退化为数组中有一个数字只出现了一次
        //分别对nums1和nums2遍历异或就能得到我们预期的x和y
        int x = 0, y = 0;
        for(int i : nums) {
            //这里我们是通过if...else将nums分为了两组，一边遍历一遍异或。
            //跟我们创建俩数组nums1和nums2原理是一样的。
            if((i & m) == 0) x ^= i;
            else y ^= i;
        }
        return new int[]{x, y};
    }


    /**
     * 剑指 Offer 56 - II. 数组中数字出现的次数 II
     * https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/
     *
     * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
     *
     * 示例 1：
     * 输入：nums = [3,4,3,3]
     * 输出：4
     */
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            a = (a ^ num) & ~b;
            b = (b ^ num) & ~a;
        }
        return a;
    }


    /**
     * 剑指 Offer 57. 和为s的两个数字
     * https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/
     *
     * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
     *
     *
     * 示例 1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[2,7] 或者 [7,2]
     */
    public int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if ((nums[left] + nums[right]) > target) {
                right--;
            } else if ((nums[left] + nums[right]) < target) {
                left++;
            } else {
                return new int[]{nums[left], nums[right]};
            }
        }
        return null;
    }


    /**
     * 剑指 Offer 57 - II. 和为s的连续正数序列
     * https://leetcode.cn/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/
     *
     * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
     *
     * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
     *
     *
     * 示例 1：
     * 输入：target = 9
     * 输出：[[2,3,4],[4,5]]
     *
     */
    public int[][] findContinuousSequence(int target) {
        List<int[]> res = new ArrayList<>();

        for (int right = 1, left = 1, sum = 0; right < target; right++) {
            sum += right;
            while (sum > target) {
                sum -= left++;
            }
            if (sum == target) {
                int[] temp = new int[right - left + 1];
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = left + i;
                }
                res.add(temp);
            }
        }
        return res.toArray(new int[res.size()][]);
    }


    /**
     * 剑指 Offer 58 - I. 翻转单词顺序
     * https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/
     *
     * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
     * 例如输入字符串"I am a student. "，则输出"student. a am I"。
     *
     * 示例 1：
     * 输入: " hello world! "
     * 输出:"world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     *
     */
    public String reverseWords(String s) {
        String[] ss = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = ss.length - 1; i >= 0; i--) {
            if (ss[i].equals("")) continue;
            sb.append(" ").append(ss[i]);
        }
        return sb.toString().trim();
    }

    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     * https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/?favorite=xb9nqhhg
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
     * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     *
     * 示例 1：
     * 输入: s = "abcdefg", k = 2
     * 输出: "cdefgab"
     *
     */
    public String reverseLeftWords(String s, int n) {
        if (n >= s.length()) return s;
        return s.substring(n) + s.substring(0, n);
    }


    /**
     * 剑指 Offer 59 - I. 滑动窗口的最大值
     * https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
     * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
     *
     * 示例:
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     *
     *   滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        //单调队列
        LinkedList<Integer> deque = new LinkedList<>();
        for (int right = 0; right < nums.length; right++) {
            //如果队列不为空,并且对位元素小于当前元素,则移除对尾元素.
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[right]) {
                deque.removeLast();
            }
            //存当前元素下标. 这个地方很精髓.
            deque.addLast(right);
            //计算窗口左边下标
            int left = right - k + 1;
            //如果队首值小于窗口左端值,说明这个值已经不再窗口范围内了
            if (deque.peekFirst() < left) {
                //移除队首值
                deque.removeFirst();
            }
            //形成窗口时,队首的值为最大值
            if (right + 1 >= k) {
                res[left] = nums[deque.peekFirst()];
            }
        }
        return res;
    }


    /**
     * 剑指 Offer 60. n个骰子的点数
     * https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof/?favorite=xb9nqhhg
     *
     * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
     * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
     *
     * 示例 1:
     * 输入: 1
     * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
     *
     * 示例2:
     * 输入: 2
     * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
     */
    public double[] dicesProbability(int n) {
        //确定dp数组,以及边界
        int[][] dp = new int[n][6 * n + 1];
        //初始化dp数组
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }
        //第n个数
        for (int i = 2; i <= n; i++) {
            //和值结果种数
            for (int j = i; j <= 6 * i; j++) {
                //骰子1至6
                for (int k = 1; k <= 6 && k <= j; k++) {
                    //递归公式
                    //单单看第 nn 枚骰子，它的点数可能为 1 , 2, 3, ... , 61,2,3,...,6
                    // 因此投掷完 nn 枚骰子后点数 jj 出现的次数，可以由投掷完 n-1n−1 枚骰子后，
                    // 对应点数 j-1, j-2, j-3, ... , j-6j−1,j−2,j−3,...,j−6 出现的次数之和转化过来。
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        //结果集
        double ans[] = new double[5 * n + 1];
        //6的n次方,表示所有的情况数量.
        double all = Math.pow(6, n);
        for (int i = n; i <= 6 * n; i++) {
            //结果出现的概率 = 结果出现的次数 / 总次数
            ans[i - n] = dp[n][i] / all;
        }
        return ans;
    }


    /**
     * 剑指 Offer 61. 扑克牌中的顺子
     * https://leetcode.cn/problems/bu-ke-pai-zhong-de-shun-zi-lcof/
     *
     * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。
     * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
     *
     * 示例 1:
     * 输入: [1,2,3,4,5]
     * 输出: True
     *
     * 示例 2:
     * 输入: [0,0,1,2,5]
     * 输出: True
     */
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);//时间复杂度: O(NlogN)
        int zeroCount = 0, diff = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            } else {
                if (nums[i + 1] == nums[i]) {
                    return false;
                }
                if (nums[i + 1] - 1 != nums[i]) {
                    diff += nums[i + 1] - nums[i] - 1;
                }
            }
        }
        return zeroCount >= diff;
    }


    /**
     * 剑指 Offer 62. 圆圈中最后剩下的数字
     * https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/?favorite=xb9nqhhg
     *
     * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
     *
     * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     *
     * 示例 1：
     * 输入: n = 5, m = 3
     * 输出:3
     *
     * 示例 2：
     * 输入: n = 10, m = 17
     * 输出:2
     */
    public int lastRemaining(int n, int m) {
        /*ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (list.size() > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);*/
        /**
         * f(n) = ( f(n - 1) + m ) % n
         * 解释意思：
         *   f(n) 表示上一次
         *   f(n - 1) 表示这次，因为我们要从这次回推上一次
         *   m 表示隔几个
         *   n表示上一次的数组长度
         */
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = (res + m) % i;
        }
        return res;
    }


    /**
     * 剑指 Offer 63. 股票的最大利润
     * https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof/?favorite=xb9nqhhg
     *
     * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
     *
     * 示例 1:
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     *
     * 示例 2:
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int min = prices[0]; int res = 0;
        for (int i = 1; i < prices.length; i++) {
            //如果当前价格比min还小,则更新min
            if (prices[i] <= min) {
                min = prices[i];
            } else {
                //否则,重新计算利润进行比较.
                res = Math.max(res, prices[i] - min);
            }
        }
        return res;
    }


    /**
     * 剑指 Offer 64. 求1+2+…+n
     * https://leetcode.cn/problems/qiu-12n-lcof/
     *
     * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     *
     * 示例 1：
     * 输入: n = 3
     * 输出:6
     *
     */
    public int sumNums(int n) {
        //return (int) (Math.pow(n, 2) + n) >> 1; (首项+末项)*项数/2
        int sum = n;
        //利用"短路"特性 当n大于0时 就继续递归 否则停止递归 return 前面的累加值
        boolean flag = n > 0 && (sum += sumNums(n - 1)) > 0;
        return sum;
    }

    /**
     * 剑指 Offer 65. 不用加减乘除做加法
     * https://leetcode.cn/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/?favorite=xb9nqhhg
     *
     * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
     *
     * 示例:
     * 输入: a = 1, b = 1
     * 输出: 2
     */
    public int add(int a, int b) {
        /**
         * ^ 亦或 ----相当于 无进位的求和， 想象10进制下的模拟情况：（如:19+1=20；无进位求和就是10，而非20；因为它不管进位情况）
         *
         * & 与 ----相当于求每位的进位数， 先看定义：1&1=1；1&0=0；0&0=0；即都为1的时候才为1，正好可以模拟进位数的情况,
         * 还是想象10进制下模拟情况：（9+1=10，如果是用&的思路来处理，则9+1得到的进位数为1，而不是10，所以要用<<1向左再移动一位，这样就变为10了）；
         *
         * 这样公式就是：（a^b) ^ ((a&b)<<1) 即：每次无进位求 + 每次得到的进位数--------我们需要不断重复这个过程，直到进位数为0为止；
         */
        if (b == 0) {
            return a;
        }
        // 转换成非进位和 + 进位
        return add(a ^ b, (a & b) << 1);
    }


    /**
     * 剑指 Offer 66. 构建乘积数组
     * https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/
     *
     * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 
     * 即B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
     *
     * 示例:
     * 输入: [1,2,3,4,5]
     * 输出: [120,60,40,30,24]
     *
     */
    public int[] constructArr(int[] a) {
        if (a == null || a.length == 0) return new int[0];
        //从左往右累乘结果
        int[] left = new int[a.length];
        left[0] = a[0];
        for (int i = 1; i < a.length; i++) {
            left[i] = a[i] * left[i - 1];
        }
        //从右往左累乘结果
        int[] right = new int[a.length];
        right[a.length - 1] = a[a.length - 1];
        for (int i = a.length - 2; i >= 0; i--) {
            right[i] = a[i] * right[i + 1];
        }
        int[] res = new int[a.length];
        res[0] = right[1];
        res[a.length - 1] = left[a.length - 2];
        for (int i = 1; i < a.length - 1; i++) {
            //最终结果
            res[i] = left[i - 1] * right[i + 1];
        }
        return res;
    }


    /**
     * 剑指 Offer 67. 把字符串转换成整数
     * https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/
     *
     * 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
     *
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
     * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
     * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
     * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
     *
     * 说明：
     * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为[−231, 231− 1]。
     * 如果数值超过这个范围，请返回 INT_MAX (231− 1) 或INT_MIN (−231) 。
     *
     */
    public int strToInt(String str) {
        //去除前后空格
        char[] chars = str.trim().toCharArray();
        if (chars.length == 0) return 0;
        //结果
        int res = 0;
        //符号
        int sign = 1;
        //开始遍历的位置
        int i = 1;
        //第一个元素为负数
        if (chars[0] == '-') {
            sign = -1;
        } else if (chars[0] != '+') {
            i = 0;
        }
        //用来存储除以10后的值，从而确定是否越界
        int member = Integer.MAX_VALUE / 10;
        for (int j = i; j < chars.length; j++) {
            if (chars[j] > '9' || chars[j] < '0') break;
            //如果res 大于member，那乘以10肯定越界了  或 如果res 等于 member，后一位如果大于7，那也越界
            if (res > member || (member == res && chars[j] > '7')) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + (chars[j] - '0');
        }
        return res * sign;
    }


    /**
     * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
     * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/?favorite=xb9nqhhg
     *
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：
     * “对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 例如，给定如下二叉搜索树: root =[6,2,8,0,4,7,9,null,null,3,5]
     *
     * 说明:
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉搜索树中。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        //利用二叉搜索树的性质:左小右大
        //如果当前节点大于左右子节点的值, 则遍历左子树
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        //如果当前节点小于左右子节点的值, 则遍历右子树
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }


    /**
     * 剑指 Offer 68 - II. 二叉树的最近公共祖先
     * https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/?favorite=xb9nqhhg
     *
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 说明:
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉树中。
     *
     * 三种情况：
     *   1、p q 一个在左子树 一个在右子树 那么当前节点即是最近公共祖先
     *   2、p q 都在左子树
     *   3、p q 都在右子树
     *
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //如果节点为空,获取已经找到一个节点,则返回
        if (root == null || root == q || root == p) return root;
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        //说明公共节点在右子树
        if (left == null) return right;
        //说明公共节点在左子树
        if (right == null) return left;
        //当前为公共节点
        return root;
    }

    public static void main(String[] args) {
        printNumbers1(1);
        int a[][] = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(a[0].length);//3
        System.out.println(a.length);//2
        System.out.println(a[1][2]);//6
        System.out.println(hammingWeight(13));//3
        System.out.println(1 / 2);//0
        System.out.println(minArray(new int[]{2, 2, 2, 0, 1}));
        System.out.println(Integer.MAX_VALUE);//2147483647
        System.out.println(printNumbers(1));
        //System.out.println(Arrays.toString(printNumbers1(4).toArray()));
        System.out.println(5 / 2);//2
        System.out.println(5 / 2.0);//2.5
        System.out.println(31%100);//31
        System.out.println("123".length());//3
        System.out.println("123".charAt(0));//1
        System.out.println("subString()" + "012345678".substring(1, 2));//8
        int b[] = new int[5];
        //System.out.println(b[5]);//报错,只能b[4]
        System.out.println("123".toCharArray());
        int a1 = 1;
        int a2 = 2;
        Long  a3 = 1L;
       // a1 = a1 ^ a2 ^ a1;
        System.out.println(a1);//2
        System.out.println(a1 == a3.intValue());
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class NodeTree {
    public int val;
    public NodeTree left;
    public NodeTree right;

    public NodeTree() {
    }

    public NodeTree(int _val) {
        val = _val;
    }

    public NodeTree(int _val, NodeTree _left, NodeTree _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};