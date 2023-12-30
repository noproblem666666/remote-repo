package Leetcode;

import java.util.HashMap;
import java.util.Stack;

//从前序与中序遍历序列构造二叉树
public class hot43 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();       //便于快速找到对应的数组位置
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length, map);
    }

    private TreeNode buildTreeHelper(int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end,
                                     HashMap<Integer, Integer> map) {
        if (p_start == p_end) {     //空结点返回
            return null;
        }
        int root_val = preorder[p_start];        //p_start指向的即为当前要构造的根结点
        TreeNode root = new TreeNode(root_val);
        int i_root_index = map.get(root_val);         //找到该结点在中序遍历中对应的位置
        int leftNum = i_root_index - i_start;         //计算中序遍历中左边子树结点的数量
        //递归的构造左右子树                                 第一个结点以及用掉了，先序遍历中左子树一定是先遍历完成，p_end指向为左子树的最后一个结点的后一个结点
        //                                               中序遍历也一样，把左子树的结点传过去，i_root_index不用-1
        root.left = buildTreeHelper(preorder, p_start + 1, p_start + leftNum + 1, inorder, i_start, i_root_index, map);
        //                                       用leftNum确定了左子树的范围后，右子树就是剩下的范围
        root.right = buildTreeHelper(preorder, p_start + leftNum + 1, p_end, inorder, i_root_index + 1, i_end, map);
        return root;
    }

    public TreeNode buildTree2(int[] preorder, int[] inorder) {       //用迭代和栈来构建
        if (preorder.length == 0) {
            return null;
        }
        Stack<TreeNode> roots = new Stack<TreeNode>();
        int pre = 0;
        int in = 0;
        //先序遍历第一个值作为根节点
        TreeNode curRoot = new TreeNode(preorder[pre]);
        TreeNode root = curRoot;
        roots.push(curRoot);
        pre++;
        //遍历前序遍历的数组
        while (pre < preorder.length) {
            //出现了当前节点的值和中序遍历数组的值相等，寻找是谁的右子树
            if (curRoot.val == inorder[in]) {
                //每次进行出栈，实现倒着遍历
                while (!roots.isEmpty() && roots.peek().val == inorder[in]) {
                    curRoot = roots.peek();
                    roots.pop();
                    in++;
                }
                //设为当前的右孩子
                curRoot.right = new TreeNode(preorder[pre]);
                //更新 curRoot
                curRoot = curRoot.right;
                roots.push(curRoot);
                pre++;
            } else {
                //否则的话就一直作为左子树
                curRoot.left = new TreeNode(preorder[pre]);
                curRoot = curRoot.left;
                roots.push(curRoot);
                pre++;
            }
        }
        return root;
    }



}
