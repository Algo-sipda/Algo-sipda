import java.util.*;

/*
    풀이 방식: bfs
*/

class Solution {

    static List<Integer>[] adjList;

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        adjList = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < roads.length; i++) {
            adjList[roads[i][0]].add(roads[i][1]);
            adjList[roads[i][1]].add(roads[i][0]);
        }

        for (int i = 0; i < sources.length; i++) {
            int res = bfs(n, roads, sources[i], destination);
            answer[i] = res;
        }
        return answer;
    }

    private static int bfs(int n, int[][] roads, int start, int destination) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        queue.add(new int[] { start, 0 });
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == destination) {
                return cur[1];
            }

            for (Integer next : adjList[cur[0]]) {
                if (next == destination) {
                    return cur[1] + 1;
                }
                if (!visited[next]) {
                    queue.add(new int[] { next, cur[1] + 1 });
                    visited[next] = true;
                }
            }

        }

        return -1;
    }
}