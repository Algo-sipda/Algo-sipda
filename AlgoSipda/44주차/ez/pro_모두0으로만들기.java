import java.util.*;

class pro_모두0으로만들기 {

    List<Integer> adjList[];
    boolean[] visited;
    long[] arr;
    int[] degree;

    public long solution(int[] a, int[][] edges) {
        long answer = 0;
        long sum = 0;
        adjList = new ArrayList[a.length];
        visited = new boolean[a.length];
        degree = new int[a.length];
        arr = new long[a.length];
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            arr[i] = a[i];
            adjList[i] = new ArrayList<>();
        }
        if (sum != 0) {
            return -1;
        }

        for (int[] edge : edges) {
            adjList[edge[0]].add(edge[1]);
            adjList[edge[1]].add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 1) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visited[cur] = true;

            for (int next : adjList[cur]) {
                if (visited[next]) continue;
                degree[next]--;
                arr[next] += arr[cur];
                answer += Math.abs(arr[cur]);
                arr[cur] = 0;
                if (degree[next] == 1) {
                    queue.add(next);
                }
            }
        }
        return answer;
    }
}
/*
가중치가 부여된 트리
모든 점들의 가중치 -> 0으로

한쪽은 1 증가, 다른 한쪽은 1감소

가능한지 판별 / 가능하다면 최소한의 행동
불가능하면 -1


어떻게 판별할 것인가?
*/
