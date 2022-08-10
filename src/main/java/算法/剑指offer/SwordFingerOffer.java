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
     *
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
     *  剑指 Offer 04. 二维数组中的查找
     *  https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
     *
     *  在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     *  请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * 示例:
     * 现有矩阵 matrix 如下：
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     * 给定 target=5，返回true。
     * 给定target=20，返回false。
     *
     * 从右上角开始走，利用这个顺序关系可以在O(m+n)的复杂度下解决这个题：
     *
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
     *
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     *
     * 示例 1：
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     *
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
     *
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     *
     * 示例 1：
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     *
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
     *
     * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     *
     * 示例 1:
     * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * Output: [3,9,20,null,null,15,7]
     *
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
     *
     * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
     * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead操作返回 -1 )
     *
     * 示例 1：
     * 输入：
     * ["CQueue","appendTail","deleteHead","deleteHead"]
     * [[],[3],[],[]]
     * 输出：[null,null,3,-1]
     *
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
     *
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
     * F(0) = 0, F(1)= 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     * 示例 1：
     * 输入：n = 5
     * 输出：5
     *
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
     *
     *  一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n级的台阶总共有多少种跳法。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     * 示例 1：
     * 输入：n = 7
     * 输出：21
     */
    public int numWays(int n) {
        if (n == 0) return 1;
        if (n < 2) return n;
        int a = 1, b = 2, res = 0;
        for (int i = 3; i <=n; i++) {
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
     *
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     *
     * 给你一个可能存在重复元素值的数组numbers，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。
     * 例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。
     *
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     *
     * 有序数组:二分法
     *
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
     *
     * 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     *
     * 示例 1：
     *
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
     * 输出：true
     *
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
                || dfs(board, chars, visited, i, j -1, start + 1);
        //回溯,如果某条路已经不通了,则回溯.
        visited[i][j] =false;
        return ans;
    }


    /**
     * 剑指 Offer 13. 机器人的运动范围
     * https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
     *
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
     * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     *
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
     *
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
     * 请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     *
     * 示例 1:
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
     *
     *  动态五部曲
     *     1.确定dp下标及其含义
     *     2.确定递推公式
     *     3.确定dp初始化
     *     4.确定遍历顺序
     *     5.打印dp
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
     *
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。
     * 请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     *
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     *
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
        return (int)(res * n % 1000000007);
    }


    /**
     * 剑指 Offer 15. 二进制中1的个数
     * https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
     *
     * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。
     *
     * 示例 1：
     *
     * 输入：n = 11 (控制台输入 00000000000000000000000000001011)
     * 输出：3
     * 解释：输入的二进制串 00000000000000000000000000001011中，共有三位为 '1'。
     *
     * 位运算
     *
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
     *
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
     *
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
     *
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     *
     * 示例 1:
     * 输入: n = 1
     * 输出: [1,2,3,4,5,6,7,8,9]
     *
     */
    public static int[] printNumbers(int n) {
        //10的n次方
        int length = (int)Math.pow(10, n);
        int[] res = new int[length - 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = i + 1;
        }
        return res;
    }

    static List<String> res = new ArrayList<>();

    public static List<String> printNumbers1(int n) {
        for (int i = 1; i < n; i++) {
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
     *
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     * 返回删除后的链表的头节点。
     * 
     * 示例 1:
     *
     * 输入: head = [4,5,1,9], val = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为5的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     *
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
     *
     * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
     * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
     *
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
        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                // 空的p的情形
                if(j == 0) {
                    // dp[0][0]=true;其余为false
                    dp[i][j] = i == 0;
                }else {
                    // 非空的p都是要经过计算的
                    if(p.charAt(j - 1) != '*') {
                        // 1.p[j-1]!='*'的情形(2.1)
                        // 此时j>=1
                        if(i >= 1 && (s.charAt(i - 1) == p.charAt(j - 1) ||
                                p.charAt(j - 1) == '.')) {
                            // p与s都向前一位匹配
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    }else {
                        // 2.p[j-1]=='*'的情形(2.2)
                        // 看"x*"的情形(2.2.1与2.2.2)
                        if(i >= 1 && j >= 2 &&
                                (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')) {
                            // p不动s向前一位
                            dp[i][j] = dp[i - 1][j];
                        }
                        // 不看"x*"的情形(2.2.3)
                        // 此时p[j-2]为'a'-'z',且p[j-2]!=s[i-1],"p[j-2]*"直接废掉
                        if(j >= 2) {
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
     *
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     *
     * 数值（按顺序）可以分成以下几个部分：
     *
     * 若干空格
     * 一个小数或者整数
     * （可选）一个'e'或'E'，后面跟着一个整数
     * 若干空格
     * 小数（按顺序）可以分成以下几个部分：
     *
     * （可选）一个符号字符（'+' 或 '-'）
     * 下述格式之一：
     * 至少一位数字，后面跟着一个点 '.'
     * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
     * 一个点 '.' ，后面跟着至少一位数字
     * 整数（按顺序）可以分成以下几个部分：
     *
     * （可选）一个符号字符（'+' 或 '-'）
     * 至少一位数字
     * 部分数值列举如下：
     *
     * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
     * 部分非数值列举如下：
     *
     * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
     *
     * 示例 1：
     * 输入：s = "0"
     * 输出：true
     * 
     * 示例 2：
     * 输入：s = "."
     * 输出：false
     * 
     * 示例 3：
     * 输入：s = "    .1  "
     * 输出：true
     */
    public boolean isNumber(String s) {
        if(s == null || s.length() == 0) return false;
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
     *
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
     *
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

    /**
     * 剑指 Offer 22. 链表中倒数第k个节点
     * https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
     *
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
     *
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 k = 2.
     * 返回链表 4->5.
     *
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
     *
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     *
     * 示例:
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     *
     *
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


    /**
     * 剑指 Offer 25. 合并两个排序的链表
     * https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
     *
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
     *
     * 示例1：
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     *
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
     *
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     * 
     * 例如:
     * 给定的树 A:
     *   3
     *   / \
     *  4  5
     *  / \
     * 1  2
     *
     * 给定的树 B：
     *
     *  4
     *  /
     * 1
     * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
     *
     * 示例 1：
     * 输入：A = [1,2,3], B = [3,1]
     * 输出：false
     *
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


    /**
     * 剑指 Offer 27. 二叉树的镜像
     * https://leetcode.cn/problems/er-cha-shu-de-jing-xiang-lcof/
     *
     * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
     *
     * 例如输入：
     *   4
     *  /  \
     *  2   7
     * / \  / \
     * 1  3 6  9
     *
     * 镜像输出：
     *   4
     *  /  \
     *  7   2
     * / \  / \
     * 9  6 3 1
     *
     * 示例 1：
     * 输入：root = [4,2,7,1,3,6,9]
     * 输出：[4,7,2,9,6,3,1]
     *
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
     *
     * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     *  1
     *  / \
     *  2  2
     * / \ / \
     * 3 4 4 3
     *
     * 但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
     *
     *   1
     *  / \
     *  2  2
     *  \  \
     *  3  3
     *
     *
     * 示例 1：
     * 输入：root = [1,2,2,3,4,4,3]
     * 输出：true
     *
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        TreeNode node1 = root;
        TreeNode node2 = root;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(node1.left);
        deque.add(node2.right);
        while(!deque.isEmpty()) {
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

    /**
     * 做递归思考三步：
     *
     * 递归的函数要干什么？
     * 函数的作用是判断传入的两个树是否镜像。
     * 输入：TreeNode left, TreeNode right
     * 输出：是：true，不是：false
     *
     * 递归停止的条件是什么？
     * 左节点和右节点都为空 -> 倒底了都长得一样 ->true
     * 左节点为空的时候右节点不为空，或反之 -> 长得不一样-> false
     * 左右节点值不相等 -> 长得不一样 -> false
     *
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
     *
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
            top ++;
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


    /**
     * 剑指 Offer 30. 包含min函数的栈
     * https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/
     *
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
     *
     * 示例:
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.min();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.min();   --> 返回 -2.
     *
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
     *
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。
     * 例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
     *
     * 示例 1：
     * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
     * 输出：true
     * 解释：我们可以按以下顺序执行：
     * push(1), push(2), push(3), push(4), pop() -> 4,
     * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
     *
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
     *
     * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
     *
     * 例如:
     * 给定二叉树:[3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *    
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
     *
     * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
     *
     * 例如:
     * 给定二叉树:[3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     *
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
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

    public static void main(String[] args) {
        int a[][]={{1,2,3},{4,5,6}};
        System.out.println(a[0].length);//3
        System.out.println(a.length);//2
        System.out.println(a[1][2]);//6
        System.out.println(hammingWeight(13));//3
        System.out.println(1/2);//0
        System.out.println(minArray(new int[]{2,2,2,0,1}));
        System.out.println(Integer.MAX_VALUE);//2147483647
        System.out.println(printNumbers(1));
        System.out.println(Arrays.toString(printNumbers1(4).toArray()));
    }



}
