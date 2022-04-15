package 算法.二叉树;

import java.util.*;

/**
 * @author lupeng
 */
public class TreeCode {


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

    /**
     * 513. 找树左下角的值
     * https://leetcode-cn.com/problems/find-bottom-left-tree-value/
     * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
     * 假设二叉树中至少有一个节点。
     *
     * 输入: [1,2,3,4,null,5,6,null,null,7]
     * 输出: 7
     *
     */
    public int findBottomLeftValue(TreeNode root) {
        int ret = root.val;
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
                if (i == 0) {
                    ret = node.val;
                }
            }
        }
        return ret;
        /**
         *    递归:
         *    int left, maxDepth = -1;
         *     public int findBottomLeftValue(TreeNode root) {
         *         dfs(root, 0);
         *         return left;
         *     }
         *
         *     public void dfs(TreeNode node, int depth) {
         *         if (node == null) {
         *             return;
         *         }
         *         if (depth > maxDepth) {
         *             left = node.val;
         *             maxDepth++;
         *         }
         *         dfs(node.left, depth + 1);
         *         dfs(node.right, depth + 1);
         *     }
         */
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
     * 类似: 404. 左叶子之和
     *
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return getPathSum(root, 0, targetSum);
    }

    public boolean getPathSum(TreeNode node, int sum, int targetSum) {
        if (node != null) {
            sum = sum + node.val;
            if (node.right == null && node.left == null && sum == targetSum) {
                return true;
            } else {
                return (getPathSum(node.left, sum, targetSum) || getPathSum(node.right, sum, targetSum));
            }
        }
        return false;
    }

    /**
     * 113. 路径总和 II
     * https://leetcode-cn.com/problems/path-sum-ii/
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     * 叶子节点 是指没有子节点的节点。
     *
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
     * 输出：[[5,4,11,2],[5,8,4,5]]
     *
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        getPath(root, targetSum, 0, ret, list);
        return ret;
    }

    public void getPath (TreeNode node, int targetSum, int sum, List<List<Integer>> ret, List<Integer> list) {
        if (node != null) {
            List<Integer> tempList = new ArrayList<>();
            tempList.addAll(list);
            tempList.add(node.val);
            sum = sum + node.val;
            if (node.left == null && node.right== null && sum == targetSum) {
                ret.add(tempList);
            } else {
                getPath(node.left, targetSum, sum, ret, tempList);
                getPath(node.right, targetSum, sum, ret, tempList);
            }
        }

    }

    /**
     * 654. 最大二叉树
     * 给定一个不重复的整数数组nums 。最大二叉树可以用下面的算法从nums 递归地构建:
     *
     * 创建一个根节点，其值为nums 中的最大值。
     * 递归地在最大值左边的子数组前缀上构建左子树。
     * 递归地在最大值 右边 的子数组后缀上构建右子树。
     * 返回nums 构建的 最大二叉树 。
     *
     * 输入：nums = [3,2,1,6,0,5]
     * 输出：[6,3,5,null,2,0,null,null,1]
     * 解释：递归调用如下所示：
     * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
     *     - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
     *         - 空数组，无子节点。
     *         - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
     *             - 空数组，无子节点。
     *             - 只有一个元素，所以子节点是一个值为 1 的节点。
     *     - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
     *         - 只有一个元素，所以子节点是一个值为 0 的节点。
     *         - 空数组，无子节点。
     *
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length);
    }

    private TreeNode construct(int[] nums, int left, int right) {
        if (left == right) {
            return null;
        }
        int maxIndex = left;
        for (int i = left; i < right; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = construct(nums, left, maxIndex);
        root.right = construct(nums, maxIndex + 1, right);
        return root;
    }

    private HashMap<Integer, Integer> map = new HashMap<>();
    private int[] post;

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
     * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗二叉树
     *
     * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
     * 输出：[3,9,20,null,null,15,7]
     *
     */
    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        for(int i = 0;i < inorder.length; i++) {
            map.put(inorder[i], i);//妙啊！将节点值及索引全部记录在哈希表中
        }

        post = postorder;
        TreeNode root = buildTree1(0, inorder.length - 1, 0, post.length - 1);
        return root;
    }

    public TreeNode buildTree1(int inorderS, int inorderE, int postorderS, int postorderE) {
        if(inorderE < inorderS || postorderE < postorderS) return null;

        int root = post[postorderE];//根据后序遍历结果，取得根节点
        int rootIndex = map.get(root);//获取对应的索引

        TreeNode node = new TreeNode(root);//创建该节点
        node.left = buildTree1(inorderS, rootIndex - 1, postorderS, postorderS + rootIndex - inorderS - 1);//中序遍历后的左子树起止位置, 后续遍历后的左子树起止位置
        node.right = buildTree1(rootIndex + 1, inorderE, postorderS + rootIndex - inorderS, postorderE - 1);//中序遍历后的右子树起止位置, 后续遍历后的右子树起止位置
        return node;//注意！返回的是新建的node！
    }


    private HashMap<Integer, Integer> map2 = new HashMap<>();
    private int[] pre;


    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     * 给定两个整数数组preorder 和 inorder，其中preorder 是二叉树的先序遍历， inorder是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * 输出: [3,9,20,null,null,15,7]
     *
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map2.put(inorder[i], i);
        }
        pre = preorder;
        TreeNode root = buildTree2(0, inorder.length - 1, 0, preorder.length -1);
        return root;
    }

    private TreeNode buildTree2(int inorderS, int inorderE, int preorderS, int preorderE) {
        if (inorderE < inorderS || preorderE < preorderS) return null;
        int root = pre[preorderS];
        int rootIndex = map2.get(root);
        TreeNode node = new TreeNode(root);
        node.left = buildTree2(inorderS, rootIndex - 1, preorderS + 1, rootIndex - inorderS + preorderS);
        node.right = buildTree2(rootIndex + 1, inorderE, rootIndex - inorderS + preorderS + 1, preorderE);
        return node;
    }

    /**
     * 617. 合并二叉树
     * https://leetcode-cn.com/problems/merge-two-binary-trees/
     * 给你两棵二叉树： root1 和 root2 。
     * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
     * 返回合并后的二叉树。
     * 注意: 合并过程必须从两个树的根节点开始。
     *
     * 输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
     * 输出：[3,4,5,5,4,null,7]
     */
    public TreeNode mergeTrees(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null) return tree2;
        if (tree2 == null) return tree1;
        TreeNode root = new TreeNode(tree1.val + tree2.val);
        root.left = mergeTrees(tree1.left, tree2.left);
        root.right = mergeTrees(tree1.right, tree2.right);
        return root;
    }



    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4,5,0};
        System.out.println(a[0]);
        System.out.println(a[1]);
    }

}
