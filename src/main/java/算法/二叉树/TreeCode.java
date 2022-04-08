package 算法.二叉树;

import java.util.*;

/**
 * @author lupeng
 */
public class TreeCode {


    public static void main(String[] args) {

    }


    /**
     * 144. 二叉树的前序遍历  中左右
     * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
     * 输入：root = [1,null,2,3]
     * 输出：[1,2,3]
     */
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        //递归
        /*preorder(root, res);
        return res;*/
        //迭代法
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }

    /**
     * 前序遍历 先中, 后左,再右.
     */
    public void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        //先添加中间节点
        res.add(root.val);
        //再遍历左子树
        preorder(root.left, res);
        //最后遍历右子树
        preorder(root.right, res);
    }

    /**
     * 后序遍历 先左, 后右, 再中
     */
    public void postorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
    }


    /**
     * 101. 对称二叉树
     * https://leetcode-cn.com/problems/symmetric-tree/
     * 输入：root = [1,2,2,3,4,4,3]
     * 输出：true
     *
     * 输入：root = [1,2,2,null,3,null,3]
     * 输出：false
     *
     * 解题思路: 复制一份二叉树, 反过来放入队列中
     *
     */
    public boolean isSymmetric(TreeNode root) {
        TreeNode q = root;
        TreeNode u = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(q);
        queue.add(u);
        while (!queue.isEmpty()) {
            q = queue.poll();
            u = queue.poll();
            if (q == null && u == null) {
                continue;
            }
            if ((q == null || u == null) || (q.val != u.val)) {
                return false;
            }
            queue.add(q.left);
            queue.add(u.right);
            queue.add(q.right);
            queue.add(u.left);
        }
        return true;
    }

    /**
     * 110. 平衡二叉树
     * https://leetcode-cn.com/problems/balanced-binary-tree/
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 本题中，一棵高度平衡二叉树定义为：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：true
     * 输入：root = [1,2,2,3,3,null,null,4,4]
     * 输出：false
     *
     * 求高度,用后序遍历(左右中)
     */
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = getHeight(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        if (leftHeight - rightHeight > 1 ||  rightHeight - leftHeight > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 257. 二叉树的所有路径
     * https://leetcode-cn.com/problems/binary-tree-paths/
     * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
     * 叶子节点 是指没有子节点的节点。
     *
     * 输入：root = [1,2,3,null,5]
     * 输出：["1->2->5","1->3"]
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        getPath(root, "", ret);
        return ret;
    }

    public void getPath(TreeNode root, String path, List<String> ret) {
        if (root != null) {
            StringBuffer buff = new StringBuffer(path);
            buff.append(root.val);
            if (root.left == null && root.right == null) {
                ret.add(buff.toString());
            } else {
                buff.append("->");
                getPath(root.left, buff.toString(), ret);
                getPath(root.right, buff.toString(), ret);
            }
        }
    }


    /**
     * 404. 左叶子之和
     * https://leetcode-cn.com/problems/sum-of-left-leaves/
     * 给定二叉树的根节点 root ，返回所有左叶子之和。
     * 输入: root = [3,9,20,null,null,15,7]
     * 输出: 24
     * 解释: 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
     *
     */
    public int sumOfLeftLeaves(TreeNode root) {
        return leaveValue(root);
    }

    public int leaveValue(TreeNode root) {
        int sum = 0;
        if (root == null) {
            return sum;
        }
        //判断是否为左叶子
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum = root.left.val;
        }
        return sum + leaveValue(root.left) + leaveValue(root.right);
    }

}
