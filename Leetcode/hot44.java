package Leetcode;
//二叉树展开为链表


public class hot44 {       //这道题需要直接到root进行操作，而不是返回值
    public void flatten(TreeNode root) {
        if(root==null){
            return;
        }
        TreeNode pre =new TreeNode();
        flattenHelper(root,pre);
        root.right=pre.right.right;     //直接将root链接到成型的二叉树上
        root.left=null;
//        while(pre.right!=null){
//           // System.out.println(pre.right.val);
//            pre=pre.right;
//        }


    }
    public void flattenHelper(TreeNode root,TreeNode pre){   //先序遍历，但是要创建新结点
        if(root==null){
            return;
        }
        TreeNode newNode = new TreeNode(root.val);
        pre.right=newNode;
       // System.out.println(newNode.val);
        pre =newNode;
        flattenHelper(root.left,pre);
        while(pre.right!=null){      //因为在上一个递归后，pre可能以及在右子树上挂有结点了
            pre=pre.right;
        }
        flattenHelper(root.right,pre);
    }

    public void flatten2(TreeNode root) {
        while (root != null) {
            //左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                //将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
                root = root.right;
            }
        }
    }

    private TreeNode pre = null;

    public void flatten3(TreeNode root) {     //后序遍历，这样就不会丢失右子结点了
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }


}
