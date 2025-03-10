import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int minDiff = Integer.MAX_VALUE;

        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];

            graph.get(a).remove(Integer.valueOf(b));
            graph.get(b).remove(Integer.valueOf(a));

            int count = bfs(n, graph, a);
            int diff = Math.abs((n - count) - count);
            minDiff = Math.min(minDiff, diff);

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        return minDiff;
    }

    private int bfs(int n, List<List<Integer>> graph, int start) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        int count = 1;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    count++;
                }
            }
        }
        return count;
    }
}
