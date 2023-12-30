package nowcoder.erchashu;

import java.util.ArrayDeque;
import java.util.Deque;

//序列化二叉树
public class BM39 {

    //层次遍历
    int INF = 0x3f3f3f3f;
    TreeNode emptyNode = new TreeNode(INF);
    public String Serialize(TreeNode root) {
        if (root == null) return "";

        StringBuilder sb = new StringBuilder();
        // 使用队列进行层序遍历，起始先将 root 放入队列
        Deque<TreeNode> d = new ArrayDeque<>();
        d.addLast(root);
        while (!d.isEmpty()) {
            // 每次从队列中取出元素进行「拼接」，包括「正常节点」和「叶子节点对应的首位空节点」
            TreeNode poll = d.pollFirst();
            sb.append(poll.val + "_");
            // 如果取出的节点不为「占位节点」，则继续往下拓展，同时防止「占位节点」不继续往下拓展
            if (!poll.equals(emptyNode)) {
                d.addLast(poll.left != null ? poll.left : emptyNode);
                d.addLast(poll.right != null ? poll.right : emptyNode);
            }
        }
        return sb.toString();
    }

    public TreeNode Deserialize(String data) {
        if (data.equals("")) return null;

        // 根据分隔符进行分割
        String[] ss = data.split("_");
        int n = ss.length;
        // 怎么序列化就怎么反序列化
        // 使用队列进行层序遍历，起始先将 root 构建出来，并放入队列
        TreeNode root = new TreeNode(Integer.parseInt(ss[0]));
        Deque<TreeNode> d = new ArrayDeque<>();
        d.addLast(root);
        for (int i = 1; i < n - 1; i += 2) {
            TreeNode poll = d.pollFirst();
            // 每次从中取出左右节点对应 val
            int a = Integer.parseInt(ss[i]), b = Integer.parseInt(ss[i + 1]);
            // 如果左节点对应的值不是 INF，则构建「真实节点」
            if (a != INF) {
                poll.left = new TreeNode(a);
                d.addLast(poll.left);
            }
            // 如果右节点对应的值不是 INF，则构建「真实节点」
            if (b != INF) {
                poll.right = new TreeNode(b);
                d.addLast(poll.right);
            }
        }
        return root;
    }


    //先序遍历
    //作为序列的下标
    public int index = 0;
    //处理序列化的功能函数（递归）
    private void SerializeFunction(TreeNode root, StringBuilder str){
        //如果节点为空，表示左子节点或右子节点为空，用#表示
        if(root == null){
            str.append('#');
            return;
        }
        //根节点
        str.append(root.val).append('!');
        //左子树
        SerializeFunction(root.left, str);
        //右子树
        SerializeFunction(root.right, str);
    }

    public String Serialize2(TreeNode root) {
        //处理空树
        if(root == null)
            return "#";
        StringBuilder res = new StringBuilder();
        SerializeFunction(root, res);
        //把str转换成char
        return res.toString();
    }
    //处理反序列化的功能函数（递归）
    private TreeNode DeserializeFunction(String str){
        //到达叶节点时，构建完毕，返回继续构建父节点
        //空节点
        if(str.charAt(index) == '#'){
            index++;
            return null;
        }
        //数字转换
        int val = 0;
        //遇到分隔符或者结尾
        while(str.charAt(index) != '!' && index != str.length()){
            val = val * 10 + ((str.charAt(index)) - '0');
            index++;
        }
        TreeNode root = new TreeNode(val);
        //序列到底了，构建完成
        if(index == str.length())
            return root;
        else
            index++;
        //反序列化与序列化一致，都是前序
        root.left = DeserializeFunction(str);
        root.right = DeserializeFunction(str);
        return root;
    }

    public TreeNode Deserialize2(String str) {
        //空序列对应空树
        if(str == "#")
            return null;
        TreeNode res = DeserializeFunction(str);
        return res;
    }

}
