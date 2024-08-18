import java.util.*;

class Solution {
    public class Node implements Comparable<Node> {
        int x, y, value;
        Node left, right;
        
        public Node(int x, int y, int value, Node left, Node right) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.left = left;
            this.right = right;
        }
        
        @Override
        public int compareTo(Node o) {
            if(this.y == o.y) return this.x - o.x;
            else return o.y - this.y;
        }
    }
    
    int[][] result;
    int idx;
    
    public int[][] solution(int[][] nodeinfo) {
        Node[] node = new Node[nodeinfo.length];
        for(int i = 0; i < nodeinfo.length; i++) {
            node[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1, null, null);
        }
        
        Arrays.sort(node);
        
        Node root = node[0];
        for(int i = 1; i < node.length; i++) {
            insertNode(root, node[i]); 
        }
        
        result = new int[2][nodeinfo.length];
        //전위 순회
        idx = 0;
        preorder(root); 
        
        //후위 순회
        idx = 0;
        postorder(root); 
        
        return result;
    }
    
    
    
    public void insertNode(Node parent, Node child) {
        if(parent.x > child.x) {
            if(parent.left == null) parent.left = child;
            else insertNode(parent.left, child);
        } else {
            if(parent.right == null) parent.right = child;
            else insertNode(parent.right, child);
        }
    }
    
    public void preorder(Node root) {
        if(root != null) {
            result[0][idx++] = root.value;
            preorder(root.left);
            preorder(root.right);
        }
    }
    
    public void postorder(Node root) {
        if(root != null) {
            postorder(root.left);
            postorder(root.right);
            result[1][idx++] = root.value;
        }
    }
}