package 算法.二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * BFS:广度优先搜索
 */
public class BFS {

    /**
     * 102. 二叉树的层序遍历
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[3],[9,20],[15,7]]
     * <p>
     * BFS:广度优先搜索,通过队列先进先出思想进行实现.
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                //移除并获取元素
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ret.add(list);
        }
        return ret;
    }

    /**
     * 107. 二叉树的层序遍历 II
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
     * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[15,7],[9,20],[3]]
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levelOrder = new LinkedList<>();
        if (root == null) {
            return levelOrder;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                TreeNode left = node.left, right = node.right;
                if (left != null) {
                    queue.offer(left);
                }
                if (right != null) {
                    queue.offer(right);
                }
            }
            //后面的元素总数添加到最前面
            levelOrder.add(0, level);
        }
        return levelOrder;
    }


    /**
     * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     * https://leetcode-cn.com/problems/binary-tree-right-side-view/
     * 输入: [1,2,3,null,5,null,4]
     * 输出: [1,3,4]
     */
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (i == n - 1) {
                    ret.add(node.val);
                }
            }
        }
        return ret;
    }

    /**
     * 637. 二叉树的层平均值
     * https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/
     * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[3.00000,14.50000,11.00000]
     * 解释：第 0 层的平均值为 3,第 1 层的平均值为 14.5,第 2 层的平均值为 11 。
     * 因此返回 [3, 14.5, 11] 。
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            Double sum = new Double(0);
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ret.add(sum / n);
        }
        return ret;
    }


    /**
     * 104. 二叉树的最大深度
     * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     */
    public int maxDepth(TreeNode root) {
        int ret = 0;
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            ret++;
            int n = queue.size();//一层有多少个节点
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return ret;
    }

    /**
     * 226. 翻转二叉树
     * https://leetcode-cn.com/problems/invert-binary-tree/
     * 输入：root = [4,2,7,1,3,6,9]
     * 输出：[4,7,2,9,6,3,1]
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                TreeNode tmp = node.left;
                node.left = node.right;
                node.right = tmp;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        /* 递归
        if (root == null) {
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;*/
        return root;
    }

    /**
     * 111. 二叉树的最小深度
     * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * 说明：叶子节点是指没有子节点的节点。
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：2
     *
     */
    public int minDepth(TreeNode root) {
        //递归  先把一边的结果求出来,  再求另外一边的   最后进行比较.
        if (root == null) {
            return 0;
        }
        int minLeft = minDepth(root.left);
        int minRight = minDepth(root.right);
        if (root.left == null && root.right != null) {
            return minRight + 1;
        }
        if (root.right == null&& root.left != null) {
            return minLeft + 1;
        }
        return Math.min(minLeft, minRight) + 1;
        /*int min = 0;
        if (root == null) {
            return min;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            min++;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.right == null && node.left == null) {
                    return min;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

            }
        }
        return min;*/
    }

    /**
     * 222. 完全二叉树的节点个数
     * https://leetcode-cn.com/problems/count-complete-tree-nodes/
     * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
     *
     * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~2h个节点。
     *
     * 输入：root = [1,2,3,4,5,6]
     * 输出：6
     */
    public int countNodes(TreeNode root) {
        int ret = 0;
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                ret++;
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
            }
        }
        return ret;
        /*递归
        if (root == null) {
            return 0;
        }
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return left + right + 1;*/
    }


    /**
     * 112. 路径总和
     * https://leetcode-cn.com/problems/path-sum/
     * 给你二叉树的根节点root 和一个表示目标和的整数targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和targetSum 。如果存在，返回 true ；否则，返回 false 。
     * 叶子节点 是指没有子节点的节点。
     *
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
     * 输出：true
     * 解释：等于目标和的根节点到叶节点路径如上图所示。
     *
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        //采用两个队列,一个存节点,一个存总和.
        Queue<TreeNode> nodeQue = new LinkedList<TreeNode>();
        Queue<Integer> sumQue = new LinkedList<Integer>();
        nodeQue.add(root);
        sumQue.add(root.val);
        while (!nodeQue.isEmpty()) {
            int size = nodeQue.size();
            while (size-- > 0) {
                TreeNode node = nodeQue.poll();
                int sum = sumQue.poll();
                if (node.right == null && node.left == null && sum == targetSum) {
                    return true;
                }
                if (node.left != null) {
                    nodeQue.add(node.left);
                    sumQue.add(sum + node.left.val);
                }
                if (node.right != null) {
                    nodeQue.add(node.right);
                    sumQue.add(sum + node.right.val);
                }
            }
        }
        return false;
    }

}
