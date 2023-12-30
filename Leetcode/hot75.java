package Leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//二叉树的序列化与反序列化（用字符串来记录二叉树与还原二叉树）
public class hot75 {
    public class Codec {      //自己写的BFS

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root==null){
                return null;
            }

            Queue<TreeNode> queue = new LinkedList<>();     //用队列层次遍历
            Queue<TreeNode> queue1 = new LinkedList<>();    //暂时存储，用来标记当前层数结点是否遍历完成
            queue.add(root);
            StringBuilder str = new StringBuilder();
            while(!queue.isEmpty()){
                TreeNode node = queue.poll();
                if(node == null){
                    str.append("?");    //标记这是个空结点
                }else{
                    str.append(node.val);
                    queue1.add(node.left);
                    queue1.add(node.right);
                }
                if(queue.isEmpty()){
                    str.append("!");     //标记这一层结束
                    while(!queue1.isEmpty()){
                       TreeNode node1 = queue1.poll();
                       queue.add(node1);
                    }
                }
            }
            return str.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {       //这里其实也要用queue复原，把上面的思路反转下即可
            if(data == null){
                return null;
            }
            char[] chars = data.toCharArray();
            TreeNode root = new TreeNode(Integer.parseInt(String.valueOf(chars[0])));
            for (int i = 1; i < chars.length; i++) {
                if(chars[i]=='!'){

                }
            }
            return root;
        }
    }

    public class Codec2 {       //BFS(使用队列记录)

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node == null) {
                    sb.append("X,");
                } else {
                    sb.append(node.val + ",");
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == "") {
                return null;
            }
            Queue<String> nodes = new ArrayDeque<>(Arrays.asList(data.split(",")));
            TreeNode root = new TreeNode(Integer.parseInt(nodes.poll()));
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                String left = nodes.poll();
                String right = nodes.poll();
                if (!left.equals("X")) {
                    node.left = new TreeNode(Integer.parseInt(left));
                    queue.add(node.left);
                }
                if (!right.equals("X")) {
                    node.right = new TreeNode(Integer.parseInt(right));
                    queue.add(node.right);
                }
            }
            return root;
        }
    }

    public class Codec3 {        //DFS  （递归）

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "X,";
            }
            String left = serialize(root.left);
            String right = serialize(root.right);
            return root.val + "," + left + right;   //由于递归，left和right后面也带着","
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] nodes = data.split(",");
            Queue<String> queue = new ArrayDeque<>(Arrays.asList(nodes));
            return buildTree(queue);
        }

        public TreeNode buildTree(Queue<String> queue) {
            String value = queue.poll();
            if (value.equals("X")) {
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(value));
            node.left = buildTree(queue);
            node.right = buildTree(queue);
            return node;
        }
    }
}
