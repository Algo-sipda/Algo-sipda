import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dist = new int[N + 1];
        int[] prev = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            dist[i] = -1;
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.add(N);
        dist[N] = 0;
        prev[N] = 0;
        while (!q.isEmpty()) {
            int v = q.poll();
            if (v == 1) {
                break;
            }
            if (v % 3 == 0) {
                int u = v / 3;
                if (dist[u] == -1) {
                    dist[u] = dist[v] + 1;
                    prev[u] = v;
                    q.add(u);
                }
            }
            if (v % 2 == 0) {
                int u = v / 2;
                if (dist[u] == -1) {
                    dist[u] = dist[v] + 1;
                    prev[u] = v;
                    q.add(u);
                }
            }
            if (v - 1 >= 1) {
                int u = v - 1;
                if (dist[u] == -1) {
                    dist[u] = dist[v] + 1;
                    prev[u] = v;
                    q.add(u);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        Deque<Integer> path = new ArrayDeque<>();
        int cur = 1;
        while (cur != 0) {
            path.addLast(cur);
            if (cur == N) {
                break;
            }
            cur = prev[cur];
        }
        System.out.println(dist[1]);
        while (!path.isEmpty()) {
            sb.append(path.removeLast()).append(' ');
        }
        System.out.println(sb.toString().trim());
    }
}
