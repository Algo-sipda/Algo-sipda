import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_숨바꼭질4 {
    static int N, K, cnt;
    static int[] visited = new int[100001];
    static int[] parent = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs();
        List<Integer> res = new ArrayList<Integer>();
        int idx = K;
        while (idx != N) {
            res.add(idx);
            idx = parent[idx];
        }

        System.out.println(visited[K] - 1);
        System.out.print(N + " ");
        for (int i = res.size() - 1; i >= 0; i--) {
            System.out.print(res.get(i) + " ");
        }
    }

    private static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(N);
        visited[N] = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == K) {
                cnt = cur;
                return;
            }

            if (cur * 2 < 100001 && visited[cur * 2] == 0) {
                queue.add(cur*2);
                parent[cur * 2] = cur;
                visited[cur * 2] = visited[cur] + 1;
            }

            if (cur + 1 < 100001 && visited[cur + 1] == 0) {
                queue.add(cur + 1);
                parent[cur + 1] = cur;
                visited[cur + 1] = visited[cur] + 1;
            }

            if (cur - 1 >= 0 && visited[cur - 1] == 0) {
                queue.add(cur - 1);
                parent[cur - 1] = cur;
                visited[cur - 1] = visited[cur] + 1;
            }
        }

    }
}