import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    - 풀이 방식: bfs
    bfs 사용해서 A에서 B로 갈 수 있는지 체크
    + A와 B가 같을 수 있다는 점 유의
 */

class boj_여행가자 {

    static int N, M;
    static List<Integer>[] adjList;
    static int[] road;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N + 1];
        road = new int[M];

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    adjList[i].add(j);
                }
                if (i == j) {
                    adjList[i].add(j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            road[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M - 1; i++) {
            bfs(road[i], road[i + 1]);
        }

        System.out.println("YES");
    }

    private static void bfs(int start, int end) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 0; i < adjList[cur].size(); i++) {
                int next = adjList[cur].get(i);
                if (next == end) return;
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }

        System.out.println("NO");
        System.exit(0);
    }
}