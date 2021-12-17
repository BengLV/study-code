package 算法.队列;

import java.util.*;

/**
 * @author lupeng
 */
public class MyQueue {
    /**
     * 232. 用栈实现队列
     * * https://leetcode-cn.com/problems/implement-queue-using-stacks/
     * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
     * <p>
     * 实现 MyQueue 类：
     * void push(int x) 将元素 x 推到队列的末尾
     * int pop() 从队列的开头移除并返回元素
     * int peek() 返回队列开头的元素
     * boolean empty() 如果队列为空，返回 true ；否则，返回 false
     * <p>
     * 示例：
     * <p>
     * 输入：
     * ["MyQueue", "push", "push", "peek", "pop", "empty"]
     * [[], [1], [2], [], [], []]
     * 输出：
     * [null, null, null, 1, 1, false]
     * <p>
     * 解释：
     * MyQueue myQueue = new MyQueue();
     * myQueue.push(1); // queue is: [1]
     * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
     * myQueue.peek(); // return 1
     * myQueue.pop(); // return 1, queue is [2]
     * myQueue.empty(); // return false
     */
    //输入栈
    private Stack<Integer> inStack;
    //输出栈
    private Stack<Integer> outStack;

    /**
     * Initialize your data structure here.
     * stack : FILO 先进后出
     * queue : FIFO 先进先出
     */
    public MyQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    /**
     * 将元素 x 推到队列的末尾
     */
    public void push(int x) {
        inStack.push(x);
    }

    /**
     * 从队列的开头移除并返回元素
     */
    public int pop() {
        changeStack();
        return outStack.pop();
    }

    /**
     * 返回队列开头的元素.
     */
    public int peek() {
        changeStack();
        return outStack.peek();
    }

    /**
     * 判断队列是否为空 空:true  非空:false.
     */
    public boolean empty() {
        return inStack.empty() && outStack.empty();
    }

    private void changeStack() {
        if (outStack.empty()) {
            while (!inStack.empty()) {
                outStack.push(inStack.pop());
            }
        }
    }

    /**
     * 239. 滑动窗口最大值
     * https://leetcode-cn.com/problems/sliding-window-maximum/
     * <p>
     * 给你一个整数数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
     * 返回滑动窗口中的最大值。
     * <p>
     * 示例 1：
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * 解释：
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     * 1 [3  -1  -3] 5  3  6  7       3
     * 1  3 [-1  -3  5] 3  6  7       5
     * 1  3  -1 [-3  5  3] 6  7       5
     * 1  3  -1  -3 [5  3  6] 7       6
     * 1  3  -1  -3  5 [3  6  7]      7
     * <p>
     * 示例 2：
     * 输入：nums = [1], k = 1
     * 输出：[1]
     * <p>
     * 示例 3：
     * 输入：nums = [1,-1], k = 1
     * 输出：[1,-1]
     * <p>
     * 示例 4：
     * 输入：nums = [9,11], k = 2
     * 输出：[11]
     * <p>
     * 示例 5：
     * 输入：nums = [4,-2], k = 2
     * 输出：[4]
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] num1, int[] num2) {
                return num1[0] != num2[0] ? num2[0] - num1[0] : num2[1] - num1[1];
            }
        });
        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = queue.peek()[0];
        for (int i = k; i < n; i++) {
            queue.offer(new int[]{nums[i], i});
            while (queue.peek()[1] <= i - k) {
                queue.poll();
            }
            ans[i - k + 1] = queue.peek()[0];
        }
        return ans;
    }

    /**
     * 347. 前 K 个高频元素
     * https://leetcode-cn.com/problems/top-k-frequent-elements/
     *
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     *
     * 示例 1:
     *
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     * 示例 2:
     *
     * 输入: nums = [1], k = 1
     * 输出: [1]
     * 提示：
     *
     * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
     * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
     * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
     *
     * 思路:
     * 1.要统计元素出现频率(Map)
     * 2.对频率排序(PriorityQueue)
     * 3.找出前K个高频元素
     */
    public static int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        // 根据map的value值正序排，相当于一个小顶堆
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> entry : entries) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        for (int i = k - 1; i >= 0; i--) {
            result[i] = queue.poll().getKey();
        }
        return result;
    }




    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int[] nums2 = new int[]{1,1,2,3,2, 2};
        System.out.println(Arrays.toString(maxSlidingWindow(nums, 3)));
        topKFrequent(nums2, 2);
    }

}
