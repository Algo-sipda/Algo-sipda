import java.util.*;

class Solution {
    public class Node {
        int num;
        int x;
        int y;
        Node left;
        Node right;

        public Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    public int[][] solution(int[][] nodeinfo) {

        int[][] nodes = new int[nodeinfo.length][3];
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes[i][0] = i + 1;  // 노드 번호
            nodes[i][1] = nodeinfo[i][0];  // x 좌표
            nodes[i][2] = nodeinfo[i][1];  // y 좌표
        }
        Arrays.sort(nodes, (a, b) -> b[2] == a[2] ? a[1] - b[1] : b[2] - a[2]);
        System.out.println(Arrays.deepToString(nodes));

        Node root = new Node(nodes[0][0], nodes[0][1], nodes[0][2]);

        for (int i = 1; i < nodes.length; i++) {
            insertNode(root, new Node(nodes[i][0], nodes[i][1], nodes[i][2]));
        }

        List<Integer> preorderList = new ArrayList<>();
        List<Integer> postorderList = new ArrayList<>();

        preorderTraversal(root, preorderList);
        postorderTraversal(root, postorderList);

        int[][] answer = new int[2][nodeinfo.length];
        answer[0] = preorderList.stream().mapToInt(i -> i).toArray();
        answer[1] = postorderList.stream().mapToInt(i -> i).toArray();

        return answer;
    }

    public void insertNode(Node parent, Node child) {
        if (child.x < parent.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                insertNode(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                insertNode(parent.right, child);
            }
        }
    }

    public void preorderTraversal(Node node, List<Integer> list) {
        if (node != null) {
            list.add(node.num);
            preorderTraversal(node.left, list);
            preorderTraversal(node.right, list);
        }
    }

    public void postorderTraversal(Node node, List<Integer> list) {
        if (node != null) {
            postorderTraversal(node.left, list);
            postorderTraversal(node.right, list);
            list.add(node.num);
        }
    }
}