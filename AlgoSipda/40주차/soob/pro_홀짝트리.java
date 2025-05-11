import java.util.*;

class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int x : nodes) graph.put(x, new ArrayList<>());
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        Set<Integer> visited = new HashSet<>();
        int evenType = 0;
        int oddType = 0;

        for (int x : nodes) {
            if (visited.contains(x)) continue;

            List<Integer> group = new ArrayList<>();
            Deque<Integer> q = new ArrayDeque<>();
            q.add(x);
            visited.add(x);

            while (!q.isEmpty()) {
                int cur = q.poll();
                group.add(cur);
                for (int next : graph.get(cur)) {
                    if (!visited.contains(next)) {
                        visited.add(next);
                        q.add(next);
                    }
                }
            }

            int same = 0;
            int diff = 0;
            for (int node : group) {
                int deg = graph.get(node).size();
                if ((node % 2) == (deg % 2)) same++;
                else diff++;
            }

            if (same == 1) evenType++;
            if (diff == 1) oddType++;
        }

        return new int[]{evenType, oddType};
    }
}
