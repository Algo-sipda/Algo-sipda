import java.util.*;

class Solution {
    static class Node {
        int curr, parent;
        Node(int curr, int parent) {
            this.curr = curr;
            this.parent = parent;
        }
    }

    public long solution(int[] a, int[][] edges) {
        int n = a.length;
        long[] weights = new long[n];
        for (int i = 0; i < n; i++) weights[i] = a[i];

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        long sum = 0;
        for (long w : weights) sum += w;
        if (sum != 0) return -1;

        boolean[] visited = new boolean[n];
        Stack<Integer> postOrder = new Stack<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (visited[curr]) continue;
            visited[curr] = true;
            postOrder.push(curr);
            for (int next : graph.get(curr)) {
                if (!visited[next]) {
                    stack.push(next);
                    parent[next] = curr;
                }
            }
        }

        long answer = 0;
        while (!postOrder.isEmpty()) {
            int curr = postOrder.pop();
            if (parent[curr] != -1) {
                weights[parent[curr]] += weights[curr];
                answer += Math.abs(weights[curr]);
            }
        }

        return answer;
    }
}
