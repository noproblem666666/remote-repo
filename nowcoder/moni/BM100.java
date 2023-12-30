package nowcoder.moni;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//设计LRU缓存结构
public class BM100 {

    //哈希表加双向链表(也可直接使用LinkedHashMap)
    private int capacity;
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;
    private int used;

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value, Node prev, Node next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    //构造函数
    public BM100(int capacity) {
        // write code here
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.used = 0;
    }

    public int get(int key) {
        // write code here
        if (!map.containsKey(key)) {
            return -1;
        }

        makeRecently(key);

        return map.get(key).value;
    }

    public void set(int key, int value) {
        // 如果 key 已存在，直接修改值，再移到链表头部
        if (map.containsKey(key)) {
            map.get(key).value = value;
            makeRecently(key);
            return;
        }

        // 如果达到容量上限，就要移除尾部节点，注意 HashMap 要 remove！！！
        if (used == capacity) {
            map.remove(tail.key);
            tail = tail.prev;
            tail.next = null;
            used--;
        }
        // 头节点为空，单独处理
        if (head == null) {
            head = new Node(key, value, null, null);
            tail = head;
        }
        else {
            Node t = new Node(key, value, null, head);
            head.prev = t;
            head = t;
        }
        map.put(key, head);

        used++;
    }

    // 把 key 对应的节点移到链表头部
    private void makeRecently(int key) {
        Node t = map.get(key);
        if (t != head) {
            if (t == tail) {
                tail = tail.prev;
                tail.next = null;
            }
            else {
                t.prev.next = t.next;
                t.next.prev = t.prev;
            }

            t.prev = null;
            t.next = head;
            head.prev = t;
            head = t;
        }
    }



}
