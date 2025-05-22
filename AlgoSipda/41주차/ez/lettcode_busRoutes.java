import java.util.*;

class lettcode_busRoutes {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        boolean[] visited = new boolean[501];
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();
        int answer = 0;

        if (source == target) {
            return 0;
        }

        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                ArrayList<Integer> buses = map.getOrDefault(routes[i][j], new ArrayList<>());
                buses.add(i);
                map.put(routes[i][j], buses);
            }
        }

        queue.add(source);
        while (!queue.isEmpty()) {
            int size = queue.size();
            answer++;
            while (size > 0) {
                int cur = queue.poll();
                if (!map.containsKey(cur)) {
                    return -1;
                }
                List<Integer> buses = map.get(cur);

                for (int bus : buses) {
                    if (visited[bus]) continue;
                    visited[bus] = true;
                    for (int j = 0; j < routes[bus].length; j++) {
                        if (routes[bus][j] == target) {
                            return answer;
                        }
                        queue.add(routes[bus][j]);
                    }
                }
                size--;
            }
        }
        return -1;
    }
}
