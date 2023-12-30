package Leetcode;

import java.util.*;
//LRU缓存
public class hot52 {
    class LRUCache {       //因为我们既要实现随机存取，又要实现时间复杂度为o(1)的增删，还要把新的链表放在表尾，需要使用哈希链表
        int cap;           //通过维护一个运行于所有条目的双向链表，LinkedHashMap保证了元素迭代的顺序。该迭代顺序可以是插入顺序或者是访问顺序。
        LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();   //java内置的哈希链表
        public LRUCache(int capacity) {
            this.cap = capacity;     //容器大小
        }

        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            // 将 key 变为最近使用
            makeRecently(key);
            return cache.get(key);
        }

        public void put(int key, int val) {
            if (cache.containsKey(key)) {
                // 修改 key 的值
                cache.put(key, val);
                // 将 key 变为最近使用
                makeRecently(key);
                return;
            }

            if (cache.size() >= this.cap) {
                // 链表头部就是最久未使用的 key
                int oldestKey = cache.keySet().iterator().next();
                cache.remove(oldestKey);
            }
            // 将新的 key 添加链表尾部
            cache.put(key, val);
        }

        private void makeRecently(int key) {
            int val = cache.get(key);
            // 删除 key，重新插入到队尾
            cache.remove(key);
            cache.put(key, val);
        }
    }

    public class LRUCache2 {         //用自己设计的双向链表和哈希表
        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;
            public DLinkedNode() {}
            public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
        }

        private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
        private int size;
        private int capacity;
        private DLinkedNode head, tail;

        public LRUCache2(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            // 使用伪头部和伪尾部节点
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                return -1;
            }
            // 如果 key 存在，先通过哈希表定位，再移到头部
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                // 如果 key 不存在，创建一个新的节点
                DLinkedNode newNode = new DLinkedNode(key, value);
                // 添加进哈希表
                cache.put(key, newNode);
                // 添加至双向链表的头部
                addToHead(newNode);
                ++size;
                if (size > capacity) {
                    // 如果超出容量，删除双向链表的尾部节点
                    DLinkedNode tail = removeTail();
                    // 删除哈希表中对应的项
                    cache.remove(tail.key);      //别忘了在哈希表中的操作
                    --size;
                }
            }
            else {
                // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
                node.value = value;
                moveToHead(node);
            }
        }

        private void addToHead(DLinkedNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        private DLinkedNode removeTail() {
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }
    }



}
