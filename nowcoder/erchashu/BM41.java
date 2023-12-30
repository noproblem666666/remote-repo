package nowcoder.erchashu;

import java.util.*;
//Todo：建树的方法和上一题原理相同，遍历右视图可以用栈深度优先或队列层次遍历
//输出二叉树的右视图
public class BM41 {

    //建树函数
    //四个int参数分别是前序最左节点下标，前序最右节点下标
    //中序最左节点下标，中序最右节点坐标
    public TreeNode buildTree(int[] xianxu, int l1, int r1, int[] zhongxu, int l2, int r2){
        if(l1 > r1 || l2 > r2)
            return null;
        //构建节点
        TreeNode root = new TreeNode(xianxu[l1]);
        //用来保存根节点在中序遍历列表的下标
        int rootIndex = 0;
        //寻找根节点
        for(int i = l2; i <= r2; i++){
            if(zhongxu[i] == xianxu[l1]){
                rootIndex = i;
                break;
            }
        }
        //左子树大小
        int leftsize = rootIndex - l2;
        //右子树大小
        int rightsize = r2 - rootIndex;
        //递归构建左子树和右子树
        root.left = buildTree(xianxu, l1 + 1, l1 + leftsize, zhongxu, l2 , l2 + leftsize - 1);
        root.right = buildTree(xianxu, r1 - rightsize + 1, r1, zhongxu, rootIndex + 1, r2);
        return root;
    }

    //深度优先搜索函数
    public ArrayList<Integer> rightSideView(TreeNode root) {
        //右边最深处的值
        Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
        //记录最大深度
        int max_depth = -1;
        //维护深度访问节点
        Stack<TreeNode> nodes = new Stack<TreeNode>();
        //维护dfs时的深度
        Stack<Integer> depths = new Stack<Integer>();
        nodes.push(root);
        depths.push(0);
        while(!nodes.isEmpty()){
            TreeNode node = nodes.pop();
            int depth = depths.pop();
            if(node != null){
                //维护二叉树的最大深度
                max_depth = Math.max(max_depth, depth);
                //如果不存在对应深度的节点我们才插入
                if(mp.get(depth) == null)
                    mp.put(depth, node.val);
                nodes.push(node.left);       //因为栈的特性，所以肯定是先搜索右边的结点
                nodes.push(node.right);
                depths.push(depth + 1);
                depths.push(depth + 1);
            }
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        //结果加入链表
        for(int i = 0; i <= max_depth; i++)
            res.add(mp.get(i));
        return res;
    }

    public int[] solve (int[] xianxu, int[] zhongxu) {
        //空节点
        if(xianxu.length == 0)
            return new int[0];
        //建树
        TreeNode root = buildTree(xianxu, 0, xianxu.length - 1, zhongxu, 0, zhongxu.length - 1);
        //获取右视图输出
        ArrayList<Integer> temp = rightSideView(root);
        //转化为数组
        int[] res = new int[temp.size()];
        for(int i = 0; i < temp.size(); i++)
            res[i] = temp.get(i);
        return res;
    }


    public Map<Integer, Integer> index;
    //建树函数
    //四个int参数分别是前序最左节点下标，前序最右节点下标
    //中序最左节点下标，中序最右节点坐标
    public TreeNode buildTree2(int[] xianxu, int l1, int r1, int[] zhongxu, int l2, int r2){
        if(l1 > r1 || l2 > r2)
            return null;
        //前序遍历中的第一个节点就是根节点
        int xianxu_root = l1;
        //在中序遍历中定位根节点
        int zhongxu_root = index.get(xianxu[xianxu_root]);
        TreeNode root = new TreeNode(xianxu[xianxu_root]);
        //得到左子树中的节点数目
        int leftsize = zhongxu_root - l2;
        root.left = buildTree2(xianxu, l1 + 1, l1 + leftsize, zhongxu, l2, zhongxu_root - 1);
        root.right = buildTree2(xianxu, l1 + leftsize + 1, r1, zhongxu, zhongxu_root + 1, r2);
        return root;
    }
    //层次遍历
    public ArrayList<Integer> rightSideView2(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while(!q.isEmpty()){
            //队列中的大小即是这一层的节点树
            int size = q.size();
            while(size-- != 0){
                TreeNode temp = q.poll();
                if(temp.left != null)
                    q.offer(temp.left);
                if(temp.right != null)
                    q.offer(temp.right);
                //最右元素
                if(size == 0)
                    res.add(temp.val);
            }
        }
        return res;
    }

    public int[] solve2 (int[] xianxu, int[] zhongxu) {
        index = new HashMap<Integer, Integer>();
        //空节点
        if(xianxu.length == 0)
            return new int[0];
        //用哈希表标记中序节点在前序中的位置
        for(int i = 0; i < xianxu.length; i++)
            index.put(zhongxu[i], i);
        //建树
        TreeNode root = buildTree(xianxu, 0, xianxu.length - 1, zhongxu, 0, zhongxu.length - 1);
        //获取右视图输出
        ArrayList<Integer> temp = rightSideView(root);
        //转化为数组
        int[] res = new int[temp.size()];
        for(int i = 0; i < temp.size(); i++)
            res[i] = temp.get(i);
        return res;
    }
}
