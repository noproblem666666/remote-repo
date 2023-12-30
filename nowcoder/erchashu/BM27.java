package nowcoder.erchashu;

import java.util.*;

//按之字型顺序打印二叉树
public class BM27 {
    //用栈存储先放左结点再放右结点，到下一层先放右结点再放左结点
    public ArrayList<ArrayList<Integer>> Print (TreeNode pRoot) {     //使用一个栈和一个队列
        // write code here
        if(pRoot==null){
            return new ArrayList<>();
        }
        Stack<TreeNode> stack = new Stack<>();       //方便之字型遍历
        Queue<TreeNode> queue = new LinkedList<>(); //暂存下一层的结点
        ArrayList<ArrayList<Integer>> res = new ArrayList();
        ArrayList<Integer> temp = new ArrayList<>();
        stack.push(pRoot);
        int i = 0;   //区分当前层该用什么顺序进栈
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            temp.add(node.val);
            if(i%2==0){
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }else{
                if(node.right!=null){
                    queue.add(node.right);
                }
                if(node.left!=null){
                    queue.add(node.left);
                }
            }
            if(stack.isEmpty()){
                i++;                        //进入下一层
//                for (TreeNode treeNode : queue) {      //todo：错误的遍历方法，不能在循环体中改变队列结构，这样无法将所有结点一次性全部输入
//                    stack.push(queue.poll());
//                }
                while(!queue.isEmpty()){
                    stack.push(queue.poll());
                }
                res.add(new ArrayList<>(temp));    //浅拷贝
                temp.clear();
            }
        }
        return res;
    }


    public ArrayList<ArrayList<Integer> > Print2(TreeNode pRoot) {     //只使用队列，加入时根据层数决定是否反转
        TreeNode head = pRoot;
        ArrayList<ArrayList<Integer> > res = new ArrayList<ArrayList<Integer>>();
        if(head == null)
            //如果是空，则直接返回空list
            return res;
        //队列存储，进行层次遍历
        Queue<TreeNode> temp = new LinkedList<TreeNode>();
        temp.offer(head);
        TreeNode p;
        boolean flag = true;
        while(!temp.isEmpty()){
            //记录二叉树的某一行
            ArrayList<Integer> row = new ArrayList<Integer>();
            int n = temp.size();
            //奇数行反转，偶数行不反转
            flag = !flag;
            //因先进入的是根节点，故每层节点多少，队列大小就是多少
            for(int i = 0; i < n; i++){
                p = temp.poll();
                row.add(p.val);
                //若是左右孩子存在，则存入左右孩子作为下一个层次
                if(p.left != null)
                    temp.offer(p.left);
                if(p.right != null)
                    temp.offer(p.right);
            }
            //奇数行反转，偶数行不反转
            if(flag)
                Collections.reverse(row);
            res.add(row);
        }
        return res;
    }


    public ArrayList<ArrayList<Integer> > Print3(TreeNode pRoot) {     //使用两个栈
        TreeNode head = pRoot;
        ArrayList<ArrayList<Integer> > res = new ArrayList<ArrayList<Integer>>();
        if(head == null)
            //如果是空，则直接返回空list
            return res;
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        //放入第一次
        s1.push(head);
        while(!s1.isEmpty() || !s2.isEmpty()){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            //遍历奇数层
            while(!s1.isEmpty()){
                TreeNode node = s1.pop();
                //记录奇数层
                temp.add(node.val);
                //奇数层的子节点加入偶数层
                if(node.left != null)
                    s2.push(node.left);
                if(node.right != null)
                    s2.push(node.right);
            }
            //数组不为空才添加
            if(temp.size() != 0)
                res.add(new ArrayList<Integer>(temp));
            //清空本层数据
            temp.clear();
            //遍历偶数层
            while(!s2.isEmpty()){
                TreeNode node = s2.pop();
                //记录偶数层
                temp.add(node.val);
                //偶数层的子节点加入奇数层
                if(node.right != null)
                    s1.push(node.right);
                if(node.left != null)
                    s1.push(node.left);
            }
            //数组不为空才添加
            if(temp.size() != 0)
                res.add(new ArrayList<Integer>(temp));
            //清空本层数据
            temp.clear();
        }
        return res;
    }
}
