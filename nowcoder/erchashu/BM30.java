package nowcoder.erchashu;

import java.util.Stack;

//二叉搜索树与双向链表（不允许新建结点）
//Todo:中序遍历并且设立pre结点记录当前结点的前一个结点
public class BM30 {
    //返回的第一个指针，即为最小值，先定为null
    public TreeNode head = null;
    //中序遍历当前值的上一位，初值为最小值，先定为null
    public TreeNode pre = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree == null)
            //中序递归，叶子为空则返回
            return null;
        //首先递归到最左最小值
        Convert(pRootOfTree.left);
        //找到最小值，初始化head与pre
        if(pre == null){
            head = pRootOfTree;       //head只需要记录最左结点作为头结点
            pre = pRootOfTree;
        }
        //当前节点与上一节点建立连接，将pre设置为当前值
        else{
            pre.right = pRootOfTree;
            pRootOfTree.left = pre;
            pre = pRootOfTree;
        }
        Convert(pRootOfTree.right);
        return head;         //最后返回头结点
    }


    public TreeNode Convert2(TreeNode pRootOfTree) {        //用栈进行中序遍历
        if (pRootOfTree == null)
            return null;
        //设置栈用于遍历
        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode head = null;
        TreeNode pre = null;
        //确认第一个遍历到最左，即为首位
        boolean isFirst = true;
        while(pRootOfTree != null || !s.isEmpty()){
            //直到没有左节点
            while(pRootOfTree != null){
                s.push(pRootOfTree);
                pRootOfTree = pRootOfTree.left;
            }
            pRootOfTree = s.pop();
            //最左元素即表头
            if(isFirst){
                head = pRootOfTree;
                pre = head;
                isFirst = false;
                //当前节点与上一节点建立连接，将pre设置为当前值
            }else{
                pre.right = pRootOfTree;
                pRootOfTree.left = pre;
                pre = pRootOfTree;
            }
            pRootOfTree = pRootOfTree.right;
        }
        return head;
    }
}
